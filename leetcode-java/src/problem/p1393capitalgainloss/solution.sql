/*
1393. Capital Gain/Loss

https://leetcode.cn/problems/capital-gainloss/

Table: Stocks

+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| stock_name    | varchar |
| operation     | enum    |
| operation_day | int     |
| price         | int     |
+---------------+---------+
(stock_name, operation_day) is the primary key for this table.
The operation column is an ENUM of type ('Sell', 'Buy')
Each row of this table indicates that the stock which has stock_name had an operation
on the day operation_day with the price.
It is guaranteed that each 'Sell' operation for a stock has a corresponding 'Buy' operation
in a previous day. It is also guaranteed that each 'Buy' operation for a stock
has a corresponding 'Sell' operation in an upcoming day.


Write an SQL query to report the Capital gain/loss for each stock.

The Capital gain/loss of a stock is the total gain or loss after buying and selling
the stock one or many times.

Return the result table in any order.


Create Table If Not Exists Stocks (stock_name varchar(15), operation ENUM('Sell', 'Buy'), operation_day int, price int);
Truncate table Stocks;
insert into Stocks (stock_name, operation, operation_day, price) values ('Leetcode', 'Buy', '1', '1000');
insert into Stocks (stock_name, operation, operation_day, price) values ('Corona Masks', 'Buy', '2', '10');
insert into Stocks (stock_name, operation, operation_day, price) values ('Leetcode', 'Sell', '5', '9000');
insert into Stocks (stock_name, operation, operation_day, price) values ('Handbags', 'Buy', '17', '30000');
insert into Stocks (stock_name, operation, operation_day, price) values ('Corona Masks', 'Sell', '3', '1010');
insert into Stocks (stock_name, operation, operation_day, price) values ('Corona Masks', 'Buy', '4', '1000');
insert into Stocks (stock_name, operation, operation_day, price) values ('Corona Masks', 'Sell', '5', '500');
insert into Stocks (stock_name, operation, operation_day, price) values ('Corona Masks', 'Buy', '6', '1000');
insert into Stocks (stock_name, operation, operation_day, price) values ('Handbags', 'Sell', '29', '7000');
insert into Stocks (stock_name, operation, operation_day, price) values ('Corona Masks', 'Sell', '10', '10000');
*/
select
    s1.stock_name,
    s2.sell-s1.buy as capital_gain_loss
from (
    select stock_name, sum(price) as buy
    from Stocks
    where operation='Buy'
    group by stock_name
) as s1, (
    select stock_name,sum(price) as sell
    from Stocks
    where operation = 'Sell'
    group by stock_name
) as s2
where s1.stock_name = s2.stock_name
