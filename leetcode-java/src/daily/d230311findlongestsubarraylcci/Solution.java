package daily.d230311findlongestsubarraylcci;

import common.Checker;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public String[] findLongestSubarray(String[] array) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);

        int state = 0, start = 0, len = 0;
        for (int i = 0; i < array.length; i++) {
            if (Character.isDigit(array[i].charAt(0))) state++;
            else state--;

            Integer prev = map.get(state);
            if (prev != null && i - prev > len) {
                len = i - prev; start = prev;
            }
            map.putIfAbsent(state, i);
        }
        if (len == 0) return new String[0];

        String[] ans = new String[len];
        System.arraycopy(array, start, ans, 0, len + 1);
        return ans;
    }

    public static void main(String[] args) {
        Checker.check(new Solution().findLongestSubarray(new String[]{
            "42","10","O","t","y","p","g","B","96","H","5","v","P","52","25",
            "96","b","L","Y","z","d","52","3","v","71","J","A","0","v","51",
            "E","k","H","96","21","W","59","I","V","s","59","w","X","33","29",
            "H","32","51","f","i","58","56","66","90","F","10","93","53","85",
            "28","78","d","67","81","T","K","S","l","L","Z","j","5","R","b","44",
            "R","h","B","30","63","z","75","60","m","61","a","5","S","Z","D","2",
            "A","W","k","84","44","96","96","y","M"
        }), new String[]{
            "52","3","v","71","J","A","0","v","51","E","k","H","96","21","W","59",
            "I","V","s","59","w","X","33","29","H","32","51","f","i","58","56","66",
            "90","F","10","93","53","85","28","78","d","67","81","T","K","S","l","L",
            "Z","j","5","R","b","44","R","h","B","30","63","z","75","60","m","61","a","5"
        });
    }

}
