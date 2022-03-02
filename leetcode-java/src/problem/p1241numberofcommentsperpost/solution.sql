/*
1241. Number of Comments per Post

https://leetcode-cn.com/problems/number-of-comments-per-post/

+---------------+----------+
| Column Name   | Type     |
+---------------+----------+
| sub_id        | int      |
| parent_id     | int      |
+---------------+----------+
There is no primary key for this table, it may have duplicate rows.
Each row can be a post or comment on the post.
parent_id is null for posts.
parent_id for comments is sub_id for another post in the table.

Write an SQL query to find the number of comments per post. The result table should contain post_id and its corresponding number_of_comments.

The Submissions table may contain duplicate comments. You should count the number of unique comments per post.

The Submissions table may contain duplicate posts. You should treat them as one post.

The result table should be ordered by post_id in ascending order.


Create table If Not Exists Submissions (sub_id int, parent_id int);
Truncate table Submissions;
insert into Submissions (sub_id, parent_id) values ('1', null);
insert into Submissions (sub_id, parent_id) values ('2', null);
insert into Submissions (sub_id, parent_id) values ('1', null);
insert into Submissions (sub_id, parent_id) values ('12', null);
insert into Submissions (sub_id, parent_id) values ('3', '1');
insert into Submissions (sub_id, parent_id) values ('5', '2');
insert into Submissions (sub_id, parent_id) values ('3', '1');
insert into Submissions (sub_id, parent_id) values ('4', '1');
insert into Submissions (sub_id, parent_id) values ('9', '1');
insert into Submissions (sub_id, parent_id) values ('10', '2');
insert into Submissions (sub_id, parent_id) values ('6', '7');
*/
select p.post_id, if(parent_id is null, 0, count(distinct sub_id)) as number_of_comments from (
    select distinct sub_id as post_id from Submissions where parent_id is null
) as p left join Submissions as t on t.parent_id = p.post_id
group by p.post_id order by post_id
