package offer2.p58;

import java.util.ArrayList;
import java.util.List;

/**
 * 剑指 Offer II 058. 日程表
 *
 * https://leetcode.cn/problems/fi9suh/
 *
 * 请实现一个 MyCalendar 类来存放你的日程安排。如果要添加的时间内没有其他安排，则可以存储这个新的日程安排。
 *
 * MyCalendar 有一个 book(int start, int end)方法。它意味着在 start 到 end 时间内增加一个日程安排，注意，
 * 这里的时间是半开区间，即 [start, end), 实数 x 的范围为，  start <= x < end。
 *
 * 当两个日程安排有一些时间上的交叉时（例如两个日程安排都在同一时间内），就会产生重复预订。
 *
 * 每次调用 MyCalendar.book方法时，如果可以将日程安排成功添加到日历中而不会导致重复预订，返回 true。否则，
 * 返回 false 并且不要将该日程安排添加到日历中。
 *
 * 请按照以下步骤调用 MyCalendar 类: MyCalendar cal = new MyCalendar(); MyCalendar.book(start, end)
 */

public class Solution {

    private static class MyCalendar {
        private final List<int[]> eps = new ArrayList<>();
        public MyCalendar() {}

        public boolean book(int start, int end) {
            if (eps.isEmpty()) {
                eps.add(new int[]{start, end});
                return true;
            }

            int idx = binarySearch(start);
            if (idx > 0 && eps.get(idx - 1)[1] > start) return false;
            if (idx < eps.size() && eps.get(idx)[0] < end) return false;

            eps.add(idx, new int[]{start, end});
            return true;
        }

        // 找到大于 target 的第一个位置
        private int binarySearch(int target) {
            int l = 0, r = eps.size();
            while (l < r) {
                int mid = l + (r - l) / 2;
                if (eps.get(mid)[0] <= target)  l = mid + 1;
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
    }

}
