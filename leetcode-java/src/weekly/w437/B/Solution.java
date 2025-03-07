package weekly.w437.B;

import java.util.Arrays;
import java.util.TreeMap;

/**
 * 3457. Eat Pizzas!
 *
 * https://leetcode.cn/contest/weekly-contest-437/problems/eat-pizzas/
 *
 * You are given an integer array pizzas of size n, where pizzas[i] represents the weight of the ith pizza.
 * Every day, you eat exactly 4 pizzas. Due to your incredible metabolism, when you eat
 * pizzas of weights W, X, Y, and Z, where W <= X <= Y <= Z, you gain the weight of only 1 pizza!
 *
 * On odd-numbered days (1-indexed), you gain a weight of Z.
 * On even-numbered days, you gain a weight of Y.
 * Find the maximum total weight you can gain by eating all pizzas optimally.
 *
 * Note: It is guaranteed that n is a multiple of 4, and each pizza can be eaten only once.
 */

public class Solution {

    /** @noinspection DuplicatedCode*/
    public long maxWeight(int[] pizzas) {
        TreeMap<Integer, Integer> m = new TreeMap<>();
        for (var v : pizzas) m.merge(v, 1, Integer::sum);

        // 如果是奇数天, 使用前三后一的方式吃
        // 如果是偶数天, 使用前二后二的方式吃
        //  - 会亏一个最大的数
        // 优先用所有的奇数先把大的数全吃了
        int cnt = pizzas.length / 4, odd = cnt / 2 + (cnt & 1), even = cnt - odd;

        long ans = 0;
        for (int i = 0; i < odd; i++) {
            ans += m.lastKey();
            m.merge(m.lastKey(), -1, (a, b) -> a + b == 0 ? null : (a + b));
            m.merge(m.firstKey(), -1, (a, b) -> a + b == 0 ? null : (a + b));
            m.merge(m.firstKey(), -1, (a, b) -> a + b == 0 ? null : (a + b));
            m.merge(m.firstKey(), -1, (a, b) -> a + b == 0 ? null : (a + b));
        }

        // 再吃次大的
        for (int i = 0; i < even; i++) {
            m.merge(m.lastKey(), -1, (a, b) -> a + b == 0 ? null : (a + b));
            ans += m.lastKey();
            m.merge(m.lastKey(), -1, (a, b) -> a + b == 0 ? null : (a + b));
            m.merge(m.firstKey(), -1, (a, b) -> a + b == 0 ? null : (a + b));
            m.merge(m.firstKey(), -1, (a, b) -> a + b == 0 ? null : (a + b));
        }

        return ans;
    }

    private static class Optimization {
        public long maxWeight(int[] pizzas) {
            Arrays.sort(pizzas);

            long ans = 0;
            int n = pizzas.length, cnt = n / 4, odd = cnt / 2 + (cnt & 1), even = cnt - odd;
            for (int i = 0; i < odd; i++) ans += pizzas[n - i - 1];
            for (int i = 0; i < even; i++) ans += pizzas[(n - odd - 1) - 2 * i - 1];

            return ans;
        }
    }

    public static void main(String[] args) {
        assert new Optimization().maxWeight(new int[]{3,4,2,4,2,4,2,2,4,5,3,2,1,2,1,1}) == 16;
        assert new Optimization().maxWeight(new int[]{5,2,2,4,3,3,1,3,2,5,4,2}) == 14;

        assert new Solution().maxWeight(new int[]{3,4,2,4,2,4,2,2,4,5,3,2,1,2,1,1}) == 16;
        assert new Solution().maxWeight(new int[]{5,2,2,4,3,3,1,3,2,5,4,2}) == 14;
    }

}
