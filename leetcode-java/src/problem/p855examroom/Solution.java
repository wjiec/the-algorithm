package problem.p855examroom;

import common.TODO;

import java.util.TreeSet;

/**
 * 855. Exam Room
 *
 * https://leetcode.cn/problems/exam-room/
 *
 * There is an exam room with n seats in a single row labeled from 0 to n - 1.
 *
 * When a student enters the room, they must sit in the seat that maximizes the distance to the closest person.
 * If there are multiple such seats, they sit in the seat with the lowest number.
 * If no one is in the room, then the student sits at seat number 0.
 *
 * Design a class that simulates the mentioned exam room.
 *
 * Implement the ExamRoom class:
 *
 * ExamRoom(int n) Initializes the object of the exam room with the number of the seats n.
 * int seat() Returns the label of the seat at which the next student will set.
 * void leave(int p) Indicates that the student sitting at seat p will leave the room.
 * It is guaranteed that there will be a student sitting at seat p.
 */

public class Solution {

    @TODO
    private static class ExamRoom {
        private final int len;
        private final TreeSet<Integer> seats = new TreeSet<>();
        public ExamRoom(int n) { len = n; }
        public void leave(int p) { seats.remove(p); }

        public int seat() {
            int sit = 0;
            if (seats.size() > 0) {
                int dist = seats.first();
                Integer prev = null;
                for (Integer s : seats) {
                    if (prev != null) {
                        int d = (s - prev) / 2;
                        if (d > dist) {
                            dist = d;
                            sit = prev + d;
                        }
                    }
                    prev = s;
                }

                if (len - 1 - seats.last() > dist) sit = len - 1;
            }

            seats.add(sit);
            return sit;
        }
    }

    public static void main(String[] args) {
        ExamRoom examRoom = new ExamRoom(10);
        assert examRoom.seat() == 0;
        assert examRoom.seat() == 9;
        assert examRoom.seat() == 4;
        assert examRoom.seat() == 2;
        examRoom.leave(4);
        assert examRoom.seat() == 5;
    }

}
