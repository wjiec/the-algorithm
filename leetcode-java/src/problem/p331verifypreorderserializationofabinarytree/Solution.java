package problem.p331verifypreorderserializationofabinarytree;

/**
 * 331. Verify Preorder Serialization of a Binary Tree
 *
 * https://leetcode-cn.com/problems/verify-preorder-serialization-of-a-binary-tree/
 *
 * One way to serialize a binary tree is to use preorder traversal. When we encounter a non-null node,
 * we record the node's value. If it is a null node, we record using a sentinel value such as '#'.
 *
 * For example, the above binary tree can be serialized to the string
 * "9,3,4,#,#,1,#,#,2,#,6,#,#", where '#' represents a null node.
 *
 * Given a string of comma-separated values preorder, return true if
 * it is a correct preorder traversal serialization of a binary tree.
 *
 * It is guaranteed that each comma-separated value in the string must be
 * either an integer or a character '#' representing null pointer.
 *
 * You may assume that the input format is always valid.
 *
 * For example, it could never contain two consecutive commas, such as "1,,3".
 *
 * Note: You are not allowed to reconstruct the tree.
 */

public class Solution {

    public boolean isValidSerialization(String preorder) {
        int slots = 1;
        for (var token : preorder.split(",")) {
            if (--slots < 0) return false;
            if (!token.equals("#")) slots += 2;
        }
        return slots == 0;
    }

    public static void main(String[] args) {
        assert new Solution().isValidSerialization("9,3,4,#,#,1,#,#,2,#,6,#,#");
        assert !new Solution().isValidSerialization("1,#");
        assert !new Solution().isValidSerialization("9,#,#,1");
    }

}
