package lcci.s8.p7permutationilcci;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 面试题 08.07. 无重复字符串的排列组合
 *
 * https://leetcode.cn/problems/permutation-i-lcci/
 *
 * 无重复字符串的排列组合。编写一种方法，计算某字符串的所有排列组合，字符串每个字符均不相同。
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    private final List<String> ans = new ArrayList<>();

    public String[] permutation(String S) {
        dfs(S.toCharArray(), new StringBuilder());
        return ans.toArray(new String[0]);
    }

    private void dfs(char[] chars, StringBuilder sb) {
        if (sb.length() == chars.length) {
            ans.add(sb.toString());
            return;
        }

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != 0) {
                sb.append(chars[i]);
                chars[i] = 0;
                dfs(chars, sb);
                chars[i] = sb.charAt(sb.length() - 1);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().permutation("abc")));
    }

}
