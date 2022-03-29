package problem.p427constructquadtree;

/**
 * 427. Construct Quad Tree
 *
 * https://leetcode-cn.com/problems/construct-quad-tree/
 *
 * Given a n * n matrix grid of 0's and 1's only. We want to represent the grid with a Quad-Tree.
 *
 * Return the root of the Quad-Tree representing the grid.
 *
 * Notice that you can assign the value of a node to True or False when isLeaf is False,
 * and both are accepted in the answer.
 *
 * A Quad-Tree is a tree data structure in which each internal node has exactly four children.
 * Besides, each node has two attributes:
 *
 * val: True if the node represents a grid of 1's or False if the node represents a grid of 0's.
 * isLeaf: True if the node is leaf node on the tree or False if the node has the four children.
 */

public class Solution {

    private static class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;


        public Node() {
            this.val = false;
            this.isLeaf = false;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = topLeft;
            this.topRight = topRight;
            this.bottomLeft = bottomLeft;
            this.bottomRight = bottomRight;
        }

        @Override
        public String toString() {
            return "Node{" +
                "val=" + val +
                ", isLeaf=" + isLeaf +
                ", topLeft=" + topLeft +
                ", topRight=" + topRight +
                ", bottomLeft=" + bottomLeft +
                ", bottomRight=" + bottomRight +
                '}';
        }
    }

    public Node construct(int[][] grid) {
        return build(grid, 0, 0, grid.length, grid.length);
    }

    private Node build(int[][] grid, int x0, int y0, int x1, int y1) {
        Node node = new Node(true, false);
        if (x0 + 1 == x1 || y0 + 1 == y1 || isLeaf(grid, x0, y0, x1, y1)) {
            node.isLeaf = true;
            node.val = grid[x0][y0] == 1;
            return node;
        }

        int mx = x0 + (x1 - x0) / 2, my = y0 + (y1 - y0) / 2;
        node.topLeft = build(grid, x0, y0, mx, my);
        node.topRight = build(grid, x0, my, mx, y1);
        node.bottomLeft = build(grid, mx, y0, x1, my);
        node.bottomRight = build(grid, mx, my, x1, y1);

        return node;
    }

    private boolean isLeaf(int[][] grid, int x0, int y0, int x1, int y1) {
        for (int x = x0; x < x1; x++) {
            for (int y = y0; y < y1; y++) {
                if (grid[x][y] != grid[x0][y0]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().construct(new int[][]{
            {0, 1},
            {1, 0}
        }));

        System.out.println(new Solution().construct(new int[][]{
            {1, 1, 1, 1, /**/ 0, 0, 0, 0},
            {1, 1, 1, 1, /**/ 0, 0, 0, 0},
            {1, 1, 1, 1, /**/ 1, 1, 1, 1},
            {1, 1, 1, 1, /**/ 1, 1, 1, 1},
            //////////////##/////////////
            {1, 1, 1, 1, /**/ 0, 0, 0, 0},
            {1, 1, 1, 1, /**/ 0, 0, 0, 0},
            {1, 1, 1, 1, /**/ 0, 0, 0, 0},
            {1, 1, 1, 1, /**/ 0, 0, 0, 0}
        }));

        System.out.println(new Solution().construct(new int[][]{
            {1, 1},
            {1, 1}
        }));

        System.out.println(new Solution().construct(new int[][]{
            {0}
        }));

        System.out.println(new Solution().construct(new int[][]{
            {1,1,0,0},
            {1,1,0,0},
            {0,0,1,1},
            {0,0,1,1}
        }));
    }

}
