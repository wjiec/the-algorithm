package weekly.w291.p1minimumconsecutivecardstopickup;

import java.util.HashMap;
import java.util.Map;

/**
 * 6048. Minimum Consecutive Cards to Pick Up
 *
 * https://leetcode-cn.com/contest/weekly-contest-291/problems/minimum-consecutive-cards-to-pick-up/
 *
 * You are given an integer array cards where cards[i] represents the value of the ith card.
 * A pair of cards are matching if the cards have the same value.
 *
 * Return the minimum number of consecutive cards you have to pick up to have a pair of matching cards
 * among the picked cards. If it is impossible to have matching cards, return -1.
 */

public class Solution {

    public int minimumCardPickup(int[] cards) {
        int ans = Integer.MAX_VALUE;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < cards.length; i++) {
            if (map.containsKey(cards[i])) {
                int l = map.get(cards[i]);
                if (i - l + 1 < ans) {
                    ans = i - l + 1;
                }
            }
            map.put(cards[i], i);
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static void main(String[] args) {
        assert new Solution().minimumCardPickup(new int[]{3,4,2,3,4,7}) == 4;
        assert new Solution().minimumCardPickup(new int[]{1,0,5,3}) == -1;
    }

}
