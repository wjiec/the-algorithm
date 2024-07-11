package weekly.w405.B;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 3211. Generate Binary Strings Without Adjacent Zeros
 *
 * https://leetcode.cn/contest/weekly-contest-405/problems/generate-binary-strings-without-adjacent-zeros/
 *
 * You are given a positive integer n.
 *
 * A binary string x is valid if all substrings of x of length 2 contain at least one "1".
 *
 * Return all valid strings with length n, in any order.
 */

public class Solution {

    public List<String> validStrings(int n) {
        if (n == 1) return List.of("0", "1");
        if (n == 0) return Collections.emptyList();

        List<String> ans = new ArrayList<>();
        for (var s : validStrings(n - 1)) {
            switch (s.charAt(s.length() - 1)) {
                case '0' -> ans.add(s + "1");
                case '1' -> {
                    ans.add(s + "1");
                    ans.add(s + "0");
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
