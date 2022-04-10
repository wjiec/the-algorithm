package weekly.w288.p0largestnumberafterdigitswapsbyparity;

import java.util.PriorityQueue;

/**
 * 6037. Largest Number After Digit Swaps by Parity
 *
 * https://leetcode-cn.com/contest/weekly-contest-288/problems/largest-number-after-digit-swaps-by-parity/
 *
 * You are given a positive integer num. You may swap any two digits of num that have the same parity
 * (i.e. both odd digits or both even digits).
 *
 * Return the largest possible value of num after any number of swaps.
 */

public class Solution {

    public int largestInteger(int num) {
        if (num < 10) return num;

        long base = 1;
        PriorityQueue<Integer> odd = new PriorityQueue<>((a, b) -> b - a);
        PriorityQueue<Integer> even = new PriorityQueue<>((a, b) -> b - a);
        for (int v = num; v != 0; v /= 10) {
            base *= 10;
            if (v % 2 == 0) even.add(v % 10);
            else odd.add(v % 10);
        }

        int ans = 0;
        for (base /= 10; base != 0; base /= 10) {
            if ((num / base) % 2 == 0) ans = ans * 10 + even.remove();
            else ans = ans * 10 + odd.remove();
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().largestInteger(247) == 427;

        assert new Solution().largestInteger(1234) == 3412;
        assert new Solution().largestInteger(65875) == 87655;
        assert new Solution().largestInteger(1000000000) == 1000000000;
    }

}
