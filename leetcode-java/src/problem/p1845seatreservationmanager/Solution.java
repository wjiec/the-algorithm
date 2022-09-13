package problem.p1845seatreservationmanager;

import java.util.PriorityQueue;

/**
 * 1845. Seat Reservation Manager
 *
 * https://leetcode.cn/problems/seat-reservation-manager/
 *
 * Design a system that manages the reservation state of n seats that are numbered from 1 to n.
 *
 * Implement the SeatManager class:
 *
 * SeatManager(int n) Initializes a SeatManager object that will manage n seats numbered from 1 to n.
 * All seats are initially available.
 * int reserve() Fetches the smallest-numbered unreserved seat, reserves it, and returns its number.
 * void unreserve(int seatNumber) Unreserves the seat with the given seatNumber.
 */

public class Solution {

    private static class SeatManager {
        private int curr = 1;
        private final PriorityQueue<Integer> returns = new PriorityQueue<>();
        public SeatManager(int n) {}
        public void unreserve(int seatNumber) { returns.add(seatNumber); }
        public int reserve() { return returns.isEmpty() ? curr++ : returns.remove(); }
    }

    public static void main(String[] args) {
        SeatManager seatManager = new SeatManager(5);
        assert seatManager.reserve() == 1;
        assert seatManager.reserve() == 2;
        seatManager.unreserve(2);
        assert seatManager.reserve() == 2;
        assert seatManager.reserve() == 3;
        assert seatManager.reserve() == 4;
        assert seatManager.reserve() == 5;
        seatManager.unreserve(5);
    }

}
