/*
177. Nth Highest Salary

https://leetcode-cn.com/problems/nth-highest-salary/

Write a SQL query to get the nth highest salary from the Employee table.

For example, given the above Employee table, the nth highest salary where n = 2 is 200.
If there is no nth highest salary, then the query should return null.
*/
CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
    set N := N - 1;
    RETURN (
        select (select distinct(Salary) as SecondHighestSalary from Employee order by Salary desc limit 1 offset N) as SecondHighestSalary
    );
END
