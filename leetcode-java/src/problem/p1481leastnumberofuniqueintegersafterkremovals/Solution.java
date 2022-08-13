package problem.p1481leastnumberofuniqueintegersafterkremovals;

import java.util.*;

/**
 * 1481. Least Number of Unique Integers after K Removals
 *
 * https://leetcode.cn/problems/least-number-of-unique-integers-after-k-removals/
 *
 * Given an array of integers arr and an integer k. Find the least number of unique integers
 * after removing exactly k elements.
 */

public class Solution {

    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        if (k == arr.length) return 0;
        if (k == arr.length - 1) return 1;

        Map<Integer, Integer> map = new HashMap<>();
        for (var n : arr) map.merge(n, 1, Integer::sum);
        if (k == 0) return map.size();

        List<Map.Entry<Integer, Integer>> groups = new ArrayList<>(map.entrySet());
        groups.sort(Comparator.comparingInt(Map.Entry::getValue));

        int ans = map.size();
        for (int i = 0; k > 0; i++) {
            k -= groups.get(i).getValue();
            if (k >= 0) ans--;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().findLeastNumOfUniqueInts(new int[]{5,5,4}, 1) == 1;
        assert new Solution().findLeastNumOfUniqueInts(new int[]{4,3,1,1,3,3,2}, 3) == 2;
    }

}
