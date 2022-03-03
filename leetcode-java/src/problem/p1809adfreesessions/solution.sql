/*
1809. Ad-Free Sessions

https://leetcode-cn.com/problems/ad-free-sessions/

+-------------+------+
| Column Name | Type |
+-------------+------+
| session_id  | int  |
| customer_id | int  |
| start_time  | int  |
| end_time    | int  |
+-------------+------+
session_id is the primary key for this table.
customer_id is the ID of the customer watching this session.
The session runs during the inclusive interval between start_time and end_time.
It is guaranteed that start_time <= end_time and that two sessions for the same customer do not intersect.

+-------------+------+
| Column Name | Type |
+-------------+------+
| ad_id       | int  |
| customer_id | int  |
| timestamp   | int  |
+-------------+------+
ad_id is the primary key for this table.
customer_id is the ID of the customer viewing this ad.
timestamp is the moment of time at which the ad was shown.

Write an SQL query to report all the sessions that did not get shown any ads.

Return the result table in any order.


Create table If Not Exists Playback(session_id int,customer_id int,start_time int,end_time int);
Create table If Not Exists Ads (ad_id int, customer_id int, timestamp int);
Truncate table Playback;
insert into Playback (session_id, customer_id, start_time, end_time) values ('1', '1', '1', '5');
insert into Playback (session_id, customer_id, start_time, end_time) values ('2', '1', '15', '23');
insert into Playback (session_id, customer_id, start_time, end_time) values ('3', '2', '10', '12');
insert into Playback (session_id, customer_id, start_time, end_time) values ('4', '2', '17', '28');
insert into Playback (session_id, customer_id, start_time, end_time) values ('5', '2', '2', '8');
Truncate table Ads;
insert into Ads (ad_id, customer_id, timestamp) values ('1', '1', '5');
insert into Ads (ad_id, customer_id, timestamp) values ('2', '2', '17');
insert into Ads (ad_id, customer_id, timestamp) values ('3', '2', '20');
*/
select session_id from Playback
where session_id not in (
    select distinct p.session_id from Playback p
    left join Ads a ON p.customer_id = a.customer_id
    where p.start_time <= a.timestamp and a.timestamp <= p.end_time
)
