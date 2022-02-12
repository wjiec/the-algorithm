package daily.d210729p1104pathinzigzaglabelledbinarytree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 1104. Path In Zigzag Labelled Binary Tree
 *
 * https://leetcode-cn.com/problems/path-in-zigzag-labelled-binary-tree/
 *
 * In an infinite binary tree where every node has two children, the nodes are labelled in row order.
 *
 * In the odd numbered rows (ie., the first, third, fifth,...), the labelling is left to right,
 * while in the even numbered rows (second, fourth, sixth,...), the labelling is right to left.
 *
 * Given the label of a node in this tree, return the labels
 * in the path from the root of the tree to the node with that label.
 */

public class Solution {

    // @TODO math
    public List<Integer> pathInZigZagTree(int label) {
        int base = 1, row = 1;
        for (; base <= label; base <<= 1) row++;

        row--;
        if (row % 2 == 0) label = reverse(label, row);

        List<Integer> ans = new ArrayList<>();
        while (row > 0) {
            if (row % 2 == 0) ans.add(reverse(label, row));
            else ans.add(label);

            row--;
            label >>= 1;
        }

        Collections.reverse(ans);
        return ans;
    }

    private int reverse(int label, int row) {
        return (1 << (row - 1)) + (1 << row) - 1 - label;
    }

    public static void main(String[] args) {
        assert new Solution().pathInZigZagTree(14).equals(List.of(1,3,4,14));
        assert new Solution().pathInZigZagTree(26).equals(List.of(1,2,6,10,26));
    }

}
