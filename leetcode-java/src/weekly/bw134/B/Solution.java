package weekly.bw134.B;

import java.util.Arrays;

/**
 * 3207. Maximum Points After Enemy Battles
 *
 * https://leetcode.cn/contest/biweekly-contest-134/problems/maximum-points-after-enemy-battles/
 *
 * You are given an integer array enemyEnergies denoting the energy values of various enemies.
 *
 * You are also given an integer currentEnergy denoting the amount of energy you have initially.
 *
 * You start with 0 points, and all the enemies are unmarked initially.
 *
 * You can perform either of the following operations zero or multiple times to gain points:
 *
 * Choose an unmarked enemy, i, such that currentEnergy >= enemyEnergies[i]. By choosing this option:
 * You gain 1 point.
 * Your energy is reduced by the enemy's energy, i.e. currentEnergy = currentEnergy - enemyEnergies[i].
 * If you have at least 1 point, you can choose an unmarked enemy, i. By choosing this option:
 * Your energy increases by the enemy's energy, i.e. currentEnergy = currentEnergy + enemyEnergies[i].
 * The enemy i is marked.
 *
 * Return an integer denoting the maximum points you can get in the end by optimally performing operations.
 */

public class Solution {

    public long maximumPoints(int[] enemyEnergies, int currentEnergy) {
        Arrays.sort(enemyEnergies);
        // 无法获得分数
        if (currentEnergy < enemyEnergies[0]) return 0;

        // 对能量最少的执行操作一
        long ans = currentEnergy / enemyEnergies[0];
        currentEnergy = currentEnergy % enemyEnergies[0];
        for (int i = enemyEnergies.length - 1; i > 0; i--) {
            currentEnergy += enemyEnergies[i];

            ans += currentEnergy / enemyEnergies[0];
            currentEnergy = currentEnergy % enemyEnergies[0];
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
