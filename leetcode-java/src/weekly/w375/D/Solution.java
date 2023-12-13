package weekly.w375.D;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 2963. Count the Number of Good Partitions
 *
 * https://leetcode.cn/contest/weekly-contest-375/problems/count-the-number-of-good-partitions/
 *
 * You are given a 0-indexed array nums consisting of positive integers.
 *
 * A partition of an array into one or more contiguous subarrays is called
 * good if no two subarrays contain the same number.
 *
 * Return the total number of good partitions of nums.
 *
 * Since the answer may be large, return it modulo 109 + 7.
 */

public class Solution {

    // 相同的数字一定要分为一组
    public int numberOfGoodPartitions(int[] nums) {
        Map<Integer, int[]> ranges = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            final int ci = i;
            var range = ranges.computeIfAbsent(nums[i], v -> new int[]{ci, ci});
            range[1] = ci;
        }

        List<int[]> sorted = new ArrayList<>(ranges.values());
        sorted.sort((a, b) -> {
            if (a[0] == b[0]) return b[1] - a[1];
            return a[0] - b[0];
        });

        List<int[]> segments = new ArrayList<>();
        for (int i = 0, l = -1, r = -1; i < sorted.size(); i++) {
            if (sorted.get(i)[0] > r) {
                if (l != -1) segments.add(new int[]{l, r});
                l = sorted.get(i)[0];
            }
            r = Math.max(r, sorted.get(i)[1]);
        }

        return pow(2, segments.size(), 1_000_000_007);
    }

    private int pow(long a, long b, long m) {
        long ans = 1;
        while (b > 0) {
            if ((b & 1) != 0) {
                ans = (ans * a) % m;
            }

            a = (a * a) % m;
            b >>= 1;
        }
        return (int) (ans % m);
    }

    public static void main(String[] args) {
        assert new Solution().numberOfGoodPartitions(new int[]{1,6,8,1,5}) == 2;
        assert new Solution().numberOfGoodPartitions(new int[]{1, 2, 3, 4}) == 8;
        assert new Solution().numberOfGoodPartitions(new int[]{1, 1, 1, 1}) == 1;
        assert new Solution().numberOfGoodPartitions(new int[]{1, 2, 1, 3}) == 2;
    }

}
