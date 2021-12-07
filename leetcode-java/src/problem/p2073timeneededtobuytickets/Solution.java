package problem.p2073timeneededtobuytickets;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 2073. Time Needed to Buy Tickets
 *
 * https://leetcode-cn.com/problems/time-needed-to-buy-tickets/
 *
 * There are n people in a line queuing to buy tickets, where the 0th person is at the front of the line
 * and the (n - 1)th person is at the back of the line.
 *
 * You are given a 0-indexed integer array tickets of length n where the number of tickets that
 * the ith person would like to buy is tickets[i].
 *
 * Each person takes exactly 1 second to buy a ticket. A person can only buy 1 ticket at a time
 * and has to go back to the end of the line (which happens instantaneously) in order to buy more tickets.
 *
 * If a person does not have any tickets left to buy, the person will leave the line.
 *
 * Return the time taken for the person at position k (0-indexed) to finish buying tickets.
 */

public class Solution {

    public int timeRequiredToBuy(int[] tickets, int k) {
        int ans = 0;
        while (tickets[k] != 0) {
            for (int i = 0; i < tickets.length; i++) {
                if (tickets[i] != 0) {
                    tickets[i]--;
                    ans++;
                }

                if (tickets[i] == 0 && i == k) {
                    break;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().timeRequiredToBuy(new int[]{2,3,2}, 2) == 6;
        assert new Solution().timeRequiredToBuy(new int[]{5,1,1,1}, 0) == 8;
    }

}
