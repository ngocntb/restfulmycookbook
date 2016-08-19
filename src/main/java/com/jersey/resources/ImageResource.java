package com.jersey.resources;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.io.*;
import java.net.URL;
import java.security.InvalidParameterException;
//import javax.ws.rs.core.MediaType;

@Path("/images")
//@Produces("image/jpeg")
@Produces(MediaType.IMAGE_JPEG_VALUE)
@Consumes(MediaType.APPLICATION_JSON_VALUE)
@Component
@Transactional
public class ImageResource {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GET
    @Produces(MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @Path("/v1/{fileId}")
    //@ResponseStatus(HttpStatus.OK)
    public void downloadJpegFile(@PathParam(value = "fileId") String fileId, @Context HttpHeaders header, @Context HttpServletResponse response) throws Exception {
        logger.info("Start downloading image file: {}", fileId);
        if (fileId == null || fileId.trim().isEmpty() || fileId.contains("..") || fileId.contains(";")) {
            logger.error("Invalid file ID: " + fileId);
            throw new InvalidParameterException("Invalid file ID: " + fileId);
        }
        try {
            // Get the file full path from fileId
            //String filePath = "Path/To/File.JPEG";
            String filePath = "images/" + fileId; // this file is under project /resources/images folder
            logger.debug("File path: {}", filePath);
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            URL uri = classloader.getResource(filePath);
            if (uri == null) {
                logger.error("Not found file: {}", fileId);
                throw new FileNotFoundException("Not found file ID: " + fileId);
            }
            File file = new File(uri.getFile());
            //File file = new File(filePath);
            InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
            //InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(filePath); // absolute path
            //InputStream inputStream = this.getClass().getResourceAsStream(fileName); // relative path

            // Set the content type and attachment header.
            //response.addHeader("Content-disposition", "attachment;filename=ioc.xml");

            // "Content-Disposition : inline" will show viewable types (like images/text/pdf/anything viewable by browser) right on the browser
            //                                while others(zip e.g) will be directly downloaded (or may be provided save as popup, based on your browser setting).
            //response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() +"\""));

            // "Content-Disposition : attachment" will be directly downloaded, may bed provided save as popup, based on your browser setting
            response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));

            response.setContentType(MediaType.IMAGE_JPEG_VALUE);
            response.setContentLengthLong(file.length());
            // Copy the stream to the response output stream.
            IOUtils.copy(inputStream, response.getOutputStream());
            //FileCopyUtils.copy(file.getFile(), response.getOutputStream());
            response.flushBuffer();
            logger.info("End downloading image file: {}", fileId);
        } catch (Exception ex) {
            logger.error("Failed to download image file={}, error={}", fileId, ex.getMessage());
            ex.printStackTrace();
            response.sendError(500, ex.getMessage());
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @Path("/v2/{fileId}")
    public ResponseEntity<InputStreamResource> getJpegFile(@PathParam(value = "fileId") String fileId, @Context HttpHeaders header, @Context HttpServletResponse response) throws Exception {
        logger.debug("Start getting image file: {}", fileId);
        String filePath = "/Full/Path/To/File.JPG";
        //ClassPathResource jpegFile = new ClassPathResource(filePath);
        FileSystemResource jpegFile = new FileSystemResource(filePath);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", jpegFile.getFilename()));
        logger.debug("End getting image file: {}", fileId);
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(jpegFile.contentLength())
                .contentType(MediaType.parseMediaType(MediaType.IMAGE_JPEG_VALUE))
                .body(new InputStreamResource(jpegFile.getInputStream()));
    }
}
