package weekly.w455.A;

import java.util.HashMap;
import java.util.Map;

/**
 * Q1. Check if Any Element Has Prime Frequency
 *
 * https://leetcode.cn/contest/weekly-contest-455/problems/check-if-any-element-has-prime-frequency/
 *
 * You are given an integer array nums.
 *
 * Return true if the frequency of any element of the array is prime, otherwise, return false.
 *
 * The frequency of an element x is the number of times it occurs in the array.
 *
 * A prime number is a natural number greater than 1 with only two factors, 1 and itself.
 */

public class Solution {

    public boolean checkPrimeFrequency(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (var v : nums) freq.merge(v, 1, Integer::sum);

        for (var v : freq.values()) {
            boolean isPrime = v != 1;
            for (int p = 2; p <= Math.sqrt(v); p++) {
                if (v % p == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) return true;
        }
        return false;
    }

    public static void main(String[] args) {
    }

}
