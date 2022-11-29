package lcci.s8.p8permutationiilcci;

import common.PrettyPrinter;

import java.util.ArrayList;
import java.util.List;

/**
 * 面试题 08.08. 有重复字符串的排列组合
 *
 * https://leetcode.cn/problems/permutation-ii-lcci/
 *
 * 有重复字符串的排列组合。编写一种方法，计算某字符串的所有排列组合。
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

        boolean[] visited = new boolean[128];
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != 0 && !visited[chars[i]]) {
                visited[chars[i]] = true;
                sb.append(chars[i]);
                chars[i] = 0;
                dfs(chars, sb);
                chars[i] = sb.charAt(sb.length() - 1);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }

    public static void main(String[] args) {
        PrettyPrinter.println(new Solution().permutation("eqq"));
        PrettyPrinter.println(new Solution().permutation("ab"));
    }

}
