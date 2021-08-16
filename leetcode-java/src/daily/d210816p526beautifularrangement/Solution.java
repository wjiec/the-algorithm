package daily.d210816p526beautifularrangement;

import java.util.ArrayList;
import java.util.List;

/**
 * 526. Beautiful Arrangement
 *
 * https://leetcode-cn.com/problems/beautiful-arrangement/
 *
 * Suppose you have n integers labeled 1 through n.
 * A permutation of those n integers perm (1-indexed) is considered a beautiful arrangement
 * if for every i (1 <= i <= n), either of the following is true:
 *
 * perm[i] is divisible by i.
 * i is divisible by perm[i].
 *
 * Given an integer n, return the number of the beautiful arrangements that you can construct.
 */

public class Solution {

    private int ans = 0;
    private final boolean[] visited = new boolean[16];
    private final List<List<Integer>> matched = new ArrayList<>();

    public int countArrangement(int n) {
        for (int i = 0; i <= n; i++) matched.add(new ArrayList<>());
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i % j == 0 || j % i == 0) {
                    matched.get(i).add(j);
                }
            }
        }

        backtrack(1, n);
        return ans;
    }

    private void backtrack(int index, int n) {
        if (index == n + 1) {
            ans++;
            return;
        }

        for (var x : matched.get(index)) {
            if (!visited[x]) {
                visited[x] = true;
                backtrack(index + 1, n);
                visited[x] = false;
            }
        }
    }

    public static void main(String[] args) {
        assert new Solution().countArrangement(2) == 2;
    }

}
