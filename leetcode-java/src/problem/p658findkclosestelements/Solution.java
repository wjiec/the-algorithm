package problem.p658findkclosestelements;

import java.util.ArrayList;
import java.util.List;

/**
 * 658. Find K Closest Elements
 *
 * https://leetcode-cn.com/problems/find-k-closest-elements/
 *
 * Given a sorted integer array arr, two integers k and x, return the k closest integers to x in the array.
 * The result should also be sorted in ascending order.
 *
 * An integer a is closer to x than an integer b if:
 *
 * |a - x| < |b - x|, or
 * |a - x| == |b - x| and a < b
 */

public class Solution {

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int l = 0, r = arr.length - k;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (x - arr[mid] <= arr[mid + k] - x) r = mid;
            else l = mid + 1;
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < k; i++) ans.add(arr[l + i]);
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().findClosestElements(new int[]{1,2,3,4,5}, 4, 3).equals(List.of(1,2,3,4));
        assert new Solution().findClosestElements(new int[]{1,2,3,4,5}, 4, -1).equals(List.of(1,2,3,4));

        assert new Solution().findClosestElements(new int[]{1,20,55,90,100}, 3, 56).equals(List.of(20,55,90));
        assert new Solution().findClosestElements(new int[]{1,20,55,90,100}, 3, 88).equals(List.of(55,90,100));
    }

}
