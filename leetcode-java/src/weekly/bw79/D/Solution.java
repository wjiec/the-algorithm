package weekly.bw79.D;

import common.Checker;

import java.util.Arrays;

/**
 * 10011. Booking Concert Tickets in Groups
 *
 * https://leetcode.cn/contest/biweekly-contest-79/problems/booking-concert-tickets-in-groups/
 *
 * A concert hall has n rows numbered from 0 to n - 1, each with m seats, numbered from 0 to m - 1.
 * You need to design a ticketing system that can allocate seats in the following cases:
 *
 * If a group of k spectators can sit together in a row.
 * If every member of a group of k spectators can get a seat. They may or may not sit together.
 * Note that the spectators are very picky. Hence:
 *
 * They will book seats only if each member of their group can get a seat with row number
 * less than or equal to maxRow. maxRow can vary from group to group.
 * In case there are multiple rows to choose from, the row with the smallest number is chosen.
 * If there are multiple seats to choose in the same row, the seat with the smallest number is chosen.
 * Implement the BookMyShow class:
 *
 * BookMyShow(int n, int m) Initializes the object with n as number of rows and m as number of seats per row.
 *
 * int[] gather(int k, int maxRow) Returns an array of length 2 denoting the row and seat number (respectively)
 * of the first seat being allocated to the k members of the group, who must sit together.
 * In other words, it returns the smallest possible r and c such that all [c, c + k - 1] seats
 * are valid and empty in row r, and r <= maxRow. Returns [] in case it is not
 * possible to allocate seats to the group.
 *
 * boolean scatter(int k, int maxRow) Returns true if all k members of the group can be
 * allocated seats in rows 0 to maxRow, who may or may not sit together.
 * If the seats can be allocated, it allocates k seats to the group with the smallest row numbers,
 * and the smallest possible seat numbers in each row. Otherwise, returns false.
 */

public class Solution {

    private static class BookMyShow {

        private int lo = 0;
        private final int m;
        private final int[] sits;

        // n 行，每行 m 个座位
        public BookMyShow(int n, int m) { this.m = m; sits = new int[n]; Arrays.fill(sits, m); }

        public int[] gather(int k, int maxRow) {
            for (int i = lo; i <= maxRow; i++) {
                if (k <= sits[i]) {
                    sits[i] -= k;
                    return new int[]{i, m - sits[i] - k};
                }
            }
            return new int[]{};
        }

        public boolean scatter(int k, int maxRow) {
            int idx = lo;
            while (idx <= maxRow && k > sits[idx]) k -= sits[idx++];
            if (idx > maxRow && k > 0) return false;

            sits[idx] -= k;
            lo = idx;

            return true;
        }
    }

    public static void main(String[] args) {
        BookMyShow bms = new BookMyShow(2, 5);
        assert Checker.check(bms.gather(4, 0), new int[]{0,0});
        assert Checker.check(bms.gather(2, 0), new int[]{});
        assert bms.scatter(5, 1);
        assert !bms.scatter(5, 1);

        bms = new BookMyShow(1, 2);
        assert bms.scatter(2, 0);
    }

}
