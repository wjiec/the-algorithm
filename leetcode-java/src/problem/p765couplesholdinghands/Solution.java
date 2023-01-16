package problem.p765couplesholdinghands;

import ability.Ability;
import common.TODO;

/**
 * 765. Couples Holding Hands
 *
 * https://leetcode.cn/problems/couples-holding-hands/
 *
 * There are n couples sitting in 2n seats arranged in a row and want to hold hands.
 *
 * The people and seats are represented by an integer array row where row[i] is the
 * ID of the person sitting in the ith seat. The couples are numbered in order, the
 * first couple being (0, 1), the second couple being (2, 3), and so on with the last
 * couple being (2n - 2, 2n - 1).
 *
 * Return the minimum number of swaps so that every couple is sitting side by side.
 * A swap consists of choosing any two people, then they stand up and switch seats.
 */

public class Solution {

    @TODO
    public int minSwapsCouples(int[] row) {
        // 按位置分组，如果该分组是一对情侣的话
        //  row[i] / 2 == row[i + 1] / 2 所以他们此时是同一个连通分量
        // 如果该分组不是一个情侣的话, 将他们连通将导致错误, 需要进行交换
        int ans = row.length / 2;
        Ability.UnionFind uf = new Ability.UnionFind(ans);
        for (int i = 0; i < row.length; i += 2) {
            if (!uf.union(row[i] / 2, row[i + 1] / 2)) {
                ans--;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minSwapsCouples(new int[]{0,2,4,6,7,1,3,5}) == 3;

        assert new Solution().minSwapsCouples(new int[]{0,2,1,3}) == 1;
        assert new Solution().minSwapsCouples(new int[]{3,2,0,1}) == 0;
    }

}
