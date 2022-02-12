package problem.p77combinations;

import java.util.ArrayList;
import java.util.List;

/**
 * 77. Combinations
 *
 * https://leetcode-cn.com/problems/combinations/
 *
 * Given two integers n and k, return all possible combinations of k numbers out of the range [1, n].
 *
 * You may return the answer in any order.
 */

public class Solution {

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(new boolean[n + 1], ans, new ArrayList<>(), k, 1);
        return ans;
    }

    private void dfs(boolean[] visited, List<List<Integer>> ans, List<Integer> curr, int k, int i) {
        if (curr.size() == k) {
            ans.add(new ArrayList<>(curr));
            return;
        }

        for (; i < visited.length; i++) {
            if (!visited[i]) {
                visited[i] = true; curr.add(i);
                dfs(visited, ans, curr, k, i);
                visited[i] = false; curr.remove(curr.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().combine(4, 2));
        System.out.println(new Solution().combine(1, 1));
    }

}
