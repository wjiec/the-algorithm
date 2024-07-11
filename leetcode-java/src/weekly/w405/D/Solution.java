package weekly.w405.D;

import common.Tag;

import java.util.*;

/**
 * 3213. Construct String with Minimum Cost
 *
 * https://leetcode.cn/contest/weekly-contest-405/problems/construct-string-with-minimum-cost/
 *
 * You are given a string target, an array of strings words, and an integer array costs, both
 * arrays of the same length.
 *
 * Imagine an empty string s.
 *
 * You can perform the following operation any number of times (including zero):
 *
 * Choose an index i in the range [0, words.length - 1].
 * Append words[i] to s.
 * The cost of operation is costs[i].
 *
 * Return the minimum cost to make s equal to target. If it's not possible, return -1.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    @Tag({"超时", "不知道怎么优化都是同一个字母的特殊情况"})
    public int minimumCost(String target, String[] words, int[] costs) {
        char[] targetChars = target.toCharArray();

        // 可以把构建target看成是从 0 到 n-1 的一个图, words 中是各种路径, 目的是找到最小的权重
        int[] charMinCost = new int[128];
        Arrays.fill(charMinCost, Integer.MAX_VALUE);
        Map<Integer, Map<Integer, Integer>> g = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            int n = words[i].length();
            if (n == 1) {
                char c = words[i].charAt(0);
                charMinCost[c] = Math.min(charMinCost[c], costs[i]);
                continue;
            }

            for (var match : kmp(targetChars, words[i].toCharArray())) {
                g.computeIfAbsent(match, k -> new HashMap<>()).merge(match + n, costs[i], Integer::min);
            }
        }

        // 然后根据 Dijkstra 求解
        long[] distance = new long[target.length() + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);

        // [node, distance]
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(v -> v[1]));
        pq.add(new long[]{0, 0});

        // 对那些刚刚被加入集合的结点的所有出边执行松弛操作
        while (!pq.isEmpty()) {
            long[] curr = pq.remove();
            int idx = (int) curr[0];

            if (distance[idx] <= curr[1]) continue;
            distance[idx] = curr[1];

            if (g.containsKey(idx)) {
                for (var next : g.get(idx).entrySet()) {
                    if (distance[next.getKey()] >= Integer.MAX_VALUE) {
                        pq.add(new long[]{next.getKey(), curr[1] + next.getValue()});
                    }
                }
            }
            if (idx < targetChars.length && charMinCost[targetChars[idx]] != Integer.MAX_VALUE) {
                if (distance[idx + 1] >= Integer.MAX_VALUE) {
                    pq.add(new long[]{idx + 1, curr[1] + charMinCost[targetChars[idx]]});
                }
            }
        }

        return distance[target.length()] >= Integer.MAX_VALUE ? -1 : (int) distance[target.length()];
    }

    private List<Integer> kmp(char[] array, char[] target) {
        int[] next = lps(target);
        List<Integer> ans = new ArrayList<>();
        for (int i = 0, j = 0; i < array.length; ) {
            if (array[i] == target[j]) {
                i++; j++;
            } else {
                if (j != 0) j = next[j - 1];
                else i++;
            }

            if (j == target.length) {
                ans.add(i - j);
                i -= j - 1;
                j = 0;
            }
        }

        return ans;
    }
    
    private int[] lps(char[] array) {
        int[] next = new int[array.length];
        for (int i = 1, j = 0; i < array.length; ) {
            if (array[i] == array[j]) {
                next[i++] = ++j;
            } else {
                if (j != 0) j = next[j - 1];
                else next[i++] = 0;
            }
        }
        return next;
    }

    public static void main(String[] args) {
        assert new Solution().minimumCost("abcdef", new String[]{"abdef","abc","d","def","ef"}, new int[]{100,1,1,10,5}) == 7;
    }

}
