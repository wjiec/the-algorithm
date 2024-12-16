package weekly.w428.B;

import java.util.*;

/**
 * 3387. Maximize Amount After Two Days of Conversions
 *
 * https://leetcode.cn/contest/weekly-contest-428/problems/maximize-amount-after-two-days-of-conversions/
 *
 * You are given a string initialCurrency, and you start with 1.0 of initialCurrency.
 *
 * You are also given four arrays with currency pairs (strings) and rates (real numbers):
 *
 * pairs1[i] = [startCurrencyi, targetCurrencyi] denotes that you can convert
 * from startCurrencyi to targetCurrencyi at a rate of rates1[i] on day 1.
 *
 * pairs2[i] = [startCurrencyi, targetCurrencyi] denotes that you can convert
 * from startCurrencyi to targetCurrencyi at a rate of rates2[i] on day 2.
 *
 * Also, each targetCurrency can be converted back to its corresponding startCurrency at a rate of 1 / rate.
 *
 * You can perform any number of conversions, including zero, using rates1 on day 1,
 * followed by any number of additional conversions, including zero, using rates2 on day 2.
 *
 * Return the maximum amount of initialCurrency you can have after performing any
 * number of conversions on both days in order.
 *
 * Note: Conversion rates are valid, and there will be no contradictions in the rates
 * for either day.
 *
 * The rates for the days are independent of each other.
 */

public class Solution {

    public double maxAmount(String initialCurrency, List<List<String>> pairs1, double[] rates1, List<List<String>> pairs2, double[] rates2) {
        return maxAmount(1, 1.0, initialCurrency, initialCurrency, pairs1, rates1, pairs2, rates2);
    }

    private record Currency(String name, double value, String parent) {}

    public double maxAmount(int day, double value, String currency, String targetCurrency, List<List<String>> pairs1, double[] rates1, List<List<String>> pairs2, double[] rates2) {
        if (day > 2) return currency.equals(targetCurrency) ? value : 0;

        double ans = 0;

        // 第一天可以选择使用 pairs1 和 rates1 进行兑换
        List<List<String>> pairs; double[] rates;
        if (day == 1) { pairs = pairs1; rates = rates1; } else { pairs = pairs2; rates = rates2; }

        // 为货币兑换建立图
        Map<String, Map<String, Double>> g = new HashMap<>();
        for (int i = 0; i < pairs.size(); i++) {
            var pair = pairs.get(i);

            g.computeIfAbsent(pair.get(0), k -> new HashMap<>()).put(pair.get(1), rates[i]);
            g.computeIfAbsent(pair.get(1), k -> new HashMap<>()).put(pair.get(0), 1 / rates[i]);
        }

        // 开始执行换汇
        Queue<Currency> q = new ArrayDeque<>(); q.add(new Currency(currency, value, "#"));
        while (!q.isEmpty()) {
            var curr = q.remove();
            ans = Math.max(ans,  maxAmount(day + 1, curr.value, curr.name, targetCurrency, pairs1, rates1, pairs2, rates2));

            if (g.containsKey(curr.name)) {
                for (var e : g.get(curr.name).entrySet()) {
                    if (e.getKey().equals(curr.parent)) continue;

                    // 按照比例换汇
                    q.add(new Currency(e.getKey(), curr.value * e.getValue(), curr.name));
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
    }

}
