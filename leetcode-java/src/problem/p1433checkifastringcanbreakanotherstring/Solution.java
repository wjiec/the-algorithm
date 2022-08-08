package problem.p1433checkifastringcanbreakanotherstring;

/**
 * 1433. Check If a String Can Break Another String
 *
 * https://leetcode.cn/problems/check-if-a-string-can-break-another-string/
 *
 * Given two strings: s1 and s2 with the same size, check if some permutation of string s1
 * can break some permutation of string s2 or vice-versa. In other words s2 can break s1 or vice-versa.
 *
 * A string x can break string y (both of size n) if x[i] >= y[i] (in alphabetical order) for all i between 0 and n-1.
 */

public class Solution {

    public boolean checkIfCanBreak(String s1, String s2) {
        char[] cs1 = s1.toCharArray(), cs2 = s2.toCharArray();
        return canBreak(cs1, cs2) || canBreak(cs2, cs1);
    }

    private boolean canBreak(char[] s1, char[] s2) {
        int[] m1 = new int[128];
        for (var c : s1) m1[c]++;

        int[] m2 = new int[128];
        for (var c : s2) m2[c]++;

        for (int i = 'a'; i <= 'z'; i++) {
            for (int j = i; m1[i] > 0 && j <= 'z'; j++) {
                int v = Math.min(m1[i], m2[j]);
                m1[i] -= v; m2[j] -= v;
            }
            if (m1[i] != 0) return false;
        }

        return true;
    }

    public static void main(String[] args) {
        assert new Solution().checkIfCanBreak("abc", "xya");
        assert !new Solution().checkIfCanBreak("abe", "acd");
        assert new Solution().checkIfCanBreak("leetcodee", "interview");
    }

}
