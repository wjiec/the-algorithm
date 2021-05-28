package problem.p599minimumindexsumoftwolists;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 599. Minimum Index Sum of Two Lists
 *
 * https://leetcode-cn.com/problems/minimum-index-sum-of-two-lists/
 *
 * Suppose Andy and Doris want to choose a restaurant for dinner,
 * and they both have a list of favorite restaurants represented by strings.
 *
 * You need to help them find out their common interest with the least list index sum.
 * If there is a choice tie between answers, output all of them with no order requirement.
 * You could assume there always exists an answer.
 */

public class Solution {

    public String[] findRestaurant(String[] list1, String[] list2) {
        Map<String, Integer> map = new HashMap<>();
        int l1 = list1.length, l2 = list2.length, min = Integer.MAX_VALUE, cnt = 1;
        for (int i = 0; i < l1; i++) {
            map.put(list1[i], i);
        }

        for (int i = 0; i < l2; i++) {
            Integer v = map.get(list2[i]);
            if (v != null) {
                int w = v + i + 1001;
                map.put(list2[i], w);
                if (w == min) {
                    cnt++;
                } else {
                    cnt = 1;
                    min = Math.min(min, w);
                }
            }
        }

        String[] ans = new String[cnt];
        for (var kv : map.entrySet()) {
            if (kv.getValue() == min) {
                ans[--cnt] = kv.getKey();
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert Arrays.deepEquals(new Solution().findRestaurant(
            new String[]{"Shogun", "Tapioca Express", "Burger King", "KFC"},
            new String[]{"Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"}
        ), new String[]{"Shogun"});
        assert Arrays.deepEquals(new Solution().findRestaurant(
            new String[]{"Shogun", "Tapioca Express", "Burger King", "KFC"},
            new String[]{"KFC", "Shogun", "Burger King"}
        ), new String[]{"Shogun"});
    }

}
