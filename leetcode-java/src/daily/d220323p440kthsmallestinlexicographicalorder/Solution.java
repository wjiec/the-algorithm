package daily.d220323p440kthsmallestinlexicographicalorder;

import common.TODO;

/**
 * 440. K-th Smallest in Lexicographical Order
 *
 * https://leetcode-cn.com/problems/k-th-smallest-in-lexicographical-order/
 *
 * Given two integers n and k, return the kth lexicographically smallest integer in the range [1, n].
 */

public class Solution {

    @TODO(url = "https://leetcode-cn.com/problems/k-th-smallest-in-lexicographical-order/solution/zi-dian-xu-de-di-kxiao-shu-zi-by-leetcod-bfy0/")
    public int findKthNumber(int n, int k) {
        int curr = 1;
        for (k--; k > 0; ) {
            int steps = getSteps(curr, n);
            if (steps <= k) {
                k -= steps;
                curr++;
            } else {
                curr = curr * 10;
                k--;
            }
        }
        return curr;
    }

    private int getSteps(int curr, long n) {
        int steps = 0;
        for (long first = curr, last = curr; first <= n; ) {
            steps += Math.min(n, last) - first + 1;
            first *= 10;
            last = last * 10 + 9;
        }
        return steps;
    }

    public static void main(String[] args) {
        assert new Solution().findKthNumber(13, 2) == 10;
        assert new Solution().findKthNumber(1, 1) == 1;
    }

}
