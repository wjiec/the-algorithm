package weekly.bw164.A;

/**
 * Q1. Find The Least Frequent Digit
 *
 * https://leetcode.cn/contest/biweekly-contest-164/problems/find-the-least-frequent-digit/
 *
 * Given an integer n, find the digit that occurs least frequently in its decimal representation.
 *
 * If multiple digits have the same frequency, choose the smallest digit.
 *
 * Return the chosen digit as an integer.
 *
 * The frequency of a digit x is the number of times it appears in the decimal representation of n.
 */

public class Solution {

    public int getLeastFrequentDigit(int n) {
        int[] freq = new int[10];
        for (; n != 0; n /= 10) freq[n % 10]++;

        int ans = -1;
        for (int i = 0; i < freq.length; i++) {
            if (freq[i] != 0 && (ans == -1 || freq[i] < freq[ans])) ans = i;
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
