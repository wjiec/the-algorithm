package offer2.p15;

import common.Checker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 剑指 Offer II 015. 字符串中的所有变位词
 *
 * https://leetcode.cn/problems/VabMRr/
 *
 * 给定两个字符串 s 和 p，找到 s 中所有 p 的 变位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 *
 * 变位词 指字母相同，但排列不同的字符串。
 */

public class Solution {

    public List<Integer> findAnagrams(String s, String p) {
        int sl = s.length(), pl = p.length();
        if (pl > sl) return Collections.emptyList();

        char[] sChars = s.toCharArray();
        char[] pChars = p.toCharArray();
        int[] sCnt = new int[26], pCnt = new int[26];

        for (int i = 0; i < pl; i++) {
            sCnt[sChars[i] - 'a']++;
            pCnt[pChars[i] - 'a']++;
        }

        List<Integer> ans = new ArrayList<>();
        if (Arrays.equals(sCnt, pCnt)) ans.add(0);

        for (int i = pl; i < sl; i++) {
            sCnt[sChars[i] - 'a']++;
            sCnt[sChars[i - pl] - 'a']--;
            if (Arrays.equals(sCnt, pCnt)) {
                ans.add(i - pl + 1);
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        assert Checker.anyOrder(new Solution().findAnagrams("cbaebabacd", "abc"), List.of(0,6));
        assert Checker.anyOrder(new Solution().findAnagrams("abab", "ab"), List.of(0,1,2));
    }

}
