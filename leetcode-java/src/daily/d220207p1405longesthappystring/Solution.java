package daily.d220207p1405longesthappystring;

import java.util.Arrays;

/**
 * 1405. Longest Happy String
 *
 * https://leetcode-cn.com/problems/longest-happy-string/
 *
 * A string s is called happy if it satisfies the following conditions:
 *
 * s only contains the letters 'a', 'b', and 'c'.
 * s does not contain any of "aaa", "bbb", or "ccc" as a substring.
 * s contains at most a occurrences of the letter 'a'.
 * s contains at most b occurrences of the letter 'b'.
 * s contains at most c occurrences of the letter 'c'.
 *
 * Given three integers a, b, and c, return the longest possible happy string.
 * If there are multiple longest happy strings, return any of them.
 * If there is no such string, return the empty string "".
 *
 * A substring is a contiguous sequence of characters within a string.
 */

public class Solution {

    private static class Pair {
        private int freq;
        private final char ch;
        public Pair(char ch, int freq) { this.freq = freq; this.ch = ch; }
    }

    public String longestDiverseString(int a, int b, int c) {
        StringBuilder sb = new StringBuilder();
        Pair[] pairs = new Pair[]{new Pair('a', a), new Pair('b', b), new Pair('c', c)};
        while (true) {
            boolean hasNext = false;
            Arrays.sort(pairs, (x, y) -> y.freq - x.freq);
            for (var pair : pairs) {
                if (pair.freq <= 0) break;

                int l = sb.length();
                if (l >= 2 && sb.charAt(l - 2) == pair.ch && sb.charAt(l - 1) == pair.ch) continue;

                hasNext = true;
                sb.append(pair.ch);
                pair.freq -= 1;
                break;
            }

            if (!hasNext) break;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Solution().longestDiverseString(1, 1, 7));
        System.out.println(new Solution().longestDiverseString(2, 2, 1));
        System.out.println(new Solution().longestDiverseString(7, 1, 0));
        System.out.println(new Solution().longestDiverseString(12, 0, 17));
    }

}
