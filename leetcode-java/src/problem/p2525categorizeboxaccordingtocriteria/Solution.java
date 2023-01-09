package problem.p2525categorizeboxaccordingtocriteria;

/**
 * 2525. Categorize Box According to Criteria
 *
 * https://leetcode.cn/problems/categorize-box-according-to-criteria/
 *
 * Given four integers length, width, height, and mass, representing the dimensions
 * and mass of a box, respectively, return a string representing the category of the box.
 *
 * The box is "Bulky" if:
 * Any of the dimensions of the box is greater or equal to 104.
 * Or, the volume of the box is greater or equal to 109.
 * If the mass of the box is greater or equal to 100, it is "Heavy".
 * If the box is both "Bulky" and "Heavy", then its category is "Both".
 * If the box is neither "Bulky" nor "Heavy", then its category is "Neither".
 * If the box is "Bulky" but not "Heavy", then its category is "Bulky".
 * If the box is "Heavy" but not "Bulky", then its category is "Heavy".
 * Note that the volume of the box is the product of its length, width and height.
 */

public class Solution {

    public String categorizeBox(int length, int width, int height, int mass) {
        boolean heavy = mass >= 100;
        long volume = (long) length * width * height;
        boolean bulky = length >= 10000 || width >= 10000 || height >= 10000 || volume >= 1_000_000_000;

        if (heavy && bulky) return "Both";
        else if (heavy) return "Heavy";
        else if (bulky) return "Bulky";
        return "Neither";
    }

    public static void main(String[] args) {
        assert new Solution().categorizeBox(1000, 35, 700, 300).equals("Heavy");
        assert new Solution().categorizeBox(200, 50, 800, 50).equals("Neither");
    }

}
