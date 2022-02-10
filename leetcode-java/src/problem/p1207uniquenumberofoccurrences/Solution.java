package problem.p1207uniquenumberofoccurrences;

import java.util.Arrays;

/**
 * 1207. Unique Number of Occurrences
 *
 * https://leetcode-cn.com/problems/unique-number-of-occurrences/
 *
 * Given an array of integers arr, write a function that returns true
 * if and only if the number of occurrences of each value in the array is unique.
 */

public class Solution {

    public boolean uniqueOccurrences(int[] arr) {
        Arrays.sort(arr);

        int x = 1001, count = 0;
        boolean[] visited = new boolean[1001];
        for (var n : arr) {
            if (n != x) {
                if (visited[count]) return false;
                visited[count] = true;
                x = n; count = 1;
            } else count++;
        }
        return !visited[count];
    }

    public static void main(String[] args) {
        assert new Solution().uniqueOccurrences(new int[]{1,2,2,1,1,3});
        assert !new Solution().uniqueOccurrences(new int[]{1,2});
        assert new Solution().uniqueOccurrences(new int[]{-3,0,1,-3,1,1,1,-3,10,0});
    }

}
