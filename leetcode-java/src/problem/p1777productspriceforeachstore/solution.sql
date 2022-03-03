/*
1777. Product's Price for Each Store

https://leetcode-cn.com/problems/products-price-for-each-store/

+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| product_id  | int     |
| store       | enum    |
| price       | int     |
+-------------+---------+
(product_id, store) is the primary key for this table.
store is an ENUM of type ('store1', 'store2', 'store3') where each represents the store this product is available at.
price is the price of the product at this store.

Write an SQL query to find the price of each product in each store.

Return the result table in any order.


drop table if exists Products;
Create table if Not Exists Products (product_id int, store ENUM('store1', 'store2', 'store3'), price int);
Truncate table Products;
insert into Products (product_id, store, price) values ('0', 'store1', '95');
insert into Products (product_id, store, price) values ('0', 'store3', '105');
insert into Products (product_id, store, price) values ('0', 'store2', '100');
insert into Products (product_id, store, price) values ('1', 'store1', '70');
insert into Products (product_id, store, price) values ('1', 'store3', '80');
*/
select product_id,
    sum(if(store='store1', price, null)) as store1,
    sum(if(store='store2', price, null)) as store2,
    sum(if(store='store3', price, null)) as store3
from Products
group by product_id
