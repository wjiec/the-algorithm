package problem.p985sumofevennumbersafterqueries;

import common.Checker;

/**
 * 985. Sum of Even Numbers After Queries
 *
 * https://leetcode-cn.com/problems/sum-of-even-numbers-after-queries/
 *
 * We have an array nums of integers, and an array queries of queries.
 *
 * For the i-th query val = queries[i][0], index = queries[i][1], we add val to nums[index].
 * Then, the answer to the i-th query is the sum of the even values of A.
 *
 * (Here, the given index = queries[i][1] is a 0-based index, and each query permanently modifies the array nums.)
 *
 * Return the answer to all queries.  Your answer array should have answer[i] as the answer to the i-th query.
 */

public class Solution {

    public int[] sumEvenAfterQueries(int[] nums, int[][] queries) {
        int sum = 0, l = queries.length;
        for (var n : nums) if (n % 2 == 0) sum += n;

        int[] ans = new int[l];
        for (int i = 0; i < l; i++) {
            int v = queries[i][0], j = queries[i][1];
            if (nums[j] % 2 == 0) sum -= nums[j];
            nums[j] += v;
            if (nums[j] % 2 == 0) sum += nums[j];

            ans[i] = sum;
        }
        return ans;
    }

    public int[] sumEvenAfterQueries1(int[] nums, int[][] queries) {
        int[] ans = new int[queries.length];
        for (int i = 0, l = queries.length; i < l; i++) {
            nums[queries[i][1]] += queries[i][0];
            for (var n : nums) ans[i] += n % 2 == 0 ? n : 0;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().sumEvenAfterQueries(new int[]{1,2,3,4}, new int[][]{
            {1,0}, {-3,1}, {-4,0}, {2,3}
        }), new int[]{8,6,2,4});
    }

}
