package problem.p460lfucache;

import java.util.*;

/**
 * 460. LFU Cache
 *
 * https://leetcode.cn/problems/lfu-cache/
 *
 * Design and implement a data structure for a Least Frequently Used (LFU) cache.
 *
 * Implement the LFUCache class:
 *
 * LFUCache(int capacity) Initializes the object with the capacity of the data structure.
 *
 * int get(int key) Gets the value of the key if the key exists in the cache. Otherwise, returns -1.
 *
 * void put(int key, int value) Update the value of the key if present, or inserts the key
 * if not already present. When the cache reaches its capacity, it should invalidate and remove
 * the least frequently used key before inserting a new item. For this problem, when there is a
 * tie (i.e., two or more keys with the same frequency), the least recently used key would be invalidated.
 *
 * To determine the least frequently used key, a use counter is maintained for each key in the cache.
 * The key with the smallest use counter is the least frequently used key.
 *
 * When a key is first inserted into the cache, its use counter is set to 1 (due to the put operation).
 * The use counter for a key in the cache is incremented either a get or put operation is called on it.
 *
 * The functions get and put must each run in O(1) average time complexity.
 */

public class Solution {

    private static class LFUCache {

        private final int capacity;
        private final Map<Integer, Integer> map = new HashMap<>();
        private final Map<Integer, Integer> cnt = new HashMap<>();
        private final TreeMap<Integer, Queue<int[]>> cSeq = new TreeMap<>();
        public LFUCache(int capacity) { this.capacity = capacity; }
        public int get(int key) { return use(key); }

        public void put(int key, int value) {
            if (map.size() == capacity && !map.containsKey(key)) {
                for (boolean cleaned = false; !cleaned; ) {
                    Queue<int[]> least = cSeq.firstEntry().getValue();
                    while (!least.isEmpty()) {
                        int[] curr = least.remove();
                        if (cnt.get(curr[0]) == curr[1]) {
                            map.remove(curr[0]);
                            cnt.remove(curr[0]);
                            cleaned = true;
                            break;
                        }
                    }
                    if (least.isEmpty()) cSeq.pollFirstEntry();
                }
            }
            map.put(key, value);
            use(key);
        }

        private int use(int key) {
            if (map.containsKey(key)) {
                int curr = cnt.merge(key, 1, Integer::sum);
                cSeq.computeIfAbsent(curr, v -> new ArrayDeque<>())
                    .add(new int[]{key, curr});
                return map.get(key);
            }
            return -1;
        }

    }

    public static void main(String[] args) {
        LFUCache lfu = new LFUCache(3);
        lfu.put(2, 2);
        lfu.put(1, 1);
        assert lfu.get(2) == 2;
        assert lfu.get(1) == 1;
        assert lfu.get(2) == 2;
        lfu.put(3, 3);
        lfu.put(4, 4);
        assert lfu.get(3) == -1;
        assert lfu.get(2) == 2;
        assert lfu.get(1) == 1;
        assert lfu.get(4) == 4;

        lfu = new LFUCache(2);
        lfu.put(1, 1);
        lfu.put(2, 2);
        assert lfu.get(1) == 1;
        lfu.put(3, 3);
        assert lfu.get(2) == -1;
        assert lfu.get(3) == 3;
        lfu.put(4, 4);
        assert lfu.get(1) == -1;
        assert lfu.get(3) == 3;
        assert lfu.get(4) == 4;
    }

}
