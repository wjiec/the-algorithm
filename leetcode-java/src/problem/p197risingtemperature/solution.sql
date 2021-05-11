/*
197. Rising Temperature

https://leetcode-cn.com/problems/rising-temperature/

Write an SQL query to find all dates' id with higher temperature compared to its previous dates (yesterday).

Return the result table in any order.

Create table If Not Exists Weather (Id int, RecordDate date, Temperature int);
Truncate table Weather;
insert into Weather (Id, RecordDate, Temperature) values ('1', '2015-01-01', '10');
insert into Weather (Id, RecordDate, Temperature) values ('2', '2015-01-02', '25');
insert into Weather (Id, RecordDate, Temperature) values ('3', '2015-01-03', '20');
insert into Weather (Id, RecordDate, Temperature) values ('4', '2015-01-04', '30');
*/
select w2.Id from Weather as w1
join Weather as w2 on date_add(w1.RecordDate, interval 1 day) = w2.RecordDate
where w2.Temperature > w1.Temperature
