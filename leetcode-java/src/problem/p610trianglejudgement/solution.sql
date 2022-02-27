/*
610. Triangle Judgement

https://leetcode-cn.com/problems/triangle-judgement/

+-------------+------+
| Column Name | Type |
+-------------+------+
| x           | int  |
| y           | int  |
| z           | int  |
+-------------+------+
(x, y, z) is the primary key column for this table.
Each row of this table contains the lengths of three line segments.

Write an SQL query to report for every three line segments whether they can form a triangle.

Return the result table in any order.

Create table If Not Exists Triangle (x int, y int, z int);
Truncate table Triangle;
insert into Triangle (x, y, z) values ('13', '15', '30');
insert into Triangle (x, y, z) values ('10', '20', '15');
*/
select x, y, z, if(x + y > z and x + z > y and y + z > x, 'Yes', 'No') as triangle from Triangle
