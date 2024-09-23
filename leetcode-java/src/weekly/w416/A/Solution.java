package weekly.w416.A;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 3295. Report Spam Message
 *
 * https://leetcode.cn/contest/weekly-contest-416/problems/report-spam-message/
 *
 * You are given an array of strings message and an array of strings bannedWords.
 *
 * An array of words is considered spam if there are at least two words in it that
 * exactly match any word in bannedWords.
 *
 * Return true if the array message is spam, and false otherwise.
 */

public class Solution {

    public boolean reportSpam(String[] message, String[] bannedWords) {
        Set<String> banned = new HashSet<>(Arrays.asList(bannedWords));
        int ans = 0;
        for (var word : message) {
            if (banned.contains(word)) ans++;
        }
        return ans >= 2;
    }

    public static void main(String[] args) {
    }

}
