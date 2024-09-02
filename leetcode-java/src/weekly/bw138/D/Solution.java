package weekly.bw138.D;

import java.util.PriorityQueue;

/**
 * 3273. Minimum Amount of Damage Dealt to Bob
 *
 * https://leetcode.cn/contest/biweekly-contest-138/problems/minimum-amount-of-damage-dealt-to-bob/
 *
 * You are given an integer power and two integer arrays damage and health, both having length n.
 *
 * Bob has n enemies, where enemy i will deal Bob damage[i] points of damage per second while
 * they are alive (i.e. health[i] > 0).
 *
 * Every second, after the enemies deal damage to Bob, he chooses one of the enemies that is still
 * alive and deals power points of damage to them.
 *
 * Determine the minimum total amount of damage points that will be dealt to Bob before all n enemies are dead.
 */

public class Solution {

    public long minDamage(int power, int[] damage, int[] health) {
        long ans = 0, sum = 0;
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> -Long.compare(a[0] * b[1], a[1] * b[0]));
        for (int i = 0; i < damage.length; i++) {
            sum += damage[i];
            pq.add(new long[]{damage[i], (health[i] + power - 1) / power});
        }

        while (!pq.isEmpty()) {
            var curr = pq.remove();
            ans += curr[1] * sum;
            sum -= curr[0];
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minDamage(4, new int[]{1,2,3,4}, new int[]{4,5,6,8}) == 39;
    }

}
