package problem.p781rabbitsinforest;

import java.util.HashMap;
import java.util.Map;

/**
 * 781. Rabbits in Forest
 *
 * https://leetcode.cn/problems/rabbits-in-forest/
 *
 * There is a forest with an unknown number of rabbits.
 * We asked n rabbits "How many rabbits have the same color as you?" and collected the answers
 * in an integer array answers where answers[i] is the answer of the ith rabbit.
 *
 * Given the array answers, return the minimum number of rabbits that could be in the forest.
 */

public class Solution {

    public int numRabbits(int[] answers) {
        Map<Integer, Integer> count = new HashMap<>();
        for (var answer : answers) count.merge(answer, 1, Integer::sum);

        int ans = 0;
        for (var kv : count.entrySet()) {
            int unit = kv.getKey() + 1, cnt = kv.getValue();
            ans += (cnt / unit) * unit;
            if (cnt % unit != 0) ans += unit;
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().numRabbits(new int[]{0,1,0,2,0,1,0,2,1,1}) == 11;
        assert new Solution().numRabbits(new int[]{1,0,1,0,0}) == 5;

        assert new Solution().numRabbits(new int[]{1,1,2}) == 5;
        assert new Solution().numRabbits(new int[]{10,10,10}) == 11;
    }

}
