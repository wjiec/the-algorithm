package lcci.s17.p26sparsesimilaritylcci;

import common.PrettyPrinter;

import java.util.*;

/**
 * 面试题 17.26. 稀疏相似度
 *
 * https://leetcode.cn/problems/sparse-similarity-lcci/
 *
 * 两个(具有不同单词的)文档的交集(intersection)中元素的个数除以并集(union)中元素的个数，就是这两个文档的相似度。
 * 例如，{1, 5, 3} 和 {1, 7, 2, 3} 的相似度是 0.4，其中，交集的元素有 2 个，并集的元素有 5 个。
 * 给定一系列的长篇文档，每个文档元素各不相同，并与一个 ID 相关联。它们的相似度非常“稀疏”，也就是说任选 2 个文档，
 * 相似度都很接近 0。请设计一个算法返回每对文档的 ID 及其相似度。只需输出相似度大于 0 的组合。请忽略空文档。
 * 为简单起见，可以假定每个文档由一个含有不同整数的数组表示。
 *
 * 输入为一个二维数组 docs，docs[i] 表示 id 为 i 的文档。返回一个数组，其中每个元素是一个字符串，
 * 代表每对相似度大于 0 的文档，其格式为 {id1},{id2}: {similarity}，其中 id1 为两个文档中较小的
 * id，similarity 为相似度，精确到小数点后 4 位。以任意顺序返回数组均可。
 */

@SuppressWarnings("unchecked")
public class Solution {

    public List<String> computeSimilarities(int[][] docs) {
        Set<Integer>[] uniqueDocs = new Set[docs.length];
        for (int i = 0; i < docs.length; i++) {
            uniqueDocs[i] = new HashSet<>();
            for (var v : docs[i]) uniqueDocs[i].add(v);
        }

        // {string: [docId]}
        Map<Integer, Set<Integer>> back = new HashMap<>();
        for (int i = 0; i < docs.length; i++) {
            for (var word : uniqueDocs[i]) {
                back.computeIfAbsent(word, v -> new HashSet<>()).add(i);
            }
        }

        // {smaller: {larger: intersections}}
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        for (var values : back.values()) {
            for (var a : values) {
                for (var b : values) {
                    if (!a.equals(b)) {
                        map.computeIfAbsent(Math.min(a, b), v -> new HashMap<>())
                            .merge(Math.max(a, b), 1, Integer::sum);
                    }
                }
            }
        }

        List<String> ans = new ArrayList<>();
        for (var smaller : map.entrySet()) {
            int a = smaller.getKey();
            int al = uniqueDocs[a].size();
            for (var larger : smaller.getValue().entrySet()) {
                int b = larger.getKey();
                int bl = uniqueDocs[b].size();
                double intersections = larger.getValue() / 2.0;
                ans.add(String.format("%d,%d: %.4f", a, b, intersections / (al + bl - intersections)));
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        PrettyPrinter.println(new Solution().computeSimilarities(new int[][]{
            {14, 15, 100, 9, 3},
            {32, 1, 9, 3, 5},
            {15, 29, 2, 6, 8, 7},
            {7, 10}
        }));
    }

}
