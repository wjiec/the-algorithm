package problem.p1196howmanyapplescanyouputintothebasket;

import java.util.Arrays;

/**
 * 1196. How Many Apples Can You Put into the Basket
 *
 * https://leetcode-cn.com/problems/how-many-apples-can-you-put-into-the-basket/
 *
 * You have some apples and a basket that can carry up to 5000 units of weight.
 *
 * Given an integer array weight where weight[i] is the weight of the ith apple,
 * return the maximum number of apples you can put in the basket.
 */

public class Solution {

    public int maxNumberOfApples(int[] weight) {
        Arrays.sort(weight);
        int ans = 0, total = 5000;
        for (int val : weight) {
            total -= val;
            if (total >= 0) ans++;
            else break;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxNumberOfApples(new int[]{100,200,150,1000}) == 4;
        assert new Solution().maxNumberOfApples(new int[]{900,950,800,1000,700,800}) == 5;
    }

}
