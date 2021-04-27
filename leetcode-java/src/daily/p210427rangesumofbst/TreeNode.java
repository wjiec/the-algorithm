package daily.p210427rangesumofbst;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    void insert(int v) {
        if (v > val) {
            if (right != null) {
                right.insert(v);
            } else {
                right = new TreeNode(v);
            }
        } else {
            if (left != null) {
                left.insert(v);
            } else {
                left = new TreeNode(v);
            }
        }
    }

    @Override
    public String toString() {
        return String.format("[val=%d, left=%s, right=%s]", val, left, right);
    }

}
