package org.top.链表;

import org.top.common.ListNode;

//https://leetcode.cn/problems/liang-ge-lian-biao-de-di-yi-ge-gong-gong-jie-dian-lcof/
//两个链表的第一个公共节点
/*
输入两个链表，找出它们的第一个公共节点。

输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
输出：Reference of the node with value = 8
输入解释：相交节点的值为 8 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。
在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。

输入：intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
输出：Reference of the node with value = 2
输入解释：相交节点的值为 2 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [0,9,1,2,4]，链表 B 为 [3,2,4]。在 A 中，
相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。

输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
输出：null
输入解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。
解释：这两个链表不相交，因此返回 null。

注意：

    如果两个链表没有交点，返回 null.
    在返回结果后，两个链表仍须保持原有的结构。
    可假定整个链表结构中没有循环。
    程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
    本题与主站 160 题相同：https://leetcode-cn.com/problems/intersection-of-two-linked-lists/

 */
public class liang_ge_lian_biao_de_di_yi_ge_gong_gong_jie_dian_lcof_2 {
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
