package problem.p1374generateastringwithcharactersthathaveoddcounts;

/**
 * 1374. Generate a String With Characters That Have Odd Counts
 *
 * https://leetcode-cn.com/problems/generate-a-string-with-characters-that-have-odd-counts/
 *
 * Given an integer n, return a string with n characters such
 * that each character in such string occurs an odd number of times.
 *
 * The returned string must contain only lowercase English letters.
 *
 * If there are multiples valid strings, return any of them.  
 */

public class Solution {

    public String generateTheString(int n) {
        return "a".repeat(n - 1) + ((n % 2 == 0) ? "b" : "a");
    }

    public static void main(String[] args) {
        System.out.println(new Solution().generateTheString(1));
        System.out.println(new Solution().generateTheString(2));
        System.out.println(new Solution().generateTheString(3));
        System.out.println(new Solution().generateTheString(4));
        System.out.println(new Solution().generateTheString(5));
        System.out.println(new Solution().generateTheString(6));
        System.out.println(new Solution().generateTheString(7));
    }

}
