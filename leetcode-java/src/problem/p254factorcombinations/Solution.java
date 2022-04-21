package problem.p254factorcombinations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 254. Factor Combinations
 *
 * https://leetcode-cn.com/problems/factor-combinations/
 *
 * Numbers can be regarded as the product of their factors.
 *
 * For example, 8 = 2 x 2 x 2 = 2 x 4.
 * Given an integer n, return all possible combinations of its factors.
 * You may return the answer in any order.
 *
 * Note that the factors should be in the range [2, n - 1].
 */

public class Solution {

    private List<Integer> curr = new ArrayList<>();
    private List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> getFactors(int n) {
        if (n == 1) return Collections.emptyList();
        dfs(n, 2);
        return ans;
    }

    private void dfs(int n, int v) {
        if (!curr.isEmpty()) {
            curr.add(n);
            ans.add(new ArrayList<>(curr));
            curr.remove(curr.size() - 1);
        }

        int sqrt = (int) Math.sqrt(n);
        for (int i = v; i <= sqrt; i++) {
            if (n % i == 0) {
                curr.add(i);
                dfs(n / i, i);
                curr.remove(curr.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().getFactors(1));
        System.out.println(new Solution().getFactors(12));
        System.out.println(new Solution().getFactors(32));
        System.out.println(new Solution().getFactors(37));
        System.out.println(new Solution().getFactors(9999));
        System.out.println(new Solution().getFactors(10000));
    }

}
