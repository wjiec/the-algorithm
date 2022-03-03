/*
1821. Find Customers With Positive Revenue this Year

https://leetcode-cn.com/problems/find-customers-with-positive-revenue-this-year/

+--------------+------+
| Column Name  | Type |
+--------------+------+
| customer_id  | int  |
| year         | int  |
| revenue      | int  |
+--------------+------+
(customer_id, year) is the primary key for this table.
This table contains the customer ID and the revenue of customers in different years.
Note that this revenue can be negative.

Write an SQL query to report the customers with postive revenue in the year 2021.

Return the result table in any order.


drop table if exists Customers;
Create table If Not Exists Customers (customer_id int, year int, revenue int);
Truncate table Customers;
insert into Customers (customer_id, year, revenue) values ('1', '2018', '50');
insert into Customers (customer_id, year, revenue) values ('1', '2021', '30');
insert into Customers (customer_id, year, revenue) values ('1', '2020', '70');
insert into Customers (customer_id, year, revenue) values ('2', '2021', '-50');
insert into Customers (customer_id, year, revenue) values ('3', '2018', '10');
insert into Customers (customer_id, year, revenue) values ('3', '2016', '50');
insert into Customers (customer_id, year, revenue) values ('4', '2021', '20');
*/
select customer_id from Customers where year = 2021 and revenue > 0
