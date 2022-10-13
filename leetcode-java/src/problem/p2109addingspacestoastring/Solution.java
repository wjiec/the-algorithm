package problem.p2109addingspacestoastring;

/**
 * 2109. Adding Spaces to a String
 *
 * https://leetcode.cn/problems/adding-spaces-to-a-string/
 *
 * You are given a 0-indexed string s and a 0-indexed integer array spaces that describes the
 * indices in the original string where spaces will be added. Each space should be inserted
 * before the character at the given index.
 *
 * For example, given s = "EnjoyYourCoffee" and spaces = [5, 9], we place spaces
 * before 'Y' and 'C', which are at indices 5 and 9 respectively. Thus, we obtain
 * "Enjoy Your Coffee".
 *
 * Return the modified string after the spaces have been added.
 */

public class Solution {

    public String addSpaces(String s, int[] spaces) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0, j = 0; i < s.length(); i++) {
            if (j < spaces.length && i == spaces[j]) {
                sb.append(' '); j++;
            }
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        assert new Solution().addSpaces("LeetcodeHelpsMeLearn", new int[]{8,13,15}).equals("Leetcode Helps Me Learn");
        assert new Solution().addSpaces("icodeinpython", new int[]{1,5,7,9}).equals("i code in py thon");
        assert new Solution().addSpaces("spacing", new int[]{0,1,2,3,4,5,6}).equals(" s p a c i n g");
    }

}
