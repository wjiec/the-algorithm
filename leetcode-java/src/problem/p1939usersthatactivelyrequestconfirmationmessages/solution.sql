/*
1939. Users That Actively Request Confirmation Messages

https://leetcode-cn.com/problems/users-that-actively-request-confirmation-messages/

+----------------+----------+
| Column Name    | Type     |
+----------------+----------+
| user_id        | int      |
| time_stamp     | datetime |
+----------------+----------+
user_id is the primary key for this table.
Each row contains information about the signup time for the user with ID user_id.


+----------------+----------+
| Column Name    | Type     |
+----------------+----------+
| user_id        | int      |
| time_stamp     | datetime |
| action         | ENUM     |
+----------------+----------+
(user_id, time_stamp) is the primary key for this table.
user_id is a foreign key with a reference to the Signups table.
action is an ENUM of the type ('confirmed', 'timeout')
Each row of this table indicates that the user with ID user_id requested a confirmation message at
time_stamp and that confirmation message was either confirmed ('confirmed') or expired without confirming ('timeout').


Write an SQL query to find the IDs of the users that requested a confirmation message twice within a 24-hour window.
Two messages exactly 24 hours apart are considered to be within the window.
The action does not affect the answer, only the request time.

Return the result table in any order.


Create table If Not Exists Signups (user_id int, time_stamp datetime);
Create table If Not Exists Confirmations (user_id int, time_stamp datetime, action ENUM('confirmed','timeout'));
Truncate table Signups;
insert into Signups (user_id, time_stamp) values ('3', '2020-03-21 10:16:13');
insert into Signups (user_id, time_stamp) values ('7', '2020-01-04 13:57:59');
insert into Signups (user_id, time_stamp) values ('2', '2020-07-29 23:09:44');
insert into Signups (user_id, time_stamp) values ('6', '2020-12-09 10:39:37');
Truncate table Confirmations;
insert into Confirmations (user_id, time_stamp, action) values ('3', '2021-01-06 03:30:46', 'timeout');
insert into Confirmations (user_id, time_stamp, action) values ('3', '2021-01-06 03:37:45', 'timeout');
insert into Confirmations (user_id, time_stamp, action) values ('7', '2021-06-12 11:57:29', 'confirmed');
insert into Confirmations (user_id, time_stamp, action) values ('7', '2021-06-13 11:57:30', 'confirmed');
insert into Confirmations (user_id, time_stamp, action) values ('2', '2021-01-22 00:00:00', 'confirmed');
insert into Confirmations (user_id, time_stamp, action) values ('2', '2021-01-23 00:00:00', 'timeout');
insert into Confirmations (user_id, time_stamp, action) values ('6', '2021-10-23 14:14:14', 'confirmed');
insert into Confirmations (user_id, time_stamp, action) values ('6', '2021-10-24 14:14:13', 'timeout');
*/
select distinct c1.user_id
from Confirmations c1
join Confirmations c2 on
    c1.user_id = c2.user_id and
    c1.time_stamp > c2.time_stamp and
    c1.time_stamp <= date_add(c2.time_stamp, interval 1 day);
