/*
182. Duplicate Emails

https://leetcode-cn.com/problems/duplicate-emails/

Write a SQL query to find all duplicate emails in a table named Person.


Create table If Not Exists Person (Id int, Email varchar(255));
Truncate table Person;
insert into Person (Id, Email) values ('1', 'a@b.com');
insert into Person (Id, Email) values ('2', 'c@d.com');
insert into Person (Id, Email) values ('3', 'a@b.com');
*/
select Email from (
  select Email, count(1) as cnt from Person group by Email
) as t
where t.cnt > 1;

/* 6.87% */
select Email from Person group by Email having count(1) > 1;
