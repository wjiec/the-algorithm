package weekly.bw118.A;

import java.util.ArrayList;
import java.util.List;

/**
 * 2942. Find Words Containing Character
 *
 * https://leetcode.cn/contest/biweekly-contest-118/problems/find-words-containing-character/
 *
 * You are given a 0-indexed array of strings words and a character x.
 *
 * Return an array of indices representing the words that contain the character x.
 *
 * Note that the returned array may be in any order.
 */

public class Solution {

    public List<Integer> findWordsContaining(String[] words, char x) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            if (words[i].indexOf(x) != -1) ans.add(i);
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
