package problem.p1488avoidfloodinthecity;

import common.PrettyPrinter;

import java.util.*;

/**
 * 1488. Avoid Flood in The City
 *
 * https://leetcode.cn/problems/avoid-flood-in-the-city/
 *
 * Your country has an infinite number of lakes. Initially, all the lakes are empty, but when it rains over the nth lake, the nth lake becomes full of water. If it rains over a lake that is full of water, there will be a flood. Your goal is to avoid floods in any lake.
 *
 * Given an integer array rains where:
 *
 * rains[i] > 0 means there will be rains over the rains[i] lake.
 * rains[i] == 0 means there are no rains this day and you can choose one lake this day and dry it.
 * Return an array ans where:
 *
 * ans.length == rains.length
 * ans[i] == -1 if rains[i] > 0.
 * ans[i] is the lake you choose to dry in the ith day if rains[i] == 0.
 * If there are multiple valid answers return any of them. If it is impossible to
 * avoid flood return an empty array.
 *
 * Notice that if you chose to dry a full lake, it becomes empty, but if
 * you chose to dry an empty lake, nothing changes.
 */

public class Solution {

    public int[] avoidFlood(int[] rains) {
        int[] ans = new int[rains.length];
        Set<Integer> rain = new HashSet<>();
        TreeSet<Integer> sun = new TreeSet<>();
        Map<Integer, Integer> last = new HashMap<>();
        for (int i = 0; i < rains.length; i++) {
            if (rains[i] == 0) sun.add(i);
            else {
                ans[i] = -1;

                if (rain.contains(rains[i])) {
                    Integer dry = sun.ceiling(last.get(rains[i]));
                    if (dry == null) return new int[]{};
                    ans[dry] = rains[i]; sun.remove(dry);
                } else {
                    rain.add(rains[i]);
                }

                last.put(rains[i], i);
            }
        }
        for (var idx : sun) ans[idx] = 1;
        return ans;
    }

    public static void main(String[] args) {
        PrettyPrinter.println(new Solution().avoidFlood(new int[]{1,0,2,3,0,1,2}));
        PrettyPrinter.println(new Solution().avoidFlood(new int[]{1,0,2,0,2,1}));
        PrettyPrinter.println(new Solution().avoidFlood(new int[]{0,1,1}));
        PrettyPrinter.println(new Solution().avoidFlood(new int[]{69,0,0,0,69}));

        PrettyPrinter.println(new Solution().avoidFlood(new int[]{1,2,3,4}));
        PrettyPrinter.println(new Solution().avoidFlood(new int[]{1,2,0,0,2,1}));
        PrettyPrinter.println(new Solution().avoidFlood(new int[]{1,2,0,1,2}));
    }

}
