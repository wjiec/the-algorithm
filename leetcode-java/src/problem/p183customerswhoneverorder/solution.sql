/*
183. Customers Who Never Order

https://leetcode-cn.com/problems/customers-who-never-order/

Suppose that a website contains two tables, the Customers table and the Orders table.
Write a SQL query to find all customers who never order anything.


Create table If Not Exists Customers (Id int, Name varchar(255));
Create table If Not Exists Orders (Id int, CustomerId int);
Truncate table Customers;
insert into Customers (Id, Name) values ('1', 'Joe');
insert into Customers (Id, Name) values ('2', 'Henry');
insert into Customers (Id, Name) values ('3', 'Sam');
insert into Customers (Id, Name) values ('4', 'Max');
Truncate table Orders;
insert into Orders (Id, CustomerId) values ('1', '3');
insert into Orders (Id, CustomerId) values ('2', '1');
*/
select `Name` as `Customers` from Customers as c left join Orders as o on c.Id = o.CustomerId where o.Id is null;
