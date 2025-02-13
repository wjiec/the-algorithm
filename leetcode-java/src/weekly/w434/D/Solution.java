package weekly.w434.D;

import common.PrettyPrinter;

import java.util.*;

/**
 * 3435. Frequencies of Shortest Supersequences
 *
 * https://leetcode.cn/contest/weekly-contest-434/problems/frequencies-of-shortest-supersequences/
 *
 * You are given an array of strings words. Find all shortest common super-sequences (SCS) of words
 * that are not permutations of each other.
 *
 * A shortest common supersequence is a string of minimum length that contains
 * each string in words as a subsequence.
 *
 * Return a 2D array of integers freqs that represent all the SCSs.
 * Each freqs[i] is an array of size 26, representing the frequency of each
 * letter in the lowercase English alphabet for a single SCS.
 *
 * You may return the frequency arrays in any order.
 */

public class Solution {

    // words 中所有字符串由不超过 16 个互不相同的小写英文字母组成
    // words 中的字符串互不相同
    public List<List<Integer>> supersequences(String[] words) {
        // 一个字母在公共超序列中的出现次数不会多于 2 次, 我们将这个字母放在左右两边
        //  如 a...a 可以满足所有 a* 以及 *a 的需求
        //      - 同时 ab...ba 与 ba...ab 是相同的, 可以匹配所有的 a* b* *a *b
        // 首先找到所有需要的字母
        int charset = 0;
        boolean[][] g = new boolean[26][26];
        for (var word : words) {
            int a = word.charAt(0) - 'a', b = word.charAt(1) - 'a';
            g[a][b] = true;
            charset |= (1 << a) | (1 << b);
        }

        // 枚举 charset 的所有子集, 子集中的 0 表示出现 1 次, 1 表示出现 2 次
        Set<Integer> s = new HashSet<>(); int minSize = 31;
        for (int curr = charset, cnt = 0; cnt == 0 || curr != charset; curr = (curr - 1) & charset, cnt++) {
            int size = Integer.bitCount(curr);
            if (size <= minSize && !hasCycle(curr, g)) {
                if (size < minSize) {
                    s.clear();
                    minSize = size;
                }
                s.add(curr);
            }
        }

        // 构造答案
        List<List<Integer>> ans = new ArrayList<>();
        for (var curr : s) {
            Integer[] freq = new Integer[26];
            for (int i = 0; i < 26; i++) {
                freq[i] = 0; // 默认不存在该字母

                // 该字母在字符集中存在
                if ((charset & (1 << i)) != 0) freq[i] += 1;
                // 该字母在子集中为1, 表示有数量为 2
                if ((curr & (1 << i)) != 0) freq[i] += 1;
            }
            ans.add(Arrays.asList(freq));
        }
        return ans;
    }

    // 检查使用当前方案是否会导致环的出现(三色标记算法)
    private boolean hasCycle(int mask, boolean[][] g) {
        int[] color = new int[26];
        for (int i = 0; i < 26; i++) {
            // 如果当前字母没检查过且只出现一次则需要检查
            if (color[i] == 0 && (mask & (1 << i)) == 0 && hasCycle(i, color, g, mask)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasCycle(int curr, int[] color, boolean[][] g, int mask) {
        color[curr] = 1; // 标记为灰色
        for (int next = 0; next < 26; next++) {
            // 只检查有连边的且在 mask 中只有一个字母存在的
            if (g[curr][next] && (mask & (1 << next)) == 0) {
                if (color[next] == 1) return true;
                if (color[next] == 0 && hasCycle(next, color, g, mask)) return true;
            }
        }
        color[curr] = 2; // 标记为黑色
        return false;
    }

    public static void main(String[] args) {
        PrettyPrinter.println(new Solution().supersequences(new String[]{"lf"}));
        PrettyPrinter.println(new Solution().supersequences(new String[]{"ab", "ba"}));
        PrettyPrinter.println(new Solution().supersequences(new String[]{"aa", "ac"}));
        PrettyPrinter.println(new Solution().supersequences(new String[]{"aa", "bb", "cc"}));
    }

}
