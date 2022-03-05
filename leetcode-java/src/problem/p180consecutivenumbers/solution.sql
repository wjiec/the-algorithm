/*
180. Consecutive Numbers

https://leetcode-cn.com/problems/consecutive-numbers/

+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| id          | int     |
| num         | varchar |
+-------------+---------+
id is the primary key for this table.

Write an SQL query to find all numbers that appear at least three times consecutively.

Return the result table in any order.


drop table if exists Logs;
Create table If Not Exists Logs (id int, num int);
Truncate table Logs;
insert into Logs (id, num) values ('1', '1');
insert into Logs (id, num) values ('2', '1');
insert into Logs (id, num) values ('3', '1');
insert into Logs (id, num) values ('4', '2');
insert into Logs (id, num) values ('5', '1');
insert into Logs (id, num) values ('6', '2');
insert into Logs (id, num) values ('7', '2');
*/
select distinct l1.num as ConsecutiveNums from Logs as l1
join Logs as l2 on l2.id = l1.id + 1 and l2.num = l1.num
join Logs as l3 on l3.id = l1.id + 2 and l3.num = l1.num
