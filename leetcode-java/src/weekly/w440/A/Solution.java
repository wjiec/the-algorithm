package weekly.w440.A;

/**
 * 3477. Fruits Into Baskets II
 *
 * https://leetcode.cn/contest/weekly-contest-440/problems/fruits-into-baskets-ii/
 *
 * You are given two arrays of integers, fruits and baskets, each of length n, where fruits[i]
 * represents the quantity of the ith type of fruit, and baskets[j] represents the capacity of the jth basket.
 *
 * From left to right, place the fruits according to these rules:
 *
 * Each fruit type must be placed in the leftmost available basket with a capacity greater than or equal
 * to the quantity of that fruit type.
 *
 * Each basket can hold only one type of fruit.
 * If a fruit type cannot be placed in any basket, it remains unplaced.
 * Return the number of fruit types that remain unplaced after all possible allocations are made.
 */

public class Solution {

    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        int ans = 0, n = baskets.length;
        for (var fruit : fruits) {
            for (int i = 0; i < n; i++) {
                if (baskets[i] >= fruit) {
                    ans++;
                    baskets[i] = 0;
                    break;
                }
            }
        }
        return fruits.length - ans;
    }

    public static void main(String[] args) {
    }

}
