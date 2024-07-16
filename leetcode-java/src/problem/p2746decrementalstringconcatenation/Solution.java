package problem.p2746decrementalstringconcatenation;

import java.util.HashMap;
import java.util.Map;

/**
 * 2746. Decremental String Concatenation
 *
 * https://leetcode.cn/problems/decremental-string-concatenation/
 *
 * You are given a 0-indexed array words containing n strings.
 *
 * Let's define a join operation join(x, y) between two strings x and y as concatenating them into xy.
 * However, if the last character of x is equal to the first character of y, one of them is deleted.
 *
 * For example join("ab", "ba") = "aba" and join("ab", "cde") = "abcde".
 *
 * You are to perform n - 1 join operations. Let str0 = words[0]. Starting from i = 1 up to i = n - 1, for the ith operation, you can do one of the following:
 *
 * Make stri = join(stri - 1, words[i])
 * Make stri = join(words[i], stri - 1)
 * Your task is to minimize the length of strn - 1.
 *
 * Return an integer denoting the minimum possible length of strn - 1.
 */

public class Solution {

    public int minimizeConcatenatedLength(String[] words) {
        Map<Integer, Integer> curr = new HashMap<>();
        curr.put(key(first(words[0]), last(words[0])), words[0].length());

        for (int i = 1; i < words.length; i++) {
            int cf = first(words[i]), cl = last(words[i]);
            Map<Integer, Integer> next = new HashMap<>();
            for (var elem : curr.entrySet()) {
                int s = elem.getKey() >> 16, e = elem.getKey() & 0xffff;
                // 把当前字符串拼在前面
                next.merge(key(cf, e), elem.getValue() + words[i].length() - (cl == s ? 1 : 0), Integer::min);
                // 把当前字符串拼在后面
                next.merge(key(s, cl), elem.getValue() + words[i].length() - (e == cf ? 1 : 0), Integer::min);
            }

            curr.clear(); curr.putAll(next);
        }

        int ans = Integer.MAX_VALUE;
        for (var v : curr.values()) ans = Math.min(ans, v);
        return ans;
    }

    private int first(String s) { return s.charAt(0) - 'a'; }
    private int last(String s) { return s.charAt(s.length() - 1) - 'a'; }
    private int key(int a, int b) { return a << 16 | b; }

    public static void main(String[] args) {
        assert new Solution().minimizeConcatenatedLength(new String[]{"aa","ab","bc"}) == 4;
        assert new Solution().minimizeConcatenatedLength(new String[]{"ab","b"}) == 2;
        assert new Solution().minimizeConcatenatedLength(new String[]{"aaa","c","aba"}) == 6;
    }

}
