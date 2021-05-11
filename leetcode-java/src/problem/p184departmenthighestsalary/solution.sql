/*
184. Department Highest Salary

https://leetcode-cn.com/problems/department-highest-salary/

The Employee table holds all employees. Every employee has an Id, a salary, and there is also a column for the department Id.

The Department table holds all departments of the company.

Write a SQL query to find employees who have the highest salary in each of the departments.
For the above tables, your SQL query should return the following rows (order of rows does not matter).


Create table If Not Exists Employee (Id int, Name varchar(255), Salary int, DepartmentId int);
Create table If Not Exists Department (Id int, Name varchar(255));
Truncate table Employee;
insert into Employee (Id, Name, Salary, DepartmentId) values ('1', 'Joe', '70000', '1');
insert into Employee (Id, Name, Salary, DepartmentId) values ('2', 'Jim', '90000', '1');
insert into Employee (Id, Name, Salary, DepartmentId) values ('3', 'Henry', '80000', '2');
insert into Employee (Id, Name, Salary, DepartmentId) values ('4', 'Sam', '60000', '2');
insert into Employee (Id, Name, Salary, DepartmentId) values ('5', 'Max', '90000', '1');
Truncate table Department;
insert into Department (Id, Name) values ('1', 'IT');
insert into Department (Id, Name) values ('2', 'Sales');
*/
select d.`Name` as `Department`, e.`Name` as `Employee`, t.Salary from Employee as e
join (
    select DepartmentId, max(Salary) as Salary from Employee group by DepartmentId
) as t on e.DepartmentId = t.DepartmentId and e.Salary = t.Salary
join Department as d on d.Id = e.DepartmentId;
