package problem.p1352productofthelastknumbers;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * 1352. Product of the Last K Numbers
 *
 * https://leetcode.cn/problems/product-of-the-last-k-numbers/
 *
 * Design an algorithm that accepts a stream of integers and retrieves the product of
 * the last k integers of the stream.
 *
 * Implement the ProductOfNumbers class:
 *
 * ProductOfNumbers() Initializes the object with an empty stream.
 * void add(int num) Appends the integer num to the stream.
 * int getProduct(int k) Returns the product of the last k numbers in the current list.
 * You can assume that always the current list has at least k numbers.
 *
 * The test cases are generated so that, at any time, the product of any contiguous
 * sequence of numbers will fit into a single 32-bit integer without overflowing.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    private static class ProductOfNumbers {
        private final List<BigInteger> list = new ArrayList<>();
        private final TreeSet<Integer> zero = new TreeSet<>();
        public ProductOfNumbers() { list.add(BigInteger.ONE); }
        public void add(int num) {
            if (num == 0) { zero.add(list.size()); num = 1; }
            list.add(list.get(list.size() - 1).multiply(BigInteger.valueOf(num)));
        }

        public int getProduct(int k) {
            if (zero.ceiling(list.size() - k) != null) return 0;

            BigInteger sum = list.get(list.size() - 1);
            BigInteger pre = list.get(list.size() - k - 1);
            return sum.divide(pre).intValue();
        }
    }

    private static class ProductOfNumbersOptimization {
        private final List<Integer> list = new ArrayList<>();
        public ProductOfNumbersOptimization() {}
        public void add(int num) {
            if (num == 0) list.clear();
            else if (list.isEmpty()) list.add(num);
            else list.add(list.get(list.size() - 1) * num);
        }

        public int getProduct(int k) {
            if (k > list.size()) return 0;
            if (k == list.size()) return list.get(list.size() - 1);

            int sum = list.get(list.size() - 1);
            int pre = list.get(list.size() - k - 1);
            return sum / pre;
        }
    }

    public static void main(String[] args) {
        ProductOfNumbers productOfNumbers = new ProductOfNumbers();
        productOfNumbers.add(3);
        productOfNumbers.add(0);
        productOfNumbers.add(2);
        productOfNumbers.add(5);
        productOfNumbers.add(4);
        assert productOfNumbers.getProduct(2) == 20;
        assert productOfNumbers.getProduct(3) == 40;
        assert productOfNumbers.getProduct(4) == 0;
        productOfNumbers.add(8);
        assert productOfNumbers.getProduct(2) == 32;

        ProductOfNumbersOptimization optimization = new ProductOfNumbersOptimization();
        optimization.add(3);
        optimization.add(0);
        optimization.add(2);
        optimization.add(5);
        optimization.add(4);
        assert optimization.getProduct(2) == 20;
        assert optimization.getProduct(3) == 40;
        assert optimization.getProduct(4) == 0;
        optimization.add(8);
        assert optimization.getProduct(2) == 32;
    }

}
