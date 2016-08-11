create table category
(
    id serial primary key,
    name varchar(200),
    picture_url varchar(200),
    parent_id integer references CATEGORY(id)
);

create table recipe
(
    id serial primary key,
    name varchar(200),
    cooking varchar(100),
    picture_url varchar(200),
    video_url varchar(200),
    prep varchar(100),
    serving varchar(100),
    source_url varchar(200),
    source_name varchar(100),
    upload_date date
);

create table category_recipe
(
    category_id integer references CATEGORY(id),
    recipe_id integer references RECIPE(id),
    PRIMARY KEY (category_id, recipe_id)
);

create table ingredient
(
  id serial primary key,
  description text,
  recipe_id integer references RECIPE(id)
);

create table direction
  (
    id serial primary key,
    description text,
    picture_url varchar(100),
    step integer,
    recipe_id integer references RECIPE(id)
  );