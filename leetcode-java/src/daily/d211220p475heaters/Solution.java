package daily.d211220p475heaters;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 475. Heaters
 *
 * https://leetcode-cn.com/problems/heaters/
 *
 * Winter is coming! During the contest, your first job is to design a standard
 * heater with a fixed warm radius to warm all the houses.
 *
 * Every house can be warmed, as long as the house is within the heater's warm radius range. 
 *
 * Given the positions of houses and heaters on a horizontal line, return the minimum radius
 * standard of heaters so that those heaters could cover all houses.
 *
 * Notice that all the heaters follow your radius standard, and the warm radius will the same.
 */

public class Solution {

    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);

        int ans = 0;
        for (int l = 0, r = 0; l < houses.length; l++) {
            int dist = Math.abs(houses[l] - heaters[r]);
            while (r < heaters.length - 1 && Math.abs(houses[l] - heaters[r]) >= Math.abs(houses[l] - heaters[r + 1])) {
                r++;
                dist = Math.min(dist, Math.abs(houses[l] - heaters[r]));
            }
            ans = Math.max(ans, dist);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().findRadius(new int[]{1,2,3}, new int[]{2}) == 1;
        assert new Solution().findRadius(new int[]{1,2,3,4}, new int[]{1,4}) == 1;
        assert new Solution().findRadius(new int[]{1,5}, new int[]{2}) == 3;
        assert new Solution().findRadius(new int[]{1,99}, new int[]{2,98}) == 1;
    }

}
