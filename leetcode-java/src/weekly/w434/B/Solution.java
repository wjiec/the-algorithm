package weekly.w434.B;

import java.util.Arrays;
import java.util.List;

/**
 * 3433. Count Mentions Per User
 *
 * https://leetcode.cn/contest/weekly-contest-434/problems/count-mentions-per-user/
 *
 * You are given an integer numberOfUsers representing the total number of users and an array events of size n x 3.
 *
 * Each events[i] can be either of the following two types:
 *
 * Message Event: ["MESSAGE", "timestampi", "mentions_stringi"]
 * This event indicates that a set of users was mentioned in a message at timestampi.
 * The mentions_stringi string can contain one of the following tokens:
 *  id<number>: where <number> is an integer in range [0,numberOfUsers - 1]. There can be multiple
 *      ids separated by a single whitespace and may contain duplicates.
 *      This can mention even the offline users.
 * ALL: mentions all users.
 * HERE: mentions all online users.
 * Offline Event: ["OFFLINE", "timestampi", "idi"]
 *
 * This event indicates that the user idi had become offline at timestampi for 60 time units.
 * The user will automatically be online again at time timestampi + 60.
 *
 * Return an array mentions where mentions[i] represents the number of mentions
 * the user with id i has across all MESSAGE events.
 *
 * All users are initially online, and if a user goes offline or comes back online, their
 * status change is processed before handling any message event that occurs at the same timestamp.
 *
 * Note that a user can be mentioned multiple times in a single message event, and each
 * mention should be counted separately.
 */

public class Solution {

    public int[] countMentions(int numberOfUsers, List<List<String>> events) {
        events.sort((a, b) -> {
            int cmp = Integer.parseInt(a.get(1)) - Integer.parseInt(b.get(1));
            if (cmp == 0) return b.get(0).compareTo(a.get(0));
            return cmp;
        });

        int[] ans = new int[numberOfUsers];
        int[] offline = new int[numberOfUsers]; // offline[i] 表示 i 用户的离线到期时间
        Arrays.fill(offline, -1);

        int curr = 0;
        for (var event : events) {
            String typ = event.get(0), msg = event.get(2);
            curr = Integer.parseInt(event.get(1));

            if (typ.equals("OFFLINE")) {
                int id = Integer.parseInt(msg);
                offline[id] = curr + 60;
            } else {
                if (msg.equals("ALL")) {
                    for (int i = 0; i < numberOfUsers; i++) ans[i]++;
                } else if (msg.equals("HERE")) {
                    for (int i = 0; i < numberOfUsers; i++) if (curr >= offline[i]) ans[i]++;
                } else {
                    for (var user : msg.split(" ")) {
                        int id = Integer.parseInt(user.substring(2));
                        ans[id]++;
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
