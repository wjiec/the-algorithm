package problem.p2512rewardtopkstudents;

import common.Checker;

import java.util.*;

/**
 * 2512. Reward Top K Students
 *
 * https://leetcode.cn/problems/reward-top-k-students/
 *
 * You are given two string arrays positive_feedback and negative_feedback, containing
 * the words denoting positive and negative feedback, respectively.
 *
 * Note that no word is both positive and negative.
 *
 * Initially every student has 0 points. Each positive word in a feedback report
 * increases the points of a student by 3, whereas each negative word
 * decreases the points by 1.
 *
 * You are given n feedback reports, represented by a 0-indexed string array
 * report and a 0-indexed integer array student_id, where student_id[i]
 * represents the ID of the student who has received the feedback report
 * report[i]. The ID of each student is unique.
 *
 * Given an integer k, return the top k students after ranking them in
 * non-increasing order by their points. In case more than one student
 * has the same points, the one with the lower ID ranks higher.
 */

public class Solution {

    public List<Integer> topStudents(String[] positive_feedback, String[] negative_feedback, String[] report, int[] student_id, int k) {
        // [id, score]
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) ->
            a[1] == b[1] ? b[0] - a[0] : a[1] - b[1]);

        Map<String, Integer> scores = new HashMap<>();
        for (var word : positive_feedback) scores.put(word, 3);
        for (var word : negative_feedback) scores.put(word, -1);
        for (int i = 0; i < report.length; i++) {
            int score = 0;
            for (var word : report[i].split(" ")) {
                score += scores.getOrDefault(word, 0);
            }
            pq.add(new int[]{student_id[i], score});
            if (pq.size() > k) pq.remove();
        }

        List<Integer> ans = new ArrayList<>(pq.size());
        while (!pq.isEmpty()) ans.add(0, pq.remove()[0]);
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().topStudents(new String[]{
            "smart","brilliant","studious"
        }, new String[]{
            "not"
        }, new String[]{
            "this student is studious",
            "the student is smart"
        }, new int[]{1,2}, 2), List.of(
            1,2
        ));

        assert Checker.check(new Solution().topStudents(new String[]{
            "smart","brilliant","studious"
        }, new String[]{
            "not"
        }, new String[]{
            "this student is not studious",
            "the student is smart"
        }, new int[]{1,2}, 2), List.of(
            2,1
        ));
    }

}
