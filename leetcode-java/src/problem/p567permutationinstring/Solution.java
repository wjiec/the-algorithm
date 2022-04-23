package problem.p567permutationinstring;

/**
 * 567. Permutation in String
 *
 * https://leetcode-cn.com/problems/permutation-in-string/
 *
 * Given two strings s1 and s2, return true if s2 contains a permutation of s1, or false otherwise.
 *
 * In other words, return true if one of s1's permutations is the substring of s2.
 */

public class Solution {

    public boolean checkInclusionV2(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        if (n < m) return false;

        int[] counts = new int[128];
        for (int i = 0; i < m; i++) counts[s1.charAt(i)]--;

        for (int l = 0, r = 0; r < n; r++) {
            int curr = s2.charAt(r);
            counts[curr]++;
            while (counts[curr] > 0) counts[s2.charAt(l++)]--;
            if (r - l + 1 == m) return true;
        }
        return false;
    }

    public boolean checkInclusion(String s1, String s2) {
        if (s2.length() < s1.length()) return false;

        int[] chars = new int[128];
        for (var c : s1.toCharArray()) chars[c]++;

        int[] current = new int[128];
        for (int i = 0; i < s1.length(); i++) current[s2.charAt(i)]++;
        if (isEquals(chars, current)) return true;

        for (int l = 0, r = s1.length(); r < s2.length(); r++, l++) {
            current[s2.charAt(r)]++;
            current[s2.charAt(l)]--;
            if (isEquals(chars, current)) return true;
        }
        return false;
    }

    private boolean isEquals(int[] l, int[] r) {
        for (int i = 'a'; i <= 'z'; i++) {
            if (l[i] != r[i]) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        assert new Solution().checkInclusion("adc", "dcda");

        assert new Solution().checkInclusion("ab", "eidbaooo");
        assert !new Solution().checkInclusion("ab", "eidboaoo");
        assert new Solution().checkInclusion("abc", "eidboacobcocaboo");

        assert new Solution().checkInclusionV2("adc", "qaqdqcqqqqdcdaqqq");
        assert new Solution().checkInclusionV2("adc", "dcda");
        assert new Solution().checkInclusionV2("ab", "eidbaooo");
        assert !new Solution().checkInclusionV2("ab", "eidboaoo");
        assert new Solution().checkInclusionV2("abc", "eidboacobcocaboo");
    }

}
