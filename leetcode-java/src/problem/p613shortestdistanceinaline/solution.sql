/*
613. Shortest Distance in a Line

https://leetcode-cn.com/problems/shortest-distance-in-a-line/

+-------------+------+
| Column Name | Type |
+-------------+------+
| x           | int  |
+-------------+------+
x is the primary key column for this table.
Each row of this table indicates the position of a point on the X-axis.

Write an SQL query to report the shortest distance between any two points from the Point table.

Create Table If Not Exists Point (x int not null);
Truncate table Point;
insert into Point (x) values ('-1');
insert into Point (x) values ('0');
insert into Point (x) values ('2');
*/
select min(abs(p.x - q.x)) as shortest from Point as p join Point as q on p.x != q.x
