package problem.p383ransomnote;

/**
 * 383. Ransom Note
 *
 * https://leetcode-cn.com/problems/ransom-note/
 *
 * Given two stings ransomNote and magazine, return true if ransomNote can be constructed from magazine and false otherwise.
 *
 * Each letter in magazine can only be used once in ransomNote.
 */

public class Solution {

    public boolean canConstruct(String ransomNote, String magazine) {
        int[] chars = new int[26];
        int sz1 = ransomNote.length(), sz2 = magazine.length();
        for (int i = 0; i < sz2; i++) {
            chars[magazine.charAt(i) - 'a']++;
        }
        for (int i = 0; i < sz1; i++) {
            if (--chars[ransomNote.charAt(i) - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        assert !new Solution().canConstruct("a", "b");
        assert !new Solution().canConstruct("aa", "ab");
        assert new Solution().canConstruct("aa", "aab");
    }

}
