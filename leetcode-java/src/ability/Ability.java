package ability;

// 通用算法以及一些通用方法
public class Ability {

    // indexOf 找到数组中值 v 所在的位置, 如果找不到则返回 -1
    public static int indexOf(int[] list, int v) {
        for (int i = 0; i < list.length; i++) {
            if (list[i] == v) return i;
        }
        return -1;
    }

    // 不可变键值对
    public record Pair<K, V>(K key, V value) {}

    // 简单并查集
    public static class UnionFind {
        private final int[] parent;
        public UnionFind(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }
        public void union(int a, int b) {
            parent[find(a)] = find(b);
        }
        public int find(int v) {
            while (v != parent[v]) {
                parent[v] = parent[parent[v]];
                v = parent[v];
            }
            return v;
        }
    }

}
