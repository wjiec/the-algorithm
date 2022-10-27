package lcp.p23;

/**
 * LCP 23. 魔术排列
 *
 * https://leetcode.cn/problems/er94lq/
 *
 * 秋日市集上，魔术师邀请小扣与他互动。魔术师的道具为分别写有数字 1~N 的 N 张卡牌，
 * 然后请小扣思考一个 N 张卡牌的排列 target。
 *
 * 魔术师的目标是找到一个数字 k（k >= 1），使得初始排列顺序为 1~N 的卡牌经过特殊的洗牌方式
 * 最终变成小扣所想的排列 target，特殊的洗牌方式为：
 *
 * 第一步，魔术师将当前位于 偶数位置 的卡牌（下标自 1 开始），保持 当前排列顺序 放在位于 奇数位置 的卡牌之前。
 * 例如：将当前排列 [1,2,3,4,5] 位于偶数位置的 [2,4] 置于奇数位置的 [1,3,5] 前，排列变为 [2,4,1,3,5]；
 *
 * 第二步，若当前卡牌数量小于等于 k，则魔术师按排列顺序取走全部卡牌；若当前卡牌数量大于 k，则取走前 k 张卡牌，
 * 剩余卡牌继续重复这两个步骤，直至所有卡牌全部被取走；
 *
 * 卡牌按照魔术师取走顺序构成的新排列为「魔术取数排列」，请返回是否存在这个数字 k 使得「魔术取数排列」恰好
 * 就是 target，从而让小扣感到大吃一惊。
 */

public class Solution {

    public boolean isMagic(int[] target) {
        int n = target.length, idx = 0;
        int[] initial = new int[n];

        for (int v = 2; v <= n; v += 2) initial[idx++] = v;
        for (int v = 1; v <= n; v += 2) initial[idx++] = v;

        int k = 0;
        while (k < n && target[k] == initial[k]) k++;
        if (k == 0) return false;

        int targetIdx = k;
        while (targetIdx < n) {
            initial = rollout(initial, k);
            for (int i = 0; i < k && targetIdx < n; i++) {
                if (target[targetIdx++] != initial[i]) {
                    return false;
                }
            }
        }
        return targetIdx == n;
    }

    private int[] rollout(int[] curr, int k) {
        int idx = 0, n = curr.length;
        int[] next = new int[n - k];
        for (int i = k + 1; i < n; i += 2) next[idx++] = curr[i];
        for (int i = k; i < n; i += 2) next[idx++] = curr[i];
        return next;
    }

    public static void main(String[] args) {
        assert new Solution().isMagic(new int[]{2,4,3,1,5});
        assert !new Solution().isMagic(new int[]{5,4,3,2,1});
    }

}
