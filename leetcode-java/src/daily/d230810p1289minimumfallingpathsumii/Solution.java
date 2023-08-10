package daily.d230810p1289minimumfallingpathsumii;

public class Solution {

    public int minFallingPathSum(int[][] grid) {
        int n = grid.length;
        if (n == 1) {
            int ans = 0;
            for (int[] row : grid) ans += row[0];
            return ans;
        }

        int fi = 0, fv = 0, sv = 0;
        for (int[] row : grid) {
            int nfi = -1, nfv = Integer.MAX_VALUE, nsv = Integer.MAX_VALUE;
            for (int j = 0; j < n; j++) {
                int curr = row[j] + (j == fi ? sv : fv);
                if (nfi == -1 || curr < nfv) {
                    nsv = nfv; nfi = j; nfv = curr;
                } else if (curr < nsv) nsv = curr;
            }
            fi = nfi; fv = nfv; sv = nsv;
        }
        return fv;
    }

    public static void main(String[] args) {
        assert new Solution().minFallingPathSum(new int[][]{
            {1,2,3},
            {4,5,6},
            {7,8,9}
        }) == 13;
    }

}
