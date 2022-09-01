package problem.p1718constructthelexicographicallylargestvalidsequence;

import common.Checker;

/**
 * 1718. Construct the Lexicographically Largest Valid Sequence
 *
 * https://leetcode.cn/problems/construct-the-lexicographically-largest-valid-sequence/
 *
 * Given an integer n, find a sequence that satisfies all of the following:
 *
 * The integer 1 occurs once in the sequence.
 * Each integer between 2 and n occurs twice in the sequence.
 * For every integer i between 2 and n, the distance between the two occurrences of i is exactly i.
 * The distance between two numbers on the sequence, a[i] and a[j], is the
 * absolute difference of their indices, |j - i|.
 *
 * Return the lexicographically largest sequence. It is guaranteed that under the given
 * constraints, there is always a solution.
 *
 * A sequence a is lexicographically larger than a sequence b (of the same length) if
 * in the first position where a and b differ, sequence a has a number greater than
 * the corresponding number in b. For example, [0,1,9,0] is lexicographically larger
 * than [0,1,5,6] because the first position they differ is at the third number, and 9 is
 * greater than 5.
 */

public class Solution {

    public int[] constructDistancedSequence(int n) {
        int[] ans = new int[2 * n - 1];
        dfs(ans, 0, new boolean[n + 1], n, 0);
        return ans;
    }

    private boolean dfs(int[] ans, int idx, boolean[] visited, int n, int c) {
        if (c == n) return true;
        while (ans[idx] != 0) idx++;

        for (int v = n; v > 0; v--) {
            int ndx = idx + (v == 1 ? 0 : v);
            if (!visited[v] && ndx < ans.length &&  ans[idx] == 0 && ans[ndx] == 0) {
                ans[idx] = ans[ndx] = v; visited[v] = true;
                if (dfs(ans, idx + 1, visited, n, c + 1)) {
                    return true;
                }
                ans[idx] = ans[ndx] = 0; visited[v] = false;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().constructDistancedSequence(3), new int[]{3,1,2,3,2});
        assert Checker.check(new Solution().constructDistancedSequence(5), new int[]{5,3,1,4,3,5,2,4,2});
    }

}
