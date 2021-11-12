package problem.p1773countitemsmatchingarule;

import java.util.List;

/**
 * 1773. Count Items Matching a Rule
 *
 * https://leetcode-cn.com/problems/count-items-matching-a-rule/
 *
 * You are given an array items, where each items[i] = [typei, colori, namei] describes the type, color,
 * and name of the ith item.
 *
 * You are also given a rule represented by two strings, ruleKey and ruleValue.
 *
 * The ith item is said to match the rule if one of the following is true:
 *
 * ruleKey == "type" and ruleValue == typei.
 * ruleKey == "color" and ruleValue == colori.
 * ruleKey == "name" and ruleValue == namei.
 *
 * Return the number of items that match the given rule.
 */

public class Solution {

    public int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {
        return (int) items.stream().filter((item -> {
            switch (ruleKey) {
                case "type": return item.get(0).equals(ruleValue);
                case "color": return item.get(1).equals(ruleValue);
                case "name": return item.get(2).equals(ruleValue);
            }
            return false;
        })).count();
    }

    public static void main(String[] args) {
        assert new Solution().countMatches(List.of(
            List.of("phone","blue","pixel"),
            List.of("computer","silver","lenovo"),
            List.of("phone","gold","iphone")
        ), "color", "silver") == 1;

        assert new Solution().countMatches(List.of(
            List.of("phone","blue","pixel"),
            List.of("computer","silver","lenovo"),
            List.of("phone","gold","iphone")
        ), "type", "phone") == 2;
    }

}
