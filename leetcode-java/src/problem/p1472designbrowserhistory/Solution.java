package problem.p1472designbrowserhistory;

/**
 * 1472. Design Browser History
 *
 * https://leetcode.cn/problems/design-browser-history/
 *
 * You have a browser of one tab where you start on the homepage and you can visit another url, get
 * back in the history number of steps or move forward in the history number of steps.
 *
 * Implement the BrowserHistory class:
 *
 * BrowserHistory(string homepage) Initializes the object with the homepage of the browser.
 * void visit(string url) Visits url from the current page. It clears up all the forward history.
 * string back(int steps) Move steps back in history. If you can only return x steps in the history
 * and steps > x, you will return only x steps. Return the current url after moving back in history at most steps.
 * string forward(int steps) Move steps forward in history. If you can only forward x steps in the history
 * and steps > x, you will forward only x steps. Return the current url after forwarding in history at most steps.
 */

public class Solution {

    private static class BrowserHistory {
        private int bp = 0, sp = 0;
        private final String[] history = new String[5001];
        public BrowserHistory(String homepage) { history[sp] = homepage; }
        public void visit(String url) { history[++sp] = url; bp = sp; }
        public String back(int steps) { sp -= Math.min(sp, steps); return history[sp]; }
        public String forward(int steps) { sp += Math.min(steps, bp - sp); return history[sp]; }
    }

    public static void main(String[] args) {
        BrowserHistory browserHistory = new BrowserHistory("leetcode.com");
        browserHistory.visit("google.com");
        browserHistory.visit("facebook.com");
        browserHistory.visit("youtube.com");
        assert browserHistory.back(1).equals("facebook.com");
        assert browserHistory.back(1).equals("google.com");
        assert browserHistory.forward(1).equals("facebook.com");
        browserHistory.visit("linkedin.com");
        assert browserHistory.forward(2).equals("linkedin.com");
        assert browserHistory.back(2).equals("google.com");
        assert browserHistory.back(7).equals("leetcode.com");
    }

}
