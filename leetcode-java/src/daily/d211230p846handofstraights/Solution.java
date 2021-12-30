package daily.d211230p846handofstraights;

import java.util.*;

/**
 * 846. Hand of Straights
 *
 * https://leetcode-cn.com/problems/hand-of-straights/
 *
 * Alice has some number of cards and she wants to rearrange the cards into groups so that
 * each group is of size groupSize, and consists of groupSize consecutive cards.
 *
 * Given an integer array hand where hand[i] is the value written on the ith card and an integer groupSize,
 * return true if she can rearrange the cards, or false otherwise.
 */

public class Solution {

    public boolean isNStraightHand(int[] hand, int groupSize) {
        if (hand.length % groupSize != 0) return false;

        Set<Integer> set = new HashSet<>();
        Map<Integer, Integer> map = new HashMap<>();
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (var n : hand) {
            map.merge(n, 1, Integer::sum);
            if (!set.contains(n)) {
                set.add(n);
                queue.add(n);
            }
        }

        while (!queue.isEmpty()) {
            int n = queue.remove();
            int c = map.getOrDefault(n, 0);
            if (c != 0) {
                for (int i = 0; i < groupSize; i++) {
                    if (map.getOrDefault(n + i, 0) < c) return false;
                    map.put(n + i, map.get(n + i) - c);
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        assert !new Solution().isNStraightHand(new int[]{8,10,12}, 3);

        assert new Solution().isNStraightHand(new int[]{1,2,3,6,2,3,4,7,8}, 3);
        assert !new Solution().isNStraightHand(new int[]{1,2,3,4,5}, 3);
    }

}
