package problem.p60permutationsequence;

/**
 * 60. Permutation Sequence
 *
 * https://leetcode.cn/problems/permutation-sequence/
 *
 * The set [1, 2, 3, ..., n] contains a total of n! unique permutations.
 *
 * By listing and labeling all of the permutations in order, we get the following sequence for n = 3:
 *
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 *
 * Given n and k, return the kth permutation sequence.
 */

public class Solution {

    public String getPermutation(int n, int k) {
        return getPermutation(new boolean[n + 1], n, k);
    }

    private String getPermutation(boolean[] visited, int r, int k) {
        if (r == 1) return String.valueOf(select(visited, k));
        if (k == 0) return select(visited, r) + getPermutation(visited, r - 1, k);

        int f = factorial(r - 1);
        int c = select(visited, (k + f - 1) / f);
        visited[c] = true;
        return c + getPermutation(visited, r - 1, k % f);
    }

    private int select(boolean[] visited, int k) {
        for (int i = 1; i < visited.length; i++) {
            if (!visited[i] && --k <= 0) {
                return i;
            }
        }
        return -1;
    }

    private int factorial(int n) {
        int ans = 1;
        for (int i = 2; i <= n; i++) ans *= i;
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().getPermutation(3, 2).equals("132");

        assert new Solution().getPermutation(3, 3).equals("213");
        assert new Solution().getPermutation(4, 9).equals("2314");
        assert new Solution().getPermutation(3, 1).equals("123");
    }

}
