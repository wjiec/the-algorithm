package offer2.p66;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer II 066. 单词之和
 *
 * https://leetcode.cn/problems/z1R5dt/
 *
 * 实现一个 MapSum 类，支持两个方法，insert 和 sum：
 *
 * MapSum() 初始化 MapSum 对象
 * void insert(String key, int val) 插入 key-val 键值对，字符串表示键 key ，整数表示值 val 。
 * 如果键 key 已经存在，那么原来的键值对将被替代成新的键值对。
 * int sum(string prefix) 返回所有以该前缀 prefix 开头的键 key 的值的总和。
 */

public class Solution {

    private static class MapSum {
        private static class Trie {
            private int value = 0;
            private final Trie[] children = new Trie[26];
            public Trie get(char c) { return children[c - 'a']; }
            public Trie set(char c) {
                if (children[c - 'a'] == null) {
                    children[c - 'a'] = new Trie();
                }
                return children[c - 'a'];
            }
        }

        private final Trie root = new Trie();
        private final Map<String, Integer> map = new HashMap<>();
        public MapSum() {}
        public void insert(String key, int val) {
            Integer old = map.put(key, val);
            int delta = val - (old == null ? 0 : old);
            Trie curr = root;
            for (var c : key.toCharArray()) {
                curr = curr.set(c);
                curr.value += delta;
            }
        }

        public int sum(String prefix) {
            Trie curr = root;
            for (var c : prefix.toCharArray()) {
                curr = curr.get(c);
                if (curr == null) return 0;
            }
            return curr.value;
        }
    }

    public static void main(String[] args) {
        MapSum mapSum = new MapSum();
        mapSum.insert("apple", 3);
        assert mapSum.sum("ap") == 3;
        mapSum.insert("app", 2);
        mapSum.insert("apple", 2);
        assert mapSum.sum("ap") == 4;
    }

}
