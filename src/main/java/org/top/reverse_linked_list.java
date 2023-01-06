package org.top;

// https://leetcode-cn.com/problems/reverse-linked-list
// 反转链表
public class reverse_linked_list {

    static class ListNode {
        ListNode next;
        int val;
        ListNode(int v) {
            val = v;
        }
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        n1.next = n2;
        n2.next = n3;

        ListNode listNode = reverseList1(n1);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }

    // 递归反转链表
    public static ListNode reverseList1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode listNode = reverseList1(head.next);
        head.next.next = head;
        head.next = null;
        return listNode;
    }

    // 正常反转
    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode cur = head;
        ListNode pre = null;

        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        return pre;
    }

}
