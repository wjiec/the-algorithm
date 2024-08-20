package weekly.w411.B;

/**
 * 3259. Maximum Energy Boost From Two Drinks
 *
 * https://leetcode.cn/contest/weekly-contest-411/problems/maximum-energy-boost-from-two-drinks/
 *
 * You are given two integer arrays energyDrinkA and energyDrinkB of the same length n
 * by a futuristic sports scientist. These arrays represent the energy boosts per hour
 * provided by two different energy drinks, A and B, respectively.
 *
 * You want to maximize your total energy boost by drinking one energy drink per hour.
 * However, if you want to switch from consuming one energy drink to the other, you need
 * to wait for one hour to cleanse your system (meaning you won't get any energy boost in that hour).
 *
 * Return the maximum total energy boost you can gain in the next n hours.
 *
 * Note that you can start consuming either of the two energy drinks.
 */

public class Solution {

    public long maxEnergyBoost(int[] energyDrinkA, int[] energyDrinkB) {
        long[] drinkA = new long[energyDrinkA.length + 1];
        long[] drinkB = new long[energyDrinkB.length + 1];

        drinkA[1] = drinkA[0] + energyDrinkA[0];
        drinkB[1] = drinkB[0] + energyDrinkB[0];

        for (int i = 2; i <= energyDrinkA.length; i++) {
            drinkA[i] = energyDrinkA[i - 1] + Math.max(
                drinkA[i - 1],
                // 如果切换到另一种饮料
                drinkB[i - 2]
            );

            drinkB[i] = energyDrinkB[i - 1] + Math.max(
                drinkB[i - 1],
                // 如果切换到另一种饮料
                drinkA[i - 2]
            );
        }

        return Math.max(drinkA[energyDrinkA.length], drinkB[energyDrinkB.length]);
    }

    public static void main(String[] args) {
    }

}
