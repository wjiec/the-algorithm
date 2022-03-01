/*
1303. Find the Team Size

https://leetcode-cn.com/problems/find-the-team-size/

+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| employee_id   | int     |
| team_id       | int     |
+---------------+---------+
employee_id is the primary key for this table.
Each row of this table contains the ID of each employee and their respective team.

Write an SQL query to find the team size of each of the employees.

Return result table in any order.


Create table If Not Exists Employee (employee_id int, team_id int);
Truncate table Employee;
insert into Employee (employee_id, team_id) values ('1', '8');
insert into Employee (employee_id, team_id) values ('2', '8');
insert into Employee (employee_id, team_id) values ('3', '8');
insert into Employee (employee_id, team_id) values ('4', '7');
insert into Employee (employee_id, team_id) values ('5', '9');
insert into Employee (employee_id, team_id) values ('6', '9');
*/
select employee_id, team_size
from Employee as e
join (select team_id, count(1) as team_size from Employee group by team_id) as t
    on e.team_id = t.team_id;

select employee_id, count(team_id) over (partition by team_id) as team_size from Employee;
