package problem.p1385findthedistancevaluebetweentwoarrays;

/**
 * 1385. Find the Distance Value Between Two Arrays
 *
 * https://leetcode-cn.com/problems/find-the-distance-value-between-two-arrays/
 *
 * Given two integer arrays arr1 and arr2, and the integer d, return the distance value between the two arrays.
 *
 * The distance value is defined as the number of elements arr1[i] such that
 * there is not any element arr2[j] where |arr1[i]-arr2[j]| <= d.
 */

public class Solution {

    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        int ans = 0;
        for (var a : arr1) {
            boolean ok = true;
            for (int i = 0; i < arr2.length && ok; i++) {
                ok = Math.abs(a - arr2[i]) > d;
            }

            if (ok) ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().findTheDistanceValue(new int[]{4,5,8}, new int[]{10,9,1,8}, 2) == 2;
        assert new Solution().findTheDistanceValue(new int[]{1,4,2,3}, new int[]{-4,-3,6,10,20,30}, 3) == 2;
        assert new Solution().findTheDistanceValue(new int[]{2,1,100,3}, new int[]{-5,-2,10,-3,7}, 6) == 1;
    }

}
