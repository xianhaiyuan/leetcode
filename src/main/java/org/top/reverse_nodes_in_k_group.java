package org.top;

import org.top.common.ListNode;

public class reverse_nodes_in_k_group {

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;

        ListNode listNode = reverseKGroup(n1, 2);
        System.out.println(listNode.val);

    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode hair = new ListNode(0);
        hair.next = head;

        ListNode prev = hair;
        while (head != null) {
            ListNode tail = prev;
            for (int i = 0; i < k; i++) {
                tail = tail.next;
                if (tail == null) {
                    return hair.next;
                }
            }
            ListNode nt = tail.next;
            ListNode[] reverse = myReverse(head, tail);
            prev.next = reverse[0];
            reverse[1].next = nt;

            prev = tail;
            head = nt;


        }

        return hair.next;

    }

    /*public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode hair = new ListNode(0);
        hair.next = head;
        ListNode pre = hair;

        while (head != null) {
            ListNode tail = pre;

            for (int i = 0; i < k; ++i) {
                tail = tail.next;
                if (tail == null) {
                    return hair.next;
                }
            }
            ListNode nxt = tail.next;
            ListNode[] reverse = myReverse(head, tail);

            head = reverse[0];
            tail = reverse[1];
            pre.next = head;
            tail.next = nxt;
            pre = tail;
            head = tail.next;

        }

        return hair.next;
    }*/

    public static ListNode[] myReverse(ListNode head, ListNode tail) {
        ListNode prev = tail.next;
        ListNode p = head;
        while (prev != tail) {
            ListNode nex = p.next;
            p.next = prev;
            prev = p;
            p = nex;
        }
        return new ListNode[]{tail, head};
    }
}
