/*
1294. Weather Type in Each Country

https://leetcode-cn.com/problems/weather-type-in-each-country/

+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| country_id    | int     |
| country_name  | varchar |
+---------------+---------+
country_id is the primary key for this table.
Each row of this table contains the ID and the name of one country.

+---------------+------+
| Column Name   | Type |
+---------------+------+
| country_id    | int  |
| weather_state | int  |
| day           | date |
+---------------+------+
(country_id, day) is the primary key for this table.
Each row of this table indicates the weather state in a country for one day.

Write an SQL query to find the type of weather in each country for November 2019.

The type of weather is:

Cold if the average weather_state is less than or equal 15,
Hot if the average weather_state is greater than or equal to 25, and
Warm otherwise.
Return result table in any order.


Create table If Not Exists Countries (country_id int, country_name varchar(20));
Create table If Not Exists Weather (country_id int, weather_state int, day date);
Truncate table Countries;
insert into Countries (country_id, country_name) values ('2', 'USA');
insert into Countries (country_id, country_name) values ('3', 'Australia');
insert into Countries (country_id, country_name) values ('7', 'Peru');
insert into Countries (country_id, country_name) values ('5', 'China');
insert into Countries (country_id, country_name) values ('8', 'Morocco');
insert into Countries (country_id, country_name) values ('9', 'Spain');
Truncate table Weather;
insert into Weather (country_id, weather_state, day) values ('2', '15', '2019-11-01');
insert into Weather (country_id, weather_state, day) values ('2', '12', '2019-10-28');
insert into Weather (country_id, weather_state, day) values ('2', '12', '2019-10-27');
insert into Weather (country_id, weather_state, day) values ('3', '-2', '2019-11-10');
insert into Weather (country_id, weather_state, day) values ('3', '0', '2019-11-11');
insert into Weather (country_id, weather_state, day) values ('3', '3', '2019-11-12');
insert into Weather (country_id, weather_state, day) values ('5', '16', '2019-11-07');
insert into Weather (country_id, weather_state, day) values ('5', '18', '2019-11-09');
insert into Weather (country_id, weather_state, day) values ('5', '21', '2019-11-23');
insert into Weather (country_id, weather_state, day) values ('7', '25', '2019-11-28');
insert into Weather (country_id, weather_state, day) values ('7', '22', '2019-12-01');
insert into Weather (country_id, weather_state, day) values ('7', '20', '2019-12-02');
insert into Weather (country_id, weather_state, day) values ('8', '25', '2019-11-05');
insert into Weather (country_id, weather_state, day) values ('8', '27', '2019-11-15');
insert into Weather (country_id, weather_state, day) values ('8', '31', '2019-11-25');
insert into Weather (country_id, weather_state, day) values ('9', '7', '2019-10-23');
insert into Weather (country_id, weather_state, day) values ('9', '3', '2019-12-23');
*/
select c.country_name, if(avg_weather_state <= 15, 'Cold', if(avg_weather_state >= 25, 'Hot', 'Warm')) as weather_type
from Countries as c
join (
    select country_id, avg(weather_state) as avg_weather_state
    from Weather
    where day >= '2019-11-01' and day < '2019-12-01'
    group by country_id
) as w on c.country_id = w.country_id
