package problem.p957prisoncellsafterndays;

import common.Checker;

import java.util.HashMap;
import java.util.Map;

/**
 * 957. Prison Cells After N Days
 *
 * https://leetcode.cn/problems/prison-cells-after-n-days/
 *
 * There are 8 prison cells in a row and each cell is either occupied or vacant.
 *
 * Each day, whether the cell is occupied or vacant changes according to the following rules:
 *
 * If a cell has two adjacent neighbors that are both occupied or both vacant, then the cell becomes occupied.
 * Otherwise, it becomes vacant.
 * Note that because the prison is a row, the first and the last cells in the row can't have two adjacent neighbors.
 *
 * You are given an integer array cells where cells[i] == 1 if the ith cell is occupied and cells[i] == 0
 * if the ith cell is vacant, and you are given an integer n.
 *
 * Return the state of the prison after n days (i.e., n such changes described above).
 */

public class Solution {

    public int[] prisonAfterNDays(int[] cells, int n) {
        int state = 0;
        for (int i = 0; i < 8; i++)
            if (cells[i] != 0) state |= 1 << i;

        Map<Integer, Integer> seen = new HashMap<>();
        while (n > 0) {
            if (seen.containsKey(state)) {
                n %= seen.get(state) - n;
            }
            seen.put(state, n);

            if (n >= 1) {
                n--;
                state = nextState(state);
            }
        }

        int[] ans = new int[8];
        for (int i = 0; i < 8; i++) {
            if ((state & (1 << i)) != 0) {
                ans[i] = 1;
            }
        }
        return ans;
    }

    private int nextState(int state) {
        int next = 0;
        for (int i = 1; i < 7; i++) {
            if (((state >> (i - 1)) & 1) == ((state >> (i + 1)) & 1)) {
                next |= 1 << i;
            }
        }
        return next;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().prisonAfterNDays(new int[]{
            0,1,0,1,1,0,0,1
        }, 7), new int[]{0,0,1,1,0,0,0,0});

        assert Checker.check(new Solution().prisonAfterNDays(new int[]{
            1,0,0,1,0,0,1,0
        }, 1000000000), new int[]{0,0,1,1,1,1,1,0});
    }

}
