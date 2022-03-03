/*
1990. Count the Number of Experiments

https://leetcode-cn.com/problems/count-the-number-of-experiments/

+-----------------+------+
| Column Name     | Type |
+-----------------+------+
| experiment_id   | int  |
| platform        | enum |
| experiment_name | enum |
+-----------------+------+
experiment_id is the primary key for this table.
platform is an enum with one of the values ('Android', 'IOS', 'Web').
experiment_name is an enum with one of the values ('Reading', 'Sports', 'Programming').
This table contains information about the ID of an experiment done with a random person,
the platform used to do the experiment, and the name of the experiment.


Write an SQL query to report the number of experiments done on each of the three platforms
for each of the three given experiments. Notice that all the pairs of (platform, experiment)
should be included in the output including the pairs with zero experiments.

Return the result table in any order.


Create table If Not Exists Experiments (experiment_id int, platform ENUM('Android', 'IOS', 'Web'), experiment_name ENUM('Reading', 'Sports', 'Programming'));
Truncate table Experiments;
insert into Experiments (experiment_id, platform, experiment_name) values ('4', 'IOS', 'Programming');
insert into Experiments (experiment_id, platform, experiment_name) values ('13', 'IOS', 'Sports');
insert into Experiments (experiment_id, platform, experiment_name) values ('14', 'Android', 'Reading');
insert into Experiments (experiment_id, platform, experiment_name) values ('8', 'Web', 'Reading');
insert into Experiments (experiment_id, platform, experiment_name) values ('12', 'Web', 'Reading');
insert into Experiments (experiment_id, platform, experiment_name) values ('18', 'Web', 'Programming');
*/
select
    p.platform,
    en.experiment_name,
    count(e.experiment_id) as num_experiments
from (
    select 'Android' as platform
    union all
    select 'IOS'
    union all
    select 'Web'
) as p
cross join (
    select 'Reading' as experiment_name
    union all
    select 'Sports'
    union all
    select 'Programming'
) as en
left join Experiments as e
    on p.platform=e.platform and en.experiment_name = e.experiment_name
group by p.platform, en.experiment_name
