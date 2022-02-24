/*
586. Customer Placing the Largest Number of Orders

https://leetcode-cn.com/problems/customer-placing-the-largest-number-of-orders/

+-----------------+----------+
| Column Name     | Type     |
+-----------------+----------+
| order_number    | int      |
| customer_number | int      |
+-----------------+----------+
order_number is the primary key for this table.
This table contains information about the order ID and the customer ID.

Write an SQL query to find the customer_number for the customer who has placed the largest number of orders.

The test cases are generated so that exactly one customer will have placed more orders than any other customer.

Create table If Not Exists orders (order_number int, customer_number int);
Truncate table orders;
insert into orders (order_number, customer_number) values ('1', '1');
insert into orders (order_number, customer_number) values ('2', '2');
insert into orders (order_number, customer_number) values ('3', '3');
insert into orders (order_number, customer_number) values ('4', '3');
*/

select a.customer_number from (
    select customer_number, count(1) as order_count from orders group by customer_number
) as a join (
    select customer_number, count(1) as order_count from orders group by customer_number order by order_count desc limit 1
) as b on a.order_count = b.order_count
