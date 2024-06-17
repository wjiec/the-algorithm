package weekly.w402.C;

import java.util.TreeMap;

/**
 * 3186. Maximum Total Damage With Spell Casting
 *
 * https://leetcode.cn/contest/weekly-contest-402/problems/maximum-total-damage-with-spell-casting/
 *
 * A magician has various spells.
 *
 * You are given an array power, where each element represents the damage of a spell.
 * Multiple spells can have the same damage value.
 *
 * It is a known fact that if a magician decides to cast a spell with a damage of power[i], they
 * cannot cast any spell with a damage of power[i] - 2, power[i] - 1, power[i] + 1, or power[i] + 2.
 *
 * Each spell can be cast only once.
 *
 * Return the maximum possible total damage that a magician can cast.
 */

public class Solution {

    public long maximumTotalDamage(int[] power) {
        TreeMap<Integer, Long> map = new TreeMap<>();
        for (var v : power) map.merge(v, (long) v, Long::sum);

        TreeMap<Integer, Long> dp = new TreeMap<>();
        dp.put(Integer.MIN_VALUE, 0L);

        long ans = 0;
        for (var kv : map.entrySet()) {
            dp.put(kv.getKey(), Math.max(dp.lowerEntry(kv.getKey() - 2).getValue() + kv.getValue(), dp.lastEntry().getValue()));
            ans = Math.max(ans, dp.get(kv.getKey()));
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maximumTotalDamage(new int[]{5,9,2,10,2,7,10,9,3,8}) == 31;

        assert new Solution().maximumTotalDamage(new int[]{1,1,3,4}) == 6;
        assert new Solution().maximumTotalDamage(new int[]{7,1,6,6}) == 13;
    }

}
