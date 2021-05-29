/*
620. Not Boring Movies

https://leetcode-cn.com/problems/not-boring-movies/

X city opened a new cinema, many people would like to go to this cinema.
The cinema also gives out a poster indicating the movies’ ratings and descriptions.

Please write a SQL query to output movies with an odd numbered ID
and a description that is not 'boring'. Order the result by rating.


Drop table If Exists cinema;
Create table If Not Exists cinema (id int, movie varchar(255), description varchar(255), rating float(2, 1));
Truncate table cinema;
insert into cinema (id, movie, description, rating) values ('1', 'War', 'great 3D', '8.9');
insert into cinema (id, movie, description, rating) values ('2', 'Science', 'fiction', '8.5');
insert into cinema (id, movie, description, rating) values ('3', 'irish', 'boring', '6.2');
insert into cinema (id, movie, description, rating) values ('4', 'Ice song', 'Fantacy', '8.6');
insert into cinema (id, movie, description, rating) values ('5', 'House card', 'Interesting', '9.1');
*/
select id, movie, description, rating from cinema where description <> 'boring' and id % 2 = 1 order by rating desc
