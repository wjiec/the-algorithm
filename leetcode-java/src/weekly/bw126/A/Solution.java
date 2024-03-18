package weekly.bw126.A;

/**
 * 100262. Find the Sum of Encrypted Integers
 *
 * https://leetcode.cn/contest/biweekly-contest-126/problems/find-the-sum-of-encrypted-integers/
 *
 * You are given an integer array nums containing positive integers. We define a function
 * encrypt such that encrypt(x) replaces every digit in x with the largest digit in x.
 *
 * For example, encrypt(523) = 555 and encrypt(213) = 333.
 *
 * Return the sum of encrypted elements.
 */

public class Solution {

    public int sumOfEncryptedInt(int[] nums) {
        int sum = 0;
        for (var v : nums) sum += encrypt(v);
        return sum;
    }

    private int encrypt(int n) {
        char max = '0'; int len = 0;
        for (var c : String.valueOf(n).toCharArray()) {
            if (c > max) max = c; len++;
        }
        return Integer.parseInt(String.valueOf(max).repeat(len));
    }

    public static void main(String[] args) {
    }

}
