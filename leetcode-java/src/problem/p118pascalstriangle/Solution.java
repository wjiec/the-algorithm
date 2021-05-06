package problem.p118pascalstriangle;

import java.util.ArrayList;
import java.util.List;

/**
 * 118. Pascal's Triangle
 *
 * https://leetcode-cn.com/problems/pascals-triangle/
 *
 * Given an integer numRows, return the first numRows of Pascal's triangle.
 *
 * In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:
 */

public class Solution {

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> rs = new ArrayList<>();
        rs.add(new ArrayList<>(){{ add(1); }});
        for (int i = 1; i < numRows; i++) {
            List<Integer> line = new ArrayList<>(), prev = rs.get(i - 1);
            line.add(1);
            for (int j = 1; j < i; j++) {
                line.add(prev.get(j - 1) + prev.get(j));
            }
            line.add(1);
            rs.add(line);
        }
        return rs;
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 30; i++) {
            System.out.println(new Solution().generate(i));
        }
    }

}
