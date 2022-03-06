package weekly.w283.p2createbinarytreefromdescriptions;

import common.TreeNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 6018. Create Binary Tree From Descriptions
 *
 * https://leetcode-cn.com/contest/weekly-contest-283/problems/create-binary-tree-from-descriptions/
 *
 * You are given a 2D integer array descriptions where descriptions[i] = [parenti, childi, isLefti] indicates that
 * parenti is the parent of childi in a binary tree of unique values. Furthermore,
 *
 * If isLefti == 1, then childi is the left child of parenti.
 * If isLefti == 0, then childi is the right child of parenti.
 * Construct the binary tree described by descriptions and return its root.
 *
 * The test cases will be generated such that the binary tree is valid.
 */

public class Solution {

    public TreeNode createBinaryTree(int[][] descriptions) {
        Set<Integer> parents = new HashSet<>();
        Set<Integer> childs = new HashSet<>();
        Map<Integer, TreeNode> map = new HashMap<>();
        for (var description : descriptions) {
            int parent = description[0], child = description[1], isLeft = description[2];
            if (!map.containsKey(parent)) {
                map.put(parent, new TreeNode(parent));
            }

            if (!map.containsKey(child)) {
                map.put(child, new TreeNode(child));
            }

            parents.add(parent);
            childs.add(child);

            if (isLeft == 1) map.get(parent).left = map.get(child);
            else map.get(parent).right = map.get(child);
        }
        parents.removeAll(childs);

        return map.get(parents.iterator().next());
    }

    public static void main(String[] args) {
        System.out.println(new Solution().createBinaryTree(new int[][]{
            {85,82,1},
            {74,85,1},
            {39,70,0},
            {82,38,1},
            {74,39,0},
            {39,13,1},
        }));

        System.out.println(new Solution().createBinaryTree(new int[][]{
            {20,15,1},
            {20,17,0},
            {50,20,1},
            {50,80,0},
            {80,19,1}
        }));

        System.out.println(new Solution().createBinaryTree(new int[][]{
            {1,2,1},
            {2,3,0},
            {3,4,1},
        }));
    }

}
