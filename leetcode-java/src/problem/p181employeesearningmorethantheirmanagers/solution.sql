/*
181. Employees Earning More Than Their Managers

https://leetcode-cn.com/problems/employees-earning-more-than-their-managers/

The Employee table holds all employees including their managers. Every employee has an Id,
and there is also a column for the manager Id.

Given the Employee table, write a SQL query that finds out employees who earn more than their managers.
For the above table, Joe is the only employee who earns more than his manager.


Create table If Not Exists Employee (Id int, Name varchar(255), Salary int, ManagerId int);
Truncate table Employee;
insert into Employee (Id, Name, Salary, ManagerId) values ('1', 'Joe', '70000', '3');
insert into Employee (Id, Name, Salary, ManagerId) values ('2', 'Henry', '80000', '4');
insert into Employee (Id, Name, Salary, ManagerId) values ('3', 'Sam', '60000', 'None');
insert into Employee (Id, Name, Salary, ManagerId) values ('4', 'Max', '90000', 'None');
*/
select l.`Name` as `Employee` from Employee as l join Employee as r on l.ManagerId = r.Id where l.Salary > r.Salary;
