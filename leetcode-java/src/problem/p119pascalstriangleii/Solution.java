package problem.p119pascalstriangleii;

import java.util.ArrayList;
import java.util.List;

/**
 * 119. Pascal's Triangle II
 *
 * https://leetcode-cn.com/problems/pascals-triangle-ii/
 *
 * Given an integer rowIndex, return the rowIndexth (0-indexed) row of the Pascal's triangle.
 *
 * In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:
 */

public class Solution {

    public List<Integer> getRow(int rowIndex) {
        List<Integer> rs = new ArrayList<>(rowIndex + 1);
        for (int i = 0; i <= rowIndex; i++) {
            rs.add(i, 1);
        }

        for (int i = 2; i <= rowIndex; i++) {
            for (int j = i - 1; j >= 1; j--) {
                rs.set(j, rs.get(j - 1) + rs.get(j));
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        for (int i = 0; i <= 33; i++) {
            System.out.println(new Solution().getRow(i));
        }
    }

}
