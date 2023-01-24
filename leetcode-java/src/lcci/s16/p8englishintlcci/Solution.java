package lcci.s16.p8englishintlcci;

/**
 * 面试题 16.08. 整数的英语表示
 *
 * https://leetcode.cn/problems/english-int-lcci/
 *
 * 给定一个整数，打印该整数的英文描述。
 */

public class Solution {

    private final String[] units = {
        "Zero",      // 0
        "One",       // 1
        "Two",       // 2
        "Three",     // 3
        "Four",      // 4
        "Five",      // 5
        "Six",       // 6
        "Seven",     // 7
        "Eight",     // 8
        "Nine",      // 9
        "Ten",       // 10,
        "Eleven",    // 11,
        "Twelve",    // 12,
        "Thirteen",  // 13,
        "Fourteen",  // 14,
        "Fifteen",   // 15,
        "Sixteen",   // 16,
        "Seventeen", // 17,
        "Eighteen",  // 18,
        "Nineteen",  // 19,
        "Twenty",    // 20,
    };

    private final String[] tenfoldUnits = {
        "",         // 0
        "",         // 10
        "Twenty",   // 20
        "Thirty",   // 30
        "Forty",    // 40
        "Fifty",    // 50
        "Sixty",    // 60
        "Seventy",  // 70
        "Eighty",   // 80
        "Ninety",   // 90
    };

    private static final int Hundred = 100;
    private static final int Thousand = 1_000;
    private static final int Million = 1_000_000;
    private static final int Billion = 1_000_000_000;

    public String numberToWords(int num) {
        if (num < units.length) return units[num];
        if (num < Hundred) return join(tenfoldUnits[num / 10], numberToWords(num % 10));
        if (num < Thousand) return join(numberToWords(num / Hundred), "Hundred", numberToWords(num % Hundred));
        if (num < Million) return join(numberToWords(num / Thousand), "Thousand", numberToWords(num % Thousand));
        if (num < Billion) return join(numberToWords(num / Million), "Million", numberToWords(num % Million));
        return join(numberToWords(num / Billion), "Billion", numberToWords(num % Billion));
    }

    public String join(String ...parts) {
        StringBuilder sb = new StringBuilder();
        for (String part : parts) {
            if (!part.equals(units[0])) {
                sb.append(part).append(" ");
            }
        }
        return sb.toString().trim();
    }

    public static void main(String[] args) {
        assert new Solution().numberToWords(100).equals("One Hundred");
        assert new Solution().numberToWords(123).equals("One Hundred Twenty Three");
        assert new Solution().numberToWords(12345).equals("Twelve Thousand Three Hundred Forty Five");
        assert new Solution().numberToWords(1234567).equals("One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven");
        assert new Solution().numberToWords(1234567891).equals("One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One");
    }

}
