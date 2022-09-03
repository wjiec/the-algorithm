package ability;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

// 图论算法模板
@SuppressWarnings({"unchecked", "SpellCheckingInspection"})
public class Graph {

    // 有向图
    public static class Directed {
        // 图中的顶点数
        protected final int vertices;

        // 图中的所有边
        protected final Set<Integer>[] edges;

        // 初始化一个有向图
        public Directed(int n) {
            vertices = n; edges = new Set[vertices];
            for (int i = 0; i < vertices; i++) {
                edges[i] = new HashSet<>();
            }
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

}
