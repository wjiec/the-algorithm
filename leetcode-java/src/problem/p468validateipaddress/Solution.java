package problem.p468validateipaddress;

/**
 * 468. Validate IP Address
 *
 * https://leetcode-cn.com/problems/validate-ip-address/
 *
 * Given a string queryIP, return "IPv4" if IP is a valid IPv4 address, "IPv6" if IP is a valid IPv6 address
 * or "Neither" if IP is not a correct IP of any type.
 *
 * A valid IPv4 address is an IP in the form "x1.x2.x3.x4" where 0 <= xi <= 255 and xi cannot
 * contain leading zeros. For example, "192.168.1.1" and "192.168.1.0" are valid IPv4 addresses
 * but "192.168.01.1", while "192.168.1.00" and "192.168@1.1" are invalid IPv4 addresses.
 *
 * A valid IPv6 address is an IP in the form "x1:x2:x3:x4:x5:x6:x7:x8" where:
 *
 * 1 <= xi.length <= 4
 * xi is a hexadecimal string which may contain digits, lower-case English letter ('a' to 'f')
 * and upper-case English letters ('A' to 'F').
 *
 * Leading zeros are allowed in xi.
 *
 * For example, "2001:0db8:85a3:0000:0000:8a2e:0370:7334" and "2001:db8:85a3:0:0:8A2E:0370:7334"
 * are valid IPv6 addresses, while "2001:0db8:85a3::8A2E:037j:7334" and "02001:0db8:85a3:0000:0000:8a2e:0370:7334"
 * are invalid IPv6 addresses.
 */

public class Solution {

    public String validIPAddress(String queryIP) {
        if (queryIP.contains(".")) { // IPv4
            String[] segments = queryIP.split("\\.", 4);
            if (segments.length == 4) {
                boolean valid = true;
                for (var segment: segments) {
                    if (segment.length() == 0 || segment.length() > 4) {
                        valid = false; break;
                    }
                    if (segment.length() == 1 && segment.charAt(0) == '0') {
                        continue;
                    }

                    int value = 0;
                    for (var c : segment.toCharArray()) {
                        if (c < '0' || c > '9') {
                            valid = false; break;
                        }
                        if (value == 0 && c == '0') {
                            valid = false; break;
                        }
                        value = value * 10 + (c - '0');
                        if (value > 255) {
                            valid = false; break;
                        }
                    }
                }
                if (valid) return "IPv4";
            }
        } else if (queryIP.contains(":")) { // IPv6
            String[] segments = queryIP.split(":", 8);
            if (segments.length == 8) {
                boolean valid = true;
                for (var segment : segments) {
                    if (segment.length() == 0 || segment.length() > 4) {
                        valid = false; break;
                    }

                    for (var c : segment.toCharArray()) {
                        if ('0' <= c && c <= '9') continue;
                        if ('A' <= c && c <= 'F') continue;
                        if ('a' <= c && c <= 'f') continue;
                        valid = false; break;
                    }
                }
                if (valid) return "IPv6";
            }
        }

        return "Neither";
    }

    public static void main(String[] args) {
        assert new Solution().validIPAddress("172.0.0.1").equals("IPv4");
        assert new Solution().validIPAddress(":2001:0db8:").equals("Neither");
        assert new Solution().validIPAddress("2001:0db8:85a3:0:0:8A2E:0370:7334:").equals("Neither");
        assert new Solution().validIPAddress(":2001:0db8:85a3:0:0:8A2E:0370:7334").equals("Neither");
        assert new Solution().validIPAddress("172.16.254.1.").equals("Neither");
        assert new Solution().validIPAddress("172.16.254.").equals("Neither");
        assert new Solution().validIPAddress(":172.16.254.").equals("Neither");
        assert new Solution().validIPAddress(".172.16.254.").equals("Neither");
        assert new Solution().validIPAddress("2001.0:8:0:0:8:0:7").equals("Neither");

        assert new Solution().validIPAddress("172.16.254.1").equals("IPv4");
        assert new Solution().validIPAddress("2001:0db8:85a3:0:0:8A2E:0370:7334").equals("IPv6");
        assert new Solution().validIPAddress("256.256.256.256").equals("Neither");

        assert new Solution().validIPAddress("192.168.01.1").equals("Neither");
        assert new Solution().validIPAddress("192.168.1.00").equals("Neither");
        assert new Solution().validIPAddress("192.168@1.1").equals("Neither");
        assert new Solution().validIPAddress("2001:db8:85a3:0:0:8A2E:0370:7334").equals("IPv6");
        assert new Solution().validIPAddress("2001:0db8:85a3::8A2E:037j:7334").equals("Neither");
        assert new Solution().validIPAddress("02001:0db8:85a3:0000:0000:8a2e:0370:7334").equals("Neither");
    }

}
