package problem.p809expressivewords;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * 809. Expressive Words
 *
 * https://leetcode.cn/problems/expressive-words/
 *
 * Sometimes people repeat letters to represent extra feeling. For example:
 *
 * "hello" -> "heeellooo"
 * "hi" -> "hiiii"
 * In these strings like "heeellooo", we have groups of adjacent letters
 * that are all the same: "h", "eee", "ll", "ooo".
 *
 * You are given a string s and an array of query strings words.
 * A query word is stretchy if it can be made to be equal to s by
 * any number of applications of the following extension operation:
 *  choose a group consisting of characters c, and add some number of characters c
 *  to the group so that the size of the group is three or more.
 *
 * For example, starting with "hello", we could do an extension on the group "o" to get "hellooo",
 * but we cannot get "helloo" since the group "oo" has a size less than three.
 *
 * Also, we could do another extension like "ll" -> "lllll" to get "helllllooo".
 * If s = "helllllooo", then the query word "hello" would be stretchy because of these
 * two extension operations: query = "hello" -> "hellooo" -> "helllllooo" = s.
 *
 * Return the number of query strings that are stretchy.
 */

public class Solution {

    private static class Node {
        private int n = 1;
        private final char c;
        public Node(char c) { this.c = c; }
        @Override public String toString() { return String.format("<%c %d>", c, n); }
    }

    public int expressiveWords(String s, String[] words) {
        if (words == null || words.length == 0) return 0;

        LinkedList<Node> nodes = new LinkedList<>();
        for (var c : s.toCharArray()) {
            if (nodes.size() == 0 || nodes.getLast().c != c) nodes.add(new Node(c));
            else nodes.getLast().n += 1;
        }

        int ans = 0;
        for (var word : words) {
            if (word.length() > s.length() || word.length() == 0) continue;
            if (word.length() == s.length() && word.equals(s)) ans++;

            boolean stretchy = true;
            Node curr = new Node(word.charAt(0));
            Iterator<Node> iter = nodes.iterator();
            for (int i = 1, n = word.length(); i <= n; i++) {
                if (i == n || word.charAt(i) != curr.c) {
                    if (iter.hasNext()) {
                        Node target = iter.next();
                        if (target.c != curr.c || (target.n < 3 && target.n != curr.n) || (curr.n > target.n)) {
                            stretchy = false;
                            break;
                        }

                        if (i < n) curr = new Node(word.charAt(i));
                    } else {
                        stretchy = false;
                        break;
                    }
                } else curr.n += 1;
            }

            if (stretchy && !iter.hasNext()) ans++;
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().expressiveWords("heeellooo", new String[]{"hello", "hi", "helo"}) == 1;
        assert new Solution().expressiveWords("zzzzzyyyyy", new String[]{"zzyy","zy","zyy"}) == 3;
    }

}
