/*
2072. The Winner University

https://leetcode-cn.com/problems/the-winner-university/

+-------------+------+
| Column Name | Type |
+-------------+------+
| student_id  | int  |
| score       | int  |
+-------------+------+
student_id is the primary key for this table.
Each row contains information about the score of one student from New York University in an exam.

+-------------+------+
| Column Name | Type |
+-------------+------+
| student_id  | int  |
| score       | int  |
+-------------+------+
student_id is the primary key for this table.
Each row contains information about the score of one student from California University in an exam.

There is a competition between New York University and California University. The competition is
held between the same number of students from both universities.

The university that has more excellent students wins the competition.
If the two universities have the same number of excellent students, the competition ends in a draw.

An excellent student is a student that scored 90% or more in the exam.

Write an SQL query to report:

"New York University" if New York University wins the competition.
"California University" if California University wins the competition.
"No Winner" if the competition ends in a draw.


Create table If Not Exists NewYork (student_id int, score int);
Create table If Not Exists California (student_id int, score int);
Truncate table NewYork;
insert into NewYork (student_id, score) values ('1', '90');
insert into NewYork (student_id, score) values ('2', '87');
Truncate table California;
insert into California (student_id, score) values ('2', '89');
insert into California (student_id, score) values ('3', '88');
*/
select
    case when c1 > c2 then 'New York University'
         when c2 > c1 then 'California University'
         else 'No Winner' end as winner
from
    (select count(student_id) c1 from NewYork  where score>=90) as t1,
    (select count(student_id) c2 from California  where score>=90) as t2
