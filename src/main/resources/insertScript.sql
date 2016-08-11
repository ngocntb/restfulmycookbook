//Parent Category
insert into category(name, parent_id) values ('Nguyên liệu', null);
insert into category(name, parent_id) values ('Phương pháp nấu', null);
insert into category(name, parent_id) values ('Bữa ăn', null);
insert into category(name, parent_id) values ('Làm bánh', null);

//Ingredients Categories
insert into category (name, parent_id)
select 'Thịt heo', id from category where name = 'Nguyên liệu';

insert into category(name, parent_id)
select 'Thịt bò', id from category where name ='Nguyên liệu';

insert into category(name, parent_id)
select 'Trứng', id from category where name ='Nguyên liệu';

insert into category(name, parent_id)
select 'Thịt gia cầm', id from category where name ='Nguyên liệu';

insert into category(name, parent_id)
select 'Hải sản', id from category where name ='Nguyên liệu';

insert into category(name, parent_id)
select 'Đậu phụ', id from category where name ='Nguyên liệu';

insert into category(name, parent_id)
select 'Bún - Mỳ - Miến - Phở', id from category where name ='Nguyên liệu';

//Cooking methods Categories
insert into category (name, parent_id)
select 'Chiên', id from category where name = 'Phương pháp nấu';

insert into category(name, parent_id)
select 'Xào - Rang - Rim', id from category where name ='Phương pháp nấu';

insert into category(name, parent_id)
select 'Luộc - Hấp - Hầm', id from category where name ='Phương pháp nấu';

insert into category(name, parent_id)
select 'Kho - Om', id from category where name ='Phương pháp nấu';

insert into category(name, parent_id)
select 'Nướng', id from category where name ='Phương pháp nấu';

insert into category(name, parent_id)
select 'Canh - Súp', id from category where name ='Phương pháp nấu';

//Meals or Courses Categories
insert into category (name, parent_id)
select 'Khai vị', id from category where name = 'Bữa ăn';

insert into category(name, parent_id)
select 'Ăn sáng', id from category where name ='Bữa ăn';

insert into category(name, parent_id)
select 'Tráng miện', id from category where name ='Bữa ăn';

insert into category(name, parent_id)
select 'Món chính', id from category where name ='Bữa ăn';

//Banking Categories
insert into category (name, parent_id)
select 'Mousse – Custard – Pudding – Tiramisu – Choux', id from category where name = 'Làm bánh';

insert into category(name, parent_id)
select 'Muffin - Crepe - Pancake', id from category where name ='Làm bánh';

insert into category(name, parent_id)
select 'Bread', id from category where name ='Làm bánh';

insert into category(name, parent_id)
select 'Cake', id from category where name ='Làm bánh';


