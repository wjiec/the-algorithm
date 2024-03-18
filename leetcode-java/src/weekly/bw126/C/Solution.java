package weekly.bw126.C;

import java.util.PriorityQueue;

/**
 * 100249. Replace Question Marks in String to Minimize Its Value
 *
 * https://leetcode.cn/contest/biweekly-contest-126/problems/replace-question-marks-in-string-to-minimize-its-value/
 *
 * You are given a string s. s[i] is either a lowercase English letter or '?'.
 *
 * For a string t having length m containing only lowercase English letters, we define the
 * function cost(i) for an index i as the number of characters equal to t[i] that appeared
 * before it, i.e. in the range [0, i - 1].
 *
 * The value of t is the sum of cost(i) for all indices i.
 *
 * For example, for the string t = "aab":
 *
 * cost(0) = 0
 * cost(1) = 1
 * cost(2) = 0
 * Hence, the value of "aab" is 0 + 1 + 0 = 1.
 * Your task is to replace all occurrences of '?' in s with any lowercase
 * English letter so that the value of s is minimized.
 *
 * Return a string denoting the modified string with replaced occurrences of '?'.
 * If there are multiple strings resulting in the minimum value, return
 * the lexicographically smallest one.
 */

public class Solution {

    public String minimizeStringValue(String s) {
        int[] count = new int[128];
        for (var c : s.toCharArray()) count[c]++;

        PriorityQueue<Character> pq = new PriorityQueue<>();
        for (var c : s.toCharArray()) {
            if (c == '?') {
                c = findMinimize(count);
                pq.add(c); count[c]++;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (var c : s.toCharArray()) {
            if (c == '?') c = pq.remove();
            sb.append(c);
        }

        return sb.toString();
    }

    private char findMinimize(int[] count) {
        char ans = 'z'; int min = count['z'];
        for (char c = 'y'; c >= 'a'; c--) {
            if (count[c] <= min) {
                ans = c; min = count[c];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minimizeStringValue("abcdefghijklmnopqrstuvwxy??").equals("abcdefghijklmnopqrstuvwxyaz");

        assert new Solution().minimizeStringValue("???").equals("abc");
        assert new Solution().minimizeStringValue("a?a?").equals("abac");
    }

}
