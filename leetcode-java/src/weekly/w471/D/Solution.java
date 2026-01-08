package weekly.w471.D;

import common.Tag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Q4. Sum of Perfect Square Ancestors
 *
 * https://leetcode.cn/contest/weekly-contest-471/problems/sum-of-perfect-square-ancestors/
 *
 * You are given an integer n and an undirected tree rooted at node 0 with n nodes numbered from 0 to n - 1.
 *
 * This is represented by a 2D array edges of length n - 1, where edges[i] = [ui, vi]
 * indicates an undirected edge between nodes ui and vi.
 *
 * You are also given an integer array nums, where nums[i] is the positive integer assigned to node i.
 *
 * Define a value ti as the number of ancestors of node i such that the
 * product nums[i] * nums[ancestor] is a perfect square.
 *
 * Return the sum of all ti values for all nodes i in range [1, n - 1].
 *
 * Note:
 * In a rooted tree, the ancestors of node i are all nodes on the path
 * from node i to the root node 0, excluding i itself.
 */

@SuppressWarnings({"unchecked", "DuplicatedCode"})
public class Solution {

    private static final int MAX_N = 100_001;
    private static final int[] core = new int[MAX_N];
    static {
        // 对于 v1 = p1^e1 * p2^e2 (对 v 进行质因数分解), v2 = p3^e3 * p4^e4
        //  - 将其相乘之后得到 (p1^e1 * p2^e2) * (p3^e3 * p4^e4)
        //  - 我们将其中的所有指数中的 2 提取出来, 得到 (p1^e1' * p2^e2')^2 * (p3^e3' * p4^e4')^2
        //  - 现在我们只需要保证 (p1^e1' * p2^e2') * (p3^e3' * p4^e4') 为完全平方数即可
        //      - 也就是 (p1^e1' * p2^e2') == (p3^e3' * p4^e4')
        // 我们称其为 "平方剩余核 core"
        for (int i = 1; i < MAX_N; i++) {
            if (core[i] == 0) { // 当前 i 是一个平方剩余核
                for (int j = 1; i * j * j < MAX_N; j++) {
                    core[i * j * j] = i;
                }
            }
        }
    }

    @Tag({"平方剩余核", "无平方因子核"})
    public long sumOfAncestors(int n, int[][] edges, int[] nums) {
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (var edge : edges) {
            g[edge[0]].add(edge[1]);
            g[edge[1]].add(edge[0]);
        }

        return dfs(g, 0, -1, nums);
    }

    private final int[] freq = new int[MAX_N];

    private long dfs(List<Integer>[] g, int curr, int parent, int[] nums) {
        int c = core[nums[curr]]; long ans = freq[c]++;
        for (var next : g[curr]) {
            if (next != parent) {
                ans += dfs(g, next, curr, nums);
            }
        }
        freq[c]--; // 回滚
        return ans;
    }

    public static void main(String[] args) {
    }

}
