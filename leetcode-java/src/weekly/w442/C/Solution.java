package weekly.w442.C;

/**
 * 3494. Find the Minimum Amount of Time to Brew Potions
 *
 * https://leetcode.cn/contest/weekly-contest-442/problems/find-the-minimum-amount-of-time-to-brew-potions/
 *
 * You are given two integer arrays, skill and mana, of length n and m, respectively.
 *
 * In a laboratory, n wizards must brew m potions in order. Each potion has a mana capacity
 * mana[j] and must pass through all the wizards sequentially to be brewed properly.
 *
 * The time taken by the ith wizard on the jth potion is time_ij = skill[i] * mana[j].
 *
 * Since the brewing process is delicate, a potion must be passed to the next wizard immediately
 * after the current wizard completes their work. This means the timing must be synchronized
 * so that each wizard begins working on a potion exactly when it arrives.
 *
 * Return the minimum amount of time required for the potions to be brewed properly.
 */

public class Solution {

    // 第 i 个巫师在第 j 个药水上处理需要的时间为 time_ij = skill[i] * mana[j]
    public long minTime(int[] skill, int[] mana) {
        // 在处理第 j = 0 个药水时, 所有的巫师都是空闲的, 可以直接按顺序来执行
        //  - 在处理这个药水时, 我们可以计算得到每个巫师的空闲开始时间
        //  - 对于第 j + 1 个药水和第 i 个巫师
        //      - 首先他要是空闲的
        //      - 需要等待 sum(skill[0 ... (i - 1)] * mana[j + 1]) 才可以开始处理
        long start = 0;
        for (int j = 0; j < mana.length; j++) {
            long nextStart = 0, nextWait = 0;
            //noinspection ForLoopReplaceableByForEach
            for (int i = 0; i < skill.length; i++) {
                // 当前巫师的空闲时间, 从 idle 开始都是空闲的
                long idle = start += (long) skill[i] * mana[j];
                // 对于下一个药水, 至少需要等待 nextWait 时间, 有以下公式
                //  nextStart + nextWait >= idle
                //      => nextStart >= idle - nextWait
                nextStart = Math.max(nextStart, idle - nextWait);

                // 累加下一个药水的等待时间
                if (j + 1 < mana.length) nextWait += (long) skill[i] * mana[j + 1];
            }

            start = nextStart;
        }
        return start;
    }

    public static void main(String[] args) {
        assert new Solution().minTime(new int[]{1,5,2,4}, new int[]{5,1,4,2}) == 110;
    }

}
