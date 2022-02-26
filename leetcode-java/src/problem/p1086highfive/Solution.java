package problem.p1086highfive;

import common.PrettyPrinter;

import java.util.*;

/**
 * 1086. High Five
 *
 * https://leetcode-cn.com/problems/high-five/
 *
 * Given a list of the scores of different students, items, where items[i] = [IDi, scorei] represents
 * one score from a student with IDi, calculate each student's top five average.
 *
 * Return the answer as an array of pairs result, where result[j] = [IDj, topFiveAveragej] represents
 * the student with IDj and their top five average. Sort result by IDj in increasing order.
 *
 * A student's top five average is calculated by taking the sum of their
 * top five scores and dividing it by 5 using integer division.
 */

public class Solution {

    public int[][] highFive(int[][] items) {
        Arrays.sort(items, (a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];
            return b[1] - a[1];
        });

        Map<Integer, List<Integer>> map = new HashMap<>();
        for (var item : items) {
            if (!map.containsKey(item[0])) {
                map.put(item[0], new ArrayList<>());
            }

            if (map.get(item[0]).size() != 5) {
                map.get(item[0]).add(item[1]);
            }
        }

        int i = 0;
        int[][] ans = new int[map.size()][];
        for (var e : map.entrySet()) {
            List<Integer> scs = e.getValue();
            ans[i++] = new int[]{e.getKey(), (scs.get(0) + scs.get(1) + scs.get(2) + scs.get(3) + scs.get(4)) / 5};
        }
        return ans;
    }

    public static void main(String[] args) {
        PrettyPrinter.println(new Solution().highFive(new int[][]{
            {1,91},{1,92},{2,93},{2,97},{1,60},{2,77},{1,65},{1,87},{1,100},{2,100},{2,76}
        }));

        PrettyPrinter.println(new Solution().highFive(new int[][]{
            {1,100},{7,100},{1,100},{7,100},{1,100},{7,100},{1,100},{7,100},{1,100},{7,100}
        }));
    }

}
