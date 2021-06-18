package problem.p929uniqueemailaddresses;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 929. Unique Email Addresses
 *
 * https://leetcode-cn.com/problems/unique-email-addresses/
 *
 * Every valid email consists of a local name and a domain name, separated by the '@' sign.
 * Besides lowercase letters, the email may contain one or more '.' or '+'.
 *
 * For example, in "alice@leetcode.com", "alice" is the local name, and "leetcode.com" is the domain name.
 * If you add periods '.' between some characters in the local name part of an email address,
 * mail sent there will be forwarded to the same address without dots in the local name.
 * Note that this rule does not apply to domain names.
 *
 * For example, "alice.z@leetcode.com" and "alicez@leetcode.com" forward to the same email address.
 * If you add a plus '+' in the local name, everything after the first plus sign will be ignored.
 * This allows certain emails to be filtered. Note that this rule does not apply to domain names.
 *
 * For example, "m.y+name@email.com" will be forwarded to "my@email.com".
 * It is possible to use both of these rules at the same time.
 *
 * Given an array of strings emails where we send one email to each email[i],
 * return the number of different addresses that actually receive mails.
 */

public class Solution {

    public int numUniqueEmails(String[] emails) {
        Map<String, Set<String>> map = new HashMap<>();
        for (var email : emails) {
            String[] domains = email.split("@");
            map.putIfAbsent(domains[1], new HashSet<>());

            map.get(domains[1]).add(parseLocalName(domains[0]));
        }

        int ans = 0;
        for (var set : map.values()) {
            ans += set.size();
        }
        return ans;
    }

    private String parseLocalName(String name) {
        StringBuilder sb = new StringBuilder();
        for (var c : name.toCharArray()) {
            if (c == '+') break;
            if (c != '.') sb.append(c);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        assert new Solution().numUniqueEmails(new String[]{
            "test.email+alex@leetcode.com", "test.e.mail+bob.cathy@leetcode.com", "testemail+david@lee.tcode.com"
        }) == 2;
    }
}
