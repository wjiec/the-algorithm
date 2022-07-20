package problem.p1268searchsuggestionssystem;

import common.PrettyPrinter;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 1268. Search Suggestions System
 *
 * https://leetcode.cn/problems/search-suggestions-system/
 *
 * You are given an array of strings products and a string searchWord.
 *
 * Design a system that suggests at most three product names from products
 * after each character of searchWord is typed. Suggested products should
 * have common prefix with searchWord. If there are more than three products
 * with a common prefix return the three lexicographically minimums products.
 *
 * Return a list of lists of the suggested products after each character of searchWord is typed.
 */

@SuppressWarnings("unchecked")
public class Solution {

    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        PriorityQueue<String>[] qs = new PriorityQueue[128];
        for (var product : products) {
            char first = product.charAt(0);
            if (qs[first] == null) qs[first] = new PriorityQueue<>();
            qs[first].add(product);
        }

        List<List<String>> ans = new ArrayList<>();
        for (int i = 0, n = searchWord.length(); i < n; i++) {
            char c = searchWord.charAt(i);
            List<String> curr = new ArrayList<>();
            if (qs[c] != null) {
                int ni = i + 1;
                PriorityQueue<String>[] ns = new PriorityQueue[128];
                while (!qs[c].isEmpty()) {
                    String item = qs[c].remove();
                    if (curr.size() < 3) curr.add(item);
                    if (ni < item.length()) {
                        char x = item.charAt(ni);
                        if (ns[x] == null) ns[x] = new PriorityQueue<>();
                        ns[x].add(item);
                    }
                }
                qs = ns;
            } else qs = new PriorityQueue[128];
            ans.add(curr);
        }
        return ans;
    }

    public static void main(String[] args) {
        PrettyPrinter.println(new Solution().suggestedProducts(new String[]{
            "mobile","mouse","moneypot","monitor","mousepad"
        }, "mouse"));

        PrettyPrinter.println(new Solution().suggestedProducts(new String[]{
            "havana"
        }, "havana"));

        PrettyPrinter.println(new Solution().suggestedProducts(new String[]{
            "bags","baggage","banner","box","cloths"
        }, "bags"));

        PrettyPrinter.println(new Solution().suggestedProducts(new String[]{
            "havana"
        }, "tatiana"));
    }

}
