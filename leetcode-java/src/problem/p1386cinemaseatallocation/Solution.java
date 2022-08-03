package problem.p1386cinemaseatallocation;

import java.util.TreeMap;

/**
 * 1386. Cinema Seat Allocation
 *
 * https://leetcode.cn/problems/cinema-seat-allocation/
 *
 * A cinema has n rows of seats, numbered from 1 to n and there are ten seats
 * in each row, labelled from 1 to 10 as shown in the figure above.
 *
 * Given the array reservedSeats containing the numbers of seats already
 * reserved, for example, reservedSeats[i] = [3,8] means the seat located
 * in row 3 and labelled with 8 is already reserved.
 *
 * Return the maximum number of four-person groups you can assign on the cinema seats.
 * A four-person group occupies four adjacent seats in one single row. Seats across an
 * aisle (such as [3,3] and [3,4]) are not considered to be adjacent, but there is an
 * exceptional case on which an aisle split a four-person group, in that case, the aisle
 * split a four-person group in the middle, which means to have two people on each side.
 */

@SuppressWarnings({"ConstantConditions", "FieldCanBeLocal"})
public class Solution {

    private final int left = (1 << 2) | (1 << 3) | (1 << 4) | (1 << 5);
    private final int mid = (1 << 4) | (1 << 5) | (1 << 6) | (1 << 7);
    private final int right = (1 << 6) | (1 << 7) | (1 << 8) | (1 << 9);

    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(1, 0); map.put(n, 0);
        for (var seat : reservedSeats) {
            map.putIfAbsent(seat[0], 0);
            map.put(seat[0], map.get(seat[0]) | 1 << seat[1]);
        }

        int ans = 0, prev = 0;
        for (Integer curr = map.firstKey(); curr != null; curr = map.higherKey(curr)) {
            int state = map.get(curr);
            if (prev + 1 < curr) {
                ans += 2 * (curr - prev - 1);
            }

            boolean l = ((state ^ left) & left) == left;
            boolean m = ((state ^ mid) & mid) == mid;
            boolean r = ((state ^ right) & right) == right;

            if (l && m && r) ans += 2;
            else if (l || r || m) ans += 1;

            prev = curr;
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxNumberOfFamilies(3, new int[][]{{1,2},{1,3},{1,8},{2,6},{3,1},{3,10}}) == 4;
        assert new Solution().maxNumberOfFamilies(2, new int[][]{{2,1},{1,8},{2,6}}) == 2;
        assert new Solution().maxNumberOfFamilies(4, new int[][]{{4,3},{1,4},{4,6},{1,7}}) == 4;
    }

}
