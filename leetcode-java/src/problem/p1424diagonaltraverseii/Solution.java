package problem.p1424diagonaltraverseii;

import common.Checker;

import java.util.ArrayList;
import java.util.List;

/**
 * 1424. Diagonal Traverse II
 *
 * https://leetcode.cn/problems/diagonal-traverse-ii/
 *
 * Given a 2D integer array nums, return all elements of nums in diagonal order as
 * shown in the below images.
 */

public class Solution {

    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < nums.size(); i++) {
            List<Integer> rows = nums.get(i);
            for (int j = 0; j < rows.size(); j++) {
                int num = rows.get(j);
                list.add(new int[]{i + j, j, num});
            }
        }

        list.sort((a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            } else {
                return a[1] - b[1];
            }
        });

        int[] order = new int[list.size()];
        for (int i = 0; i < order.length; i++) {
            order[i] = list.get(i)[2];
        }

        return order;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().findDiagonalOrder(List.of(
            List.of(1,2,3),
            List.of(4,5,6),
            List.of(7,8,9)
        )), new int[]{1,4,2,7,5,3,8,6,9});

        assert Checker.check(new Solution().findDiagonalOrder(List.of(
            List.of(1,2,3,4,5),
            List.of(6,7),
            List.of(8),
            List.of(9,10,11),
            List.of(12,13,14,15,16)
        )), new int[]{1,6,2,8,7,3,9,4,12,10,5,13,11,14,15,16});

        assert Checker.check(new Solution().findDiagonalOrder(List.of(
            List.of(1,2,3),
            List.of(4),
            List.of(5,6,7),
            List.of(8),
            List.of(9,10,11)
        )), new int[]{1,4,2,5,3,8,6,9,7,10,11});

        assert Checker.check(new Solution().findDiagonalOrder(List.of(
            List.of(1,2,3,4,5,6)
        )), new int[]{1,2,3,4,5,6});

        assert Checker.check(new Solution().findDiagonalOrder(List.of(
            List.of(1),
            List.of(2),
            List.of(3),
            List.of(4),
            List.of(5),
            List.of(6)
        )), new int[]{1,2,3,4,5,6});
    }

}
