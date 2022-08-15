package problem.p2363mergesimilaritems;

import common.Checker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 2363. Merge Similar Items
 *
 * https://leetcode.cn/problems/merge-similar-items/
 *
 * You are given two 2D integer arrays, items1 and items2, representing two sets of items.
 * Each array items has the following properties:
 *
 * items[i] = [valuei, weighti] where valuei represents the value and weighti
 * represents the weight of the ith item.
 *
 * The value of each item in items is unique.
 *
 * Return a 2D integer array ret where ret[i] = [valuei, weighti], with weighti being
 * the sum of weights of all items with value valuei.
 *
 * Note: ret should be returned in ascending order by value.
 */

public class Solution {

    public List<List<Integer>> mergeSimilarItems(int[][] items1, int[][] items2) {
        Arrays.sort(items1, Comparator.comparingInt(v -> v[0]));
        Arrays.sort(items2, Comparator.comparingInt(v -> v[0]));

        int a = 0, b = 0;
        List<List<Integer>> ans = new ArrayList<>();
        while (a < items1.length && b < items2.length) {
            if (items1[a][0] == items2[b][0]) {
                ans.add(List.of(items1[a][0], items1[a][1] + items2[b][1]));
                a++; b++;
            } else if (items1[a][0] < items2[b][0]) {
                ans.add(List.of(items1[a][0], items1[a][1]));
                a++;
            } else {
                ans.add(List.of(items2[b][0], items2[b][1]));
                b++;
            }
        }

        for (; a < items1.length; a++) ans.add(List.of(items1[a][0], items1[a][1]));
        for (; b < items2.length; b++) ans.add(List.of(items2[b][0], items2[b][1]));

        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().mergeSimilarItems(
            new int[][]{{1,1},{4,5},{3,8}}, new int[][]{{3,1},{1,5}}),
            List.of(List.of(1,6),List.of(3,9),List.of(4,5))
        );
    }

}
