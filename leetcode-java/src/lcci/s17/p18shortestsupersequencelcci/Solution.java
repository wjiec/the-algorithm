package lcci.s17.p18shortestsupersequencelcci;

import ability.Ability;
import common.Checker;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 面试题 17.18. 最短超串
 *
 * https://leetcode.cn/problems/shortest-supersequence-lcci/
 *
 * 假设你有两个数组，一个长一个短，短的元素均不相同。找到长数组中包含短数组所有的元素的最短子数组，其出现顺序无关紧要。
 *
 * 返回最短子数组的左端点和右端点，如有多个满足条件的子数组，返回左端点最小的一个。若不存在，返回空数组。
 */

public class Solution {

    public int[] shortestSeq(int[] big, int[] small) {
        Set<Integer> set = new HashSet<>();
        for (var v : small) set.add(v);

        int start = -1, count = big.length + 1;
        Map<Integer, Integer> map = new HashMap<>();
        for (int l = 0, r = 0; r < big.length; r++) {
            if (set.contains(big[r])) {
                map.merge(big[r], 1, Integer::sum);
            }

            while (l < r) {
                if (!set.contains(big[l])) l++;
                else if (map.get(big[l]) - 1 > 0) {
                    map.merge(big[l++], 1, Ability::subtract);
                } else break;
            }

            if (map.size() == set.size() && r - l + 1 < count) {
                count = r - (start = l) + 1;
            }
        }
        return start == -1 ? (new int[0]) : (new int[]{start, start + count - 1});
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().shortestSeq(new int[]{1,2,3}, new int[]{1,2,3}), new int[]{0, 2});

        assert Checker.check(new Solution().shortestSeq(new int[]{7,5,9,0,2,1,3,5,7,9,1,1,5,8,8,9,7}, new int[]{1,5,9}), new int[]{7, 10});
        assert Checker.check(new Solution().shortestSeq(new int[]{1,2,3}, new int[]{4}), new int[]{});
    }

}
