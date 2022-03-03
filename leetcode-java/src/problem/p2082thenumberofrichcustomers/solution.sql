/*
2082. The Number of Rich Customers

https://leetcode-cn.com/problems/the-number-of-rich-customers/

+-------------+------+
| Column Name | Type |
+-------------+------+
| bill_id     | int  |
| customer_id | int  |
| amount      | int  |
+-------------+------+
bill_id is the primary key for this table.
Each row contains information about the amount of one bill and the customer associated with it.

Write an SQL query to report the number of customers who had at least one
bill with an amount strictly greater than 500.


Create table If Not Exists Store (bill_id int, customer_id int, amount int);
Truncate table Store;
insert into Store (bill_id, customer_id, amount) values ('6', '1', '549');
insert into Store (bill_id, customer_id, amount) values ('8', '1', '834');
insert into Store (bill_id, customer_id, amount) values ('4', '2', '394');
insert into Store (bill_id, customer_id, amount) values ('11', '3', '657');
insert into Store (bill_id, customer_id, amount) values ('13', '3', '257');
*/
select count(distinct customer_id) as rich_count from Store where amount > 500
