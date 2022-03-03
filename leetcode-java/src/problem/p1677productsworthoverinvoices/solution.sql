/*
1677. Product's Worth Over Invoices

https://leetcode-cn.com/problems/products-worth-over-invoices/

+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| product_id  | int     |
| name        | varchar |
+-------------+---------+
product_id is the primary key for this table.
This table contains the ID and the name of the product. The name consists of only lowercase English letters.
No two products have the same name.


+-------------+------+
| Column Name | Type |
+-------------+------+
| invoice_id  | int  |
| product_id  | int  |
| rest        | int  |
| paid        | int  |
| canceled    | int  |
| refunded    | int  |
+-------------+------+
invoice_id is the primary key for this table and the id of this invoice.
product_id is the id of the product for this invoice.
rest is the amount left to pay for this invoice.
paid is the amount paid for this invoice.
canceled is the amount canceled for this invoice.
refunded is the amount refunded for this invoice.


Write an SQL query that will, for all products, return each product name with the total amount due,
paid, canceled, and refunded across all invoices.

Return the result table ordered by product_name.


drop table if exists Product;
Create table If Not Exists Product(product_id int, name varchar(15));
drop table if exists Invoice;
Create table If Not Exists Invoice(invoice_id int,product_id int,rest int, paid int, canceled int, refunded int);
Truncate table Product;
insert into Product (product_id, name) values ('0', 'ham');
insert into Product (product_id, name) values ('1', 'bacon');
Truncate table Invoice;
insert into Invoice (invoice_id, product_id, rest, paid, canceled, refunded) values ('23', '0', '2', '0', '5', '0');
insert into Invoice (invoice_id, product_id, rest, paid, canceled, refunded) values ('12', '0', '0', '4', '0', '3');
insert into Invoice (invoice_id, product_id, rest, paid, canceled, refunded) values ('1', '1', '1', '1', '0', '1');
insert into Invoice (invoice_id, product_id, rest, paid, canceled, refunded) values ('2', '1', '1', '0', '1', '1');
insert into Invoice (invoice_id, product_id, rest, paid, canceled, refunded) values ('3', '1', '0', '1', '1', '1');
insert into Invoice (invoice_id, product_id, rest, paid, canceled, refunded) values ('4', '1', '1', '1', '1', '0');
*/
select
    p.name,
    sum(ifnull(i.rest, 0)) as rest,
    sum(ifnull(i.paid, 0)) as paid,
    sum(ifnull(i.canceled, 0)) as canceled,
    sum(ifnull(i.refunded, 0)) as refunded
from Invoice as i
right join Product as p
    on i.product_id = p.product_id
group by p.name
order by p.name
