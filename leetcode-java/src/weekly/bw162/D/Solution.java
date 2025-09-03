package weekly.bw162.D;

import common.Checker;
import common.Tag;

import java.util.*;

/**
 * Q4. Threshold Majority Queries
 *
 * https://leetcode.cn/contest/biweekly-contest-162/problems/threshold-majority-queries/
 *
 * You are given an integer array nums of length n and an array queries, where queries[i] = [li, ri, thresholdi].
 *
 * Return an array of integers ans where ans[i] is equal to the element in the
 * subarray nums[li...ri] that appears at least thresholdi times, selecting the element
 * with the highest frequency (choosing the smallest in case of a tie), or -1 if no such
 * element exists.
 */

public class Solution {

    // query = [l, r, threshold], 需要查询在 nums[l, r] 范围内出现频次 (需要大于 threshold) 最高且值最小的元素
    @Tag({"分块", "莫队", "回滚莫队"})
    public int[] subarrayMajority(int[] nums, int[][] queries) {
        record Query(int bid, int qi, int l, int r, int threshold) {}

        int B = (int) Math.ceil(nums.length / Math.sqrt(queries.length));
        List<Query> qs = new ArrayList<>();

        int[] ans = new int[queries.length]; Arrays.fill(ans, -1);
        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0], r = queries[i][1] + 1, t = queries[i][2]; // 保证左闭右开
            // 保证整体是跨区间的离线查询
            if (r - l > B) {
                qs.add(new Query(l / B, i, l, r, t));
                continue;
            }

            // 小区间直接暴力算
            for (int j = l; j < r; j++) add(nums[j]);
            if (maxFreq >= t) ans[i] = minValue;
            // 小区间计算完成之后重置数据, 每次最多只会有 B 次计算
            cnt.clear(); maxFreq = minValue = 0;
        }
        qs.sort((a, b) -> a.bid != b.bid ? (a.bid - b.bid) : (a.r - b.r));

        for (int i = 0, r = 0; i < qs.size(); i++) {
            var q = qs.get(i);

            // 计算当前区块的最右端点, 用于保证之后我们都是插入的逻辑且方便回滚
            int l0 = (q.bid + 1) * B;
            // 如果我们遍历到一个新的块的话, 我们就需要重置统计数据
            if (i == 0 || q.bid != qs.get(i - 1).bid) {
                r = l0; // 重置右端点的起始移动位置
                cnt.clear(); maxFreq = minValue = 0;
            }

            // 开始移动右端点直到查询位置
            for (; r < q.r; r++) add(nums[r]);

            // 左端点开始从 l0 移动到 q.l, 同时记录当前的统计数据用于之后会滚
            int initMaxFreq = maxFreq, initMinValue = minValue;
            for (int j = q.l; j < l0; j++) add(nums[j]);

            // 得到结果
            if (maxFreq >= q.threshold) ans[q.qi] = minValue;

            // 回滚统计数据
            maxFreq = initMaxFreq; minValue = initMinValue;
            for (int j = q.l; j < l0; j++) cnt.merge(nums[j], -1, Integer::sum);
        }

        return ans;
    }

    private final Map<Integer, Integer> cnt = new HashMap<>();
    private int maxFreq = 0, minValue = 0;

    private void add(int v) {
        int freq = cnt.merge(v, 1, Integer::sum);
        if (freq > maxFreq) { maxFreq = freq; minValue = v; }
        else if (freq == maxFreq && v < minValue) minValue = v;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().subarrayMajority(new int[]{15}, new int[][]{{0,0,1},{0,0,1},{0,0,1}}), new int[]{15, 15, 15});
    }

}
