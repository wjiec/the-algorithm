package weekly.w446.C;

import common.Checker;
import common.PrettyPrinter;

import java.util.ArrayList;
import java.util.List;

/**
 * 3524. Find X Value of Array I
 *
 * https://leetcode.cn/contest/weekly-contest-446/problems/find-x-value-of-array-i/
 *
 * You are given an array of positive integers nums, and a positive integer k.
 *
 * You are allowed to perform an operation once on nums, where in each operation
 * you can remove any non-overlapping prefix and suffix from nums such that nums remains non-empty.
 *
 * You need to find the x-value of nums, which is the number of ways to perform this
 * operation so that the product of the remaining elements leaves a remainder of x when divided by k.
 *
 * Return an array result of size k where result[x] is the x-value of nums for 0 <= x <= k - 1.
 *
 * A prefix of an array is a subarray that starts from the beginning of the array and extends to any point within it.
 *
 * A suffix of an array is a subarray that starts at any point within the array and extends to the end of the array.
 *
 * Note that the prefix and suffix to be chosen for the operation can be empty.
 */

public class Solution {

    // 移除任意的前缀和后缀 -> 也就是找到一个子数组
    //  - ans[x] 表示子数组的乘积 % k == x 的数量
    public long[] resultArray(int[] nums, int k) {
        int n = nums.length;
        // 如果 k == 1, 所有的子数组都满足
        //  = [0, n - 1] + [1, n - 1] + ... + [n - 1, n - 1]
        //  = n + (n - 1) + ... + 1
        if (k == 1) return new long[]{n * (1L + n) / 2L};

        // v = (a * b * ... * k) % k = (a % k * b % k * ... * n % k) % k
        //  - (1 * 2 * 3) % 3 == ((1 % 3) * (2 % 3)) % 3 = 2
        //
        // 处理之后就会变成 a, b, 0, c, 0, b, c, a, 0, ... 之类中间带 0 的形式
        //  - 对于 x == 0, 我们只需要包含任意一个 0 即可
        //  - 对于 0 < x < k, 我们只能在任意两个 0 之间找子数组
        for (int i = 0; i < n; i++) nums[i] %= k;

        List<Integer> zeros = new ArrayList<>();
        for (int i = 0; i < n; i++) if (nums[i] == 0) zeros.add(i);
        zeros.add(n);

        long[] ans = new long[k];
        // 对于任意一个 nums[i] = 0, 都有 [0...i, i] + [i, i+1...n-1]
        for (int i = 0; i < zeros.size() - 1; i++) {
            int curr = zeros.get(i), next = zeros.get(i + 1);
            ans[0] += n + (next - curr);
        }

        PrettyPrinter.println(ans);
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().resultArray(new int[]{1,2,3,4,5}, 3), new int[]{9,2,4});
        assert Checker.check(new Solution().resultArray(new int[]{1,2,4,8,16,32}, 4), new int[]{18,1,2,0});
        assert Checker.check(new Solution().resultArray(new int[]{1,1,2,1,1}, 2), new int[]{9,6});
    }

}
