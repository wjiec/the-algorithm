package daily.d210929p517superwashingmachines;

/**
 * 517. Super Washing Machines
 *
 * https://leetcode-cn.com/problems/super-washing-machines/
 *
 * You have n super washing machines on a line. Initially, each washing machine has some dresses or is empty.
 *
 * For each move, you could choose any m (1 <= m <= n) washing machines,
 * and pass one dress of each washing machine to one of its adjacent washing machines at the same time.
 *
 * Given an integer array machines representing the number of dresses
 * in each washing machine from left to right on the line,
 * return the minimum number of moves to make all the washing machines have the same number of dresses.
 *
 * If it is not possible to do it, return -1.
 */

public class Solution {

    public int findMinMoves(int[] machines) {
        int ans = 0, l = machines.length, sum = 0;
        for (var n : machines) sum += n;
        if (sum % l != 0) return -1;

        int avg = sum / l, step = 0;
        for (var n : machines) {
            n -= avg;
            step += n;
            ans = Math.max(ans, Math.max(Math.abs(step), n));
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().findMinMoves(new int[]{1,0,5}) == 3;
        assert new Solution().findMinMoves(new int[]{0,3,0}) == 2;
        assert new Solution().findMinMoves(new int[]{0,2,0}) == -1;
    }

}
