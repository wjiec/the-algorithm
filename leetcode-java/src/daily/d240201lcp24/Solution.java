package daily.d240201lcp24;

import common.Checker;

import java.util.PriorityQueue;

/**
 * LCP 24. 数字游戏
 *
 * https://leetcode.cn/problems/5TxKeK/description
 *
 * 小扣在秋日市集入口处发现了一个数字游戏。主办方共有 N 个计数器，计数器编号为 0 ~ N-1。
 * 每个计数器上分别显示了一个数字，小扣按计数器编号升序将所显示的数字记于数组 nums。
 * 每个计数器上有两个按钮，分别可以实现将显示数字加一或减一。小扣每一次操作可以选择一个计数器，按下加一或减一按钮。
 *
 * 主办方请小扣回答出一个长度为 N 的数组，第 i 个元素(0 <= i < N)表示将 0~i 号计数器 初始 所示数字
 * 操作成满足所有条件 nums[a]+1 == nums[a+1],(0 <= a < i) 的最小操作数。回答正确方可进入秋日市集。
 *
 * 由于答案可能很大，请将每个最小操作数对 1,000,000,007 取余。
 */

public class Solution {

    public int[] numsGame(int[] nums) {
        long los = 0, his = 0; int MOD = 1_000_000_007;
        PriorityQueue<Integer> hi = new PriorityQueue<>();
        PriorityQueue<Integer> lo = new PriorityQueue<>((a, b) -> b - a);

        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int v = nums[i] - i;
            if (lo.isEmpty() || lo.peek() >= v) {
                lo.add(v); los += v;
                if (lo.size() > hi.size() + 1) {
                    int x = lo.remove();
                    los -= x; his += x; hi.add(x);
                }
            } else {
                hi.add(v); his += v;
                if (lo.size() < hi.size()) {
                    int x = hi.remove();
                    los += x; his -= x; lo.add(x);
                }
            }

            assert !lo.isEmpty();
            if ((i & 1) == 1) ans[i] = (int) ((his - los) % MOD);
            else ans[i] = (int) ((his - los + lo.peek()) % MOD);
        }

        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().numsGame(new int[]{1,1,1,2,3,4}), new int[]{0,1,2,3,3,3});
        assert Checker.check(new Solution().numsGame(new int[]{3,4,5,1,6,7}), new int[]{0,0,0,5,6,7});
        assert Checker.check(new Solution().numsGame(new int[]{1,2,3,4,5}), new int[]{0,0,0,0,0});
    }

}
