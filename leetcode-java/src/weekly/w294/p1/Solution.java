package weekly.w294.p1;

import java.util.PriorityQueue;

public class Solution {

    public int maximumBags(int[] capacity, int[] rocks, int additionalRocks) {
        PriorityQueue<Integer> bags = new PriorityQueue<>();
        for (int i = 0; i < capacity.length; i++) {
            bags.add(capacity[i] - rocks[i]);
        }

        int ans = 0;
        while (!bags.isEmpty() && additionalRocks != 0) {
            int curr = bags.remove();
            if (additionalRocks >= curr) {
                ans++;
                additionalRocks -= curr;
            } else break;
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
