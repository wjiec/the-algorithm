package problem.p2075decodetheslantedciphertext;

/**
 * 2075. Decode the Slanted Ciphertext
 *
 * https://leetcode.cn/problems/decode-the-slanted-ciphertext/
 *
 * A string originalText is encoded using a slanted transposition cipher to a string encodedText
 * with the help of a matrix having a fixed number of rows rows.
 *
 * originalText is placed first in a top-left to bottom-right manner.
 *
 * The blue cells are filled first, followed by the red cells, then the yellow cells, and so on, until
 * we reach the end of originalText. The arrow indicates the order in which the cells are filled.
 *
 * All empty cells are filled with ' '. The number of columns is chosen such that the rightmost column
 * will not be empty after filling in originalText.
 *
 * encodedText is then formed by appending all characters of the matrix in a row-wise fashion.
 *
 * The characters in the blue cells are appended first to encodedText, then the red cells, and so on, and
 * finally the yellow cells. The arrow indicates the order in which the cells are accessed.
 *
 * For example, if originalText = "cipher" and rows = 3, then we encode it in the following manner:
 *
 *
 * The blue arrows depict how originalText is placed in the matrix, and the red arrows denote the order
 * in which encodedText is formed. In the above example, encodedText = "ch ie pr".
 *
 * Given the encoded string encodedText and number of rows rows, return the original string originalText.
 *
 * Note: originalText does not have any trailing spaces ' '. The test cases are generated such that
 * there is only one possible originalText.
 */

public class Solution {

    public String decodeCiphertext(String encodedText, int rows) {
        char[] chars = encodedText.toCharArray();

        int count = 0, cols = chars.length / rows;
        for (var c : chars) if (c != ' ') count++;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cols && count > 0; i++) {
            int x = 0, y = i;
            while (x < rows && y < cols) {
                char c = chars[x++ * cols + y++];
                sb.append(c);
                if (c != ' ' && --count == 0) break;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        assert new Solution().decodeCiphertext("ch   ie   pr", 3).equals("cipher");
        assert new Solution().decodeCiphertext("iveo    eed   l te   olc", 4).equals("i love leetcode");
        assert new Solution().decodeCiphertext("coding", 1).equals("coding");
        assert new Solution().decodeCiphertext(" b  ac", 2).equals(" abc");
    }

}
