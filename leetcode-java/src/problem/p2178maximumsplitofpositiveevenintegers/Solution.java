package problem.p2178maximumsplitofpositiveevenintegers;

import common.Checker;
import common.PrettyPrinter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 2178. Maximum Split of Positive Even Integers
 *
 * https://leetcode.cn/problems/maximum-split-of-positive-even-integers/
 *
 * You are given an integer finalSum. Split it into a sum of a maximum number of unique positive even integers.
 *
 * For example, given finalSum = 12, the following splits are valid (unique positive even integers summing
 * up to finalSum): (12), (2 + 10), (2 + 4 + 6), and (4 + 8). Among them, (2 + 4 + 6) contains the maximum
 * number of integers. Note that finalSum cannot be split into (2 + 2 + 4 + 4) as all the numbers should be unique.
 * Return a list of integers that represent a valid split containing a maximum number of integers.
 *
 * If no valid split exists for finalSum, return an empty list. You may return the integers in any order.
 */

public class Solution {

    public List<Long> maximumEvenSplit(long finalSum) {
        if (finalSum % 2 == 1) return List.of();

        List<Long> ans = new ArrayList<>();
        for (long v = 2; finalSum != 0; v += 2) {
            if (finalSum < v) {
                ans.set(ans.size() - 1, ans.get(ans.size() - 1) + finalSum);
                break;
            }
            ans.add(v); finalSum -= v;
        }
        return ans;
    }

    public static void main(String[] args) {
        PrettyPrinter.println(new Solution().maximumEvenSplit(12));
        PrettyPrinter.println(new Solution().maximumEvenSplit(7));
        PrettyPrinter.println(new Solution().maximumEvenSplit(28));
    }

}
