/*
1322. Ads Performance

https://leetcode-cn.com/problems/ads-performance/

+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| ad_id         | int     |
| user_id       | int     |
| action        | enum    |
+---------------+---------+
(ad_id, user_id) is the primary key for this table.
Each row of this table contains the ID of an Ad, the ID of a user, and the action taken by this user regarding this Ad.
The action column is an ENUM type of ('Clicked', 'Viewed', 'Ignored').

A company is running Ads and wants to calculate the performance of each Ad.

Write an SQL query to find the ctr of each Ad. Round ctr to two decimal points.

Return the result table ordered by ctr in descending order and by ad_id in ascending order in case of a tie.


Create table If Not Exists Ads (ad_id int, user_id int, action ENUM('Clicked', 'Viewed', 'Ignored'));
Truncate table Ads;
insert into Ads (ad_id, user_id, action) values ('1', '1', 'Clicked');
insert into Ads (ad_id, user_id, action) values ('2', '2', 'Clicked');
insert into Ads (ad_id, user_id, action) values ('3', '3', 'Viewed');
insert into Ads (ad_id, user_id, action) values ('5', '5', 'Ignored');
insert into Ads (ad_id, user_id, action) values ('1', '7', 'Ignored');
insert into Ads (ad_id, user_id, action) values ('2', '7', 'Viewed');
insert into Ads (ad_id, user_id, action) values ('3', '5', 'Clicked');
insert into Ads (ad_id, user_id, action) values ('1', '4', 'Viewed');
insert into Ads (ad_id, user_id, action) values ('2', '11', 'Viewed');
insert into Ads (ad_id, user_id, action) values ('1', '2', 'Clicked');
*/
select ad_id, round(if(clicks + views = 0, 0, clicks / (clicks + views) * 100), 2) as ctr from (
    select ad_id, sum(action = 'Clicked') as clicks, sum(action = 'Viewed') as views
    from Ads group by ad_id
) as t
order by ctr desc, ad_id
