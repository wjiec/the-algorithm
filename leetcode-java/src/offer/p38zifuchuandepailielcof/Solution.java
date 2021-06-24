package offer.p38zifuchuandepailielcof;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 剑指 Offer 38. 字符串的排列
 *
 * https://leetcode-cn.com/problems/zi-fu-chuan-de-pai-lie-lcof/
 *
 * 输入一个字符串，打印出该字符串中字符的所有排列。
 *
 * 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
 */

public class Solution {

    private boolean[] visited;
    private List<String> strings;

    public String[] permutation(String s) {
        visited = new boolean[s.length()];
        strings = new ArrayList<>();

        char[] chars = s.toCharArray();
        Arrays.sort(chars);

        backtrack(chars, new StringBuffer());
        return strings.toArray(new String[]{});
    }

    private void backtrack(char[] chars, StringBuffer sb) {
        if (sb.length() == chars.length) {
            strings.add(sb.toString());
        }

        for (int i = 0; i < chars.length; i++) {
            if (visited[i] || (i > 0 && chars[i] == chars[i - 1] && visited[i - 1])) {
                continue;
            }

            visited[i] = true;
            sb.append(chars[i]);
            backtrack(chars, sb);

            sb.deleteCharAt(sb.length() - 1);
            visited[i] = false;
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().permutation("abc")));
        System.out.println(Arrays.toString(new Solution().permutation("abbb")));
        assert Arrays.deepEquals(new Solution().permutation("abc"), new String[]{
            "abc","acb","bac","bca","cab","cba"
        });
    }

}
