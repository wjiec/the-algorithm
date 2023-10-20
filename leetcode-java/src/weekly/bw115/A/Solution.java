package weekly.bw115.A;

import java.util.ArrayList;
import java.util.List;

/**
 * 2899. Last Visited Integers
 *
 * https://leetcode.cn/contest/biweekly-contest-115/problems/last-visited-integers/
 *
 * Given a 0-indexed array of strings words where words[i] is either a positive
 * integer represented as a string or the string "prev".
 *
 * Start iterating from the beginning of the array; for every "prev" string
 * seen in words, find the last visited integer in words which is defined as follows:
 *
 * Let k be the number of consecutive "prev" strings seen so far (containing the current string).
 *
 * Let nums be the 0-indexed array of integers seen so far and nums_reverse be the reverse of nums,
 * then the integer at (k - 1)th index of nums_reverse will be the last visited integer for this "prev".
 *
 * If k is greater than the total visited integers, then the last visited integer will be -1.
 *
 * Return an integer array containing the last visited integers.
 */

public class Solution {

    public List<Integer> lastVisitedIntegers(List<String> words) {
        int countPrev = 0;
        List<Integer> ans = new ArrayList<>();
        List<Integer> numbers = new ArrayList<>();
        for (var word : words) {
            if (word.equals("prev")) {
                countPrev++;
                if (countPrev > numbers.size()) ans.add(-1);
                else ans.add(numbers.get(numbers.size() - countPrev));
            } else {
                numbers.add(Integer.parseInt(word));
                countPrev = 0;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
