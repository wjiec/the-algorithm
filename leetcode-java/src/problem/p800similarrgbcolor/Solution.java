package problem.p800similarrgbcolor;

/**
 * 800. Similar RGB Color
 *
 * https://leetcode-cn.com/problems/similar-rgb-color/
 *
 * The red-green-blue color "#AABBCC" can be written as "#ABC" in shorthand.
 *
 * For example, "#15c" is shorthand for the color "#1155cc".
 * The similarity between the two colors "#ABCDEF" and "#UVWXYZ" is -(AB - UV)2 - (CD - WX)2 - (EF - YZ)2.
 *
 * Given a string color that follows the format "#ABCDEF",
 * return a string represents the color that is most similar to
 * the given color and has a shorthand (i.e., it can be represented as some "#XYZ").
 *
 * Any answer which has the same highest similarity as the best answer will be accepted.
 */

public class Solution {

    private final int[] colors = {
        0x00, 0x11, 0x22, 0x33, 0x44, 0x55, 0x66, 0x77,
        0x88, 0x99, 0xaa, 0xbb, 0xcc, 0xdd, 0xee, 0xff
    };

    public String similarRGB(String color) {
        return "#" + similar(color.substring(1, 3))
            + similar(color.substring(3, 5))
            + similar(color.substring(5, 7));
    }

    private String similar(String s) {
        int v = Integer.valueOf(s, 16);

        int min = Integer.MAX_VALUE, index = 0;
        for (int i = 0; i < colors.length; i++) {
            int diff = Math.abs(colors[i] - v);
            if (diff < min) {
                min = diff;
                index = i;
            }
        }

        return String.format("%02x", colors[index]);
    }

    public static void main(String[] args) {
        assert new Solution().similarRGB("#09f166").equals("#11ee66");
        assert new Solution().similarRGB("#4e3fe1").equals("#5544dd");
    }

}
