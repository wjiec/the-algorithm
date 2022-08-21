package weekly.w307.A;

/**
 * 6152. Minimum Hours of Training to Win a Competition
 *
 * https://leetcode.cn/contest/weekly-contest-307/problems/minimum-hours-of-training-to-win-a-competition/
 *
 * You are entering a competition, and are given two positive integers initialEnergy and initialExperience
 * denoting your initial energy and initial experience respectively.
 *
 * You are also given two 0-indexed integer arrays energy and experience, both of length n.
 *
 * You will face n opponents in order. The energy and experience of the ith opponent is denoted by energy[i]
 * and experience[i] respectively. When you face an opponent, you need to have both strictly greater experience
 * and energy to defeat them and move to the next opponent if available.
 *
 * Defeating the ith opponent increases your experience by experience[i], but decreases your energy by energy[i].
 *
 * Before starting the competition, you can train for some number of hours. After each hour of training, you
 * can either choose to increase your initial experience by one, or increase your initial energy by one.
 *
 * Return the minimum number of training hours required to defeat all n opponents.
 */

public class Solution {

    public int minNumberOfHours(int initialEnergy, int initialExperience, int[] energy, int[] experience) {
        int ans = 0, en = initialEnergy, ex = initialExperience;
        for (int i = 0; i < energy.length; i++) {
            if (en <= energy[i]) {
                int diff = energy[i] - en + 1;
                ans += diff; en += diff;
            }
            if (ex <= experience[i]) {
                int diff = experience[i] - ex + 1;
                ans += diff; ex += diff;
            }

            en -= energy[i];
            ex += experience[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minNumberOfHours(5, 3, new int[]{1, 4}, new int[]{2, 5}) == 2;
    }

}
