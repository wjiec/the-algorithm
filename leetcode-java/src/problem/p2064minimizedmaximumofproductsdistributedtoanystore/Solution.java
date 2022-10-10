package problem.p2064minimizedmaximumofproductsdistributedtoanystore;

/**
 * 2064. Minimized Maximum of Products Distributed to Any Store
 *
 * https://leetcode.cn/problems/minimized-maximum-of-products-distributed-to-any-store/
 *
 * You are given an integer n indicating there are n specialty retail stores. There are m product types of
 * varying amounts, which are given as a 0-indexed integer array quantities, where quantities[i] represents
 * the number of products of the ith product type.
 *
 * You need to distribute all products to the retail stores following these rules:
 *
 * A store can only be given at most one product type but can be given any amount of it.
 * After distribution, each store will have been given some number of products (possibly 0).
 * Let x represent the maximum number of products given to any store. You want x to be as small
 * as possible, i.e., you want to minimize the maximum number of products that are given to any store.
 *
 * Return the minimum possible x.
 */

public class Solution {

    public int minimizedMaximum(int n, int[] quantities) {
        int l = 1, r = 0, ans = 0;
        for (var q : quantities) r = Math.max(r, q);

        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (distribute(n, quantities, mid)) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return ans;
    }

    private boolean distribute(int n, int[] quantities, int v) {
        int stores = 0;
        for (var quantity : quantities) {
            stores += (quantity + v - 1) / v;
            if (stores > n) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        assert new Solution().minimizedMaximum(1, new int[]{1}) == 1;
        assert new Solution().minimizedMaximum(6, new int[]{11,6}) == 3;
        assert new Solution().minimizedMaximum(7, new int[]{15,10,10}) == 5;
        assert new Solution().minimizedMaximum(1, new int[]{100000}) == 100000;
    }

}
