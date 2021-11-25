package problem.p1886determinewhethermatrixcanbeobtainedbyrotation;

/**
 * 1886. Determine Whether Matrix Can Be Obtained By Rotation
 *
 * https://leetcode-cn.com/problems/determine-whether-matrix-can-be-obtained-by-rotation/
 *
 * Given two n x n binary matrices mat and target, return true if it is possible
 * to make mat equal to target by rotating mat in 90-degree increments,
 * or false otherwise.
 */

public class Solution {

    public boolean findRotation(int[][] mat, int[][] target) {
        int l = mat.length;
        boolean b0 = true, b90 = true, b180 = true, b270 = true;
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < l; j++) {
                if (mat[i][j] != target[i][j]) b0 = false;
                if (mat[l - j - 1][i] != target[i][j]) b90 = false;
                if (mat[l - i - 1][l - j - 1] != target[i][j]) b180 = false;
                if (mat[j][l - i - 1] != target[i][j]) b270 = false;
            }
        }
        return b0 || b90 || b180 || b270;
    }

    public static void main(String[] args) {
        assert new Solution().findRotation(new int[][]{
            {0,1},
            {1,0}
        }, new int[][]{
            {1,0},
            {0,1}
        });

        assert !new Solution().findRotation(new int[][]{
            {0,1},
            {1,1}
        }, new int[][]{
            {1,0},
            {0,1}
        });

        assert new Solution().findRotation(new int[][]{
            {0,0,0},
            {0,1,0},
            {1,1,1}
        }, new int[][]{
            {1,1,1},
            {0,1,0},
            {0,0,0},
        });
    }

}
