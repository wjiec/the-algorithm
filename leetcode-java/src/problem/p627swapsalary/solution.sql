/*
627. Swap Salary

https://leetcode-cn.com/problems/swap-salary/

Write an SQL query to swap all 'f' and 'm' values
(i.e., change all 'f' values to 'm' and vice versa)
with a single update statement and no intermediate temp table(s).

Note that you must write a single update statement,
DO NOT write any select statement for this problem.


drop table if exists salary;
create table if not exists salary(id int, name varchar(100), sex char(1), salary int);
Truncate table salary;
insert into salary (id, name, sex, salary) values ('1', 'A', 'm', '2500');
insert into salary (id, name, sex, salary) values ('2', 'B', 'f', '1500');
insert into salary (id, name, sex, salary) values ('3', 'C', 'm', '5500');
insert into salary (id, name, sex, salary) values ('4', 'D', 'f', '500');
*/
update salary set sex = if (sex = 'f', 'm', 'f');
