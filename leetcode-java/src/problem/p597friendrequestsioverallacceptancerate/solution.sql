/*
597. Friend Requests I: Overall Acceptance Rate

https://leetcode-cn.com/problems/friend-requests-i-overall-acceptance-rate/

+----------------+---------+
| Column Name    | Type    |
+----------------+---------+
| sender_id      | int     |
| send_to_id     | int     |
| request_date   | date    |
+----------------+---------+
There is no primary key for this table, it may contain duplicates.
This table contains the ID of the user who sent the request, the ID of the user who received the request, and the date of the request.

+----------------+---------+
| Column Name    | Type    |
+----------------+---------+
| requester_id   | int     |
| accepter_id    | int     |
| accept_date    | date    |
+----------------+---------+
There is no primary key for this table, it may contain duplicates.
This table contains the ID of the user who sent the request, the ID of the user who received the request,
and the date when the request was accepted.

Write an SQL query to find the overall acceptance rate of requests, which is the number of acceptance divided by
the number of requests. Return the answer rounded to 2 decimals places.

Note that:

The accepted requests are not necessarily from the table friend_request. In this case,
Count the total accepted requests (no matter whether they are in the original requests),
and divide it by the number of requests to get the acceptance rate.

It is possible that a sender sends multiple requests to the same receiver, and a request
could be accepted more than once. In this case, the ‘duplicated’ requests or acceptances are only counted once.

If there are no requests at all, you should return 0.00 as the accept_rate.

Create table If Not Exists FriendRequest (sender_id int, send_to_id int, request_date date);
Create table If Not Exists RequestAccepted (requester_id int, accepter_id int, accept_date date);
Truncate table FriendRequest;
insert into FriendRequest (sender_id, send_to_id, request_date) values ('1', '2', '2016/06/01');
insert into FriendRequest (sender_id, send_to_id, request_date) values ('1', '3', '2016/06/01');
insert into FriendRequest (sender_id, send_to_id, request_date) values ('1', '4', '2016/06/01');
insert into FriendRequest (sender_id, send_to_id, request_date) values ('2', '3', '2016/06/02');
insert into FriendRequest (sender_id, send_to_id, request_date) values ('3', '4', '2016/06/09');
Truncate table RequestAccepted;
insert into RequestAccepted (requester_id, accepter_id, accept_date) values ('1', '2', '2016/06/03');
insert into RequestAccepted (requester_id, accepter_id, accept_date) values ('1', '3', '2016/06/08');
insert into RequestAccepted (requester_id, accepter_id, accept_date) values ('2', '3', '2016/06/08');
insert into RequestAccepted (requester_id, accepter_id, accept_date) values ('3', '4', '2016/06/09');
insert into RequestAccepted (requester_id, accepter_id, accept_date) values ('3', '4', '2016/06/10');
*/
select convert(if(send_count = 0,0.00,accepted_count / send_count), DECIMAL(10,2)) as accept_rate from (
    select count(sender_id) as send_count from (select sender_id from FriendRequest group by sender_id, send_to_id) as a
) as a join (
    select count(accepter_id) as accepted_count from (select accepter_id from RequestAccepted group by requester_id, accepter_id) as b
) as b
