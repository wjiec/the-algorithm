package problem.p943findtheshortestsuperstring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 943. Find the Shortest Superstring
 *
 * https://leetcode.cn/problems/find-the-shortest-superstring/
 *
 * Given an array of strings words, return the smallest string that contains
 * each string in words as a substring. If there are multiple valid strings of
 * the smallest length, return any of them.
 *
 * You may assume that no string in words is a substring of another string in words.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    private int n = 0;
    private final int[] lengths = new int[13];
    // [i][j] = {prefix, suffix} 表示使用 i 作为 j 的前缀能重叠的
    // 长度为 prefix, 使用 i 作为 j 的后缀能重叠的长度为 suffix
    private final int[][] distances = new int[13][13];

    // 找到最佳的方式排列方式, 使得重叠的前后缀尽可能多
    public String shortestSuperstring(String[] words) {
        n = words.length;
        for (int i = 0; i < n; i++) {
            lengths[i + 1] = words[i].length();
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                // 使用 i 作为 j 的前缀
                distances[i + 1][j + 1] = overlay(words[i], words[j]);
            }
        }

        int prev = 0;
        StringBuilder sb = new StringBuilder();
        for (var index : dfs(0, 0).keys) {
            sb.append(words[index - 1].substring(distances[prev][index]));
            prev = index;
        }

        return sb.toString();
    }

    private static class Pair { private int len = 0; private List<Integer> keys = new ArrayList<>(); }
    private final Map<Integer, Pair> memo = new HashMap<>();

    private Pair dfs(int state, int prev) {
        int key = (state) << 16 | prev;
        if (!memo.containsKey(key)) {
            Pair ans = new Pair();
            for (int i = 1; i <= n; i++) {
                if ((state & (1 << i)) != 0) continue;
                if (ans.len == 0) ans.len = 999;

                // 考虑在 prev 之后拼接 i
                Pair curr = dfs(state | (1 << i), i);
                if (curr.len + lengths[i] - distances[prev][i] < ans.len) {
                    ans.len = curr.len + lengths[i] - distances[prev][i];
                    ans.keys = new ArrayList<>(curr.keys);
                    ans.keys.add(0, i);
                }
            }

            memo.put(key, ans);
        }

        return memo.get(key);
    }

    private int overlay(String a, String b) {
        int ans = 0, an = a.length(), bn = b.length();
        for (int curr = 1; curr <= an && curr <= bn; curr++) {
            if (a.substring(an - curr).equals(b.substring(0, curr))) {
                ans = curr;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().shortestSuperstring(new String[]{"alex","loves","leetcode"}));
        // gctaagttcatgcatc
        System.out.println(new Solution().shortestSuperstring(new String[]{"catg","ctaagt","gcta","ttca","atgcatc"}));
        System.out.println(new Solution().shortestSuperstring(new String[]{"gcta", "ctaagt", "ttca", "catg", "atgcatc"}));
    }

}
