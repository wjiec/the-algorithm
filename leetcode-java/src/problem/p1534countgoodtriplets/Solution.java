package problem.p1534countgoodtriplets;

/**
 * 1534. Count Good Triplets
 *
 * https://leetcode-cn.com/problems/count-good-triplets/
 *
 * Given an array of integers arr, and three integers a, b and c. You need to find the number of good triplets.
 *
 * A triplet (arr[i], arr[j], arr[k]) is good if the following conditions are true:
 *
 * 0 <= i < j < k < arr.length
 * |arr[i] - arr[j]| <= a
 * |arr[j] - arr[k]| <= b
 * |arr[i] - arr[k]| <= c
 *
 * Where |x| denotes the absolute value of x.
 *
 * Return the number of good triplets.
 */

public class Solution {

    public int countGoodTriplets(int[] arr, int a, int b, int c) {
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (Math.abs(arr[i] - arr[j]) <= a) {
                    for (int k = j + 1; k < arr.length; k++) {
                        if (Math.abs(arr[j] - arr[k]) <= b && Math.abs(arr[k] - arr[i]) <= c) {
                            ans++;
                        }
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().countGoodTriplets(new int[]{3,0,1,1,9,7}, 7, 2, 3) == 4;
        assert new Solution().countGoodTriplets(new int[]{1,1,2,2,3}, 0, 0, 1) == 0;
    }

}
