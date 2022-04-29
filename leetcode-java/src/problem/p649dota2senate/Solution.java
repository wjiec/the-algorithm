package problem.p649dota2senate;

/**
 * 649. Dota2 Senate
 *
 * https://leetcode-cn.com/problems/dota2-senate/
 *
 * In the world of Dota2, there are two parties: the Radiant and the Dire.
 *
 * The Dota2 senate consists of senators coming from two parties. Now the Senate wants to decide on a change
 * in the Dota2 game. The voting for this change is a round-based procedure. In each round,
 * each senator can exercise one of the two rights:
 *
 * Ban one senator's right: A senator can make another senator lose all his rights
 * in this and all the following rounds.
 *
 * Announce the victory: If this senator found the senators who still have rights to vote are all
 * from the same party, he can announce the victory and decide on the change in the game.
 *
 * Given a string senate representing each senator's party belonging. The character 'R' and 'D' represent
 * the Radiant party and the Dire party. Then if there are n senators, the size of the given string will be n.
 *
 * The round-based procedure starts from the first senator to the last senator in the given order.
 * This procedure will last until the end of voting. All the senators
 * who have lost their rights will be skipped during the procedure.
 *
 * Suppose every senator is smart enough and will play the best strategy for his own party.
 * Predict which party will finally announce the victory and change the Dota2 game.
 * The output should be "Radiant" or "Dire".
 */

public class Solution {

    public String predictPartyVictory(String senate) {
        int r = 0, d = 0, rc = 0, dc = 0;
        while (true) {
            StringBuilder sb = new StringBuilder();
            for (var c : senate.toCharArray()) {
                if (c == 'R' && r != 0) r--;
                else if (c == 'D' && d != 0) d--;
                else if (c == 'R') { sb.append(c); d++; rc++; }
                else if (c == 'D') { sb.append(c); r++; dc++; }
            }

            if (dc == 0) return "Radiant";
            if (rc == 0) return "Dire";

            senate = sb.toString();
            rc = 0; dc = 0;
        }
//        return "";
    }

    public static void main(String[] args) {
        assert new Solution().predictPartyVictory("RD").equals("Radiant");
        assert new Solution().predictPartyVictory("RDD").equals("Dire");
        assert new Solution().predictPartyVictory("RDRD").equals("Radiant");
    }

}
