package problem.p1566detectpatternoflengthmrepeatedkormoretimes;

/**
 * 1566. Detect Pattern of Length M Repeated K or More Times
 *
 * https://leetcode-cn.com/problems/detect-pattern-of-length-m-repeated-k-or-more-times/
 *
 * Given an array of positive integers arr,  find a pattern of length m that is repeated k or more times.
 *
 * A pattern is a subarray (consecutive sub-sequence) that consists of one or more values,
 * repeated multiple times consecutively without overlapping.
 *
 * A pattern is defined by its length and the number of repetitions.
 *
 * Return true if there exists a pattern of length m that is repeated k or more times, otherwise return false.
 */

public class Solution {

    public boolean containsPattern(int[] arr, int m, int k) {
        int l = arr.length, base = m * k, end = l - base;
        if (end < 0) return false;

        for (int i = 0; i <= end; i++) {
            int j = 0;
            for (j = 0; j < base; j++) {
                if (arr[i + j % m] != arr[i + j]) {
                    break;
                }
            }
            if (j == base) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        assert new Solution().containsPattern(new int[]{2,2}, 1, 2);

        assert new Solution().containsPattern(new int[]{1,2,4,4,4,4}, 1, 3);
        assert new Solution().containsPattern(new int[]{1,2,1,2,1,1,1,3}, 2, 2);
        assert !new Solution().containsPattern(new int[]{1,2,1,2,1,3}, 2, 3);
        assert !new Solution().containsPattern(new int[]{1,2,3,1,2}, 2, 2);
        assert !new Solution().containsPattern(new int[]{2,2,2,2}, 2, 3);
    }

}
