/*
619. Biggest Single Number

https://leetcode-cn.com/problems/biggest-single-number/

+-------------+------+
| Column Name | Type |
+-------------+------+
| num         | int  |
+-------------+------+
There is no primary key for this table. It may contain duplicates.
Each row of this table contains an integer.

A single number is a number that appeared only once in the MyNumbers table.

Write an SQL query to report the largest single number. If there is no single number, report null.

Create table If Not Exists MyNumbers (num int);
Truncate table MyNumbers;
insert into MyNumbers (num) values ('8');
insert into MyNumbers (num) values ('8');
insert into MyNumbers (num) values ('3');
insert into MyNumbers (num) values ('3');
insert into MyNumbers (num) values ('1');
insert into MyNumbers (num) values ('4');
insert into MyNumbers (num) values ('5');
insert into MyNumbers (num) values ('6');
*/
select t.num from MyNumbers left join (
    select num, count(1) as cnt from MyNumbers group by num
) as t on t.cnt < 2 order by num desc limit 1
