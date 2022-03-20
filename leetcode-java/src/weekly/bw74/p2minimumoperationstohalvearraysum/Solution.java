package weekly.bw74.p2minimumoperationstohalvearraysum;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 6022. Minimum Operations to Halve Array Sum
 *
 * https://leetcode-cn.com/problems/minimum-operations-to-halve-array-sum/
 *
 * You are given an array nums of positive integers. In one operation, you can choose any number
 * from nums and reduce it to exactly half the number.
 * (Note that you may choose this reduced number in future operations.)
 *
 * Return the minimum number of operations to reduce the sum of nums by at least half.
 */

public class Solution {

    public int halveArray(int[] nums) {
        double sum = 0;
        PriorityQueue<Double> queue = new PriorityQueue<>(Comparator.reverseOrder());
        for (var n : nums) {
            sum += n;
            queue.add((double) n);
        }

        int ans = 0;
        for (double curr = 0, half = sum / 2; curr < half; ans++) {
            double val = queue.remove();
            curr += val / 2;
            queue.add(val / 2);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().halveArray(new int[]{1}) == 1;
        assert new Solution().halveArray(new int[]{5,19,8,1}) == 3;
        assert new Solution().halveArray(new int[]{3,8,20}) == 3;
    }

}
