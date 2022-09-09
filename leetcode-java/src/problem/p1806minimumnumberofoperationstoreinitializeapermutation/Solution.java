package problem.p1806minimumnumberofoperationstoreinitializeapermutation;

import common.TODO;
import common.Tag;

/**
 * 1806. Minimum Number of Operations to Reinitialize a Permutation
 *
 * https://leetcode.cn/problems/minimum-number-of-operations-to-reinitialize-a-permutation/
 *
 * You are given an even integer n. You initially have a permutation perm of size n
 * where perm[i] == i (0-indexed).
 *
 * In one operation, you will create a new array arr, and for each i:
 *
 * If i % 2 == 0, then arr[i] = perm[i / 2].
 * If i % 2 == 1, then arr[i] = perm[n / 2 + (i - 1) / 2].
 * You will then assign arr to perm.
 *
 * Return the minimum non-zero number of operations you need to perform on perm to
 * return the permutation to its initial value.
 */

public class Solution {

    @Tag({"滚动到原样", "数学变换"})
    @TODO(url = "/solution/shu-xue-on-suan-fa-by-arsenal-591-xatz/")
    public int reinitializePermutation(int n) {
        int[] perm = new int[n], next = new int[n];
        for (int i = 0; i < n; i++) perm[i] = i;

        int ans = 0;
        while (++ans > 0) {
            boolean initial = true;
            for (int i = 0; i < n; i++) {
                if (i % 2 == 0) next[i] = perm[i / 2];
                else next[i] = perm[n / 2 + (i - 1) / 2];
                if (next[i] != i) initial = false;
            }
            if (initial) break;
            int[] t = perm; perm = next; next = t;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().reinitializePermutation(2) == 1;
        assert new Solution().reinitializePermutation(4) == 2;
        assert new Solution().reinitializePermutation(6) == 4;
    }

}
