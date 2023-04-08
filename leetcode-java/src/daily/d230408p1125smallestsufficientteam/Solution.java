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

        List<Integer>[] dp = new List[1 << nr];
        dp[0] = new ArrayList<>();

        for (int i = 0; i < pr; i++) {
            int skillState = 0;
            for (var skill : people.get(i)) skillState |= map.get(skill);
            for (int prev = 0; prev < dp.length; prev++) {
                if (dp[prev] == null) continue;

                int next = prev | skillState;
                if (dp[next] == null || (dp[prev].size() + 1) < dp[next].size()) {
                    dp[next] = new ArrayList<>(dp[prev]);
                    dp[next].add(i);
                }
            }
        }
        return dp[dp.length - 1].stream().mapToInt(v -> v).toArray();
    }

    public static void main(String[] args) {
        PrettyPrinter.println(new Solution().smallestSufficientTeam(new String[]{"java","nodejs","reactjs"}, List.of(
            List.of("java"), List.of("nodejs"), List.of("nodejs", "reactjs")
        )));
    }

}
