package org.top.链表;

import org.top.common.ListNode;
//https://leetcode-cn.com/problems/reverse-nodes-in-k-group
//K 个一组翻转链表
/*
给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。

输入：head = [1,2,3,4,5], k = 2
输出：[2,1,4,3,5]

输入：head = [1,2,3,4,5], k = 3
输出：[3,2,1,4,5]

提示：

    链表中的节点数目为 n
    1 <= k <= n <= 5000
    0 <= Node.val <= 1000

进阶：你可以设计一个只用 O(1) 额外内存空间的算法解决此问题吗？
 */
public class reverse_nodes_in_k_group_6 {

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


    public static ListNode reverseKGroup1(ListNode head, int k) {
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
            ListNode[] reverse = myReverse1(head, tail);
            prev.next = reverse[0];
            reverse[1].next = nt;

            prev = reverse[1];
            head = nt;
        }

        return hair.next;
    }

    private static ListNode[] myReverse1(ListNode head, ListNode tail) {

        if (head == null || head.next == null || head == tail) {
            return new ListNode[]{head, tail};
        }

        ListNode hair = new ListNode(0);
        hair.next = head;
        ListNode cur = head;

        ListNode end = tail.next;
        // 头插法倒序
        while (cur != null && cur != end) {
            ListNode nt = cur.next;
            cur.next = hair.next;
            hair.next = cur;
            cur = nt;
        }

        head.next = null;

        return new ListNode[]{hair.next, head};

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
