package problem.p397integerreplacement;

import java.util.HashMap;
import java.util.Map;

/**
 * 397. Integer Replacement
 *
 * https://leetcode-cn.com/problems/integer-replacement/
 *
 * Given a positive integer n, you can apply one of the following operations:
 *
 * If n is even, replace n with n / 2.
 * If n is odd, replace n with either n + 1 or n - 1.
 * Return the minimum number of operations needed for n to become 1.
 */

public class Solution {

    private int ans = Integer.MAX_VALUE;
    private final Map<Integer, Integer> memos = new HashMap<>();

    public int integerReplacement(int n) {
//        dfs(n, 0);
//        return ans;

        if (n == 1) return 0;
        if (!memos.containsKey(n)) {
            if (n % 2 == 0) {
                memos.put(n, 1 + integerReplacement(n / 2));
            } else {
                memos.put(n, 2 + Math.min(integerReplacement(n / 2), integerReplacement(n / 2 + 1)));
            }
        }
        return memos.get(n);
    }

    private void dfs(long n, int c) {
        if (n == 1) {
            ans = Math.min(ans, c);
            return;
        } else if (c >= ans) return;

        if (n % 2 == 0) dfs(n / 2, c + 1);
        else {
            dfs(n + 1, c + 1);
            dfs(n - 1, c + 1);
        }
    }

    public static void main(String[] args) {
        assert new Solution().integerReplacement(8) == 3;
        assert new Solution().integerReplacement(7) == 4;
        assert new Solution().integerReplacement(4) == 2;
        assert new Solution().integerReplacement(2147483647) == 32;
    }

}
