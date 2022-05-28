package weekly.bw79.B;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 6084. Sender With Largest Word Count
 *
 * https://leetcode.cn/contest/biweekly-contest-79/problems/sender-with-largest-word-count/
 *
 * You have a chat log of n messages. You are given two string arrays messages and senders
 * where messages[i] is a message sent by senders[i].
 *
 * A message is list of words that are separated by a single space with no leading or trailing spaces.
 * The word count of a sender is the total number of words sent by the sender.
 * Note that a sender may send more than one message.
 *
 * Return the sender with the largest word count. If there is more than one sender with the largest word count,
 * return the one with the lexicographically largest name.
 *
 * Note:
 *
 * Uppercase letters come before lowercase letters in lexicographical order.
 * "Alice" and "alice" are distinct.
 */

public class Solution {

    public String largestWordCount(String[] messages, String[] senders) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < messages.length; i++) {
            map.merge(senders[i], messages[i].split(" ").length, Integer::sum);
        }

        int ans = 0, idx = 0;
        Arrays.sort(senders);
        for (int i = 0; i < senders.length; i++) {
            if (map.get(senders[i]) >= ans) {
                idx = i;
                ans = map.get(senders[i]);
            }
        }
        return senders[idx];
    }

    public static void main(String[] args) {
    }

}
