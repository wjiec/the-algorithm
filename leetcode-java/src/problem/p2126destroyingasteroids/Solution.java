package problem.p2126destroyingasteroids;

import java.util.Arrays;

/**
 * 2126. Destroying Asteroids
 *
 * https://leetcode.cn/problems/destroying-asteroids/
 *
 * You are given an integer mass, which represents the original mass of a planet.
 * You are further given an integer array asteroids, where asteroids[i] is the mass of the ith asteroid.
 *
 * You can arrange for the planet to collide with the asteroids in any arbitrary order.
 * If the mass of the planet is greater than or equal to the mass of the asteroid, the asteroid is destroyed
 * and the planet gains the mass of the asteroid. Otherwise, the planet is destroyed.
 *
 * Return true if all asteroids can be destroyed. Otherwise, return false.
 */

public class Solution {

    public boolean asteroidsDestroyed(int mass, int[] asteroids) {
        Arrays.sort(asteroids);

        long curr = mass;
        for (var asteroid : asteroids) {
            if (curr >= asteroid) {
                curr += asteroid;
            } else return false;
        }
        return true;
    }

    public static void main(String[] args) {
        assert new Solution().asteroidsDestroyed(10, new int[]{3,9,19,5,21});
        assert !new Solution().asteroidsDestroyed(5, new int[]{4,9,23,4});
    }

}
