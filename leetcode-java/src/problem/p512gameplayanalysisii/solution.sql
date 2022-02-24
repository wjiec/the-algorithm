/*
512. Game Play Analysis II

https://leetcode-cn.com/problems/game-play-analysis-ii/

+--------------+---------+
| Column Name  | Type    |
+--------------+---------+
| player_id    | int     |
| device_id    | int     |
| event_date   | date    |
| games_played | int     |
+--------------+---------+
(player_id, event_date) is the primary key of this table.
This table shows the activity of players of some games.
Each row is a record of a player who logged in and played a number of games (possibly 0)
before logging out on someday using some device.

Write an SQL query to report the device that is first logged in for each player.

Return the result table in any order.

Create table If Not Exists Activity (player_id int, device_id int, event_date date, games_played int);
Truncate table Activity;
insert into Activity (player_id, device_id, event_date, games_played) values ('1', '2', '2016-03-01', '5');
insert into Activity (player_id, device_id, event_date, games_played) values ('1', '2', '2016-05-02', '6');
insert into Activity (player_id, device_id, event_date, games_played) values ('2', '3', '2017-06-25', '1');
insert into Activity (player_id, device_id, event_date, games_played) values ('3', '1', '2016-03-02', '0');
insert into Activity (player_id, device_id, event_date, games_played) values ('3', '4', '2018-07-03', '5');
*/
select
    a.player_id,
    a.device_id
from
    `Activity` as `a`
join (
    select
        `player_id`,
        min(`event_date`) as `first_login`
    from `Activity`
    group by `player_id`
) as t on
    a.player_id = t.player_id
    and a.event_date = t.first_login
order by a.player_id
