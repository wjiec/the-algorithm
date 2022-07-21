package problem.p1282groupthepeoplegiventhegroupsizetheybelongto;

import common.PrettyPrinter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 1282. Group the People Given the Group Size They Belong To
 *
 * https://leetcode.cn/problems/group-the-people-given-the-group-size-they-belong-to/
 *
 * There are n people that are split into some unknown number of groups.
 * Each person is labeled with a unique ID from 0 to n - 1.
 *
 * You are given an integer array groupSizes, where groupSizes[i] is the size of the group that person i is in.
 * For example, if groupSizes[1] = 3, then person 1 must be in a group of size 3.
 *
 * Return a list of groups such that each person i is in a group of size groupSizes[i].
 *
 * Each person should appear in exactly one group, and every person must be in a group.
 * If there are multiple answers, return any of them. It is guaranteed that there will be
 * at least one valid solution for the given input.
 */

public class Solution {

    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        List<List<Integer>> ans = new ArrayList<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < groupSizes.length; i++) {
            if (!map.containsKey(groupSizes[i])) {
                map.put(groupSizes[i], new ArrayList<>());
            }

            map.get(groupSizes[i]).add(i);
            if (map.get(groupSizes[i]).size() == groupSizes[i]) {
                ans.add(map.get(groupSizes[i]));
                map.put(groupSizes[i], new ArrayList<>());
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        PrettyPrinter.println(new Solution().groupThePeople(new int[]{3,3,3,3,3,1,3}));
        PrettyPrinter.println(new Solution().groupThePeople(new int[]{2,1,3,3,3,2}));
    }

}
