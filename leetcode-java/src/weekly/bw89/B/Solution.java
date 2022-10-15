package weekly.bw89.B;

/**
 * 6209. Range Product Queries of Powers
 *
 * https://leetcode.cn/contest/biweekly-contest-89/problems/range-product-queries-of-powers/
 *
 * Given a positive integer n, there exists a 0-indexed array called powers, composed of the
 * minimum number of powers of 2 that sum to n. The array is sorted in non-decreasing order, and
 * there is only one way to form the array.
 *
 * You are also given a 0-indexed 2D integer array queries, where queries[i] = [lefti, righti].
 * Each queries[i] represents a query where you have to find the
 * product of all powers[j] with lefti <= j <= righti.
 *
 * Return an array answers, equal in length to queries, where answers[i] is the answer
 * to the ith query. Since the answer to the ith query may be too large, each answers[i]
 * should be returned modulo 109 + 7.
 */

public class Solution {

    public int[] productQueries(int n, int[][] queries) {
        int[] bits = new int[32];
        for (int i = 0; i < 32; i++) {
            bits[i] = n & (1 << i);
        }

        int[] powers = new int[32]; int len = 0;
        for (int i = 0; i < bits.length; i++) {
            if (bits[i] != 0) powers[len++] = 1 << i;
        }

        int MOD = 1_000_000_007;
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            long curr = 1;
            for (int j = queries[i][0]; j <= queries[i][1]; j++) {
                curr = (curr * powers[j]) % MOD;
            }
            ans[i] = (int) curr;
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
