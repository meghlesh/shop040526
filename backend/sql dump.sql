CREATE DATABASE cws_shop;
Use cws_shop;
select * from users;
DELETE FROM users WHERE id = 1;
DELETE FROM tokens WHERE user_id = 1;

DELETE FROM notification WHERE user_id = 1;
DELETE FROM users WHERE id = 1;

select * from cws_shop.notification;