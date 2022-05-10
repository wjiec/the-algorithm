package problem.p754reachanumber;

import common.TODO;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 754. Reach a Number
 *
 * https://leetcode.cn/problems/reach-a-number/
 *
 * You are standing at position 0 on an infinite number line. There is a destination at position target.
 *
 * You can make some number of moves numMoves so that:
 *
 * On each move, you can either go left or right.
 *
 * During the ith move (starting from i == 1 to i == numMoves),
 * you take i steps in the chosen direction.
 *
 * Given the integer target, return the minimum number of moves required
 * (i.e., the minimum numMoves) to reach the destination.
 */

public class Solution {

    @TODO(tips = "数学分析")
    @TODO(url = "https://leetcode.cn/problems/reach-a-number/solution/dao-da-zhong-dian-shu-zi-by-leetcode/")
    public int reachNumber(int target) {
        if (target < 0) target = -target;
        int ans = 0;
        while (target > 0) target -= ++ans;
        return target % 2 == 0 ? ans : ans + 1 + ans % 2;
    }

    public static void main(String[] args) {
        assert new Solution().reachNumber(2) == 3;
        assert new Solution().reachNumber(3) == 2;
        assert new Solution().reachNumber(3) == 2;
        assert new Solution().reachNumber(98765432) == 14055;
    }

}
