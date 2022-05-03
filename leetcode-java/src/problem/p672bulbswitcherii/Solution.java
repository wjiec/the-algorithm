package problem.p672bulbswitcherii;

import common.TODO;

/**
 * 672. Bulb Switcher II
 *
 * https://leetcode-cn.com/problems/bulb-switcher-ii/
 *
 * There is a room with n bulbs labeled from 1 to n that all are turned on initially, and four buttons on the wall.
 * Each of the four buttons has a different functionality where:
 *
 * Button 1: Flips the status of all the bulbs.
 * Button 2: Flips the status of all the bulbs with even labels (i.e., 2, 4, ...).
 * Button 3: Flips the status of all the bulbs with odd labels (i.e., 1, 3, ...).
 * Button 4: Flips the status of all the bulbs with a label j = 3k + 1 where k = 0, 1, 2, ... (i.e., 1, 4, 7, 10, ...).
 *
 * You must make exactly presses button presses in total. For each press,
 * you may pick any of the four buttons to press.
 *
 * Given the two integers n and presses, return the number of different possible
 * statuses after performing all presses button presses.
 */

public class Solution {

    @TODO(tips = "找规律")
    public int flipLights(int n, int presses) {
        if (n > 3) n = 3;

        if (presses == 0) return 1;
        if (presses == 1) return n == 1 ? 2 : n == 2 ? 3 : 4;
        if (presses == 2) return n == 1 ? 2 : n == 2 ? 4 : 7;
        return n == 1 ? 2 : n == 2 ? 4 : 8;
    }

    public static void main(String[] args) {
        assert new Solution().flipLights(1, 1) == 2;
        assert new Solution().flipLights(2, 1) == 3;
        assert new Solution().flipLights(3, 1) == 4;
        assert new Solution().flipLights(1, 0) == 1;
    }

}
