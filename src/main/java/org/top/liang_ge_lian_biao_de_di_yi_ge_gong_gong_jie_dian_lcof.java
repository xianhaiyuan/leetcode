package org.top;

import org.top.common.ListNode;

//https://leetcode.cn/problems/liang-ge-lian-biao-de-di-yi-ge-gong-gong-jie-dian-lcof/
//两个链表的第一个公共节点
public class liang_ge_lian_biao_de_di_yi_ge_gong_gong_jie_dian_lcof {
    public static void main(String[] args) {
    }

    ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        int a = 0, b = 0;
        ListNode curA = headA;
        while (curA != null) {
            curA = curA.next;
            a++;
        }
        ListNode curB = headB;
        while (curB != null) {
            curB = curB.next;
            b++;
        }

        int k = b - a;
        curB = headB;
        curA = headA;
        if (k > 0) {
            while (k>0) {
                curB = curB.next;
                k--;
            }
        } else {
            while (k < 0) {
                curA = curA.next;
                k++;
            }
        }

        while (curA != null && curB != null && curA != curB) {
            curA = curA.next;
            curB = curB.next;
        }

        if (curA != null && curB != null) {
            return curA;
        }

        return null;


    }

    /*
    链表 headA和 headB 的长度分别是 m 和 n。考虑当 m=n 和 m≠n 时，两个指针分别会如何移动：

    如果 m=n，则两个指针会同时到达两个链表的尾节点，然后同时变成空值 null，此时返回 null；

    如果 m≠n，则由于两个链表没有公共节点，两个指针也不会同时到达两个链表的尾节点，因此两个指针都会遍历完两个链表，
    在指针 pA 移动了 m+n 次、指针 pB 移动了 n+m 次之后，两个指针会同时变成空值 null，此时返回 null。
     */
    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        ListNode curA = headA, curB = headB;
        // 当 curA 和 curB 都为空时也会退出循环
        while (curA != curB) {
            curA = curA == null ? curB : curA.next;
            curB = curB == null ? curA : curB.next;
        }
        return curA;
    }
    // 方法三，使用hash表
}
