/*
1623. All Valid Triplets That Can Represent a Country

https://leetcode-cn.com/problems/all-valid-triplets-that-can-represent-a-country/

+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| student_id    | int     |
| student_name  | varchar |
+---------------+---------+
student_id is the primary key for this table.
Each row of this table contains the name and the id of a student in school A.
All student_name are distinct.

+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| student_id    | int     |
| student_name  | varchar |
+---------------+---------+
student_id is the primary key for this table.
Each row of this table contains the name and the id of a student in school B.
All student_name are distinct.

+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| student_id    | int     |
| student_name  | varchar |
+---------------+---------+
student_id is the primary key for this table.
Each row of this table contains the name and the id of a student in school C.
All student_name are distinct.

There is a country with three schools, where each student is enrolled in exactly one school. The country is joining a competition and wants to select one student from each school to represent the country such that:

member_A is selected from SchoolA,
member_B is selected from SchoolB,
member_C is selected from SchoolC, and
The selected students' names and IDs are pairwise distinct (i.e. no two students share the same name, and no two students share the same ID).
Write an SQL query to find all the possible triplets representing the country under the given constraints.

Return the result table in any order.


Create table If Not Exists SchoolA (student_id int, student_name varchar(20));
Create table If Not Exists SchoolB (student_id int, student_name varchar(20));
Create table If Not Exists SchoolC (student_id int, student_name varchar(20));
Truncate table SchoolA;
insert into SchoolA (student_id, student_name) values ('1', 'Alice');
insert into SchoolA (student_id, student_name) values ('2', 'Bob');
Truncate table SchoolB;
insert into SchoolB (student_id, student_name) values ('3', 'Tom');
Truncate table SchoolC;
insert into SchoolC (student_id, student_name) values ('3', 'Tom');
insert into SchoolC (student_id, student_name) values ('2', 'Jerry');
insert into SchoolC (student_id, student_name) values ('10', 'Alice');
*/
select member_A, member_B, member_C from (
    select
        a.student_name as member_A, a.student_id as aid,
        b.student_name as member_B, b.student_id as bid,
        c.student_name as member_C, c.student_id as cid
    from SchoolA as a
    join SchoolB as b
    join SchoolC as c
) as t
where aid != bid and bid != cid and aid != cid
and member_A != member_B and member_B != member_C and member_A != member_C
