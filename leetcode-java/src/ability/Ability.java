package ability;

import java.util.HashMap;
import java.util.Map;

// 通用算法以及一些通用方法
public class Ability {

    // 不可变键值对
    public record Pair<K, V>(K key, V value) {}

    // indexOf 找到数组中值 v 所在的位置, 如果找不到则返回 -1
    public static int indexOf(int[] list, int v) {
        for (int i = 0; i < list.length; i++) {
            if (list[i] == v) return i;
        }
        return -1;
    }

    // 返回 a - b 的结果，如果结果为 0 则返回 null
    public static Integer subtract(Integer a, Integer b) {
        return a - b == 0 ? null : a - b;
    }

    // 一些数学的方法
    public static class Math {
        // 等差数列(arithmetic progression)
        public static class AP {
            // 等差数列求和
            public static long sum(long a1, long an, long d) {
                return ((an - a1) / d + 1) * (a1 + an) / 2;
            }
        }

        // 最大公约数
        public static int gcd(int a, int b) {
            return b == 0 ? a : gcd(b, a % b);
        }
        // 长整形最大公约数
        public static long gcd(long a, long b) {
            return b == 0 ? a : gcd(b, a % b);
        }

        // 最小公倍数
        public static int lcm(int a, int b) {
            return a / gcd(a, b) * b;
        }
        // 长整形最小公倍数
        public static long lcm(long a, long b) {
            return a / gcd(a, b) * b;
        }

        // 快速幂算法, 求 (base ^ pow) % mod 的值
        public static long pow(long base, long pow, long mod) {
            long ans = 1;
            while (pow > 0) {
                if ((pow & 1) != 0) {
                    ans = (ans * base) % mod;
                }

                base = (base * base) % mod;
                pow >>= 1;
            }
            return ans;
        }
    }

    // 简单并查集
    public static class UnionFind {
        // 记录每个节点的父节点是谁, 默认都指向自己
        private final int[] parent;

        // 初始化并查集, 设置每个节点的父节点为自己
        public UnionFind(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        // 合并两个节点
        public void union(int a, int b) {
            parent[find(a)] = find(b);
        }

        // 查找指定节点的父节点同时压缩树
        public int find(int v) {
            while (v != parent[v]) {
                parent[v] = parent[parent[v]];
                v = parent[v];
            }
            return v;
        }
    }

    // 字典树
    public static class Trie<T> {
        // 记录所有的子树
        private final Map<T, Trie<T>> children;
        // 初始化字典树
        public Trie() { children = new HashMap<>(); }

        // 如果字典树中不存在指定的子树则创建并返回该树
        public Trie<T> add(T val) {
            return children.computeIfAbsent(val, v -> new Trie<>());
        }

        // 从字典树中获取一个指定的子树
        public Trie<T> get(T val) {
            return children.get(val);
        }
    }

}
