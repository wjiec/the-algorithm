package weekly.w295.B;

/**
 * 6079. Apply Discount to Prices
 *
 * https://leetcode.cn/contest/weekly-contest-295/problems/apply-discount-to-prices/
 *
 * A sentence is a string of single-space separated words where each word can contain digits, lowercase letters,
 * and the dollar sign '$'. A word represents a price if it is a non-negative real number preceded by a dollar sign.
 *
 * For example, "$100", "$23", and "$6.75" represent prices while "100", "$", and "2$3" do not.
 * You are given a string sentence representing a sentence and an integer discount.
 * For each word representing a price, apply a discount of discount% on the price and update the word in the sentence.
 * All updated prices should be represented with exactly two decimal places.

 * Return a string representing the modified sentence.
 */

public class Solution {

    public String discountPrices(String sentence, int discount) {
        double off = (100.0 - discount) / 100;
        StringBuilder sb = new StringBuilder();
        for (var word : sentence.split(" ")) {
            if (word.charAt(0) == '$' && word.length() > 1) {
                boolean isNumber = true;
                for (int i = 1; i < word.length(); i++) {
                    if (!Character.isDigit(word.charAt(i))) {
                        isNumber = false;
                        break;
                    }
                }

                if (isNumber) {
                    long number = Long.parseLong(word.substring(1), 10);
                    sb.append(String.format("$%.2f ", off * number));
                    continue;
                }
            }

            sb.append(word).append(" ");
        }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }

    public static void main(String[] args) {
        assert new Solution().discountPrices("706hzu76jjh7yufr5x9ot60v149k5 $7651913186 pw2o $6", 28).equals("706hzu76jjh7yufr5x9ot60v149k5 $5509377493.92 pw2o $4.32");
        assert new Solution().discountPrices("$76111 ab $6 $", 48).equals("$39577.72 ab $3.12 $");
        assert new Solution().discountPrices("1 2 $3 4 $5 $6 7 8$ $9 $10$", 100).equals("1 2 $0.00 4 $0.00 $0.00 7 8$ $0.00 $10$");

        assert new Solution().discountPrices("ka3caz4837h6ada4 r1 $602", 9).equals("ka3caz4837h6ada4 r1 $547.82");
        assert new Solution().discountPrices("duew$11mengf $8 $1", 7).equals("duew$11mengf $7.44 $0.93");
    }

}
