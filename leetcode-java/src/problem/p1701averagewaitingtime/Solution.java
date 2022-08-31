package problem.p1701averagewaitingtime;

import common.Checker;

/**
 * 1701. Average Waiting Time
 *
 * https://leetcode.cn/problems/average-waiting-time/
 *
 * There is a restaurant with a single chef. You are given an array customers, where
 * customers[i] = [arrivali, timei]:
 *
 * arrivali is the arrival time of the ith customer. The arrival times are sorted in non-decreasing order.
 * timei is the time needed to prepare the order of the ith customer.
 * When a customer arrives, he gives the chef his order, and the chef starts preparing it once he is idle.
 * The customer waits till the chef finishes preparing his order. The chef does not prepare food for more
 * than one customer at a time. The chef prepares food for customers in the order they were given in the input.
 *
 * Return the average waiting time of all customers. Solutions within 10-5 from
 * the actual answer are considered accepted.
 */

public class Solution {

    public double averageWaitingTime(int[][] customers) {
        long total = 0, curr = 0;
        for (var customer : customers) {
            int arrival = customer[0], time = customer[1];
            if (curr > arrival) total += curr - arrival;
            else curr = arrival;
            total += time; curr += time;
        }
        return (double) total / customers.length;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().averageWaitingTime(new int[][]{{1,2},{2,5},{4,3}}), 5.0);
        assert Checker.check(new Solution().averageWaitingTime(new int[][]{{5,2},{5,4},{10,3},{20,1}}), 3.25);
        assert Checker.check(new Solution().averageWaitingTime(new int[][]{{5,2},{5,4},{10,3},{20,1}}), 3.25);
    }

}
