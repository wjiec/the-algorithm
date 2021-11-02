package problem.p1640checkarrayformationthroughconcatenation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 1640. Check Array Formation Through Concatenation
 *
 * https://leetcode-cn.com/problems/check-array-formation-through-concatenation/
 *
 * You are given an array of distinct integers arr and an array of integer arrays pieces,
 * where the integers in pieces are distinct.
 *
 * Your goal is to form arr by concatenating the arrays in pieces in any order.
 * However, you are not allowed to reorder the integers in each array pieces[i].
 *
 * Return true if it is possible to form the array arr from pieces. Otherwise, return false.
 */

public class Solution {

    public boolean canFormArray(int[] arr, int[][] pieces) {
        int[] start = new int[101]; Arrays.fill(start, -1);
        for (int i = 0; i < pieces.length; i++) start[pieces[i][0]] = i;

        for (int i = 0; i < arr.length; ) {
            if (start[arr[i]] == -1) return false;
            for (var n : pieces[start[arr[i]]]) {
                if (arr[i] != n) return false;
                i++;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        assert new Solution().canFormArray(new int[]{85}, new int[][]{{85}});
        assert new Solution().canFormArray(new int[]{15,88}, new int[][]{{88},{15}});
        assert !new Solution().canFormArray(new int[]{49,18,16}, new int[][]{{16,18,49}});
        assert new Solution().canFormArray(new int[]{91,4,64,78}, new int[][]{{78},{4,64},{91}});
        assert !new Solution().canFormArray(new int[]{1,3,5,7}, new int[][]{{2,4,6,8}});
    }

}
