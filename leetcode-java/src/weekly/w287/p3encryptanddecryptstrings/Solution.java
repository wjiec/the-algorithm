package weekly.w287.p3encryptanddecryptstrings;

import java.util.*;

/**
 * 5302. Encrypt and Decrypt Strings
 *
 * https://leetcode-cn.com/contest/weekly-contest-287/problems/encrypt-and-decrypt-strings/
 *
 * You are given a character array keys containing unique characters and a string array values
 * containing strings of length 2. You are also given another string array dictionary that
 * contains all permitted original strings after decryption.
 *
 * You should implement a data structure that can encrypt or decrypt a 0-indexed string.
 *
 * A string is encrypted with the following process:
 *
 * For each character c in the string, we find the index i satisfying keys[i] == c in keys.
 * Replace c with values[i] in the string.
 * A string is decrypted with the following process:
 *
 * For each substring s of length 2 occurring at an even index in the string,
 * we find an i such that values[i] == s. If there are multiple valid i, we choose any one of them.
 * This means a string could have multiple possible strings it can decrypt to.
 * Replace s with keys[i] in the string.
 * Implement the Encrypter class:
 *
 * Encrypter(char[] keys, String[] values, String[] dictionary) Initializes
 * the Encrypter class with keys, values, and dictionary.
 *
 * String encrypt(String word1) Encrypts word1 with the encryption process described
 * above and returns the encrypted string.
 *
 * int decrypt(String word2) Returns the number of possible strings word2
 * could decrypt to that also appear in dictionary.
 */

public class Solution {

    private static class Encrypter {
        private static class Trie {
            private boolean leaf = false;
            private final Trie[] children = new Trie[26];
            public Trie get(char c) { return children[c - 'a']; }
            public Trie set(char c) {
                if (children[c - 'a'] == null) {
                    children[c - 'a'] = new Trie();
                }
                return children[c - 'a'];
            }
        }

        private final String[] values;
        private final int[] map = new int[128];
        private final Trie trie = new Trie();
        private final Map<String, Set<Character>> dec = new HashMap<>();
        public Encrypter(char[] keys, String[] values, String[] dictionary) {
            this.values = values;
            for (int i = 0; i < keys.length; i++) map[keys[i]] = i;
            for (var word : dictionary) {
                Trie curr = trie;
                for (var c : word.toCharArray()) curr = curr.set(c);
                curr.leaf = true;
            }
            for (int i = 0; i < values.length; i++) {
                if (!dec.containsKey(values[i])) {
                    dec.put(values[i], new HashSet<>());
                }
                dec.get(values[i]).add(keys[i]);
            }
        }

        public String encrypt(String word1) {
            StringBuilder sb = new StringBuilder();
            for (var c : word1.toCharArray()) sb.append(values[map[c]]);
            return sb.toString();
        }

        private int ans = 0;

        public int decrypt(String word2) {
            ans = 0;
            List<Set<Character>> cs = new ArrayList<>();
            for (int i = 0, n = word2.length(); i < n; i += 2) {
                String k = word2.charAt(i) + "" + word2.charAt(i + 1);
                if (!dec.containsKey(k)) return 0;
                cs.add(dec.get(k));
            }
            dfs(cs, 0, trie);
            return ans;
        }

        private void dfs(List<Set<Character>> cs, int i, Trie trie) {
            if (trie == null) return;
            if (i == cs.size()) {
                if (trie.leaf) ans++;
                return;
            }

            for (var c : cs.get(i)) {
                dfs(cs, i + 1, trie.get(c));
            }
        }
    }

    public static void main(String[] args) {
        Encrypter encrypter = new Encrypter(new char[]{'a', 'b', 'c', 'd'},
            new String[]{"ei", "zf", "ei", "am"},
            new String[]{"abcd", "acbd", "adbc", "badc", "dacb", "cadb", "cbda", "abad"}
        );

        assert encrypter.encrypt("abcd").equals("eizfeiam");
        assert encrypter.decrypt("eizfeiam") == 2;
    }

}
