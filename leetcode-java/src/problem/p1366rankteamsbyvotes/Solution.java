package problem.p1366rankteamsbyvotes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 1366. Rank Teams by Votes
 *
 * https://leetcode.cn/problems/rank-teams-by-votes/
 *
 * In a special ranking system, each voter gives a rank from highest to lowest to
 * all teams participated in the competition.
 *
 * The ordering of teams is decided by who received the most position-one votes.
 * If two or more teams tie in the first position, we consider the second position to
 * resolve the conflict, if they tie again, we continue this process until the ties
 * are resolved. If two or more teams are still tied after considering all positions, we
 * rank them alphabetically based on their team letter.
 *
 * Given an array of strings votes which is the votes of all voters in the ranking systems.
 * Sort all teams according to the ranking system described above.
 *
 * Return a string of all teams sorted by the ranking system.
 */

public class Solution {

    public String rankTeams(String[] votes) {
        Map<Character, int[]> map = new HashMap<>();
        for (var vote : votes) {
            for (int i = 0; i < vote.length(); i++) {
                if (!map.containsKey(vote.charAt(i))) {
                    map.put(vote.charAt(i), new int[26]);
                }
                map.get(vote.charAt(i))[i]++;
            }
        }

        List<Map.Entry<Character, int[]>> list = new ArrayList<>(map.entrySet());
        list.sort((a, b) -> {
            int[] ac = a.getValue(), bc = b.getValue();
            for (int i = 0; i < 26; i++) {
                if (ac[i] != bc[i]) return bc[i] - ac[i];
            }
            return 0;
        });

        StringBuilder sb = new StringBuilder();
        for (var x : list) sb.append(x.getKey());
        return sb.toString();
    }

    public static void main(String[] args) {
        assert new Solution().rankTeams(new String[]{"ABC","ACB","ABC","ACB","ACB"}).equals("ACB");
        assert new Solution().rankTeams(new String[]{"WXYZ","XYZW"}).equals("XWYZ");
        assert new Solution().rankTeams(new String[]{"ZMNAGUEDSJYLBOPHRQICWFXTVK"}).equals("ZMNAGUEDSJYLBOPHRQICWFXTVK");
        assert new Solution().rankTeams(new String[]{"BCA","CAB","CBA","ABC","ACB","BAC"}).equals("ABC");
        assert new Solution().rankTeams(new String[]{"M","M","M","M"}).equals("M");
    }

}
