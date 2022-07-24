package weekly.w303.C;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * 6126. Design a Food Rating System
 *
 * https://leetcode.cn/contest/weekly-contest-303/problems/design-a-food-rating-system/
 *
 * Design a food rating system that can do the following:
 *
 * Modify the rating of a food item listed in the system.
 * Return the highest-rated food item for a type of cuisine in the system.
 * Implement the FoodRatings class:
 *
 * FoodRatings(String[] foods, String[] cuisines, int[] ratings) Initializes the system.
 * The food items are described by foods, cuisines and ratings, all of which have a length of n.
 * foods[i] is the name of the ith food,
 * cuisines[i] is the type of cuisine of the ith food, and
 * ratings[i] is the initial rating of the ith food.
 *
 * void changeRating(String food, int newRating) Changes the rating of the food item with the name food.
 *
 * String highestRated(String cuisine) Returns the name of the food item that has the highest rating
 * for the given type of cuisine. If there is a tie, return the item with the lexicographically smaller name.
 *
 * Note that a string x is lexicographically smaller than string y if x comes before y in
 * dictionary order, that is, either x is a prefix of y, or if i is the first position
 * such that x[i] != y[i], then x[i] comes before y[i] in alphabetic order.
 */

public class Solution {

    private static class FoodRatings {

        private record Food(String name, String cuisine, int rating) implements Comparable<Food> {
            @Override public int compareTo(Food o) {
                if (o.rating == rating) return name.compareTo(o.name);
                return Integer.compare(o.rating, rating);
            }
        }

        protected final Map<String, Food> foods = new HashMap<>();
        private final Map<String, TreeSet<Food>> map = new HashMap<>();

        public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
            for (int i = 0; i < foods.length; i++) {
                Food food = new Food(foods[i], cuisines[i], ratings[i]);
                this.foods.put(foods[i], food);
                map.computeIfAbsent(cuisines[i], v -> new TreeSet<>())
                    .add(food);
            }
        }

        public void changeRating(String food, int newRating) {
            Food f = foods.get(food);
            map.get(f.cuisine).remove(f);
            Food nf = new Food(f.name, f.cuisine, newRating);
            foods.put(f.name, nf);
            map.get(f.cuisine).add(nf);
        }

        public String highestRated(String cuisine) {
            return map.get(cuisine).first().name;
        }
    }

    public static void main(String[] args) {
    }

}
