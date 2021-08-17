package problem.p1108defanginganipaddress;

/**
 * 1108. Defanging an IP Address
 *
 * https://leetcode-cn.com/problems/defanging-an-ip-address/
 *
 * Given a valid (IPv4) IP address, return a defanged version of that IP address.
 *
 * A defanged IP address replaces every period "." with "[.]".
 */

public class Solution {

    public String defangIPaddr(String address) {
        StringBuilder sb = new StringBuilder(address.length() + 6);
        for (var c : address.toCharArray()) {
            if (c == '.') sb.append("[.]");
            else sb.append(c);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        assert new Solution().defangIPaddr("1.1.1.1").equals("1[.]1[.]1[.]1");
        assert new Solution().defangIPaddr("255.100.50.0").equals("255[.]100[.]50[.]0");
    }

}
