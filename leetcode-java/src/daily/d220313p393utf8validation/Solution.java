package daily.d220313p393utf8validation;

/**
 * 393. UTF-8 Validation
 *
 * https://leetcode-cn.com/problems/utf-8-validation/
 *
 * Given an integer array data representing the data, return whether it is a valid UTF-8 encoding.
 *
 * A character in UTF8 can be from 1 to 4 bytes long, subjected to the following rules:
 *
 * For a 1-byte character, the first bit is a 0, followed by its Unicode code.
 * For an n-bytes character, the first n bits are all one's, the n + 1 bit is 0,
 * followed by n - 1 bytes with the most significant 2 bits being 10.
 *
 * This is how the UTF-8 encoding would work:
 *
 *    Char. number range  |        UTF-8 octet sequence
 *       (hexadecimal)    |              (binary)
 *    --------------------+---------------------------------------------
 *    0000 0000-0000 007F | 0xxxxxxx
 *    0000 0080-0000 07FF | 110xxxxx 10xxxxxx
 *    0000 0800-0000 FFFF | 1110xxxx 10xxxxxx 10xxxxxx
 *    0001 0000-0010 FFFF | 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
 *
 * Note: The input is an array of integers. Only the least significant 8 bits of each
 * integer is used to store the data. This means each integer represents only 1 byte of data.
 */

public class Solution {

    public boolean validUtf8(int[] data) {
        for (int idx = 0, n = data.length; idx < n; idx++) {
            System.out.println(Integer.toString(data[idx], 2));
            switch (len(data[idx])) {
                case -1, 1 -> { return false; }
                case 2 -> {
                    if (++idx >= n) return false;
                    if (len(data[idx]) != 1) return false;
                }
                case 3 -> {
                    if (++idx >= n) return false;
                    if (len(data[idx]) != 1) return false;

                    if (++idx >= n) return false;
                    if (len(data[idx]) != 1) return false;
                }
                case 4 -> {
                    if (++idx >= n) return false;
                    if (len(data[idx]) != 1) return false;

                    if (++idx >= n) return false;
                    if (len(data[idx]) != 1) return false;

                    if (++idx >= n) return false;
                    if (len(data[idx]) != 1) return false;
                }
            }
        }
        return true;
    }

    private int len(int v) {
        if (v >> 7 == 0) return 0;  // 0xxx xxxx
        if (v >> 6 == 2) return 1;  // 10xx xxxx
        if (v >> 5 == 6) return 2;  // 110x xxxx
        if (v >> 4 == 14) return 3; // 1110 xxxx
        if (v >> 3 == 30) return 4; // 1111 0xxx

        return -1;
    }

    public static void main(String[] args) {
        assert new Solution().validUtf8(new int[]{240,162,138,147});

        assert new Solution().validUtf8(new int[]{197,130,1});
        assert !new Solution().validUtf8(new int[]{235,140,4});
        assert !new Solution().validUtf8(new int[]{197,130,1,199,12,3,5,3,6,7,112,44,5,6});
    }

}
