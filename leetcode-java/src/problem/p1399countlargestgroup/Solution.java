package problem.p1399countlargestgroup;

/**
 * 1399. Count Largest Group
 *
 * https://leetcode-cn.com/problems/count-largest-group/
 *
 * Given an integer n. Each number from 1 to n is grouped according to the sum of its digits. 
 *
 * Return how many groups have the largest size.
 */

public class Solution {

    public int countLargestGroup(int n) {
        int[] map = new int[37];
        for (int i = 1; i <= n; i++) {
            int count = 0;
            for (int x = i; x != 0; x /= 10) count += x % 10;
            map[count]++;
        }

        int max = 0, ans = 0;
        for (int value : map) {
            if (value > max) {
                max = value;
                ans = 1;
            } else if (value == max) {
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().countLargestGroup(13) == 4;
        assert new Solution().countLargestGroup(2) == 2;
        assert new Solution().countLargestGroup(15) == 6;
        assert new Solution().countLargestGroup(24) == 5;
    }

}
