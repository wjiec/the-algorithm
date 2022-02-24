package problem.p146lrucache;

import java.util.HashMap;
import java.util.Map;

/**
 * 146. LRU Cache
 *
 * https://leetcode-cn.com/problems/lru-cache/
 *
 * Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
 *
 * Implement the LRUCache class:
 *
 * LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
 *
 * int get(int key) Return the value of the key if the key exists, otherwise return -1.
 *
 * void put(int key, int value) Update the value of the key if the key exists. Otherwise,
 * add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation,
 * evict the least recently used key.
 *
 * The functions get and put must each run in O(1) average time complexity.
 */

public class Solution {

    private static class LRUCache {
        private static class DequeLinkedNode {
            private int key;
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
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        assert cache.get(1) == 1;

        cache.put(3, 3);
        assert cache.get(2) == -1;

        cache.put(4, 4);
        assert cache.get(1) == -1;
        assert cache.get(3) == 3;
        assert cache.get(4) == 4;
    }

}
