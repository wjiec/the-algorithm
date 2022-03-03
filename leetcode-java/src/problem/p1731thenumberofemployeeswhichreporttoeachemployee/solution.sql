/*
1731. The Number of Employees Which Report to Each Employee

https://leetcode-cn.com/problems/the-number-of-employees-which-report-to-each-employee/

+-------------+----------+
| Column Name | Type     |
+-------------+----------+
| employee_id | int      |
| name        | varchar  |
| reports_to  | int      |
| age         | int      |
+-------------+----------+
employee_id is the primary key for this table.
This table contains information about the employees and the id of the manager they report to.
Some employees do not report to anyone (reports_to is null).

For this problem, we will consider a manager an employee who has at least 1 other employee reporting to them.

Write an SQL query to report the ids and the names of all managers, the number of employees who report directly to them, and the average age of the reports rounded to the nearest integer.

Return the result table ordered by employee_id.


drop table if exists Employees;
Create table If Not Exists Employees(employee_id int, name varchar(20), reports_to int, age int);
Truncate table Employees;
insert into Employees (employee_id, name, reports_to, age) values ('9', 'Hercy', null, '43');
insert into Employees (employee_id, name, reports_to, age) values ('6', 'Alice', '9', '41');
insert into Employees (employee_id, name, reports_to, age) values ('4', 'Bob', '9', '36');
insert into Employees (employee_id, name, reports_to, age) values ('2', 'Winston', null, '37');
*/
select
    a.reports_to as employee_id,
    b.name, count(distinct a.employee_id) as reports_count,
    round(avg(a.age), 0) as average_age
from (select * from Employees where reports_to is not null) as a
left join Employees as b on a.reports_to = b.employee_id
group by a.reports_to
