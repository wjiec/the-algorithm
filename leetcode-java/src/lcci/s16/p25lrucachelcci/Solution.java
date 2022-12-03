package lcci.s16.p25lrucachelcci;

import java.util.HashMap;
import java.util.Map;

/**
 * 面试题 16.25. LRU 缓存
 *
 * https://leetcode.cn/problems/lru-cache-lcci/
 *
 * 设计和构建一个“最近最少使用”缓存，该缓存会删除最近最少使用的项目。缓存应该从键映射到值(允许你插入和检索特定键对应的值)，
 * 并在初始化时指定最大容量。当缓存被填满时，它应该删除最近最少使用的项目。
 *
 * 它应该支持以下操作： 获取数据 get 和 写入数据 put 。
 *
 * 获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
 * 写入数据 put(key, value) - 如果密钥不存在，则写入其数据值。当缓存容量达到上限时，
 * 它应该在写入新数据之前删除最近最少使用的数据值，从而为新的数据值留出空间。
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    private static class LRUCache {
        private static class Node {
            private final int key;
            private final int value;
            private Node prev = null;
            private Node next = null;
            public Node(int k, int v) { key = k; value = v; }
            private void remove() { prev.next = next; next.prev = prev; }
            private void insert(Node node) {
                node.prev = this;
                node.next = next;
                next.prev = node;
                next = node;
            }
        }

        private final int capacity;
        private final Node head = new Node(0, 0);
        private final Node tail = new Node(0, 0);
        private final Map<Integer, Node> map = new HashMap<>();
        public LRUCache(int capacity) {
            this.capacity = capacity;
            head.next = tail;
            tail.prev = head;
        }
        public int get(int key) {
            Node node = map.get(key);
            if (node == null) return -1;

            node.remove();
            head.insert(node);
            return node.value;
        }

        public void put(int key, int value) {
            if (map.containsKey(key)) {
                map.get(key).remove();
            } else {
                if (map.size() == capacity) {
                    map.remove(tail.prev.key);
                    tail.prev.remove();
                }
            }

            Node node = new Node(key, value);
            map.put(key, node);
            head.insert(node);
        }
    }

    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1);
        lRUCache.put(2, 2);
        assert lRUCache.get(1) == 1;
        lRUCache.put(3, 3);
        assert lRUCache.get(2) == -1;
        lRUCache.put(4, 4);
        assert lRUCache.get(1) == -1;
        assert lRUCache.get(3) == 3;
        assert lRUCache.get(4) == 4;
    }

}
