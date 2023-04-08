package daily.d230408p1125smallestsufficientteam;

import common.PrettyPrinter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 1125. Smallest Sufficient Team
 *
 * https://leetcode.cn/problems/smallest-sufficient-team/
 *
 * In a project, you have a list of required skills req_skills, and a list of people.
 * The ith person people[i] contains a list of skills that the person has.
 *
 * Consider a sufficient team: a set of people such that for every required skill in
 * req_skills, there is at least one person in the team who has that skill.
 *
 * We can represent these teams by the index of each person.
 *
 * For example, team = [0, 1, 3] represents the people with skills people[0], people[1], and people[3].
 *
 * Return any sufficient team of the smallest possible size, represented by the index of each person.
 * You may return the answer in any order.
 *
 * It is guaranteed an answer exists.
 */

public class Solution {

    public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
        int nr = req_skills.length, pr = people.size();
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < nr; i++) map.put(req_skills[i], 1 << i);

        int[] states = new int[pr];
        for (int i = 0; i < pr; i++) {
            for (var skill : people.get(i)) {
                states[i] |= map.get(skill);
            }
        }

        int mask = 1 << req_skills.length - 1;
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < pr; i++) ans.add(i);

        for (int i = 1, n = 1 << pr; i < n; i++) {
            int currMask = 0;
            List<Integer> curr = new ArrayList<>();
            for (int j = 0; j < pr; j++) {
                if ((i & (1 << j)) != 0) {
                    currMask |= states[j];
                    curr.add(j);
                }
            }
            if (currMask == mask && curr.size() < ans.size()) ans = curr;
        }

        int[] array = new int[ans.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = ans.get(i);
        }
        return array;
    }

    public static void main(String[] args) {
        PrettyPrinter.println(new Solution().smallestSufficientTeam(new String[]{"java","nodejs","reactjs"}, List.of(
            List.of("java"), List.of("nodejs"), List.of("nodejs", "reactjs")
        )));
    }

}
