package weekly.w245.B;

import java.util.Arrays;

/** @noinspection DuplicatedCode*/
class Solution {

    public int maximumRemovals(String s, String p, int[] removable) {
        boolean[] removes = new boolean[s.length()];
        char[] ss = s.toCharArray(), ps = p.toCharArray();

        int l = 0, r = removable.length + 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            Arrays.fill(removes, false);
            for (int i = 0; i < mid; i++) {
                removes[removable[i]] = true;
            }

            if (check(ss, ps, removes)) l = mid + 1;
            else r = mid;
        }
        return l - 1;
    }

    private boolean check(char[] s, char[] p, boolean[] remove) {
        int si = 0, pi = 0, sl = s.length, pl = p.length;
        for (; si < sl && pi < pl; si++) {
            if (!remove[si] && s[si] == p[pi]) pi++;
        }
        return pi == pl;
    }

    public static void main(String[] args) {
    }

}
