package daily.d210706p1418displaytableoffoodordersinarestaurant;

import java.util.*;

/**
 * 1418. Display Table of Food Orders in a Restaurant
 *
 * https://leetcode-cn.com/problems/display-table-of-food-orders-in-a-restaurant/
 *
 * Given the array orders, which represents the orders that customers have done in a restaurant.
 * More specifically orders[i]=[customerNamei,tableNumberi,foodItemi]
 * where customerNamei is the name of the customer, tableNumberi is the table customer sit at,
 * and foodItemi is the item customer orders.
 *
 * Return the restaurant's “display table”. The “display table” is a table whose row entries denote
 * how many of each food item each table ordered. The first column is the table number and
 * the remaining columns correspond to each food item in alphabetical order.
 *
 * The first row should be a header whose first column is “Table”, followed by the names of the food items.
 *
 * Note that the customer names are not part of the table.
 * Additionally, the rows should be sorted in numerically increasing order.
 */

public class Solution {

    private static class Order {
        private final int id;
        public Order(String id) { this.id = Integer.valueOf(id, 10); }
        private final Map<String, Integer> foods = new HashMap<>();
        public void append(String food) { foods.put(food, foods.getOrDefault(food, 0) + 1); }
        public String count(String food) { return String.valueOf(foods.getOrDefault(food, 0)); }
    }

    public List<List<String>> displayTable(List<List<String>> orders) {
        Map<String, Order> map = new HashMap<>();
        Set<String> foods = new HashSet<>();
        for (var order : orders) {
            map.putIfAbsent(order.get(1), new Order(order.get(1)));
            map.get(order.get(1)).append(order.get(2));
            foods.add(order.get(2));
        }

        LinkedList<String> columns = new LinkedList<>(foods);
        columns.sort(String::compareTo);
        columns.addFirst("Table");

        PriorityQueue<Map.Entry<String, Order>> tables = new PriorityQueue<>(Comparator.comparingInt(e -> e.getValue().id));
        tables.addAll(map.entrySet());

        List<List<String>> ans = new LinkedList<>();
        ans.add(columns);

        while (!tables.isEmpty()) {
            var entry = tables.remove();
            List<String> counts = new ArrayList<>();
            for (var food : columns) {
                if (counts.isEmpty()) counts.add(entry.getKey());
                else counts.add(entry.getValue().count(food));
            }
            ans.add(counts);
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().displayTable(List.of(
            List.of("David","3","Ceviche"),
            List.of("Corina","10","Beef Burrito"),
            List.of("David","3","Fried Chicken"),
            List.of("Carla","5","Water"),
            List.of("Carla","5","Ceviche"),
            List.of("Rous","3","Ceviche")
        )));
    }

}
