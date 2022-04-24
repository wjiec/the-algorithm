/*
608. Tree Node

Table: Tree
+-------------+------+
| Column Name | Type |
+-------------+------+
| id          | int  |
| p_id        | int  |
+-------------+------+
id is the primary key column for this table.
Each row of this table contains information about the id of a node and the id of its parent node in a tree.
The given structure is always a valid tree.

Each node in the tree can be one of three types:

"Leaf": if the node is a leaf node.
"Root": if the node is the root of the tree.
"Inner": If the node is neither a leaf node nor a root node.
Write an SQL query to report the type of each node in the tree.

Return the result table ordered by id in ascending order.


drop table if exists `Tree`;
Create table If Not Exists Tree (id int, p_id int);
Truncate table Tree;
insert into Tree (id, p_id) values ('1', null);
insert into Tree (id, p_id) values ('2', '1');
insert into Tree (id, p_id) values ('3', '1');
insert into Tree (id, p_id) values ('4', '2');
insert into Tree (id, p_id) values ('5', '2');
*/
select * from (
    select id, 'Root' as type from Tree where p_id is null
    union
    select id, 'Leaf' as type from Tree where p_id is not null and id not in (select p_id from Tree where p_id is not null)
    union
    select id, 'Inner' as type from Tree where p_id is not null and id in (select p_id from Tree where p_id is not null)
) as t order by id
