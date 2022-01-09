package problem.p1710maximumunitsonatruck;

import java.util.Arrays;

/**
 * 1710. Maximum Units on a Truck
 *
 * https://leetcode-cn.com/problems/maximum-units-on-a-truck/
 *
 * You are assigned to put some amount of boxes onto one truck.
 *
 * You are given a 2D array boxTypes, where boxTypes[i] = [numberOfBoxesi, numberOfUnitsPerBoxi]:
 *
 * numberOfBoxesi is the number of boxes of type i.
 * numberOfUnitsPerBoxi is the number of units in each box of the type i.
 *
 * You are also given an integer truckSize, which is the maximum number of boxes that can be put on the truck.
 *
 * You can choose any boxes to put on the truck as long as the number of boxes does not exceed truckSize.
 *
 * Return the maximum total number of units that can be put on the truck.
 */

public class Solution {

    public int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, (a, b) -> b[1] - a[1]);

        int ans = 0;
        for (var box : boxTypes) {
            ans += box[1] * Math.min(truckSize, box[0]);
            truckSize -= box[0];
            if (truckSize <= 0) break;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maximumUnits(new int[][]{{1,3}, {2,2}, {3,1}}, 4) == 8;
        assert new Solution().maximumUnits(new int[][]{{5,10}, {2,5}, {4,7}, {3,9}}, 10) == 91;
    }

}
