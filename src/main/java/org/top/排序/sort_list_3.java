package org.top.排序;

import org.top.common.ListNode;

//https://leetcode.cn/problems/sort-list
//排序链表
/*
给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。

输入：head = [4,2,1,3]
输出：[1,2,3,4]

输入：head = [-1,5,3,4,0]
输出：[-1,0,3,4,5]

输入：head = []
输出：[]

提示：

    链表中节点的数目在范围 [0, 5 * 10^4] 内
    -10^5 <= Node.val <= 10^5

进阶：你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
 */
public class sort_list_3 {
    public static void main(String[] args) {
        ListNode n1 = new ListNode(2);
        ListNode n2 = new ListNode(1);
        ListNode n3 = new ListNode(3);

        n1.next = n2;
        n2.next = n3;

        ListNode res = sortList1(n1);
        System.out.println(res.val);

    }

    // 归并排序
    public static ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        int len = 0;
        ListNode node = head;
        while (node != null) { // 用于统计链表长度
            len++;
            node = node.next;
        }

        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;

        // 每次将链表拆分成若干个长度为subLen的子链表 , 并按照每两个子链表一组进行合并
        for (int subLen = 1; subLen < len; subLen *= 2) {
            ListNode prev = dummyHead;
            ListNode cur = dummyHead.next;

            while (cur != null) { // 如果链表没有被拆完

                // 拆分subLen长度的链表1, subLen长度，循环subLen-1次
                ListNode head_1 = cur;
                for (int i = 1; i < subLen && cur != null && cur.next != null; i++) {
                    cur = cur.next;
                }

                ListNode head_2 = cur.next;
                // 断开第一个链表和第二个链表的链接
                cur.next = null;
                cur = head_2;
                // 拆分subLen长度的链表2
                for (int i = 1; i < subLen && cur != null && cur.next != null; i++) {
                    cur = cur.next;
                }

                ListNode next = null;
                if (cur != null) {
                    next = cur.next; // next用于记录 拆分完两个链表的结束位置
                    cur.next = null; // 断开连接
                }

                // 合并两个subLen长度的有序链表
                ListNode merged = mergeList(head_1, head_2);
                // 把链给接上，prev.next 指向排好序链表的头
                prev.next = merged;

                while (prev.next != null) { // 将prev移动到 subLen*2 的位置后去
                    prev = prev.next;
                }

                cur = next;

            }

        }

        return dummyHead.next;

    }

    private static ListNode mergeList(ListNode head_1, ListNode head_2) {
        if (head_1 == null) {
            return head_2;
        }
        if (head_2 == null) {
            return head_1;
        }
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (head_1 != null && head_2 != null) {
            if (head_1.val < head_2.val) {
                cur.next = head_1;
                head_1 = head_1.next;
            } else {
                cur.next = head_2;
                head_2 = head_2.next;
            }
            cur = cur.next;
        }

        if (head_1 != null) {
            cur.next = head_1;
        }

        if (head_2 != null) {
            cur.next = head_2;
        }

        return dummy.next;

    }

    // 快排(只交换值，不是真的整个节点交换)
    public static ListNode sortList1(ListNode head) {
        ListNode end = head;
        while (end.next != null) {
            end = end.next;
        }
        return quickSort(head, end);
    }

    public static ListNode quickSort(ListNode start, ListNode end) {
        ListNode res = start;
        if (start != end) {
            ListNode pos = partition(start, end);
            quickSort(start, pos);
            quickSort(pos.next, end);
        }
        return res;
    }

    private static ListNode partition(ListNode start, ListNode end) {
        if (start == end) {
            return start;
        }
        int key = start.val;
        ListNode less = start;
        ListNode cur = start.next;

        while (cur != end) {
            if (cur.val < key) {
                less = less.next;

                int temp = cur.val;
                cur.val = less.val;
                less.val = temp;

            }
            cur = cur.next;
        }

        int temp = less.val;
        less.val = start.val;
        start.val = temp;

        return less;

    }


}
