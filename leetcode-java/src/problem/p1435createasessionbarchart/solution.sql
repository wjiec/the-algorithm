/*
1435. Create a Session Bar Chart

https://leetcode-cn.com/problems/create-a-session-bar-chart/

+---------------------+---------+
| Column Name         | Type    |
+---------------------+---------+
| session_id          | int     |
| duration            | int     |
+---------------------+---------+
session_id is the primary key for this table.
duration is the time in seconds that a user has visited the application.

You want to know how long a user visits your application. You decided to create bins of "[0-5>", "[5-10>", "[10-15>",
and "15 minutes or more" and count the number of sessions on it.

Write an SQL query to report the (bin, total).

Return the result table in any order.


Create table If Not Exists Sessions (session_id int, duration int);
Truncate table Sessions;
insert into Sessions (session_id, duration) values ('1', '30');
insert into Sessions (session_id, duration) values ('2', '199');
insert into Sessions (session_id, duration) values ('3', '299');
insert into Sessions (session_id, duration) values ('4', '580');
insert into Sessions (session_id, duration) values ('5', '1000');
*/
select '[0-5>' as bin, count(1) as total from Sessions where duration < 300
union
select '[5-10>' as bin, count(1) as total from Sessions where duration >= 300 and duration < 600
union
select '[10-15>' as bin, count(1) as total from Sessions where duration >= 600 and duration < 900
union
select '15 or more' as bin, count(1) as total from Sessions where duration >= 900
