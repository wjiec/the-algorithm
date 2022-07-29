package problem.p1338reducearraysizetothehalf;

import java.util.Arrays;

/**
 * 1338. Reduce Array Size to The Half
 *
 * https://leetcode.cn/problems/reduce-array-size-to-the-half/
 *
 * You are given an integer array arr. You can choose a set of integers and remove all
 * the occurrences of these integers in the array.
 *
 * Return the minimum size of the set so that at least half of the integers of the array are removed.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public int minSetSize(int[] arr) {
        int[] freq = new int[10_0001];
        for (var v : arr) freq[v]++;
        Arrays.sort(freq);

        int ans = 0, curr = 0;
        for (int i = freq.length - 1; i >= 0; i--) {
            ans++; curr += freq[i];
            if (curr >= arr.length / 2) break;
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minSetSize(new int[]{3,3,3,3,5,5,5,2,2,7}) == 2;
        assert new Solution().minSetSize(new int[]{7,7,7,7,7,7}) == 1;
    }

}
