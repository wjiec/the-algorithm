package weekly.w407.A;

/**
 * 3226. Number of Bit Changes to Make Two Integers Equal
 *
 * https://leetcode.cn/contest/weekly-contest-407/problems/number-of-bit-changes-to-make-two-integers-equal/
 *
 * You are given two positive integers n and k.
 *
 * You can choose any bit in the binary representation of n that is equal to 1 and change it to 0.
 *
 * Return the number of changes needed to make n equal to k. If it is impossible, return -1.
 */

public class Solution {

    public int minChanges(int n, int k) {
        int ans = 0;
        for (int i = 0; i < 31; i++) {
            boolean nb = (n & (1 << i)) != 0;
            boolean kb = (k & (1 << i)) != 0;
            if (!nb && kb) return -1;
            if (nb && !kb) ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
