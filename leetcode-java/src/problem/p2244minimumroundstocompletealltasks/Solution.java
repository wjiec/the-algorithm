package problem.p2244minimumroundstocompletealltasks;

import java.util.HashMap;
import java.util.Map;

/**
 * 2244. Minimum Rounds to Complete All Tasks
 *
 * https://leetcode.cn/problems/minimum-rounds-to-complete-all-tasks/
 *
 * You are given a 0-indexed integer array tasks, where tasks[i] represents the
 * difficulty level of a task. In each round, you can complete either 2 or 3 tasks
 * of the same difficulty level.
 *
 * Return the minimum rounds required to complete all the tasks, or -1 if it is not
 * possible to complete all the tasks.
 */

public class Solution {

    public int minimumRounds(int[] tasks) {
        Map<Integer, Integer> map = new HashMap<>();
        for (var v : tasks) map.merge(v, 1, Integer::sum);

        int ans = 0;
        for (var count : map.values()) {
            if (count == 1) return -1;
            ans += count / 3;
            if (count % 3 != 0) ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minimumRounds(new int[]{5,5,5,5}) == 2;
        assert new Solution().minimumRounds(new int[]{7,7,7,7,7,7}) == 2;
        assert new Solution().minimumRounds(new int[]{2,2,3,3,2,4,4,4,4,4}) == 4;
        assert new Solution().minimumRounds(new int[]{2,3,3}) == -1;
    }

}
