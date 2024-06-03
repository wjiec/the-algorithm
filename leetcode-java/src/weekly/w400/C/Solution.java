package weekly.w400.C;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * 100322. Lexicographically Minimum String After Removing Stars
 *
 * https://leetcode.cn/contest/weekly-contest-400/problems/lexicographically-minimum-string-after-removing-stars/
 *
 * You are given a string s. It may contain any number of '*' characters. Your task is to remove all '*' characters.
 *
 * While there is a '*', do the following operation:
 *
 * Delete the leftmost '*' and the smallest non-'*' character to its left.
 * If there are several smallest characters, you can delete any of them.
 *
 * Return the lexicographically smallest resulting string after removing all '*' characters.
 */

public class Solution {

    public String clearStars(String s) {
        Set<Integer> removed = new HashSet<>();
        TreeMap<Character, TreeSet<Integer>> map = new TreeMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '*') {
                var fe = map.firstEntry();
                removed.add(fe.getValue().pollLast());
                if (fe.getValue().isEmpty()) map.remove(fe.getKey());
                removed.add(i);
            } else {
                map.computeIfAbsent(c, k -> new TreeSet<>()).add(i);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (!removed.contains(i)) {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
    }

}
