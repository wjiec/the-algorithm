package problem.p2105wateringplantsii;

/**
 * 2105. Watering Plants II
 *
 * https://leetcode.cn/problems/watering-plants-ii/
 *
 * Alice and Bob want to water n plants in their garden. The plants are arranged in a row
 * and are labeled from 0 to n - 1 from left to right where the ith plant is located at x = i.
 *
 * Each plant needs a specific amount of water. Alice and Bob have a watering can each, initially full.
 * They water the plants in the following way:
 *
 * Alice waters the plants in order from left to right, starting from the 0th plant. Bob waters the
 * plants in order from right to left, starting from the (n - 1)th plant.
 * They begin watering the plants simultaneously.
 * It takes the same amount of time to water each plant regardless of how much water it needs.
 * Alice/Bob must water the plant if they have enough in their can to fully water it. Otherwise, they
 * first refill their can (instantaneously) then water the plant.
 * In case both Alice and Bob reach the same plant, the one with more water currently in his/her
 * watering can should water this plant. If they have the same amount of water, then Alice should water this plant.
 * Given a 0-indexed integer array plants of n integers, where plants[i] is the amount of water
 * the ith plant needs, and two integers capacityA and capacityB representing the capacities of Alice's
 * and Bob's watering cans respectively, return the number of times they have to refill to water all the plants.
 */

public class Solution {

    public int minimumRefill(int[] plants, int capacityA, int capacityB) {
        int ans = 0, n = plants.length, a = capacityA, b = capacityB;
        for (int l = 0, r = n - 1; l <= r; l++, r--) {
            if (l == r) {
                if (Math.max(a, b) < plants[l]) ans++;
                continue;
            }

            if (a < plants[l]) { ans++; a = capacityA; }
            if (b < plants[r]) { ans++; b = capacityB; }

            a -= plants[l];
            b -= plants[r];
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minimumRefill(new int[]{2,2,3,3}, 5, 5) == 1;
        assert new Solution().minimumRefill(new int[]{2,2,3,3}, 3, 4) == 2;
        assert new Solution().minimumRefill(new int[]{5}, 10, 8) == 0;
    }

}
