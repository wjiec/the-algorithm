package problem.p729mycalendari;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 729. My Calendar I
 *
 * https://leetcode.cn/problems/my-calendar-i/
 *
 * You are implementing a program to use as your calendar. We can add a new event
 * if adding the event will not cause a double booking.
 *
 * A double booking happens when two events have some non-empty intersection
 * (i.e., some moment is common to both events.).
 *
 * The event can be represented as a pair of integers start and end that
 * represents a booking on the half-open interval [start, end),
 * the range of real numbers x such that start <= x < end.
 *
 * Implement the MyCalendar class:
 *
 * MyCalendar() Initializes the calendar object.
 * boolean book(int start, int end) Returns true if the event can be added to
 * the calendar successfully without causing a double booking.
 * Otherwise, return false and do not add the event to the calendar.
 */

public class Solution {

    private static class MyCalendar {
        private final List<int[]> list = new ArrayList<>();
        public MyCalendar() {
            list.add(new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE});
            list.add(new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE});
        }

        public boolean book(int start, int end) {
            int index = insert(end);
            if (list.get(index - 1)[1] <= start && list.get(index)[0] >= end) list.add(new int[]{start, end});
            else return false;

            list.sort(Comparator.comparingInt(v -> v[1]));
            return true;
        }

        private int insert(int target) {
            int l = 0, r = list.size();
            while (l < r) {
                int mid = l + (r - l) / 2;
                if (list.get(mid)[1] == target) return mid;
                else if (list.get(mid)[1] < target) l = mid + 1;
                else r = mid;
            }
            return l;
        }
    }

    public static void main(String[] args) {
        MyCalendar myCalendar = new MyCalendar();
        assert myCalendar.book(10, 20);
        assert !myCalendar.book(15, 25);
        assert myCalendar.book(20, 30);

        myCalendar = new MyCalendar();
        assert myCalendar.book(10, 20);
        assert !myCalendar.book(1, 11);
        assert myCalendar.book(8, 10);
        assert myCalendar.book(20, 30);
        assert myCalendar.book(35, 50);
        assert myCalendar.book(30, 31);
    }

}
