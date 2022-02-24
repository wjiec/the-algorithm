/*
603. Consecutive Available Seats

https://leetcode-cn.com/problems/consecutive-available-seats/

+-------------+------+
| Column Name | Type |
+-------------+------+
| seat_id     | int  |
| free        | bool |
+-------------+------+
seat_id is an auto-increment primary key column for this table.
Each row of this table indicates whether the ith seat is free or not. 1 means free while 0 means occupied.

Write an SQL query to report all the consecutive available seats in the cinema.

Return the result table ordered by seat_id in ascending order.

Create table If Not Exists Cinema (seat_id int primary key auto_increment, free bool);
Truncate table Cinema;
insert into Cinema (seat_id, free) values ('1', '1');
insert into Cinema (seat_id, free) values ('2', '0');
insert into Cinema (seat_id, free) values ('3', '1');
insert into Cinema (seat_id, free) values ('4', '1');
insert into Cinema (seat_id, free) values ('5', '1');
*/
select distinct a.seat_id from Cinema as a join (select seat_id from Cinema where free = 1) as b on a.seat_id = b.seat_id - 1 or a.seat_id = b.seat_id + 1 where a.free = 1
