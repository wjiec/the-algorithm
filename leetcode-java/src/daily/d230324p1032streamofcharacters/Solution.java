package daily.d230324p1032streamofcharacters;

import ability.Ability;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 1032. Stream of Characters
 *
 * https://leetcode.cn/problems/stream-of-characters/
 *
 * Design an algorithm that accepts a stream of characters and checks
 * if a suffix of these characters is a string of a given array of strings words.
 *
 * For example, if words = ["abc", "xyz"] and the stream added the four
 * characters (one by one) 'a', 'x', 'y', and 'z', your algorithm should
 * detect that the suffix "xyz" of the characters "axyz" matches "xyz" from words.
 *
 * Implement the StreamChecker class:
 *
 * StreamChecker(String[] words) Initializes the object with the strings array words.
 * boolean query(char letter) Accepts a new character from the stream and
 * returns true if any non-empty suffix from the stream forms a word that is in words.
 */

public class Solution {

    static class StreamChecker {
        private final Ability.AlphaTrie root = new Ability.AlphaTrie();
        public StreamChecker(String[] words) {
            for (var word : words) root.set(word).asLeaf();
        }

        private final Queue<Ability.AlphaTrie> tries = new ArrayDeque<>();

        public boolean query(char letter) {
            tries.add(root);
            boolean ans = false;
            for (int i = 0, n = tries.size(); i < n; i++) {
                Ability.AlphaTrie curr = tries.remove().get(letter);
                if (curr != null) {
                    ans = ans || curr.isLeaf();
                    tries.add(curr);
                }
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        StreamChecker streamChecker = new StreamChecker(new String[]{"cd", "f", "kl"});
        assert !streamChecker.query('a');
        assert !streamChecker.query('b');
        assert !streamChecker.query('c');
        assert streamChecker.query('d');
        assert !streamChecker.query('e');
        assert streamChecker.query('f');
        assert !streamChecker.query('g');
        assert !streamChecker.query('h');
        assert !streamChecker.query('i');
        assert !streamChecker.query('j');
        assert !streamChecker.query('k');
        assert streamChecker.query('l');
    }

}
