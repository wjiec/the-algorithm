package weekly.w340.C;

import java.util.Arrays;
import java.util.TreeMap;

/**
 * 2616. Minimize the Maximum Difference of Pairs
 *
 * https://leetcode.cn/contest/weekly-contest-340/problems/minimize-the-maximum-difference-of-pairs/
 *
 * You are given a 0-indexed integer array nums and an integer p. Find p pairs of indices of nums
 * such that the maximum difference amongst all the pairs is minimized. Also, ensure no index
 * appears more than once amongst the p pairs.
 *
 * Note that for a pair of elements at the index i and j, the difference of this
 * pair is |nums[i] - nums[j]|, where |x| represents the absolute value of x.
 *
 * Return the minimum maximum difference among all p pairs.
 * We define the maximum of an empty set to be zero.
 */

public class Solution {

    public int minimizeMax(int[] nums, int p) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (var v : nums) map.merge(v, 1, Integer::sum);
        int ans = 0, l = 0, r = map.lastKey() - map.firstKey() + 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (check(map, p, mid)) {
                ans = mid; r = mid;
            } else l = mid + 1;
        }
        return ans;
    }

    // 从 nums 中找出 p 对数字，每对数字的差值 <= max
    private boolean check(TreeMap<Integer, Integer> map, int p, int max) {
        TreeMap<Integer, Integer> curr = new TreeMap<>(map);
        while (p > 0 && !curr.isEmpty()) {
            int x = curr.firstKey();
            curr.merge(x, -1, (a, b) -> a + b == 0 ? null : a + b);

            Integer y = curr.ceilingKey(x);
            if (y == null || y - x > max) continue;
            curr.merge(y, -1, (a, b) -> a + b == 0 ? null : a + b);
            p--;
        }
        return p == 0;
    }

    private static class Binary {
        public int minimizeMax(int[] nums, int p) {
            Arrays.sort(nums);
            int n = nums.length, l = -1, r = nums[n - 1] - nums[0];
            while (l + 1 < r) {
                int mid = l + (r - l) / 2, cnt = 0;
                for (int i = 1; i < n && cnt < p; i++) {
                    if (nums[i] - nums[i - 1] <= mid) {
                        cnt++; i++;
                    }
                }
                if (cnt >= p) r = mid; else l = mid;
            }
            return r;
        }
    }

    public static void main(String[] args) {
        assert new Solution().minimizeMax(new int[]{10,1,2,7,1,3}, 2) == 1;
        assert new Solution().minimizeMax(new int[]{4,2,1,2}, 1) == 0;

        assert new Binary().minimizeMax(new int[]{10,1,2,7,1,3}, 2) == 1;
        assert new Binary().minimizeMax(new int[]{4,2,1,2}, 1) == 0;
    }

}
