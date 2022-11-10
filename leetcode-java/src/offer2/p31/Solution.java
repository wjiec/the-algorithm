package offer2.p31;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer II 031. 最近最少使用缓存
 *
 * https://leetcode.cn/problems/OrIXps/
 *
 * 运用所掌握的数据结构，设计和实现一个  LRU (Least Recently Used，最近最少使用) 缓存机制 。
 *
 * 实现 LRUCache 类：
 *
 * LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value) 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。
 * 当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    private static class LRUCache {
        private static class DequeLinkedNode {
            private final int key;
            private int value;
            private DequeLinkedNode prev;
            private DequeLinkedNode next;
            public DequeLinkedNode() { key = 0; value = 0; }
            public DequeLinkedNode(int key, int value) { this.key = key; this.value = value; }
        }

        private final int cap;
        private final Map<Integer, DequeLinkedNode> map;

        private int size = 0;
        private final DequeLinkedNode head;
        private final DequeLinkedNode tail;

        public LRUCache(int capacity) {
            cap = capacity;
            map = new HashMap<>();

            head = new DequeLinkedNode();
            tail = new DequeLinkedNode();
            head.next = tail;
            tail.prev = head;
        }

        public int get(int key) {
            DequeLinkedNode node = map.get(key);
            if (node == null) {
                return -1;
            }

            moveToHead(node);
            return node.value;
        }

        public void put(int key, int value) {
            DequeLinkedNode node = map.get(key);
            if (node == null) {
                DequeLinkedNode newNode = new DequeLinkedNode(key, value);
                map.put(key, newNode);
                addToHead(newNode);
                if (++size > cap) {
                    DequeLinkedNode tail = removeTail();
                    map.remove(tail.key);
                    size--;
                }
            } else {
                node.value = value;
                moveToHead(node);
            }
        }

        private void moveToHead(DequeLinkedNode node) {
            addToHead(removeNode(node));
        }

        private void addToHead(DequeLinkedNode node) {
            DequeLinkedNode origin = head.next;

            node.prev = head;
            head.next = node;

            node.next = origin;
            origin.prev = node;
        }

        private DequeLinkedNode removeNode(DequeLinkedNode node) {
            DequeLinkedNode prev = node.prev, next = node.next;

            prev.next = next;
            next.prev = prev;

            node.prev = null;
            node.next = null;
            return node;
        }

        private DequeLinkedNode removeTail() {
            return removeNode(tail.prev);
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
