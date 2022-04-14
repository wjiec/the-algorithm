package problem.p558logicaloroftwobinarygridsrepresentedasquadtrees;

/**
 * 558. Logical OR of Two Binary Grids Represented as Quad-Trees
 *
 * https://leetcode-cn.com/problems/logical-or-of-two-binary-grids-represented-as-quad-trees/
 *
 * A Binary Matrix is a matrix in which all the elements are either 0 or 1.
 *
 * Given quadTree1 and quadTree2. quadTree1 represents an n * n binary matrix
 * and quadTree2 represents another n * n binary matrix.
 *
 * Return a Quad-Tree representing the n * n binary matrix which is the result of logical bitwise OR
 * of the two binary matrixes represented by quadTree1 and quadTree2.
 *
 * Notice that you can assign the value of a node to True or False when isLeaf is False,
 * and both are accepted in the answer.
 *
 * A Quad-Tree is a tree data structure in which each internal node has exactly four children.
 * Besides, each node has two attributes:
 *
 * val: True if the node represents a grid of 1's or False if the node represents a grid of 0's.
 * isLeaf: True if the node is leaf node on the tree or False if the node has the four children.
 *
 * We can construct a Quad-Tree from a two-dimensional area using the following steps:
 *
 * If the current grid has the same value (i.e all 1's or all 0's) set isLeaf True and set val
 * to the value of the grid and set the four children to Null and stop.
 * If the current grid has different values, set isLeaf to False and set val to any value and
 * divide the current grid into four sub-grids as shown in the photo.
 * Recurse for each of the children with the proper sub-grid.
 *
 * Quad-Tree format:
 *
 * The input/output represents the serialized format of a Quad-Tree using level order traversal,
 * where null signifies a path terminator where no node exists below.
 *
 * It is very similar to the serialization of the binary tree.
 * The only difference is that the node is represented as a list [isLeaf, val].
 *
 * If the value of isLeaf or val is True we represent it as 1 in the list [isLeaf, val]
 * and if the value of isLeaf or val is False we represent it as 0.
 */

public class Solution {

    private static class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;

        public Node() {}

        public Node(boolean _val,boolean _isLeaf,Node _topLeft,Node _topRight,Node _bottomLeft,Node _bottomRight) {
            val = _val;
            isLeaf = _isLeaf;
            topLeft = _topLeft;
            topRight = _topRight;
            bottomLeft = _bottomLeft;
            bottomRight = _bottomRight;
        }
    }

    public Node intersect(Node quadTree1, Node quadTree2) {
        if (quadTree1 == null) return quadTree2;
        if (quadTree2 == null) return quadTree1;

        if (quadTree1.isLeaf) return quadTree1.val ? quadTree1 : quadTree2;
        if (quadTree2.isLeaf) return quadTree2.val ? quadTree2 : quadTree1;

        quadTree1.topLeft = intersect(quadTree1.topLeft, quadTree2.topLeft);
        quadTree1.topRight = intersect(quadTree1.topRight, quadTree2.topRight);
        quadTree1.bottomLeft = intersect(quadTree1.bottomLeft, quadTree2.bottomLeft);
        quadTree1.bottomRight = intersect(quadTree1.bottomRight, quadTree2.bottomRight);

        if (quadTree1.topLeft.isLeaf && quadTree1.topRight.isLeaf
            && quadTree1.bottomLeft.isLeaf && quadTree1.bottomRight.isLeaf
            && quadTree1.topLeft.val == quadTree1.topRight.val
            && quadTree1.topRight.val == quadTree1.bottomLeft.val
            && quadTree1.bottomLeft.val == quadTree1.bottomRight.val
        ) {
            quadTree1.val = quadTree1.topLeft.val;
            quadTree1.isLeaf = true;
            quadTree1.topLeft = null;
            quadTree1.topRight = null;
            quadTree1.bottomLeft = null;
            quadTree1.bottomRight = null;
        }

        return quadTree1;
    }

    public static void main(String[] args) {
        assert new Solution().intersect(null, null) == null;
    }

}
