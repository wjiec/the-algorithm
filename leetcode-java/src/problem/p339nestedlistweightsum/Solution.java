package problem.p339nestedlistweightsum;

import common.NestedInteger;

import java.util.List;

/**
 * 339. Nested List Weight Sum
 *
 * https://leetcode.cn/problems/nested-list-weight-sum/
 *
 * You are given a nested list of integers nestedList. Each element is either an integer
 * or a list whose elements may also be integers or other lists.
 *
 * The depth of an integer is the number of lists that it is inside of.
 * For example, the nested list [1,[2,2],[[3],2],1] has each integer's value set to its depth.
 *
 * Return the sum of each integer in nestedList multiplied by its depth.
 */

public class Solution {

    public int depthSum(List<NestedInteger> nestedList) {
        return depthSum(nestedList, 1);
    }

    private int depthSum(List<NestedInteger> nis, int depth) {
        int ans = 0;
        for (var ni : nis) {
            if (ni.isInteger()) ans += depth * ni.getInteger();
            else ans += depthSum(ni.getList(), depth + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().depthSum(List.of(new NestedInteger())) == 1;
    }

}
