package problem.p364nestedlistweightsumii;

import common.NestedInteger;

import java.util.List;

/**
 * 364. Nested List Weight Sum II
 *
 * https://leetcode.cn/problems/nested-list-weight-sum-ii/
 *
 * You are given a nested list of integers nestedList. Each element is either an integer
 * or a list whose elements may also be integers or other lists.
 *
 * The depth of an integer is the number of lists that it is inside of. For example, the
 * nested list [1,[2,2],[[3],2],1] has each integer's value set to its depth.
 *
 * Let maxDepth be the maximum depth of any integer.
 *
 * The weight of an integer is maxDepth - (the depth of the integer) + 1.
 *
 * Return the sum of each integer in nestedList multiplied by its weight.
 */

public class Solution {

    private int maxDepth = 0;

    public int depthSumInverse(List<NestedInteger> nestedList) {
        dfs(nestedList, 1);
        return sum(nestedList, 1);
    }

    private void dfs(List<NestedInteger> nis, int depth) {
        if (!nis.isEmpty()) maxDepth = Math.max(maxDepth, depth);
        for (var ni : nis) {
            if (!ni.isInteger()) {
                dfs(ni.getList(), depth + 1);
            }
        }
    }

    private int sum(List<NestedInteger> nis, int depth) {
        int ans = 0;
        for (var ni : nis) {
            if (ni.isInteger()) ans += ni.getInteger() * (maxDepth - depth + 1);
            else ans += sum(ni.getList(), depth + 1);
        }
        return ans;
    }

    // [[1,1],2,[1,1],[[[[]]]]]
    // 12   2   2   2 234554321
    //   4,4, 10,4,4
    public static void main(String[] args) {
        assert new Solution().depthSumInverse(List.of()) == 0;
    }

}
