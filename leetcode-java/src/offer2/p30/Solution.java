package offer2.p30;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 剑指 Offer II 030. 插入、删除和随机访问都是 O(1) 的容器
 *
 * https://leetcode.cn/problems/FortPu/
 *
 * 设计一个支持在平均 时间复杂度 O(1) 下，执行以下操作的数据结构：
 *
 * insert(val)：当元素 val 不存在时返回 true ，并向集合中插入该项，否则返回 false 。
 * remove(val)：当元素 val 存在时返回 true ，并从集合中移除该项，否则返回 false 。
 * getRandom：随机返回现有集合中的一项。每个元素应该有 相同的概率 被返回。
 */

public class Solution {

    private static class RandomizedSet {
        private int top = 0;
        private final int[] values = new int[200_001];
        private final Map<Integer, Integer> unique = new HashMap<>();
        public RandomizedSet() {}

        /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
        public boolean insert(int val) {
            if (unique.containsKey(val)) return false;

            values[top] = val;
            unique.put(val, top++);
            return true;
        }

        /** Removes a value from the set. Returns true if the set contained the specified element. */
        public boolean remove(int val) {
            if (unique.containsKey(val)) {
                int idx = unique.remove(val);

                if (--top != idx) {
                    int lastValue = values[top];
                    unique.put(lastValue, idx); // 李代桃僵
                    values[idx] = lastValue;
                }

                return true;
            }
            return false;
        }

        /** Get a random element from the set. */
        public int getRandom() { return values[new Random().nextInt(top)]; }
    }

    public static void main(String[] args) {
        RandomizedSet randomSet = new RandomizedSet();
        assert randomSet.insert(1);
        assert !randomSet.remove(2);
        assert randomSet.insert(2);
        System.out.println(randomSet.getRandom());
        System.out.println(randomSet.getRandom());
        assert randomSet.remove(1);
        assert !randomSet.insert(2);
        assert randomSet.getRandom() == 2;

        randomSet = new RandomizedSet();
        assert !randomSet.remove(0);
        assert !randomSet.remove(0);
        assert randomSet.insert(0);
        System.out.println(randomSet.getRandom());
        System.out.println(randomSet.getRandom());
        assert randomSet.remove(0);
        assert randomSet.insert(0);
    }

}
