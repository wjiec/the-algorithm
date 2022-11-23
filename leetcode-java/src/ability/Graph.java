package ability;

import java.util.*;

// 图论算法模板
@SuppressWarnings({"unchecked", "SpellCheckingInspection"})
public class Graph {

    // 图中的顶点数
    protected final int vertices;

    // 图中的所有边
    protected final Set<Integer>[] edges;

    // 构造一个图
    public Graph(int n) {
        this.vertices = n; edges = new Set[vertices];
        for (int i = 0; i < vertices; i++) {
            edges[i] = new HashSet<>();
        }
    }

    // 有向图
    public static class Directed extends Graph {
        public Directed(int n) { super(n); }
    }

    // 无向图
    public static class Undirected extends Graph {
        public Undirected(int n) { super(n); }

        // 为图中增加一条边, 无向图中需要双向匹配
        public void addEdge(int a, int b) {
            edges[a].add(b); edges[b].add(a);
        }
    }

    // 带权重的无向图
    public static class UndirectedWithWeight extends Undirected {
        // 图中顶点之间的权重
        protected final Map<Integer, Map<Integer, Integer>> weight;

        // 初始化带权重的无向图
        public UndirectedWithWeight(int n) {
            super(n); weight = new HashMap<>();
        }

        // 为图中增加一条边, 其权重为 w
        public void addEdge(int a, int b, int w) {
            addEdge(a, b);
            weight.computeIfAbsent(a, v -> new HashMap<>()).put(b, w);
            weight.computeIfAbsent(b, v -> new HashMap<>()).put(a, w);
        }
    }

    // 记录每个顶点入度的有向图
    public static class InDirected extends Directed {
        // 记录每个顶点的入度
        // indegree[i] 表示多少个顶点依赖编号为 i 的顶点
        // 即编号为 i 的任务的前置任务的数量
        protected final int[] indegree;

        // 初始化入度表
        public InDirected(int n) {
            super(n); indegree = new int[n];
        }

        // 为图中增加一条从 src 指向 dst 的边
        public void addEdge(int src, int dst) {
            // 记录从 src 出发可以到达的 dst 顶点集合
            if (edges[src].add(dst)) {
                // 顶点 dst 增加了一个前置依赖 src
                indegree[dst]++;
            }
        }

        // 更通俗形式的为图中增加一条边
        public void addTask(int target, int prerequisite) {
            addEdge(prerequisite, target);
        }
    }

    // 拓扑排序 - 对于图 G 中的任意一条有向边 (u, v), u 在排列中都出现在 v 的前面
    //  - 一般用来"排序"具有依赖关系的任务
    //  - 要求图中不能存在环
    @SuppressWarnings("DuplicatedCode")
    public static class TopologicalSort extends InDirected {
        public TopologicalSort(int n) { super(n); }

        // 返回一个数组表示完成任务的顺序
        public int[] sort() {
            Queue<Integer> queue = new ArrayDeque<>();
            // 将所有入度为 0 的顶点放入队列中
            // 这意味着这些任务没有任何前置依赖, 可以以任意的顺序返回
            for (int i = 0; i < vertices; i++) {
                if (indegree[i] == 0) queue.add(i);
            }

            int index = 0;
            int[] sorted = new int[vertices];
            while (!queue.isEmpty()) {
                // 入度为 0 的节点意味着没有前置依赖了, 可以直接填入答案中
                int u = queue.remove();
                sorted[index++] = u;

                // 此时我们从 u 出发可以到达的所有 v 节点的入度减少 1
                // 这意味着我们完成了节点 v 的一个前置任务
                for (var v : edges[u]) {
                    // 当节点 v 的入度为 0 时, 我们将其加入队列中
                    // 此时意味着 v 的所有前置任务已经完成
                    if (--indegree[v] == 0) {
                        queue.add(v);
                    }
                }
            }

            // 节点中可能存在环
            if (index != vertices) return new int[0];
            return sorted;
        }
    }

    // 最小生成树
    //  - 计算带权重的有向图中连接所有顶点的最小代价
    public static class Kruskal { }

    // 单源最短路径
    //  - 求解非负权图上单源最短路径的算法
    public static class Dijkstra extends UndirectedWithWeight {
        public Dijkstra(int n) { super(n); }

        private static final long INF = Long.MAX_VALUE / 2;

        // 返回一个数组表示每个顶点距离 source 的最短路径
        public long[] distance(long source) {
            long[] distance = new long[vertices];
            Arrays.fill(distance, INF);

            // [node, distance] 距离
            PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(v -> v[1]));
            pq.add(new long[]{source, 0});

            // 对那些刚刚被加入集合的结点的所有出边执行松弛操作
            while (pq.isEmpty()) {
                long[] curr = pq.remove();
                int idx = (int) curr[0];

                if (distance[idx] <= curr[1]) continue;
                distance[idx] = curr[1];

                for (var next : edges[idx]) {
                    if (distance[next] >= INF) {
                        pq.add(new long[]{next, curr[1] + weight.get(idx).get(next)});
                    }
                }
            }

            return distance;
        }
    }

}
