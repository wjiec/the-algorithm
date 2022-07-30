package problem.p1357applydiscounteverynorders;

import common.Checker;

import java.util.HashMap;
import java.util.Map;

/**
 * 1357. Apply Discount Every n Orders
 *
 * https://leetcode.cn/problems/apply-discount-every-n-orders/
 *
 * There is a supermarket that is frequented by many customers. The products sold at the supermarket are
 * represented as two parallel integer arrays products and prices, where the ith product has an ID of
 * products[i] and a price of prices[i].
 *
 * When a customer is paying, their bill is represented as two parallel integer arrays product
 * and amount, where the jth product they purchased has an ID of product[j], and amount[j] is
 * how much of the product they bought. Their subtotal is calculated as the sum of each
 * amount[j] * (price of the jth product).
 *
 * The supermarket decided to have a sale. Every nth customer paying for their groceries
 * will be given a percentage discount. The discount amount is given by discount, where
 * they will be given discount percent off their subtotal. More formally, if their subtotal
 * is bill, then they would actually pay bill * ((100 - discount) / 100).
 *
 * Implement the Cashier class:
 *
 * Cashier(int n, int discount, int[] products, int[] prices) Initializes the object
 * with n, the discount, and the products and their prices.
 * double getBill(int[] product, int[] amount) Returns the final total of
 * the bill with the discount applied (if any).
 *
 * Answers within 10-5 of the actual value will be accepted.
 */

public class Solution {

    private static class Cashier {
        private final int n;
        private int curr = 1;
        private final double discount;
        private final Map<Integer, Integer> map = new HashMap<>();
        public Cashier(int n, int discount, int[] products, int[] prices) {
            this.n = n; this.discount = (100.0 - discount) / 100;
            for (int i = 0; i < products.length; i++) {
                map.put(products[i], prices[i]);
            }
        }

        public double getBill(int[] product, int[] amount) {
            double total = 0;
            for (int i = 0; i < product.length; i++) {
                total += amount[i] * map.get(product[i]);
            }

            if (curr++ % n == 0) total *= discount;
            return total;
        }
    }

    public static void main(String[] args) {
        Cashier cashier = new Cashier(3,50,new int[]{1,2,3,4,5,6,7},new int[]{100,200,300,400,300,200,100});
        assert Checker.check(cashier.getBill(new int[]{1,2},new int[]{1,2}), 500.0);
        assert Checker.check(cashier.getBill(new int[]{3,7},new int[]{10,10}), 4000.0);
        assert Checker.check(cashier.getBill(new int[]{1,2,3,4,5,6,7},new int[]{1,1,1,1,1,1,1}), 800.0);
        assert Checker.check(cashier.getBill(new int[]{4},new int[]{10}), 4000.0);
        assert Checker.check(cashier.getBill(new int[]{7,3},new int[]{10,10}), 4000.0);
        assert Checker.check(cashier.getBill(new int[]{7,5,3,1,6,4,2},new int[]{10,10,10,9,9,9,7}), 7350.0);
        assert Checker.check(cashier.getBill(new int[]{2,3,5},new int[]{5,3,2}), 2500.0);
    }

}
