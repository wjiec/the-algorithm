package weekly.w457.A;

import java.util.ArrayList;
import java.util.List;

/**
 * Q1. Coupon Code Validator
 *
 * https://leetcode.cn/contest/weekly-contest-457/problems/coupon-code-validator/
 *
 * You are given three arrays of length n that describe the properties of n
 * coupons: code, businessLine, and isActive. The ith coupon has:
 *
 * code[i]: a string representing the coupon identifier.
 * businessLine[i]: a string denoting the business category of the coupon.
 * isActive[i]: a boolean indicating whether the coupon is currently active.
 *
 * A coupon is considered valid if all of the following conditions hold:
 *
 * code[i] is non-empty and consists only of alphanumeric characters (a-z, A-Z, 0-9) and underscores (_).
 * businessLine[i] is one of the following four categories: "electronics", "grocery", "pharmacy", "restaurant".
 * isActive[i] is true.
 *
 * Return an array of the codes of all valid coupons, sorted first by their
 * businessLine in the order: "electronics", "grocery", "pharmacy", "restaurant",
 * and then by code in lexicographical (ascending) order within each category
 */

public class Solution {

    public List<String> validateCoupons(String[] code, String[] businessLine, boolean[] isActive) {
        record Coupon(String code, String biz, boolean isActive) {}
        List<Coupon> coupons = new ArrayList<>();
        for (int i = 0; i < code.length; i++) {
            if (isActive[i] && isValidCode(code[i]) && isValidBiz(businessLine[i])) {
                coupons.add(new Coupon(code[i], businessLine[i], isActive[i]));
            }
        }
        coupons.sort((a, b) -> a.biz.equals(b.biz) ? a.code.compareTo(b.code) : a.biz.compareTo(b.biz));

        List<String> ans = new ArrayList<>();
        for (var coupon : coupons) ans.add(coupon.code);
        return ans;
    }

    private boolean isValidCode(String code) {
        if (code.length() == 0) return false;
        for (var c : code.toCharArray()) {
            if (!(('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z') || ('0' <= c && c <= '9') || c == '_')) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidBiz(String biz) {
        return biz.equals("electronics") || biz.equals("grocery") || biz.equals("pharmacy") || biz.equals("restaurant");
    }

    public static void main(String[] args) {
    }

}
