/*
1667. Fix Names in a Table

https://leetcode-cn.com/problems/fix-names-in-a-table/

+----------------+---------+
| Column Name    | Type    |
+----------------+---------+
| user_id        | int     |
| name           | varchar |
+----------------+---------+
user_id is the primary key for this table.
This table contains the ID and the name of the user. The name consists of only lowercase and uppercase characters.

Write an SQL query to fix the names so that only the first character is uppercase and the rest are lowercase.

Return the result table ordered by user_id.


drop table if exists Users;
Create table If Not Exists Users (user_id int, name varchar(40));
Truncate table Users;
insert into Users (user_id, name) values ('1', 'aLice');
insert into Users (user_id, name) values ('2', 'bOB');
*/
select user_id, concat(upper(substr(name, 1, 1)), lower(substr(name, 2))) as name from Users
order by user_id
