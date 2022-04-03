package weekly.w287.p2maximumcandiesallocatedtokchildren;

/**
 * 5219. Maximum Candies Allocated to K Children
 *
 * https://leetcode-cn.com/contest/weekly-contest-287/problems/maximum-candies-allocated-to-k-children/
 *
 * You are given a 0-indexed integer array candies. Each element in the array denotes
 * a pile of candies of size candies[i]. You can divide each pile into any number of sub piles,
 * but you cannot merge two piles together.
 *
 * You are also given an integer k. You should allocate piles of candies to k children such that
 * each child gets the same number of candies. Each child can take at most one pile of candies
 * and some piles of candies may go unused.
 *
 * Return the maximum number of candies each child can get.
 */

public class Solution {

    public int maximumCandies(int[] candies, long k) {
        long sum = 0;
        int min = Integer.MAX_VALUE, max = 0;
        for (var n : candies) {
            sum += n;
            if (n < min) min = n;
            if (n > max) max = n;
        }
        if (sum < k) return 0;

        // 需要拆分
        int l = 1, r = max + 1, prev = 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (check(candies, mid, k)) {
                prev = mid;
                l = mid + 1;
            } else r = mid;
        }
        return prev;
    }

    private boolean check(int[] candies, int avg, long k) {
        for (var n : candies) {
            k -= n / avg;
            if (k <= 0) break;
        }
        return k <= 0;
    }

    public static void main(String[] args) {
        assert new Solution().maximumCandies(new int[]{9,10,1,2,10,9,9,10,2,2}, 3) == 10;
        assert new Solution().maximumCandies(new int[]{1,2,6,8,6,7,3,5,2,5}, 3) == 6;
        assert new Solution().maximumCandies(new int[]{1,2,3,4,10}, 5) == 3;
        assert new Solution().maximumCandies(new int[]{4,7,5}, 4) == 3;

        assert new Solution().maximumCandies(new int[]{5, 8, 6}, 3) == 5;
        assert new Solution().maximumCandies(new int[]{2, 5}, 11) == 0;
        assert new Solution().maximumCandies(new int[]{2, 9}, 11) == 1;
        assert new Solution().maximumCandies(new int[]{2, 9, 11}, 11) == 1;
    }

}
