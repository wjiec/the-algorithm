/*
626. Exchange Seats

https://leetcode-cn.com/problems/exchange-seats/

Table: Seat

+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| id          | int     |
| name        | varchar |
+-------------+---------+
id is the primary key column for this table.
Each row of this table indicates the name and the ID of a student.
id is a continuous increment.

Write an SQL query to swap the seat id of every two consecutive students.
If the number of students is odd, the id of the last student is not swapped.

Return the result table ordered by id in ascending order.

Create table If Not Exists Seat (id int, student varchar(255));
Truncate table Seat;
insert into Seat (id, student) values ('1', 'Abbot');
insert into Seat (id, student) values ('2', 'Doris');
insert into Seat (id, student) values ('3', 'Emerson');
insert into Seat (id, student) values ('4', 'Green');
insert into Seat (id, student) values ('5', 'Jeames');
*/
select * from (
    select a.id, ifnull(b.student, a.student) as student from Seat as a left join Seat as b on a.id = b.id - 1 where a.id % 2 = 1
    union
    select a.id, b.student from Seat as a join Seat as b on a.id = b.id + 1 where a.id % 2 = 0
) as t order by id
