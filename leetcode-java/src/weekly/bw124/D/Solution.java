package weekly.bw124.D;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 3041. Maximize Consecutive Elements in an Array After Modification
 *
 * https://leetcode.cn/contest/biweekly-contest-124/problems/maximize-consecutive-elements-in-an-array-after-modification/
 *
 * You are given a 0-indexed array nums consisting of positive integers.
 *
 * Initially, you can increase the value of any element in the array by at most 1.
 *
 * After that, you need to select one or more elements from the final array such that
 * those elements are consecutive when sorted in increasing order.
 *
 * For example, the elements [3, 4, 5] are consecutive while [3, 4, 6] and [1, 1, 2, 3] are not.
 *
 * Return the maximum number of elements that you can select.
 */

public class Solution {

    public int maxSelectedElements(int[] nums) {
        Arrays.sort(nums);
        Map<Integer, Integer> map = new HashMap<>();
        for (var v : nums) {
            map.merge(v + 1, map.getOrDefault(v, 0) + 1, Integer::max);
            map.merge(v, map.getOrDefault(v - 1, 0) + 1, Integer::max);
        }

        int ans = 0;
        for (var v : map.values()) ans = Math.max(ans, v);
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxSelectedElements(new int[]{8,13,18,10,16,19,11,17,15,18,9,12,15,8,9,14,7}) == 14;

        assert new Solution().maxSelectedElements(new int[]{2,1,5,1,1}) == 3;
        assert new Solution().maxSelectedElements(new int[]{1,4,7,10}) == 1;
        assert new Solution().maxSelectedElements(new int[]{8,10,6,12,9,12,2,3,13,19,11,18,10,16}) == 8;
    }

}
