package problem.p767reorganizestring;

import java.util.PriorityQueue;

/**
 * 767. Reorganize String
 *
 * https://leetcode.cn/problems/reorganize-string/
 *
 * Given a string s, rearrange the characters of s so that any two adjacent characters are not the same.
 *
 * Return any possible rearrangement of s or return "" if not possible.
 */

public class Solution {

    public String reorganizeString(String s) {
        int[] count = new int[128];
        for (var c : s.toCharArray()) count[c]++;

        // [char, count]
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        for (int i = 'a'; i < count.length; i++) {
            if (count[i] != 0) queue.add(new int[]{i, count[i]});
        }

        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            int[] a = queue.remove(), b = queue.poll();

            sb.append((char) a[0]);
            if (b == null) {
                if (sb.length() != s.length()) return "";
                else break;
            }

            sb.append((char) b[0]);
            if (--a[1] != 0) queue.add(a);
            if (--b[1] != 0) queue.add(b);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        assert new Solution().reorganizeString("aab").equals("aba");
        assert new Solution().reorganizeString("aaab").equals("");
        assert new Solution().reorganizeString("a").equals("a");
    }

}
