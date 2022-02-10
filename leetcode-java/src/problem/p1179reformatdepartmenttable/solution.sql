/*
1179. Reformat Department Table

https://leetcode-cn.com/problems/reformat-department-table/

Write an SQL query to reformat the table such that there is a department id column and a revenue column for each month.


Create table If Not Exists Department (id int, revenue int, month varchar(5));
Truncate table Department;
insert into Department (id, revenue, month) values ('1', '8000', 'Jan');
insert into Department (id, revenue, month) values ('2', '9000', 'Jan');
insert into Department (id, revenue, month) values ('3', '10000', 'Feb');
insert into Department (id, revenue, month) values ('1', '7000', 'Feb');
insert into Department (id, revenue, month) values ('1', '6000', 'Mar');
*/
select
    id,
    sum(if(`month`='Jan', revenue, null)) as Jan_Revenue,
    sum(if(`month`='Feb', revenue, null)) as Feb_Revenue,
    sum(if(`month`='Mar', revenue, null)) as Mar_Revenue,
    sum(if(`month`='Apr', revenue, null)) as Apr_Revenue,
    sum(if(`month`='May', revenue, null)) as May_Revenue,
    sum(if(`month`='Jun', revenue, null)) as Jun_Revenue,
    sum(if(`month`='Jul', revenue, null)) as Jul_Revenue,
    sum(if(`month`='Aug', revenue, null)) as Aug_Revenue,
    sum(if(`month`='Sep', revenue, null)) as Sep_Revenue,
    sum(if(`month`='Oct', revenue, null)) as Oct_Revenue,
    sum(if(`month`='Nov', revenue, null)) as Nov_Revenue,
    sum(if(`month`='Dec', revenue, null)) as Dec_Revenue
from Department group by id
