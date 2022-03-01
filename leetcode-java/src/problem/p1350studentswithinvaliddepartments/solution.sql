/*
1350. Students With Invalid Departments

https://leetcode-cn.com/problems/students-with-invalid-departments/

+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| id            | int     |
| name          | varchar |
+---------------+---------+
id is the primary key of this table.
The table has information about the id of each department of a university.

+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| id            | int     |
| name          | varchar |
| department_id | int     |
+---------------+---------+
id is the primary key of this table.
The table has information about the id of each student at a university and the id of the department he/she studies at.

Write an SQL query to find the id and the name of all students who are enrolled in departments that no longer exist.

Return the result table in any order.


Create table If Not Exists Departments (id int, name varchar(30));
Create table If Not Exists Students (id int, name varchar(30), department_id int);
Truncate table Departments;
insert into Departments (id, name) values ('1', 'Electrical Engineering');
insert into Departments (id, name) values ('7', 'Computer Engineering');
insert into Departments (id, name) values ('13', 'Bussiness Administration');
Truncate table Students;
insert into Students (id, name, department_id) values ('23', 'Alice', '1');
insert into Students (id, name, department_id) values ('1', 'Bob', '7');
insert into Students (id, name, department_id) values ('5', 'Jennifer', '13');
insert into Students (id, name, department_id) values ('2', 'John', '14');
insert into Students (id, name, department_id) values ('4', 'Jasmine', '77');
insert into Students (id, name, department_id) values ('3', 'Steve', '74');
insert into Students (id, name, department_id) values ('6', 'Luis', '1');
insert into Students (id, name, department_id) values ('8', 'Jonathan', '7');
insert into Students (id, name, department_id) values ('7', 'Daiana', '33');
insert into Students (id, name, department_id) values ('11', 'Madelynn', '1');
*/
select s.id, s.name from Students as s left join Departments as d on s.department_id = d.id
where d.id is null
