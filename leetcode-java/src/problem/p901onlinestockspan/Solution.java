package problem.p901onlinestockspan;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 901. Online Stock Span
 *
 * https://leetcode.cn/problems/online-stock-span/
 *
 * Design an algorithm that collects daily price quotes for some stock and returns
 * the span of that stock's price for the current day.
 *
 * The span of the stock's price today is defined as the maximum number of consecutive days
 * (starting from today and going backward) for which the stock price was
 * less than or equal to today's price.
 *
 * For example, if the price of a stock over the next 7 days were [100,80,60,70,60,75,85],
 * then the stock spans would be [1,1,1,2,1,4,6].
 *
 * Implement the StockSpanner class:
 *
 * StockSpanner() Initializes the object of the class.
 * int next(int price) Returns the span of the stock's price given that today's price is price.
 */

public class Solution {

    private static class StockSpanner {
        private final Deque<Integer> prices = new ArrayDeque<>();
        private final Deque<Integer> weights = new ArrayDeque<>();
        public StockSpanner() {}

        public int next(int price) {
            int weight = 1;
            while (!prices.isEmpty() && prices.peek() <= price) {
                prices.pop();
                weight += weights.pop();
            }

            prices.push(price);
            weights.push(weight);;

            return weight;
        }
    }

    public static void main(String[] args) {
        StockSpanner stockSpanner = new StockSpanner();
        assert stockSpanner.next(100) == 1;
        assert stockSpanner.next(80) == 1;
        assert stockSpanner.next(60) == 1;
        assert stockSpanner.next(70) == 2;
        assert stockSpanner.next(60) == 1;
        assert stockSpanner.next(75) == 4;
        assert stockSpanner.next(85) == 6;
    }

}
