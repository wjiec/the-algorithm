package weekly.w483.B;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    public List<List<String>> wordSquares(String[] words) {
        Arrays.sort(words, String::compareTo);

        List<List<String>> ans = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            char t0 = words[i].charAt(0), t3 = words[i].charAt(3);
            for (int j = 0; j < words.length; j++) {
                if (i == j) continue;
                char l0 = words[j].charAt(0), l3 = words[j].charAt(3);
                if (t0 != l0) continue;

                for (int k = 0; k < words.length; k++) {
                    if (k == i || k == j) continue;
                    char r0 = words[k].charAt(0), r3 = words[k].charAt(3);
                    if (t3 != r0) continue;

                    for (int l = 0; l < words.length; l++) {
                        if (l == i || l == j || l == k) continue;
                        char b0 = words[l].charAt(0), b3 = words[l].charAt(3);
                        if (b0 != l3 || b3 != r3) continue;

                        ans.add(List.of(words[i], words[j], words[k], words[l]));
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
