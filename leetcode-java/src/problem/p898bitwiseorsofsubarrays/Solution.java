package problem.p898bitwiseorsofsubarrays;

import java.util.HashSet;
import java.util.Set;

/**
 * 898. Bitwise ORs of Subarrays
 *
 * https://leetcode.cn/problems/bitwise-ors-of-subarrays/
 *
 * We have an array arr of non-negative integers.
 *
 * For every (contiguous) subarray sub = [arr[i], arr[i + 1], ..., arr[j]] (with i <= j),
 * we take the bitwise OR of all the elements in sub, obtaining a result arr[i] | arr[i + 1] | ... | arr[j].
 *
 * Return the number of possible results. Results that occur more than once are only counted once in the final answer
 */

public class Solution {

    public int subarrayBitwiseORs(int[] arr) {
        Set<Integer> ans = new HashSet<>();

        Set<Integer> curr = new HashSet<>();
        curr.add(0);

        for (var a : arr) {
            Set<Integer> next = new HashSet<>();
            for (int b : curr) next.add(a | b);
            next.add(a);

            curr = next;
            ans.addAll(curr);
        }

        return ans.size();
    }

    public static void main(String[] args) {
        assert new Solution().subarrayBitwiseORs(new int[]{0}) == 1;
        assert new Solution().subarrayBitwiseORs(new int[]{1,1,2}) == 3;
        assert new Solution().subarrayBitwiseORs(new int[]{1,2,4}) == 4;
    }

}
