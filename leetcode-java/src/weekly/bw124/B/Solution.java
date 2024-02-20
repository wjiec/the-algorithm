package weekly.bw124.B;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 3039. Apply Operations to Make String Empty
 *
 * https://leetcode.cn/contest/biweekly-contest-124/problems/apply-operations-to-make-string-empty/
 *
 * You are given a string s.
 *
 * Consider performing the following operation until s becomes empty:
 *
 * For every alphabet character from 'a' to 'z', remove the first occurrence of that character in s (if it exists).
 * For example, let initially s = "aabcbbca". We do the following operations:
 *
 * Remove the underlined characters s = "aabcbbca". The resulting string is s = "abbca".
 * Remove the underlined characters s = "abbca". The resulting string is s = "ba".
 * Remove the underlined characters s = "ba". The resulting string is s = "".
 *
 * Return the value of the string s right before applying the last operation.
 *
 * In the example above, answer is "ba".
 */

public class Solution {

    @SuppressWarnings("unchecked")
    public String lastNonEmptyString(String s) {
        int maxSize = 0;
        List<Integer>[] chars = new List[128];
        Arrays.setAll(chars, v -> new ArrayList<>());
        for (int i = 0; i < s.length(); i++) {
            chars[s.charAt(i)].add(i);
            maxSize = Math.max(maxSize, chars[s.charAt(i)].size());
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (var cl : chars) {
            if (cl.size() == maxSize) {
                pq.add(cl.get(cl.size() - 1));
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) sb.append(s.charAt(pq.remove()));
        return sb.toString();
    }

    public static void main(String[] args) {
    }

}
