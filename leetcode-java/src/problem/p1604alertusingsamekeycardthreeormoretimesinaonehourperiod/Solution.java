package problem.p1604alertusingsamekeycardthreeormoretimesinaonehourperiod;

import common.Checker;

import java.util.*;

/**
 * 1604. Alert Using Same Key-Card Three or More Times in a One Hour Period
 *
 * https://leetcode.cn/problems/alert-using-same-key-card-three-or-more-times-in-a-one-hour-period/
 *
 * LeetCode company workers use key-cards to unlock office doors. Each time a worker uses their
 * key-card, the security system saves the worker's name and the time when it was used.
 *
 * The system emits an alert if any worker uses the key-card three or more times in a one-hour period.
 *
 * You are given a list of strings keyName and keyTime where [keyName[i], keyTime[i]] corresponds
 * to a person's name and the time when their key-card was used in a single day.
 *
 * Access times are given in the 24-hour time format "HH:MM", such as "23:51" and "09:49".
 *
 * Return a list of unique worker names who received an alert for frequent keycard use.
 * Sort the names in ascending order alphabetically.
 *
 * Notice that "10:00" - "11:00" is considered to be within a one-hour period, while
 * "22:51" - "23:52" is not considered to be within a one-hour period.
 */

public class Solution {

    private record Record(String name, int ts) {}

    public List<String> alertNames(String[] keyName, String[] keyTime) {
        Record[] records = new Record[keyName.length];
        for (int i = 0; i < keyName.length; i++) {
            records[i] = new Record(keyName[i], toTimestamp(keyTime[i]));
        }
        Arrays.sort(records, Comparator.comparingInt(v -> v.ts));

        Set<String> workers = new HashSet<>();
        Map<String, Integer> count = new HashMap<>();
        for (int l = 0, r = 0; r < records.length; r++) {
            while (records[l].ts < records[r].ts - 60) {
                count.merge(records[l++].name, 1, (old, v) -> old - v);
            }
            if (count.merge(records[r].name, 1, Integer::sum) >= 3) {
                workers.add(records[r].name);
            }
        }

        List<String> ans = new ArrayList<>(workers);
        ans.sort(String::compareTo);
        return ans;
    }

    // hh:mm
    private int toTimestamp(String time) {
        int h = Integer.parseInt(time.substring(0, 2));
        int m = Integer.parseInt(time.substring(3, 5));
        return h * 60 + m;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().alertNames(new String[]{
            "a","a","a","a","a","a","b","b","b","b","b"
        }, new String[]{
            "23:27","03:14","12:57","13:35","13:18","21:58","22:39","10:49","19:37","14:14","10:41"
        }), List.of("a"));

        assert Checker.check(new Solution().alertNames(new String[]{
            "daniel","daniel","daniel","luis","luis","luis","luis"
        }, new String[]{
            "10:00","10:40","11:00","09:00","11:00","13:00","15:00"
        }), List.of("daniel"));

        assert Checker.check(new Solution().alertNames(new String[]{
            "alice","alice","alice","bob","bob","bob","bob"
        }, new String[]{
            "12:01","12:00","18:00","21:00","21:20","21:30","23:00"
        }), List.of("bob"));

        assert Checker.check(new Solution().alertNames(new String[]{
            "john","john","john"
        }, new String[]{
            "23:58","23:59","00:01"
        }), List.of());

        assert Checker.check(new Solution().alertNames(new String[]{
            "leslie","leslie","leslie","clare","clare","clare","clare"
        }, new String[]{
            "13:00","13:20","14:00","18:00","18:51","19:30","19:49"
        }), List.of("clare","leslie"));
    }

}
