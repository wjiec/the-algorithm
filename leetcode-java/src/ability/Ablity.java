package ability;

public class Ablity {

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

    // 拓扑排序, 对有向无环图中的节点进行排序
    public static class TopologicalSort {
    }

    // 最小生成树
    public static class Kruskal {
        public void addEdge(int src, int dst, int weight) {
        }
    }

}
