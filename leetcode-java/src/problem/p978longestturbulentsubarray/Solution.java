package problem.p978longestturbulentsubarray;

/**
 * 978. Longest Turbulent Subarray
 *
 * https://leetcode.cn/problems/longest-turbulent-subarray/
 *
 * Given an integer array arr, return the length of a maximum size turbulent subarray of arr.
 *
 * A subarray is turbulent if the comparison sign flips between each adjacent pair of elements in the subarray.
 *
 * More formally, a subarray [arr[i], arr[i + 1], ..., arr[j]] of arr is said to be turbulent if and only if:
 *
 * For i <= k < j:
 * arr[k] > arr[k + 1] when k is odd, and
 * arr[k] < arr[k + 1] when k is even.
 *
 * Or, for i <= k < j:
 * arr[k] > arr[k + 1] when k is even, and
 * arr[k] < arr[k + 1] when k is odd.
 */

public class Solution {

    public int maxTurbulenceSize(int[] arr) {
        if (arr.length == 1) return 1;

        int ans = 0, curr = 0, prev = 0;
        for (int i = 1; i < arr.length; i++) {
            int cmp = Integer.compare(arr[i], arr[i - 1]);
            if (cmp == 0 || cmp != -prev) curr = cmp == 0 ? 1 : 2;
            else curr++;

            prev = cmp;
            if (curr > ans) ans = curr;
        }

        return ans;
    }

    private static class DynamicProgramming {
        public int maxTurbulenceSize(int[] arr) {
            int[] big = new int[arr.length]; big[0] = 1;
            int[] small = new int[arr.length]; small[0] = 1;

            for (int i = 1; i < arr.length; i++) {
                if (arr[i] > arr[i - 1]) {
                    big[i] = small[i - 1] + 1;
                    small[i] = 1;
                } else if (arr[i] < arr[i - 1]) {
                    small[i] = big[i - 1] + 1;
                    big[i] = 1;
                } else big[i] = small[i] = 1;
            }

            int ans = 0;
            for (int i = 0; i < arr.length; i++) {
                if (big[i] > ans) ans = big[i];
                if (small[i] > ans) ans = small[i];
            }

            return ans;
        }
    }

    public static void main(String[] args) {
        assert new Solution().maxTurbulenceSize(new int[]{9,9}) == 1;

        // 9 > 4 > 2 < 10 > 7 < 8 = 8 > 1 < 9
        assert new Solution().maxTurbulenceSize(new int[]{9,4,2,10,7,8,8,1,9}) == 5;
        assert new Solution().maxTurbulenceSize(new int[]{4,8,12,16}) == 2;
        assert new Solution().maxTurbulenceSize(new int[]{100}) == 1;
        assert new Solution().maxTurbulenceSize(new int[]{1,2,3,4,5,6,7}) == 2;
        assert new Solution().maxTurbulenceSize(new int[]{9,9,8}) == 2;
        assert new Solution().maxTurbulenceSize(new int[]{9,9,8,9}) == 3;


        assert new DynamicProgramming().maxTurbulenceSize(new int[]{9,9}) == 1;
        assert new DynamicProgramming().maxTurbulenceSize(new int[]{9,4,2,10,7,8,8,1,9}) == 5;
        assert new DynamicProgramming().maxTurbulenceSize(new int[]{4,8,12,16}) == 2;
        assert new DynamicProgramming().maxTurbulenceSize(new int[]{100}) == 1;
        assert new DynamicProgramming().maxTurbulenceSize(new int[]{1,2,3,4,5,6,7}) == 2;
        assert new DynamicProgramming().maxTurbulenceSize(new int[]{9,9,8}) == 2;
        assert new DynamicProgramming().maxTurbulenceSize(new int[]{9,9,8,9}) == 3;
    }

}
