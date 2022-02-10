package daily.d210821pstringcompression;

/**
 * 443. String Compression
 *
 * https://leetcode-cn.com/problems/string-compression/
 *
 * Given an array of characters chars, compress it using the following algorithm:
 *
 * Begin with an empty string s. For each group of consecutive repeating characters in chars:
 *
 * If the group's length is 1, append the character to s.
 * Otherwise, append the character followed by the group's length.
 * The compressed string s should not be returned separately, but instead be stored in the input character array chars.
 * Note that group lengths that are 10 or longer will be split into multiple characters in chars.
 *
 * After you are done modifying the input array, return the new length of the array.
 *
 * You must write an algorithm that uses only constant extra space.
 */

public class Solution {

    public int compress(char[] chars) {
        char curr = chars[0];
        int idx = 0, count = 1;

        for (int i = 1, l = chars.length; i < l; i++) {
            if (chars[i] != curr) {
                chars[idx++] = curr;
                if (count != 1) {
                    for (var c : String.valueOf(count).toCharArray()) chars[idx++] = c;
                }

                count = 1;
                curr = chars[i];
            } else count++;
        }

        chars[idx++] = curr;
        if (count != 1) {
            for (var c : String.valueOf(count).toCharArray()) chars[idx++] = c;
        }

        return idx;
    }

    public static void main(String[] args) {
        assert new Solution().compress(new char[]{'a','a','b','b','c','c','c'}) == 6;
        assert new Solution().compress(new char[]{'a'}) == 1;
        assert new Solution().compress(new char[]{'a','b','b','b','b','b','b','b','b','b','b','b','b'}) == 4;
    }

}
