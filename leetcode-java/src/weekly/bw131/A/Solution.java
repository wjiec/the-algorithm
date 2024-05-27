package weekly.bw131.A;

import java.util.HashMap;
import java.util.Map;

/**
 * 3158. Find the XOR of Numbers Which Appear Twice
 *
 * https://leetcode.cn/contest/biweekly-contest-131/problems/find-the-xor-of-numbers-which-appear-twice
 *
 * You are given an array nums, where each number in the array appears either once or twice.
 *
 * Return the bitwise XOR of all the numbers that appear twice in the array, or 0 if no number appears twice.
 */

public class Solution {

    public int duplicateNumbersXOR(int[] nums) {
        Map<Integer, Integer> m = new HashMap<>();
        for (var v : nums) m.merge(v, 1, Integer::sum);

        int ans = 0;
        for (var kv : m.entrySet()) {
            if (kv.getValue() == 2) ans ^= kv.getKey();
        }

        return ans;
    }

    public static void main(String[] args) {
    }

}
