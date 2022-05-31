package problem.p923a3sumwithmultiplicity;

import java.util.Arrays;

/**
 * 923. 3Sum With Multiplicity
 *
 * https://leetcode.cn/problems/3sum-with-multiplicity/
 *
 * Given an integer array arr, and an integer target, return the number of tuples i, j, k
 * such that i < j < k and arr[i] + arr[j] + arr[k] == target.
 *
 * As the answer can be very large, return it modulo 109 + 7.
 */

public class Solution {

    public int threeSumMulti(int[] arr, int target) {
        int ans = 0, MOD = 1_000_000_007;
        Arrays.sort(arr);

        for (int i = 0, n = arr.length; i < n; i++) {
            int remain = target - arr[i], l = i + 1, r = n - 1;
            while (l < r) {
                if (arr[l] + arr[r] < remain) l++;
                else if (arr[l] + arr[r] > remain) r--;
                else if (arr[l] != arr[r]) {
                    int lc = 1, rc = 1;
                    while (l + 1 < r && arr[l] == arr[l + 1]) { lc++; l++; }
                    while (r - 1 > l && arr[r] == arr[r - 1]) { rc++; r--; }

                    ans = (ans + lc * rc) % MOD;
                    l++; r--;
                } else {
                    ans = (ans + ((r - l + 1) * (r - l) / 2)) % MOD;
                    break;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().threeSumMulti(new int[]{1,1,2,2,3,3,4,4,5,5}, 8) == 20;
        assert new Solution().threeSumMulti(new int[]{1,1,2,2,2,2}, 5) == 12;
        assert new Solution().threeSumMulti(new int[]{2,1,3}, 6) == 1;
    }

}
