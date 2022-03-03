/*
1853. Convert Date Format

https://leetcode-cn.com/problems/convert-date-format/

+-------------+------+
| Column Name | Type |
+-------------+------+
| day         | date |
+-------------+------+
day is the primary key for this table.

Write an SQL query to convert each date in Days into a string formatted as "day_name, month_name day, year".

Return the result table in any order.

Create table If Not Exists Days (day date);
Truncate table Days;
insert into Days (day) values ('2022-04-12');
insert into Days (day) values ('2021-08-09');
insert into Days (day) values ('2020-06-26');
*/
select date_format(day, '%W, %M %e, %Y') as 'day' from Days
