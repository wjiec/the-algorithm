package weekly.w485.D;

/**
 * Q4. Lexicographically Smallest String After Deleting Duplicate Characters
 *
 * https://leetcode.cn/contest/weekly-contest-485/problems/lexicographically-smallest-string-after-deleting-duplicate-characters/
 *
 * You are given a string s that consists of lowercase English letters.
 *
 * You can perform the following operation any number of times (possibly zero times):
 *
 * Choose any letter that appears at least twice in the current string s and delete any one occurrence.
 *
 * Return the lexicographically smallest resulting string that can be formed this way.
 */

public class Solution {

    public String lexSmallestAfterDeletion(String s) {
        char[] chars = s.toCharArray(); int[] freq = new int[128];
        for (var c : chars) freq[c]++;

        int ans = 0;
        for (var c : chars) {
            // 如果当前 c 比较小, 那么就更适合放在前面的位置
            //  - 同时还需要保证删除的字母不是最后一个
            while (ans > 0 && c < chars[ans - 1] && freq[chars[ans - 1]] > 1) {
                freq[chars[--ans]]--;
            }
            chars[ans++] = c;
        }

        // 最后重复的元素可以删掉, 因为更短的字符串是字典序更小的
        while (freq[chars[ans - 1]] > 1) freq[chars[--ans]]--;
        return new String(chars, 0, ans);
     }

    public static void main(String[] args) {
        assert new Solution().lexSmallestAfterDeletion("baab").equals("aab");
        assert new Solution().lexSmallestAfterDeletion("bab").equals("ab");
        assert new Solution().lexSmallestAfterDeletion("aa").equals("a");

        assert new Solution().lexSmallestAfterDeletion("aaccb").equals("aacb");
        assert new Solution().lexSmallestAfterDeletion("z").equals("z");
    }

}
