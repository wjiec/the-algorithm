package weekly.w483.A;

public class Solution {

    public String largestEven(String s) {
        char[] chars = s.toCharArray();

        int n = chars.length - 1;
        while (n >= 0 && (chars[n] & 1) == 1) n--;
        if (n < 0) return "";
        return new String(chars, 0, n + 1);
    }

    public static void main(String[] args) {
    }

}
