package daily.d210512p1310xorqueriesofasubarray;

import common.Checker;

/**
 * 1310. XOR Queries of a Subarray
 *
 * https://leetcode-cn.com/problems/xor-queries-of-a-subarray/
 *
 * Given the array arr of positive integers and the array queries where queries[i] = [Li, Ri],
 * for each query i compute the XOR of elements from Li to Ri (that is, arr[Li] xor arr[Li+1] xor ... xor arr[Ri] ).
 * Return an array containing the result for the given queries.
 */

public class Solution {

    public int[] xorQueries(int[] arr, int[][] queries) {
        int[] xors = new int[arr.length + 1];
        for (int i = 0; i < arr.length; i++) {
            xors[i + 1] = xors[i] ^ arr[i];
        }

        int[] rs = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            rs[i] = xors[queries[i][0]] ^ xors[queries[i][1] + 1];
        }
        return rs;
    }

    public int[] xorQueries1(int[] arr, int[][] queries) {
        int[] rs = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            rs[i] = arr[queries[i][0]];
            for (int j = queries[i][0] + 1; j <= queries[i][1]; j++) {
                rs[i] ^= arr[j];
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().xorQueries(new int[]{1,3,4,8}, new int[][]{new int[]{0,1},
            new int[]{1,2},new int[]{0,3},new int[]{3,3}}), new int[]{2,7,14,8});
        assert Checker.check(new Solution().xorQueries(new int[]{16}, new int[][]{new int[]{0,0},
            new int[]{0,0},new int[]{0,0}}), new int[]{16,16,16});
    }

}
