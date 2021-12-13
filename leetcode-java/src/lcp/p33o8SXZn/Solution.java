package lcp.p33o8SXZn;

/**
 * LCP 33. 蓄水
 *
 * https://leetcode-cn.com/problems/o8SXZn/
 *
 * 给定 N 个无限容量且初始均空的水缸，每个水缸配有一个水桶用来打水，第 i 个水缸配备的水桶容量记作 bucket[i]。小扣有以下两种操作：
 *
 * 升级水桶：选择任意一个水桶，使其容量增加为 bucket[i]+1
 * 蓄水：将全部水桶接满水，倒入各自对应的水缸
 *
 * 每个水缸对应最低蓄水量记作 vat[i]，返回小扣至少需要多少次操作可以完成所有水缸蓄水要求。
 *
 * 注意：实际蓄水量 达到或超过 最低蓄水量，即完成蓄水要求。
 */

public class Solution {

    public int storeWater(int[] bucket, int[] vat) {
        int maxCapacity = 0;
        for (var n : vat) maxCapacity = Math.max(maxCapacity, n);
        if (maxCapacity == 0) return 0;

        int ans = Integer.MAX_VALUE;
        for (int c = 1; c <= maxCapacity; c++) {
            int curr = c;
            for (int i = 0; i < bucket.length; i++) {
                int least = (int) Math.ceil(((double) vat[i]) / c);
                curr += Math.max(least - bucket[i], 0);
            }
            ans = Math.min(ans, curr);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().storeWater(new int[]{1,3}, new int[]{6,8}) == 4;
        assert new Solution().storeWater(new int[]{9,0,1}, new int[]{0,2,2}) == 3;
    }

}
