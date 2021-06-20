package problem.p989addtoarrayformofinteger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 989. Add to Array-Form of Integer
 *
 * https://leetcode-cn.com/problems/add-to-array-form-of-integer/
 *
 * The array-form of an integer num is an array representing its digits in left to right order.
 *
 * For example, for num = 1321, the array form is [1,3,2,1].
 * Given num, the array-form of an integer, and an integer k, return the array-form of the integer num + k.
 */

public class Solution {

    public List<Integer> addToArrayForm(int[] num, int k) {
        int i = num.length - 1, carry = 0;
        List<Integer> ans = new ArrayList<>();
        for (; k != 0; k /= 10, i--) {
            int b = k % 10, v = (i >= 0 ? num[i] : 0) + b + carry;
            ans.add(v % 10); carry = v >= 10 ? 1 : 0;
        }
        for (; i >= 0; i--) {
            int v = num[i] + carry;
            ans.add(v % 10); carry = v >= 10 ? 1 : 0;
        }
        if (carry == 1) ans.add(carry);

        Collections.reverse(ans);
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().addToArrayForm(new int[]{1}, 599).equals(List.of(6,0,0));
        assert new Solution().addToArrayForm(new int[]{1,2,0,0}, 34).equals(List.of(1,2,3,4));
        assert new Solution().addToArrayForm(new int[]{2,7,4}, 181).equals(List.of(4,5,5));
        assert new Solution().addToArrayForm(new int[]{2,1,5}, 806).equals(List.of(1,0,2,1));
        assert new Solution().addToArrayForm(new int[]{9,9,9,9,9,9,9,9,9,9}, 1).equals(List.of(1,0,0,0,0,0,0,0,0,0,0));
    }

}
