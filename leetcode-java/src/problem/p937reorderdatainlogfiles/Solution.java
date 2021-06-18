package problem.p937reorderdatainlogfiles;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 937. Reorder Data in Log Files
 *
 * https://leetcode-cn.com/problems/reorder-data-in-log-files/
 *
 * You are given an array of logs. Each log is a space-delimited string of words, where the first word is the identifier.
 *
 * There are two types of logs:
 *
 * Letter-logs: All words (except the identifier) consist of lowercase English letters.
 * Digit-logs: All words (except the identifier) consist of digits.
 * Reorder these logs so that:
 *
 * The letter-logs come before all digit-logs.
 * The letter-logs are sorted lexicographically by their contents. If their contents are the same,
 * then sort them lexicographically by their identifiers.
 * The digit-logs maintain their relative ordering.
 * Return the final order of the logs.
 */

public class Solution {

    public String[] reorderLogFiles(String[] logs) {
        Queue<String> digitLogs = new ArrayDeque<>();
        PriorityQueue<String> letterLogs = new PriorityQueue<>((l0, l1) -> {
            String[] ss0 = l0.split(" ", 2);
            String[] ss1 = l1.split(" ", 2);
            var v = ss0[1].compareTo(ss1[1]);
            if (v != 0) return v;
            return ss0[0].compareTo(ss1[0]);
        });
        for (var log : logs) {
            if (isLetterLog(log)) letterLogs.add(log);
            else digitLogs.add(log);
        }

        int index = 0;
        while (!letterLogs.isEmpty()) {
            logs[index++] = letterLogs.remove();
        }
        while (!digitLogs.isEmpty()) {
            logs[index++] = digitLogs.remove();
        }

        return logs;
    }

    private boolean isLetterLog(String s) {
        boolean blank = false;
        for (int i = 0, l = s.length(); i < l; i++) {
            if (s.charAt(i) == ' ') blank = true;
            else if (blank) return Character.isLetter(s.charAt(i));
        }
        return false;
    }

    public static void main(String[] args) {
        assert Arrays.deepEquals(new Solution().reorderLogFiles(new String[]{
            "a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo","a2 act car"
        }), new String[]{
            "a2 act car","g1 act car","a8 act zoo","ab1 off key dog","a1 9 2 3 1","zo4 4 7"
        });

        assert Arrays.deepEquals(new Solution().reorderLogFiles(new String[]{
            "dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"
        }), new String[]{
            "let1 art can","let3 art zero","let2 own kit dig","dig1 8 1 5 1","dig2 3 6"
        });

        assert Arrays.deepEquals(new Solution().reorderLogFiles(new String[]{
            "a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo"
        }), new String[]{
            "g1 act car","a8 act zoo","ab1 off key dog","a1 9 2 3 1","zo4 4 7"
        });
    }

}
