package weekly.bw131.B;

import java.util.ArrayList;
import java.util.List;

/**
 * 3159. Find Occurrences of an Element in an Array
 *
 * https://leetcode.cn/contest/biweekly-contest-131/problems/find-occurrences-of-an-element-in-an-array/
 *
 * You are given an integer array nums, an integer array queries, and an integer x.
 *
 * For each queries[i], you need to find the index of the queries[i]th occurrence of x in the nums array.
 * If there are fewer than queries[i] occurrences of x, the answer should be -1 for that query.
 *
 * Return an integer array answer containing the answers to all queries.
 */

public class Solution {

    public int[] occurrencesOfElement(int[] nums, int[] queries, int x) {
        List<Integer> idx = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == x) idx.add(i);
        }

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            ans[i] = (queries[i] - 1) < idx.size() ? idx.get(queries[i] - 1) : -1;
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
