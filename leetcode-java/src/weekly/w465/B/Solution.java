package weekly.w465.B;

import common.Checker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Q2. Balanced K-Factor Decomposition
 *
 * https://leetcode.cn/contest/weekly-contest-465/problems/balanced-k-factor-decomposition/
 *
 * Given two integers n and k, split the number n into exactly k positive integers
 * such that the product of these integers is equal to n.
 *
 * Return any one split in which the maximum difference between any two numbers is minimized.
 *
 * You may return the result in any order.
 */

public class Solution {

    private static final int MAX_N = 100_000;
    private static final boolean[] isPrime = new boolean[MAX_N + 1];
    private static final List<Integer> primes = new ArrayList<>();
    static {
        Arrays.fill(isPrime, true); isPrime[0] = isPrime[1] = false;
        for (int i = 2; i <= MAX_N; i++) {
            if (!isPrime[i]) continue; else primes.add(i);
            for (long j = (long) i * i; j <= MAX_N; j += i) isPrime[(int) j] = false;
        }
    }

    public int[] minDifference(int n, int k) {
        // 将 n 分解为 k 个数相乘, 最小化这 k 个数的最大差值
        //  - 将其进行质因数分解, 然后再组合直到剩下 k 个
        List<Integer> factors = new ArrayList<>();
        for (var p : primes) {
            if (p > n) break;
            while (n % p == 0) { factors.add(p); n /= p; }
        }

        if (factors.size() <= k) {
            int[] ans = new int[k]; int i = 0;
            for (var v : factors) ans[i++] = v;
            while (i < k) ans[i++] = 1;
            return ans;
        }

        // 现在问题变为给你一个数组, 将其两两组合乘起来, 使得最后
        // 剩下 k 个数时的最大差值最小化

        minDiff(factors, factors.size() - k);

        int[] ans = new int[k]; int i = 0;
        for (var v : this.ans) ans[i++] = v;
        return ans;
    }

    private int currMinDiff = Integer.MAX_VALUE;
    private final List<Integer> ans = new ArrayList<>();

    private void minDiff(List<Integer> nums, int k) {
        if (k == 0) {
            int curr = nums.get(nums.size() - 1) - nums.get(0);
            if (curr < currMinDiff) {
                currMinDiff = curr;
                ans.clear();
                ans.addAll(nums);
            }
            return;
        }

        for (int i = 0; i < nums.size(); i++) {
            if (i > 0 && nums.get(i).equals(nums.get(i - 1))) continue;
            for (int j = i + 1; j < nums.size(); j++) {
                if (j > i + 1 && nums.get(j).equals(nums.get(j - 1))) continue;

                List<Integer> copy = new ArrayList<>();
                for (int x = 0; x < nums.size(); x++) {
                    if (x != i && x != j) copy.add(nums.get(x));
                }
                copy.add(nums.get(i) * nums.get(j));
                copy.sort(Integer::compare);
                minDiff(copy, k - 1);
            }
        }
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().minDifference(1492, 3), new int[]{2,2,373});

        assert Checker.check(new Solution().minDifference(18, 5), new int[]{2,3,3,1,1});
        assert Checker.check(new Solution().minDifference(100_000, 5), new int[]{10, 10, 10, 10, 10});
        assert Checker.check(new Solution().minDifference(100, 2), new int[]{10, 10});
        assert Checker.check(new Solution().minDifference(44, 3), new int[]{2,2,11});
    }

}
