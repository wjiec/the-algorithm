package problem.p360sorttransformedarray;

import common.Checker;
import common.PrettyPrinter;

import java.util.Arrays;

/**
 * 360. Sort Transformed Array
 *
 * https://leetcode.cn/problems/sort-transformed-array/
 *
 * Given a sorted integer array nums and three integers a, b and c, apply a quadratic function of
 * the form f(x) = ax2 + bx + c to each element nums[i] in the array, and return the array
 * in a sorted order.
 */

public class Solution {

    // ax^2 + bx + c
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            ans[i] = a * nums[i] * nums[i] + b * nums[i] + c;
        }
        Arrays.sort(ans);

        return ans;
    }

    private static class Mathematics {
        // ax^2 + bx + c
        // 对称轴: -b/2a
        // a > 0: 上开口, a < 0: 下开口
        public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
            int r = 0;
            double mid = -b / (2 * (double) a);
            while (r < nums.length && nums[r] < mid) r++;

            if (a > 0) return topShed(nums, a, b, c, r - 1, r);
            return bottomShed(nums, a, b, c, 0, nums.length - 1);
        }

        private int[] topShed(int[] nums, int a, int b, int c, int l, int r) {
            int n = nums.length, idx = 0;
            int[] ans = new int[n];
            double mid = -b / (2 * (double) a);
            if (r < n && nums[r] == mid) ans[idx++] = calc(a, b, c, nums[r++]);
            while (l >= 0 || r < n) {
                int lv = Integer.MAX_VALUE, rv = Integer.MAX_VALUE;
                if (l >= 0) lv = calc(a, b, c, nums[l]);
                if (r < n) rv = calc(a, b, c, nums[r]);

                if (lv < rv) { ans[idx++] = lv; l--; }
                else { ans[idx++] = rv; r++; }
            }

            return ans;
        }

        private int[] bottomShed(int[] nums, int a, int b, int c, int l, int r) {
            int n = nums.length, idx = 0;
            int[] ans = new int[n];
            while (l <= r) {
                int lv = calc(a, b, c, nums[l]);
                int rv = calc(a, b, c, nums[r]);

                if (lv < rv) { ans[idx++] = lv; l++; }
                else { ans[idx++] = rv; r--; }
            }
            return ans;
        }

        private int calc(int a, int b, int c, int x) { return a * x * x + b * x + c; }
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().sortTransformedArray(new int[]{-4,-2,2,4}, 1, 3, 5), new int[]{3,9,15,33});
        assert Checker.check(new Solution().sortTransformedArray(new int[]{-4,-2,2,4}, -1, 3, 5), new int[]{-23,-5,1,7});

        PrettyPrinter.println(new Mathematics().sortTransformedArray(new int[]{
            -98,-97,-93,-90,-89,-89,-86,-84,-82,-81,-78,-78,-73,-70,-68,
            -68,-67,-66,-63,-62,-61,-60,-59,-54,-54,-53,-50,-50,-48,-48,
            -47,-43,-43,-42,-42,-37,-33,-30,-28,-23,-21,-21,-20,-19,-19,
            -17,-17,-9,-7,-4,-3,-3,-2,0,0,7,8,11,11,20,21,25,27,30,33,33,
            36,40,40,41,49,50,50,52,54,61,64,65,65,67,69,72,73,74,74,76,78,
            79,81,83,84,84,85,85,86,88,89,89,93,97
        }, 4, -64, 25));
        assert Checker.check(new Mathematics().sortTransformedArray(new int[]{-4,-2,2,4}, 1, 3, 5), new int[]{3,9,15,33});
        assert Checker.check(new Mathematics().sortTransformedArray(new int[]{-4,-2,2,4}, -1, 3, 5), new int[]{-23,-5,1,7});
    }

}
