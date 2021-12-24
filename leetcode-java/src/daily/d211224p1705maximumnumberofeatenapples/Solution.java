package daily.d211224p1705maximumnumberofeatenapples;

import java.security.Principal;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 1705. Maximum Number of Eaten Apples
 *
 * https://leetcode-cn.com/problems/maximum-number-of-eaten-apples/
 *
 * There is a special kind of apple tree that grows apples every day for n days.
 *
 * On the ith day, the tree grows apples[i] apples that will rot after days[i] days,
 * that is on day i + days[i] the apples will be rotten and cannot be eaten.
 *
 * On some days, the apple tree does not grow any apples, which are denoted by apples[i] == 0 and days[i] == 0.
 *
 * You decided to eat at most one apple a day (to keep the doctors away).
 *
 * Note that you can keep eating after the first n days.
 *
 * Given two integer arrays days and apples of length n, return the maximum number of apples you can eat.
 */

public class Solution {

    public int eatenApples(int[] apples, int[] days) {
        int ans = 0;
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(v -> v[0]));
        for (int day = 0; day < apples.length || !queue.isEmpty(); day++) {
            if (day < apples.length && apples[day] != 0) queue.add(new int[]{day + days[day], apples[day]});
            while (!queue.isEmpty() && (queue.peek()[0] <= day || queue.peek()[1] == 0)) queue.remove();
            if (!queue.isEmpty()) {
                ans++;
                --queue.peek()[1];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().eatenApples(new int[]{1,2,3,5,2}, new int[]{3,2,1,4,2}) == 7;
        assert new Solution().eatenApples(new int[]{3,0,0,0,0,2}, new int[]{3,0,0,0,0,2}) == 5;
    }

}
