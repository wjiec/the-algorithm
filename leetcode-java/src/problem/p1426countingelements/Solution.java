package problem.p1426countingelements;

import java.util.HashMap;
import java.util.Map;

/**
 * 1426. Counting Elements
 *
 * https://leetcode-cn.com/problems/counting-elements/
 *
 * Given an integer array arr, count how many elements x there are, such that x + 1 is also in arr.
 *
 * If there are duplicates in arr, count them separately.
 */

public class Solution {

    public int countElements(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (var n : arr) map.merge(n, 1, Integer::sum);

        int ans = 0;
        for (var n : map.keySet()) ans += map.containsKey(n + 1) ? map.get(n) : 0;
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().countElements(new int[]{1,2,3}) == 2;
        assert new Solution().countElements(new int[]{1,1,3,3,5,5,7,7}) == 0;
        assert new Solution().countElements(new int[]{1,3,2,3,5,0}) == 3;
        assert new Solution().countElements(new int[]{1,1,2,2}) == 2;
    }

}
