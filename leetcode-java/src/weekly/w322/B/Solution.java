package weekly.w322.B;

import ability.Ability;

import java.util.HashMap;
import java.util.Map;

/**
 * 6254. Divide Players Into Teams of Equal Skill
 *
 * https://leetcode.cn/contest/weekly-contest-322/problems/divide-players-into-teams-of-equal-skill/
 *
 * You are given a positive integer array skill of even length n where skill[i] denotes
 * the skill of the ith player. Divide the players into n / 2 teams of size 2 such that
 * the total skill of each team is equal.
 *
 * The chemistry of a team is equal to the product of the skills of the players on that team.
 *
 * Return the sum of the chemistry of all the teams, or return -1 if there is no way to divide
 * the players into teams such that the total skill of each team is equal.
 */

public class Solution {

    public long dividePlayers(int[] skill) {
        long sum = 0, n = skill.length;
        for (var v : skill) sum += v;

        long count = n / 2;
        if (sum % count != 0) return -1;

        long ans = 0, avg = sum / count;
        Map<Long, Integer> map = new HashMap<>();
        for (var v : skill) {
            if (map.containsKey(avg - v)) {
                map.merge(avg - v, 1, Ability::subtract);

                ans += v * (avg - v);
            } else map.merge((long) v, 1, Integer::sum);
        }

        if (map.size() != 0) return -1;
        return ans;
    }

    public static void main(String[] args) {
    }

}
