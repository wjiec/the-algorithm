package problem.p216combinationsumiii;

import java.util.ArrayList;
import java.util.List;

/**
 * 216. Combination Sum III
 *
 * https://leetcode-cn.com/problems/combination-sum-iii/
 *
 * Find all valid combinations of k numbers that sum up to n such that the following conditions are true:
 *
 * Only numbers 1 through 9 are used.
 * Each number is used at most once.
 * Return a list of all possible valid combinations. The list must not contain the same combination twice,
 * and the combinations may be returned in any order.
 */

public class Solution {

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(1, 0, k, n, ans, new ArrayList<>());
        return ans;
    }

    private void dfs(int i, int sum, int k, int n, List<List<Integer>> ans, List<Integer> curr) {
        if (sum == n && curr.size() == k) {
            ans.add(new ArrayList<>(curr));
            return;
        }
        if (curr.size() > k) return;

        for (; i < 10; i++) {
            if (sum + i > n) break;

            curr.add(i);
            dfs(i + 1, sum + i, k, n, ans, curr);
            curr.remove(curr.size() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().combinationSum3(3, 7));
        System.out.println(new Solution().combinationSum3(3, 9));
        System.out.println(new Solution().combinationSum3(4, 1));
    }

}
