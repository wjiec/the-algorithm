package weekly.w371.B;

import java.util.*;

/**
 * 2933. High-Access Employees
 *
 * https://leetcode.cn/contest/weekly-contest-371/problems/high-access-employees/
 *
 * You are given a 2D 0-indexed array of strings, access_times, with size n.
 * For each i where 0 <= i <= n - 1, access_times[i][0] represents the
 * name of an employee, and access_times[i][1] represents the access
 * time of that employee. All entries in access_times are within the same day.
 *
 * The access time is represented as four digits using a 24-hour time format, for example, "0800" or "2250".
 *
 * An employee is said to be high-access if he has accessed the system three or more times within a one-hour period.
 *
 * Times with exactly one hour of difference are not considered part of the same one-hour period.
 * For example, "0815" and "0915" are not part of the same one-hour period.
 *
 * Access times at the start and end of the day are not counted within the same one-hour period.
 * For example, "0005" and "2350" are not part of the same one-hour period.
 *
 * Return a list that contains the names of high-access employees with any order you want.
 */

public class Solution {

    public List<String> findHighAccessEmployees(List<List<String>> access_times) {
        Map<String, List<String>> map = new HashMap<>();
        for (var access : access_times) {
            map.computeIfAbsent(access.get(0), v -> new ArrayList<>()).add(access.get(1));
        }

        Set<String> ans = new HashSet<>();
        for (var employ : map.entrySet()) {
            var times = employ.getValue();
            times.sort(String::compareTo);

            Queue<Integer> queue = new ArrayDeque<>();
            for (var time : times) {
                int curr = Integer.parseInt(time.substring(0, 2)) * 3600 + Integer.parseInt(time.substring(2)) * 60;
                System.out.printf("%s: %s %d\n", employ.getKey(), queue, curr);
                while (!queue.isEmpty() && queue.peek() + 3600 <= curr) queue.remove();

                queue.add(curr);
                if (queue.size() >= 3) ans.add(employ.getKey());
            }
        }
        return new ArrayList<>(ans);
    }

    public static void main(String[] args) {
    }

}
