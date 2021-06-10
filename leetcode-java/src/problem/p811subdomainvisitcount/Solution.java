package problem.p811subdomainvisitcount;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 811. Subdomain Visit Count
 *
 * https://leetcode-cn.com/problems/subdomain-visit-count/
 *
 * A website domain like "discuss.leetcode.com" consists of various subdomains.
 * At the top level, we have "com", at the next level, we have "leetcode.com",
 * and at the lowest level, "discuss.leetcode.com".
 * When we visit a domain like "discuss.leetcode.com",
 * we will also visit the parent domains "leetcode.com" and "com" implicitly.
 *
 * Now, call a "count-paired domain" to be a count (representing the number of visits this domain received),
 * followed by a space, followed by the address.
 * An example of a count-paired domain might be "9001 discuss.leetcode.com".
 *
 * We are given a list cpdomains of count-paired domains.
 * We would like a list of count-paired domains, (in the same format as the input, and in any order),
 * that explicitly counts the number of visits to each subdomain.
 */

public class Solution {

    public List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> map = new HashMap<>();
        for (var item : cpdomains) {
            String[] kv = item.split(" ");
            int count = Integer.valueOf(kv[0], 10);
            String domain = kv[1];
            do {
                map.put(domain, map.getOrDefault(domain, 0) + count);
                if (!domain.contains(".")) {
                    break;
                }
                domain = domain.substring(domain.indexOf('.') + 1);
            } while (domain.length() != 0);
        }

        List<String> ans = new ArrayList<>();
        for (var kv : map.entrySet()) {
            ans.add(kv.getValue() + " " + kv.getKey());
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().subdomainVisits(new String[]{"9001 discuss.leetcode.com"}));
        System.out.println(new Solution().subdomainVisits(new String[]{
            "900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"
        }));
    }
}
