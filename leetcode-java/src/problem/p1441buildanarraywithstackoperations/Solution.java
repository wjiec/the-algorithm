package problem.p1441buildanarraywithstackoperations;

import java.util.ArrayList;
import java.util.List;

/**
 * 1441. Build an Array With Stack Operations
 *
 * https://leetcode-cn.com/problems/build-an-array-with-stack-operations/
 *
 * Given an array target and an integer n. In each iteration, you will read a number from  list = {1,2,3..., n}.
 *
 * Build the target array using the following operations:
 *
 * Push: Read a new element from the beginning list, and push it in the array.
 * Pop: delete the last element of the array.
 *
 * If the target array is already built, stop reading more elements.
 * Return the operations to build the target array. You are guaranteed that the answer is unique.
 */

public class Solution {

    public List<String> buildArray(int[] target, int n) {
        List<String> ans = new ArrayList<>();

        int curr = 1;
        for (var val : target) {
            for (; curr != val; curr++) {
                ans.add("Push");
                ans.add("Pop");
            }
            ans.add("Push");
            curr++;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().buildArray(new int[]{1,3}, 3).equals(List.of("Push","Push","Pop","Push"));
        assert new Solution().buildArray(new int[]{1,2,3}, 3).equals(List.of("Push","Push","Push"));
        assert new Solution().buildArray(new int[]{1,2}, 4).equals(List.of("Push","Push"));
        assert new Solution().buildArray(new int[]{2,3,4}, 4).equals(List.of("Push","Pop","Push","Push","Push"));
    }

}
