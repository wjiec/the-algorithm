package weekly.w362.A;

import java.util.List;

/**
 * 2848. Points That Intersect With Cars
 *
 * https://leetcode.cn/contest/weekly-contest-362/problems/points-that-intersect-with-cars/
 *
 * You are given a 0-indexed 2D integer array nums representing the coordinates of the cars
 * parking on a number line. For any index i, nums[i] = [starti, endi] where starti is the
 * starting point of the ith car and endi is the ending point of the ith car.
 *
 * Return the number of integer points on the line that are covered with any part of a car.
 */

public class Solution {

    public int numberOfPoints(List<List<Integer>> nums) {
        int[] diff = new int[102];
        for (var car : nums) {
            diff[car.get(0)]++;
            diff[car.get(1) + 1]--;
        }

        int ans = 0;
        for (int i = 1; i < diff.length; i++) {
            diff[i] += diff[i - 1];
            if (diff[i] > 0) ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
