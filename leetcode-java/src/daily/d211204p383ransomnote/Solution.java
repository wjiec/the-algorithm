package daily.d211204p383ransomnote;

/**
 * 383. Ransom Note
 *
 * https://leetcode-cn.com/problems/ransom-note/
 *
 * Given two stings ransomNote and magazine, return true if ransomNote can be constructed
 * from magazine and false otherwise.
 *
 * Each letter in magazine can only be used once in ransomNote.
 */

public class Solution {

    public boolean canConstruct(String ransomNote, String magazine) {
        short[] map = new short[128];
        for (var c : magazine.toCharArray()) map[c]++;
        for (var c : ransomNote.toCharArray()) {
            if (--map[c] < 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        assert !new Solution().canConstruct("a", "b");
        assert !new Solution().canConstruct("aa", "ab");
        assert new Solution().canConstruct("aa", "aab");
    }

}
