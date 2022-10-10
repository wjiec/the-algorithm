package problem.p2070mostbeautifulitemforeachquery;

import common.Checker;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

/**
 * 2070. Most Beautiful Item for Each Query
 *
 * https://leetcode.cn/problems/most-beautiful-item-for-each-query/
 *
 * You are given a 2D integer array items where items[i] = [pricei, beautyi] denotes
 * the price and beauty of an item respectively.
 *
 * You are also given a 0-indexed integer array queries. For each queries[j], you want
 * to determine the maximum beauty of an item whose price is less than or equal to
 * queries[j]. If no such item exists, then the answer to this query is 0.
 *
 * Return an array answer of the same length as queries where answer[j] is the answer to the jth query.
 */

public class Solution {

    public int[] maximumBeauty(int[][] items, int[] queries) {
        // [price, beauty]
        Arrays.sort(items, Comparator.comparingInt(v -> v[0]));

        int beauty = 0;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(0, 0);

        for (var item : items) {
            beauty = Math.max(beauty, item[1]);
            map.put(item[0], beauty);
        }

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            ans[i] = map.floorEntry(queries[i]).getValue();
        }
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().maximumBeauty(new int[][]{
            {1,2},{3,2},{2,4},{5,6},{3,5}
        }, new int[]{1,2,3,4,5,6}), new int[]{2,4,5,5,6,6});

        assert Checker.check(new Solution().maximumBeauty(new int[][]{
            {1,2},{1,2},{1,3},{1,4}
        }, new int[]{1}), new int[]{4});

        assert Checker.check(new Solution().maximumBeauty(new int[][]{
            {10,1000}
        }, new int[]{5}), new int[]{0});
    }

}
