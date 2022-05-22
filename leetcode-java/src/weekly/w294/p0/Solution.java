package weekly.w294.p0;

public class Solution {

    public int percentageLetter(String s, char letter) {
        int n = s.length(), cnt = 0;
        for (var c : s.toCharArray()) if (c == letter) cnt++;
        return (int) ((double) cnt / n * 100);
    }

    public static void main(String[] args) {
    }

}
