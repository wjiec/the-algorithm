package weekly.bw76.p2designanatmmachine;

import java.util.Arrays;

/**
 * 6062. Design an ATM Machine
 *
 * https://leetcode-cn.com/contest/biweekly-contest-76/problems/design-an-atm-machine/
 *
 * There is an ATM machine that stores banknotes of 5 denominations: 20, 50, 100, 200, and 500 dollars.
 * Initially the ATM is empty. The user can use the machine to deposit or withdraw any amount of money.
 *
 * When withdrawing, the machine prioritizes using banknotes of larger values.
 *
 * For example, if you want to withdraw $300 and there are 2 $50 banknotes, 1 $100 banknote, and 1 $200 banknote,
 * then the machine will use the $100 and $200 banknotes.
 * However, if you try to withdraw $600 and there are 3 $200 banknotes and 1 $500 banknote,
 * then the withdraw request will be rejected because the machine will first try to use the $500 banknote
 * and then be unable to use banknotes to complete the remaining $100. Note that the machine
 * is not allowed to use the $200 banknotes instead of the $500 banknote.
 *
 * Implement the ATM class:
 *
 * ATM() Initializes the ATM object.
 * void deposit(int[] banknotesCount) Deposits new banknotes in the order $20, $50, $100, $200, and $500.
 * int[] withdraw(int amount) Returns an array of length 5 of the number of banknotes that will be
 * handed to the user in the order $20, $50, $100, $200, and $500, and update the number of banknotes
 * in the ATM after withdrawing. Returns [-1] if it is not possible (do not withdraw any banknotes in this case).
 */

public class Solution {

    private static class ATM {
        private long a500, a200, a100, a50, a20;
        public ATM() {}

        public void deposit(int[] banknotesCount) {
            a20 += banknotesCount[0];
            a50 += banknotesCount[1];
            a100 += banknotesCount[2];
            a200 += banknotesCount[3];
            a500 += banknotesCount[4];
        }

        public int[] withdraw(int amount) {
            if (amount % 10 != 0) return new int[]{-1};

            int[] ans = new int[5];
            if (amount >= 500 && a500 != 0) {
                ans[4] = (int) Math.min(amount / 500, a500);
                amount -= ans[4] * 500;
            }

            if (amount >= 200 && a200 != 0) {
                ans[3] = (int) Math.min(amount / 200, a200);
                amount -= ans[3] * 200;
            }

            if (amount >= 100 && a100 != 0) {
                ans[2] = (int) Math.min(amount / 100, a100);
                amount -= ans[2] * 100;
            }

            if (amount >= 50 && a50 != 0) {
                ans[1] = (int) Math.min(amount / 50, a50);
                amount -= ans[1] * 50;
            }

            if (amount >= 20 && a20 != 0) {
                ans[0] = (int) Math.min(amount / 20, a20);
                amount -= ans[0] * 20;
            }

            if (amount != 0) return new int[]{-1};
            a500 -= ans[4];
            a200 -= ans[3];
            a100 -= ans[2];
            a50 -= ans[1];
            a20 -= ans[0];

            return ans;
        }
    }

    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.deposit(new int[]{0,0,1,2,1});
        System.out.println(Arrays.toString(atm.withdraw(600)));
        atm.deposit(new int[]{0,1,0,1,1});
        System.out.println(Arrays.toString(atm.withdraw(600)));
        System.out.println(Arrays.toString(atm.withdraw(550)));

        atm = new ATM();
        atm.deposit(new int[]{226094735,393965432,637421322,948644288,831564545});
        atm.deposit(new int[]{46552724,433649668,171823159,857494741,710787090});
        atm.deposit(new int[]{935259214,495389631,745491587,619918730,53636060});
        System.out.println(Arrays.toString(atm.withdraw(892448980)));
        atm.deposit(new int[]{897956273,638917478,895068048,700860525,472696905});
        System.out.println(Arrays.toString(atm.withdraw(748725445)));
        atm.deposit(new int[]{855772627,899627874,370102367,328216522,563737450});
        System.out.println(Arrays.toString(atm.withdraw(230192275)));
        System.out.println(Arrays.toString(atm.withdraw(901174015)));
        atm.deposit(new int[]{630750355,525959450,96652997,399933566,191101825});
        atm.deposit(new int[]{757564362,403459275,788390370,187081434,77572739});
        atm.deposit(new int[]{123522257,48419930,489703077,151691306,938262923});
        atm.deposit(new int[]{195174028,591248143,835060612,836343810,298963554});
        System.out.println(Arrays.toString(atm.withdraw(32137250)));
        System.out.println(Arrays.toString(atm.withdraw(143224030)));
    }

}
