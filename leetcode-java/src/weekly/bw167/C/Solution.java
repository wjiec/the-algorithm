package weekly.bw167.C;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Q3. Design Exam Scores Tracker
 *
 * https://leetcode.cn/contest/biweekly-contest-167/problems/design-exam-scores-tracker/
 *
 * Alice frequently takes exams and wants to track her scores and calculate
 * the total scores over specific time periods.
 *
 * Implement the ExamTracker class:
 *
 * ExamTracker(): Initializes the ExamTracker object.
 * void record(int time, int score): Alice takes a new exam at time time and achieves the score score.
 * long long totalScore(int startTime, int endTime): Returns an integer that represents the total
 * score of all exams taken by Alice between startTime and endTime (inclusive). If there are no recorded
 * exams taken by Alice within the specified time interval, return 0.
 *
 * It is guaranteed that the function calls are made in chronological order. That is,
 *
 * Calls to record() will be made with strictly increasing time.
 * Alice will never ask for total scores that require information from the future.
 * That is, if the latest record() is called with time = t, then totalScore() will
 * always be called with startTime <= endTime <= t.
 */

public class Solution {

    @SuppressWarnings("DuplicatedCode")
    private static class ExamTracker {
        private final static int MAX_N = (int) (1e5 + 1);

        private final long[] tree;
        private final Set<Integer> seen = new HashSet<>();
        private final List<Integer> sorted = new ArrayList<>();

        // 记录每一个时刻对应的索引, 使用线段树维护序列信息
        public ExamTracker() {
            tree = new long[2 << (32 - Integer.numberOfLeadingZeros(MAX_N))];
        }

        public void record(int time, int score) {
            if (seen.add(time)) sorted.add(time);
            update(1, 0, MAX_N - 1, sorted.size() - 1, score);
        }

        private void update(int p, int l, int r, int i, int v) {
            if (l == r) { tree[p] += v; return; }

            int mid = l + (r - l) / 2;
            if (i <= mid) update(p * 2, l, mid, i, v);
            else update(p * 2 + 1, mid + 1, r, i, v);
            tree[p] = tree[p * 2] + tree[p * 2 + 1];
        }

        private long query(int p, int l, int r, int ql, int qr) {
            if (ql <= l && r <= qr) return tree[p];

            int mid = l + (r - l) / 2;
            if (qr <= mid) return query(p * 2, l, mid, ql, qr);
            if (ql > mid) return query(p * 2 + 1, mid + 1, r, ql, qr);

            long lv = query(p * 2, l, mid, ql, qr);
            long rv = query(p * 2 + 1, mid + 1, r, ql, qr);
            return lv + rv;
        }

        public long totalScore(int startTime, int endTime) {
            // 我们要找到在 [startTime, endTime] 中的所有分数和
            //  - 找到第一个 > startTime - 1 的位置
            //  - 找到第一个 > endTime 的位置减一
            int si = binarySearch(startTime - 1);
            int ei = binarySearch(endTime) - 1;
            if (si > ei) return 0;

            return query(1, 0, MAX_N - 1, si, ei);
        }

        // 找到第一个 > target 的位置
        private int binarySearch(int target) {
            int l = 0, r = sorted.size();
            while (l < r) {
                int mid = l + (r - l) / 2;
                if (sorted.get(mid) > target) r = mid;
                else l = mid + 1;
            }
            return l;
        }
    }

    public static void main(String[] args) {
        var ex = new ExamTracker();
        ex.record(1, 98);
        assert ex.totalScore(1, 1) == 98;

        ex.record(5, 99);
        assert ex.totalScore(1, 3) == 98;
        assert ex.totalScore(1, 5) == 197;
        assert ex.totalScore(3, 4) == 0;
        assert ex.totalScore(2, 5) == 99;
    }

}
