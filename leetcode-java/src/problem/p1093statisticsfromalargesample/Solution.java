package problem.p1093statisticsfromalargesample;

import common.Checker;

/**
 * 1093. Statistics from a Large Sample
 *
 * https://leetcode.cn/problems/statistics-from-a-large-sample/
 *
 * You are given a large sample of integers in the range [0, 255]. Since the sample is so large, it is
 * represented by an array count where count[k] is the number of times that k appears in the sample.
 *
 * Calculate the following statistics:
 *
 * minimum: The minimum element in the sample.
 * maximum: The maximum element in the sample.
 *
 * mean: The average of the sample, calculated as the total sum of all elements
 * divided by the total number of elements.
 *
 * median:
 * If the sample has an odd number of elements, then the median is the middle element once the sample is sorted.
 * If the sample has an even number of elements, then the median is the average of the
 * two middle elements once the sample is sorted.
 *
 * mode: The number that appears the most in the sample. It is guaranteed to be unique.
 * Return the statistics of the sample as an array of floating-point numbers [minimum, maximum, mean, median, mode].
 * Answers within 10-5 of the actual answer will be accepted.
 */

public class Solution {

    public double[] sampleStats(int[] count) {
        long minimum = -1, maximum = -1, sum = 0;
        int total = 0, mostCount = 0, mostNumber = 0;
        for (int i = 0; i < count.length; i++) {
            if (count[i] > 0) {
                maximum = i;
                total += count[i];
                sum += (long) i * count[i];
                if (minimum == -1) minimum = i;
                if (count[i] > mostCount) {
                    mostNumber = i;
                    mostCount = count[i];
                }
            }
        }

        double avg = (double) sum / total, median;
        int middle = (total + 1) / 2, idx = 0;
        for (; idx < count.length && middle > 0; idx++) {
            middle -= count[idx];
        }

        median = idx - 1;
        if (middle == 0 && total % 2 == 0) {
            for (int i = idx; i < count.length; i++) {
                if (count[i] != 0) {
                    median = (median + i) / 2;
                    break;
                }
            }
        }

        return new double[]{minimum, maximum, avg, median, mostNumber};
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().sampleStats(new int[]{
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0, //  0 - 31
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0, // 32 - 63
            0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0, // 64 - 96
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0
        }), new double[]{66.0,124.0,86.75,78.5,66.0});

        assert Checker.check(new Solution().sampleStats(new int[]{
            0,1,3,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0
        }), new double[]{1.00000,3.00000,2.37500,2.50000,3.00000});

        assert Checker.check(new Solution().sampleStats(new int[]{
            0,4,3,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0
        }), new double[]{1.00000,4.00000,2.18182,2.00000,1.00000});
    }

}
