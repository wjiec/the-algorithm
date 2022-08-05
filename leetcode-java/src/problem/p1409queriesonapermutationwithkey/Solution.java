package problem.p1409queriesonapermutationwithkey;

import common.Checker;

/**
 * 1409. Queries on a Permutation With Key
 *
 * https://leetcode.cn/problems/queries-on-a-permutation-with-key/
 *
 * Given the array queries of positive integers between 1 and m, you have to process all queries[i]
 * (from i=0 to i=queries.length-1) according to the following rules:
 *
 * In the beginning, you have the permutation P=[1,2,3,...,m].
 * For the current i, find the position of queries[i] in the permutation P (indexing from 0) and
 * then move this at the beginning of the permutation P. Notice that the position of queries[i]
 * in P is the result for queries[i].
 *
 * Return an array containing the result for the given queries.
 */

public class Solution {

    private static class BIT {
        private final int n;
        private final int[] arr;
        public BIT(int n) { this.n = n; arr = new int[n + 1]; }
        private static int low(int x) { return x & (-x); }
        public void update(int x, int d) { for (; x <= n; x += low(x)) arr[x] += d; }
        public int query(int x) {
            int ans = 0;
            for (; x != 0; x -= low(x)) ans += arr[x];
            return ans;
        }
    }

    public int[] processQueries(int[] queries, int m) {
        int n = queries.length;
        BIT bit = new BIT(m + n);

        int[] pos = new int[m + 1];
        for (int i = 1; i <= m; ++i) {
            pos[i] = n + i;
            bit.update(n + i, 1);
        }

        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            int curr = pos[queries[i]];
            bit.update(curr, -1);
            ans[i] = bit.query(curr);
            curr = n - i;
            pos[queries[i]] = curr;
            bit.update(curr, 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().processQueries(new int[]{3,1,2,1}, 5), new int[]{2,1,2,1});
        assert Checker.check(new Solution().processQueries(new int[]{4,1,2,2}, 4), new int[]{3,1,2,0});
        assert Checker.check(new Solution().processQueries(new int[]{7,5,5,8,3}, 8), new int[]{6,5,0,7,5});
    }

}
