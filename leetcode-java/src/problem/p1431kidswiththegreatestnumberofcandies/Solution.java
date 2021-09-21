package problem.p1431kidswiththegreatestnumberofcandies;

import java.util.ArrayList;
import java.util.List;

/**
 * 1431. Kids With the Greatest Number of Candies
 *
 * https://leetcode-cn.com/problems/kids-with-the-greatest-number-of-candies/
 *
 * There are n kids with candies. You are given an integer array candies,
 * where each candies[i] represents the number of candies the ith kid has,
 * and an integer extraCandies, denoting the number of extra candies that you have.
 *
 * Return a boolean array result of length n, where result[i] is true if,
 * after giving the ith kid all the extraCandies,
 * they will have the greatest number of candies among all the kids, or false otherwise.
 *
 * Note that multiple kids can have the greatest number of candies.
 */

public class Solution {

    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int max = 0;
        for (var n : candies) max = Math.max(max, n);

        List<Boolean> ans = new ArrayList<>();
        for (var n : candies) ans.add(n + extraCandies >= max);
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().kidsWithCandies(new int[]{2,3,5,1,3}, 3).equals(List.of(true,true,true,false,true));
        assert new Solution().kidsWithCandies(new int[]{4,2,1,1,2}, 1).equals(List.of(true,false,false,false,false));
        assert new Solution().kidsWithCandies(new int[]{12,1,12}, 10).equals(List.of(true,false,true));
    }

}
