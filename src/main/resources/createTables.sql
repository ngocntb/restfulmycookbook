CREATE TABLE recipes(
  id serial  PRIMARY KEY,
  name CHARACTER VARYING,
  prep CHARACTER VARYING,
  cooking CHARACTER VARYING,
  serving CHARACTER VARYING
);

CREATE TABLE ingredient(
  id serial PRIMARY KEY,
  description CHARACTER VARYING,
  recipes_id INTEGER REFERENCES recipes
);

CREATE TABLE direction(
  id serial PRIMARY KEY,
  description CHARACTER VARYING,
  recipes_id INTEGER REFERENCES recipes
);

CREATE TABLE picture(
  id serial PRIMARY KEY,
  url CHARACTER VARYING,
  recipes_id INTEGER REFERENCES recipes
);

CREATE TABLE category(
  id serial PRIMARY KEY,
  name CHARACTER VARYING,
  parent_id INTEGER DEFAULT NULL REFERENCES category(id)
);

CREATE TABLE category_recipes(
  id serial PRIMARY KEY,
  category_id INTEGER REFERENCES category,
  recipes_id INTEGER REFERENCES recipes,
);

