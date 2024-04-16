package weekly.w393.B;

import java.util.ArrayList;
import java.util.List;

/**
 * 3115. Maximum Prime Difference
 *
 * https://leetcode.cn/contest/weekly-contest-393/problems/maximum-prime-difference/
 *
 * You are given an integer array nums.
 *
 * Return an integer that is the maximum distance between the
 * indices of two (not necessarily different) prime numbers in nums.
 */

public class Solution {

    public int maximumPrimeDifference(int[] nums) {
        byte[] isPrime = new byte[101];
        for (int i = 2; i < isPrime.length; i++) {
            if (isPrime[i] == 0) {
                isPrime[i] = 1;
                for (int j = 2; i * j < isPrime.length; j++) {
                    isPrime[i * j] = -1;
                }
            }
        }

        List<Integer> pIndex = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (isPrime[nums[i]] == 1) pIndex.add(i);
        }

        System.out.println(pIndex);
        return pIndex.get(pIndex.size() - 1) - pIndex.get(0);
    }

    public static void main(String[] args) {
    }

}
