package weekly.w345.B;

/**
 * 2683. Neighboring Bitwise XOR
 *
 * https://leetcode.cn/contest/weekly-contest-345/problems/neighboring-bitwise-xor/
 *
 * A 0-indexed array derived with length n is derived by computing the bitwise XOR (⊕) of adjacent
 * values in a binary array original of length n.
 *
 * Specifically, for each index i in the range [0, n - 1]:
 *
 * If i = n - 1, then derived[i] = original[i] ⊕ original[0].
 * Otherwise, derived[i] = original[i] ⊕ original[i + 1].
 *
 * Given an array derived, your task is to determine whether there exists a valid binary array original
 * that could have formed derived.
 *
 * Return true if such an array exists or false otherwise.
 *
 * A binary array is an array containing only 0's and 1's
 */

public class Solution {

    public boolean doesValidArrayExist(int[] derived) {
        return doesValidArray(derived, 0) || doesValidArray(derived, 1);
    }

    // derived[i] = original[i] ⊕ original[i + 1]
    // original[i] = derived[i] ⊕ original[i + 1]
    // original[i + 1] = derived[i] ⊕ original[i]
    private boolean doesValidArray(int[] derived, int f) {
        int x = f;
        for (int v : derived) f = v ^ f;
        return f == x;
    }

    public static void main(String[] args) {
    }

}
