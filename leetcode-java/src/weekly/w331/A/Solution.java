package weekly.w331.A;

import java.util.PriorityQueue;

public class Solution {

    public long pickGifts(int[] gifts, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (var v : gifts) pq.add(v);
        while (k-- > 0) pq.add((int) Math.sqrt(pq.remove()));

        long ans = 0;
        while (!pq.isEmpty()) ans += pq.remove();
        return ans;
    }

    public static void main(String[] args) {
    }

}
