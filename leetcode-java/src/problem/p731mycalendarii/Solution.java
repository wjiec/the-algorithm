package problem.p731mycalendarii;

import common.TODO;

import java.util.TreeMap;

/**
 * 731. My Calendar II
 *
 * https://leetcode.cn/problems/my-calendar-ii/
 *
 * You are implementing a program to use as your calendar. We can add a new event
 * if adding the event will not cause a triple booking.
 *
 * A triple booking happens when three events have some non-empty intersection
 * (i.e., some moment is common to all the three events.).
 *
 * The event can be represented as a pair of integers start and end that
 * represents a booking on the half-open interval [start, end),
 * the range of real numbers x such that start <= x < end.
 *
 * Implement the MyCalendarTwo class:
 *
 * MyCalendarTwo() Initializes the calendar object.
 * boolean book(int start, int end) Returns true if the event
 * can be added to the calendar successfully without causing a triple booking.
 * Otherwise, return false and do not add the event to the calendar.
 */

public class Solution {

    @TODO(tips = "线段树")
    private static class MyCalendarTwo {
        private final TreeMap<Integer, Integer> map = new TreeMap<>();
        public MyCalendarTwo() {}

        public boolean book(int start, int end) {
            map.put(start, map.getOrDefault(start, 0) + 1);
            map.put(end, map.getOrDefault(end, 0) - 1);

            int count = 0;
            for (int value : map.values()) {
                count += value;
                if (count >= 3) {
                    map.put(start, map.get(start) - 1);
                    map.put(end, map.get(end) + 1);
                    if (map.get(start) == 0) map.remove(start);
                    return false;
                }
            }
            return true;
        }
    }

    public static void main(String[] args) {
        MyCalendarTwo MyCalendar = new MyCalendarTwo();
        assert MyCalendar.book(10, 20);
        assert MyCalendar.book(50, 60);
        assert MyCalendar.book(10, 40);
        assert !MyCalendar.book(5, 15);
        assert MyCalendar.book(5, 10);
        assert MyCalendar.book(25, 55);
    }

}
