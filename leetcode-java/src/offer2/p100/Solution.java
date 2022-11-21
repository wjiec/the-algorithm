package offer2.p100;

import java.util.ArrayList;
import java.util.List;

/**
 * 剑指 Offer II 100. 三角形中最小路径之和
 *
 * https://leetcode.cn/problems/IlPe0q/
 *
 * 给定一个三角形 triangle ，找出自顶向下的最小路径和。
 *
 * 每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于
 * 上一层结点下标 + 1 的两个结点。也就是说，如果正位于当前行的下标 i ，那么
 * 下一步可以移动到下一行的下标 i 或 i + 1 。
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    private final int INF = Integer.MAX_VALUE / 2;

    public int minimumTotal(List<List<Integer>> triangle) {
        for (int i = 1; i < triangle.size(); i++) {
            List<Integer> curr = triangle.get(i);
            List<Integer> prev = triangle.get(i - 1);
            int prevSize = prev.size(), currSize = curr.size();

            for (int j = 0; j < currSize; j++) {
                int a = j - 1 >= 0 ? prev.get(j - 1) : INF;
                int b = j < prevSize ? prev.get(j) : INF;
                triangle.get(i).set(j, Math.min(a, b) + curr.get(j));
            }
        }

        int ans = INF, n = triangle.size();
        for (int i = 0; i < n; i++) {
            ans = Math.min(ans, triangle.get(n - 1).get(i));
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minimumTotal(new ArrayList<>(List.of(
            new ArrayList<>(List.of(2)),
            new ArrayList<>(List.of(3,4)),
            new ArrayList<>(List.of(6,5,7)),
            new ArrayList<>(List.of(4,1,8,3))
        ))) == 11;

        assert new Solution().minimumTotal(List.of(
            List.of(-10)
        )) == -10;
    }

}
