package problem.p1333filterrestaurantsbyveganfriendlypriceanddistance;

import common.Checker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1333. Filter Restaurants by Vegan-Friendly, Price and Distance
 *
 * https://leetcode.cn/problems/filter-restaurants-by-vegan-friendly-price-and-distance/
 *
 * Given the array restaurants where restaurants[i] = [idi, ratingi, veganFriendlyi, pricei, distancei].
 * You have to filter the restaurants using three filters.
 *
 * The veganFriendly filter will be either true (meaning you should only include restaurants with
 * veganFriendlyi set to true) or false (meaning you can include any restaurant).
 * In addition, you have the filters maxPrice and maxDistance which are the maximum value for price
 * and distance of restaurants you should consider respectively.
 *
 * Return the array of restaurant IDs after filtering, ordered by rating from highest to lowest.
 * For restaurants with the same rating, order them by id from highest to lowest.
 * For simplicity veganFriendlyi and veganFriendly take value 1 when it is true, and 0 when it is false.
 */

public class Solution {

    // [id, rating, veganFriendly, price, distance]
    public List<Integer> filterRestaurants(int[][] restaurants, int veganFriendly, int maxPrice, int maxDistance) {
        if (veganFriendly == 1) {
            for (var restaurant : restaurants) {
                if (restaurant[2] == 0) restaurant[1] = -1;
            }
        }

        for (var restaurant : restaurants) {
            if (restaurant[3] > maxPrice) restaurant[1] = -1;
        }
        for (var restaurant : restaurants) {
            if (restaurant[4] > maxDistance) restaurant[1] = -1;
        }

        Arrays.sort(restaurants, (a, b) -> {
            int c = Integer.compare(b[1], a[1]);
            return c == 0 ? Integer.compare(b[0], a[0]) : c;
        });

        List<Integer> ans = new ArrayList<>();
        for (var restaurant: restaurants) {
            if (restaurant[1] == -1) break;
            ans.add(restaurant[0]);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().filterRestaurants(new int[][]{
            {1,4,1,40,10},{2,8,0,50,5},{3,8,1,30,4},{4,10,0,10,3},{5,1,1,15,1}
        }, 1, 50, 10), List.of(3,1,5));

        assert Checker.check(new Solution().filterRestaurants(new int[][]{
            {1,4,1,40,10},{2,8,0,50,5},{3,8,1,30,4},{4,10,0,10,3},{5,1,1,15,1}
        }, 0, 50, 10), List.of(4,3,2,1,5));

        assert Checker.check(new Solution().filterRestaurants(new int[][]{
            {1,4,1,40,10},{2,8,0,50,5},{3,8,1,30,4},{4,10,0,10,3},{5,1,1,15,1}
        }, 0, 30, 3), List.of(4,5));
    }

}
