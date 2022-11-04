package offer2.p14;

/**
 * 剑指 Offer II 014. 字符串中的变位词
 *
 * https://leetcode.cn/problems/MPnaiL/
 *
 * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的某个变位词。
 *
 * 换句话说，第一个字符串的排列之一是第二个字符串的 子串 。
 */

public class Solution {

    public boolean checkInclusion(String s1, String s2) {
        int[] freq = new int[26];
        for (var c : s1.toCharArray()) freq[c - 'a']++;

        int[] state = new int[26];
        char[] chars = s2.toCharArray();
        for (int l = 0, r = 0; r < chars.length; r++) {
            state[chars[r] - 'a']++;
            while (!isSubState(freq, state)) state[chars[l++] - 'a']--;
            if (isEquals(freq, state)) return true;
        }
        return false;
    }

    private boolean isSubState(int[] target, int[] curr) {
        for (int i = 0; i < target.length; i++) {
            if (curr[i] < 0 || curr[i] > target[i]) {
                return false;
            }
        }
        return true;
    }

    private boolean isEquals(int[] target, int[] curr) {
        for (int i = 0; i < target.length; i++) {
            if (target[i] != curr[i]) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        assert new Solution().checkInclusion("adc", "dcda");

        assert new Solution().checkInclusion("ab", "eidbaooo");
        assert new Solution().checkInclusion("ab", "eidbaooo");
        assert !new Solution().checkInclusion("ab", "eidboaoo");
    }

}
