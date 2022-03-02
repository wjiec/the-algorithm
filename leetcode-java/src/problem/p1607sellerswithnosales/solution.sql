/*
1607. Sellers With No Sales

https://leetcode-cn.com/problems/sellers-with-no-sales/

+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| customer_id   | int     |
| customer_name | varchar |
+---------------+---------+
customer_id is the primary key for this table.
Each row of this table contains the information of each customer in the WebStore.

+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| order_id      | int     |
| sale_date     | date    |
| order_cost    | int     |
| customer_id   | int     |
| seller_id     | int     |
+---------------+---------+
order_id is the primary key for this table.
Each row of this table contains all orders made in the webstore.
sale_date is the date when the transaction was made between the customer (customer_id) and the seller (seller_id).

+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| seller_id     | int     |
| seller_name   | varchar |
+---------------+---------+
seller_id is the primary key for this table.
Each row of this table contains the information of each seller.

Write an SQL query to report the names of all sellers who did not make any sales in 2020.

Return the result table ordered by seller_name in ascending order.


drop table if exists Customer;
Create table If Not Exists Customer (customer_id int, customer_name varchar(20));
drop table if exists Orders;
Create table If Not Exists Orders (order_id int, sale_date date, order_cost int, customer_id int, seller_id int);
drop table if exists Seller;
Create table If Not Exists Seller (seller_id int, seller_name varchar(20));
Truncate table Customer;
insert into Customer (customer_id, customer_name) values ('101', 'Alice');
insert into Customer (customer_id, customer_name) values ('102', 'Bob');
insert into Customer (customer_id, customer_name) values ('103', 'Charlie');
Truncate table Orders;
insert into Orders (order_id, sale_date, order_cost, customer_id, seller_id) values ('1', '2020-03-01', '1500', '101', '1');
insert into Orders (order_id, sale_date, order_cost, customer_id, seller_id) values ('2', '2020-05-25', '2400', '102', '2');
insert into Orders (order_id, sale_date, order_cost, customer_id, seller_id) values ('3', '2019-05-25', '800', '101', '3');
insert into Orders (order_id, sale_date, order_cost, customer_id, seller_id) values ('4', '2020-09-13', '1000', '103', '2');
insert into Orders (order_id, sale_date, order_cost, customer_id, seller_id) values ('5', '2019-02-11', '700', '101', '2');
Truncate table Seller;
insert into Seller (seller_id, seller_name) values ('1', 'Daniel');
insert into Seller (seller_id, seller_name) values ('2', 'Elizabeth');
insert into Seller (seller_id, seller_name) values ('3', 'Frank');
*/
select seller_name from Seller where seller_id not in (
    select s.seller_id from Seller as s join Orders o on s.seller_id = o.seller_id
    where o.sale_date between '2020-01-01' and '2020-12-31'
)
order by seller_name
