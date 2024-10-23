package weekly.w420.A;

import common.PrettyPrinter;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public List<String> stringSequence(String target) {
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < target.length(); i++) {
            char c = target.charAt(i);

            if (ans.isEmpty()) ans.add("a");
            else ans.add(ans.get(ans.size() - 1) + "a");

            while (ans.get(ans.size() - 1).charAt(i) != c) {
                char[] chars = ans.get(ans.size() - 1).toCharArray();
                chars[i] = (char) (chars[i] + 1);
                ans.add(new String(chars));
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        PrettyPrinter.println(new Solution().stringSequence("abc"));
    }

}
