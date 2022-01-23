package daily.d220123p2034stockpricefluctuation;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * 2034. Stock Price Fluctuation
 *
 * https://leetcode-cn.com/problems/stock-price-fluctuation/
 *
 * You are given a stream of records about a particular stock. Each record contains a timestamp and
 * the corresponding price of the stock at that timestamp.
 *
 * Unfortunately due to the volatile nature of the stock market, the records do not come in order.
 *
 * Even worse, some records may be incorrect. Another record with the same timestamp may appear later
 * in the stream correcting the price of the previous wrong record.
 *
 * Design an algorithm that:
 *
 * Updates the price of the stock at a particular timestamp, correcting the price from any previous records at the timestamp.
 * Finds the latest price of the stock based on the current records. The latest price is the price at the latest timestamp recorded.
 * Finds the maximum price the stock has been based on the current records.
 * Finds the minimum price the stock has been based on the current records.
 * Implement the StockPrice class:
 *
 * StockPrice() Initializes the object with no price records.
 * void update(int timestamp, int price) Updates the price of the stock at the given timestamp.
 * int current() Returns the latest price of the stock.
 * int maximum() Returns the maximum price of the stock.
 * int minimum() Returns the minimum price of the stock.
 */

public class Solution {

    private static class StockPrice {
        private int now = 0;
        private final HashMap<Integer, Integer> map;
        private final TreeMap<Integer, Integer> prices;

        public StockPrice() {
            map = new HashMap<>();
            prices = new TreeMap<>();
        }

        public void update(int timestamp, int price) {
            now = Math.max(now, timestamp);
            Integer prev = map.put(timestamp, price);
            if (prev != null) {
                prices.put(prev, prices.get(prev) - 1);
                if (prices.get(prev) == 0) {
                    prices.remove(prev);
                }
            }
            prices.merge(price, 1, Integer::sum);
        }

        public int current() { return map.get(now); }
        public int maximum() { return prices.lastKey(); }
        public int minimum() { return prices.firstKey(); }
    }


    public static void main(String[] args) {
        StockPrice stock = new StockPrice();
        stock.update(1, 10);
        stock.update(2, 5);
        assert stock.current() == 5;
        assert stock.maximum() == 10;

        stock.update(1, 3);
        assert stock.maximum() == 5;

        stock.update(4, 2);
        assert stock.minimum() == 2;
    }

}
