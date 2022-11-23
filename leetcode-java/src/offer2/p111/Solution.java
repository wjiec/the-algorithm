package offer2.p111;

import common.Checker;
import common.TODO;
import common.Tag;

import java.util.*;

/**
 * 剑指 Offer II 111. 计算除法
 *
 * https://leetcode.cn/problems/vlzXQL/
 *
 * 给定一个变量对数组 equations 和一个实数值数组 values 作为已知条件，其中 equations[i] = [Ai, Bi]
 * 和 values[i] 共同表示等式 Ai / Bi = values[i] 。每个 Ai 或 Bi 是一个表示单个变量的字符串。
 *
 * 另有一些以数组 queries 表示的问题，其中 queries[j] = [Cj, Dj] 表示第 j 个问题，请你根据
 * 已知条件找出 Cj / Dj = ? 的结果作为答案。
 *
 * 返回 所有问题的答案 。如果存在某个无法确定的答案，则用 -1.0 替代这个答案。如果问题中出现了
 * 给定的已知条件中没有出现的字符串，也需要用 -1.0 替代这个答案。
 *
 * 注意：输入总是有效的。可以假设除法运算中不会出现除数为 0 的情况，且不存在任何矛盾的结果。
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    // {a: {b: k}} => (a = kb)
    private final Map<String, Map<String, Double>> map = new HashMap<>();

    @TODO(url = "https://leetcode.cn/problems/vlzXQL/solution/ji-suan-chu-fa-by-leetcode-solution-p731/")
    @Tag({"带权重的并查集", "Floyd"})
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        for (int i = 0; i < values.length; i++) {
            String a = equations.get(i).get(0);
            String b = equations.get(i).get(1);
            // a / b = values[i]
            map.computeIfAbsent(a, v -> new HashMap<>()).put(b, values[i]);
            map.computeIfAbsent(b, v -> new HashMap<>()).put(a, 1.0 / values[i]);
        }

        double[] ans = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            ans[i] = -1.0;

            String a = queries.get(i).get(0);
            String b = queries.get(i).get(1);
            if (map.containsKey(a) && map.containsKey(b)) {
                Map<String, Double> exp1 = exps(a);
                if (exp1.containsKey(b)) {
                    ans[i] = exp1.get(b);
                    continue;
                }

                Map<String, Double> exp2 = exps(b);
                for (var kv : exp1.entrySet()) {
                    if (exp2.containsKey(kv.getKey())) {
                        ans[i] = kv.getValue() / exp2.get(kv.getKey());
                        break;
                    }
                }
            }
        }

        return ans;
    }

    private record Exp(String x, double k) {}

    private Map<String, Double> exps(String x) {
        // {k: v} => x = kv
        Map<String, Double> ans = new HashMap<>();
        ans.put(x, 1.0);

        Queue<Exp> queue = new ArrayDeque<>();
        queue.add(new Exp(x, 1.0));

        while (!queue.isEmpty()) {
            Exp exp = queue.remove();
            for (var kv : map.get(exp.x).entrySet()) {
                if (!ans.containsKey(kv.getKey())) {
                    ans.put(kv.getKey(), exp.k * kv.getValue());
                    queue.add(new Exp(kv.getKey(), exp.k * kv.getValue()));
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().calcEquation(List.of(
            List.of("a","b"), List.of("c","d")
        ), new double[]{1.0,1.0}, List.of(
            List.of("a","c"), List.of("b","d"), List.of("b","a"),
            List.of("d","c")
        )), new double[]{-1.0, -1.0, 1.0, 1.0});

        assert Checker.check(new Solution().calcEquation(List.of(
            List.of("x1","x2"), List.of("x2","x3"), List.of("x3","x4"), List.of("x4","x5")
        ), new double[]{3.0,4.0,5.0,6.0}, List.of(
            List.of("x1","x5"), List.of("x5","x2"), List.of("x2","x4"),
            List.of("x2","x2"), List.of("x2","x9"), List.of("x9","x9")
        )), new double[]{360.0,0.00833,20.0,1.0,-1.0,-1.0});

        assert Checker.check(new Solution().calcEquation(List.of(
            List.of("a","b"), List.of("b","c")
        ), new double[]{2.0,3.0}, List.of(
            List.of("a","c"), List.of("b","a"), List.of("a","e"),
            List.of("a","a"), List.of("x","x")
        )), new double[]{6.00000,0.50000,-1.00000,1.00000,-1.00000});
    }

}
