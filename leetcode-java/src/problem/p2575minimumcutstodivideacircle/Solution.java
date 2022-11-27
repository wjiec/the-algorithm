package problem.p2575minimumcutstodivideacircle;

/**
 * 6249. Minimum Cuts to Divide a Circle
 *
 * https://leetcode.cn/problems/minimum-cuts-to-divide-a-circle/
 *
 * A valid cut in a circle can be:
 *
 * A cut that is represented by a straight line that touches two points
 * on the edge of the circle and passes through its center, or
 *
 * A cut that is represented by a straight line that touches one point
 * on the edge of the circle and its center.
 *
 * Some valid and invalid cuts are shown in the figures below.
 *
 * Given the integer n, return the minimum number of cuts needed to divide a circle into n equal slices.
 */

public class Solution {

    public int numberOfCuts(int n) {
        return n == 1 ? 0 : (n % 2 == 0 ? n / 2 : n);
    }

    public static void main(String[] args) {
        assert new Solution().numberOfCuts(4) == 2;
        assert new Solution().numberOfCuts(3) == 3;
    }

}
