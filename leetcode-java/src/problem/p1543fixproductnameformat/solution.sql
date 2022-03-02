/*
1543. Fix Product Name Format

https://leetcode-cn.com/problems/fix-product-name-format/

+--------------+---------+
| Column Name  | Type    |
+--------------+---------+
| sale_id      | int     |
| product_name | varchar |
| sale_date    | date    |
+--------------+---------+
sale_id is the primary key for this table.
Each row of this table contains the product name and the date it was sold.


Since table Sales was filled manually in the year 2000, product_name may contain
leading and/or trailing white spaces, also they are case-insensitive.

Write an SQL query to report

product_name in lowercase without leading or trailing white spaces.
sale_date in the format ('YYYY-MM').
total the number of times the product was sold in this month.
Return the result table ordered by product_name in ascending order.
In case of a tie, order it by sale_date in ascending order.


drop table if exists Sales;
Create table If Not Exists Sales (sale_id int, product_name varchar(30), sale_date date);
Truncate table Sales;
insert into Sales (sale_id, product_name, sale_date) values ('1', 'LCPHONE', '2000-01-16');
insert into Sales (sale_id, product_name, sale_date) values ('2', 'LCPhone', '2000-01-17');
insert into Sales (sale_id, product_name, sale_date) values ('3', 'LcPhOnE', '2000-02-18');
insert into Sales (sale_id, product_name, sale_date) values ('4', 'LCKeyCHAiN', '2000-02-19');
insert into Sales (sale_id, product_name, sale_date) values ('5', 'LCKeyChain', '2000-02-28');
insert into Sales (sale_id, product_name, sale_date) values ('6', 'Matryoshka', '2000-03-31');
*/
select product_name, sale_date, count(1) as total from (
   select
       trim(lower(product_name)) as product_name,
       date_format(sale_date, '%Y-%m') as sale_date
   from Sales
) as t
group by product_name, sale_date
order by product_name, sale_date


