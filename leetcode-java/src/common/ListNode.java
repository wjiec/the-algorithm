package common;

public class ListNode {

    public int val;
    public ListNode next;

    public ListNode() {}
    public ListNode(int val) { this.val = val; }
    public ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    public String toString() { return String.format("[val=%d, next=%s]", val, next); }

    public static ListNode build(int ...numbers) {
        ListNode list = new ListNode();
        ListNode curr = list;
        for (var n : numbers) {
            curr.next = new ListNode(n);
            curr = curr.next;
        }

        return list.next;
    }

}
