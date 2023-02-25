package weekly.w334.B;

public class Solution {

    public int[] divisibilityArray(String word, int m) {
        long curr = 0; int idx = 0;
        int[] ans = new int[word.length()];
        for (var c : word.toCharArray()) {
            curr = curr * 10 + (c - '0');
            ans[idx++] = curr % m == 0 ? 1 : 0;
            curr %= m;
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
