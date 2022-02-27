/*
1050. Actors and Directors Who Cooperated At Least Three Times

https://leetcode-cn.com/problems/actors-and-directors-who-cooperated-at-least-three-times/

+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| actor_id    | int     |
| director_id | int     |
| timestamp   | int     |
+-------------+---------+
timestamp is the primary key column for this table.

Write a SQL query for a report that provides the pairs (actor_id, director_id)
where the actor has cooperated with the director at least three times.

Return the result table in any order.


Create table If Not Exists ActorDirector (actor_id int, director_id int, timestamp int);
Truncate table ActorDirector;
insert into ActorDirector (actor_id, director_id, timestamp) values ('1', '1', '0');
insert into ActorDirector (actor_id, director_id, timestamp) values ('1', '1', '1');
insert into ActorDirector (actor_id, director_id, timestamp) values ('1', '1', '2');
insert into ActorDirector (actor_id, director_id, timestamp) values ('1', '2', '3');
insert into ActorDirector (actor_id, director_id, timestamp) values ('1', '2', '4');
insert into ActorDirector (actor_id, director_id, timestamp) values ('2', '1', '5');
insert into ActorDirector (actor_id, director_id, timestamp) values ('2', '1', '6');
*/
select actor_id, director_id from ActorDirector group by actor_id, director_id having count(timestamp) >= 3
