package problem.p1011capacitytoshippackageswithinddays;

/**
 * 1011. Capacity To Ship Packages Within D Days
 *
 * https://leetcode.cn/problems/capacity-to-ship-packages-within-d-days/
 *
 * A conveyor belt has packages that must be shipped from one port to another within days days.
 *
 * The ith package on the conveyor belt has a weight of weights[i]. Each day, we
 * load the ship with packages on the conveyor belt (in the order given by weights).
 * We may not load more weight than the maximum weight capacity of the ship.
 *
 * Return the least weight capacity of the ship that will result in all the packages
 * on the conveyor belt being shipped within days days.
 */

public class Solution {

    public int shipWithinDays(int[] weights, int days) {
        int sum = 0, max = 0;
        for (int w : weights) {
            sum += w;
            if (w > max) max = w;
        }

        int l = max, r = sum;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (check(weights, mid, days)) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    private boolean check(int []weights, int capacity, int days) {
        int curr = 0;
        for (int weight : weights) {
            if (curr + weight > capacity) {
                if (--days < 0) return false;
                curr = 0;
            }
            curr += weight;
        }
        return days >= (curr == 0 ? 0 : 1);
    }

    public static void main(String[] args) {
        assert new Solution().shipWithinDays(new int[]{1,2,3,4,5,6,7,8,9,10}, 5) == 15;
        assert new Solution().shipWithinDays(new int[]{3,2,2,4,1,4}, 3) == 6;
    }

}
