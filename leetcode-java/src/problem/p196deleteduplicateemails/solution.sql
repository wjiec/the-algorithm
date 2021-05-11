/*
196. Delete Duplicate Emails

https://leetcode-cn.com/problems/delete-duplicate-emails/

Write a SQL query to delete all duplicate email entries in a table named Person,
keeping only unique emails based on its smallest Id.


Truncate table Person;
insert into Person (Id, Email) values ('1', 'john@example.com');
insert into Person (Id, Email) values ('2', 'bob@example.com');
insert into Person (Id, Email) values ('3', 'john@example.com');
*/
delete l from Person as l, Person as r where l.Email = r.Email and l.Id > r.Id
