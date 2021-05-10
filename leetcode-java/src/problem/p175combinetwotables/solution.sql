/*
175. Combine Two Tables

https://leetcode-cn.com/problems/combine-two-tables/

Create table Person (PersonId int, FirstName varchar(255), LastName varchar(255));
Create table Address (AddressId int, PersonId int, City varchar(255), State varchar(255));
Truncate table Person;
insert into Person (PersonId, LastName, FirstName) values ('1', 'Wang', 'Allen');
Truncate table Address;
insert into Address (AddressId, PersonId, City, State) values ('1', '2', 'New York City', 'New York');
*/

-- Write your MySQL query statement below
select p.FirstName, p.LastName, a.City, a.State from Person as p left join Address as a on p.PersonId = a.PersonId
