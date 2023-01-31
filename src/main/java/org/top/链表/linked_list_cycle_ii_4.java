package org.top.链表;

import org.top.common.ListNode;

import java.util.*;

//https://leetcode-cn.com/problems/linked-list-cycle-ii
//环形链表 II
/*
给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
不允许修改 链表。

输入：head = [3,2,0,-4], pos = 1
输出：返回索引为 1 的链表节点
解释：链表中有一个环，其尾部连接到第二个节点。

输入：head = [1,2], pos = 0
输出：返回索引为 0 的链表节点
解释：链表中有一个环，其尾部连接到第一个节点。

输入：head = [1], pos = -1
输出：返回 null
解释：链表中没有环。

提示：

    链表中节点的数目范围在范围 [0, 104] 内
    -10^5 <= Node.val <= 10^5
    pos 的值为 -1 或者链表中的一个有效索引

 */
public class linked_list_cycle_ii_4 {

    public ListNode detectCycle(ListNode head) {
        // 头到入口=a,环长=b+c,当慢指针走了a+b,快指针当然也走过a+b,快指针要追上慢指针至少要走c+b,根据环的大小不同，准确来说，应该走过k*(c+b)
        // 所以得 2*(a+b) = a+b+k*(c+b) ->  a+b = k*(c+b) -> a = k*(c+b) - b -> a = (k-1)(c+b) + c
        // 当快慢指针第一次相遇后，慢指针往前再走 c 步或者 c步+n圈，会到达环交点，而由上面最后的公式可以看出 从头到入口a 就等于 c步+n圈
        // 得出结论是当快慢指针第一次相遇后,慢指针继续往前走，快指针指向头部以相同的速度往前走，当快慢两个指针相遇时，这个节点就是环交点

        // 另外慢指针进入环交点后，快指针必定在慢指针走一圈内追上慢指针，因为快的速度是慢的两倍，最极端就是慢在环交点，快在慢前一个，但也能追上

        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return null;
        }

        ListNode slow = head;
        ListNode fast = slow;
        while (true) {
            if (fast.next == null || fast.next.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                break;
            }
        }
        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;

    }

    public ListNode detectCycle2(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode slow = head;
        ListNode fast = head;
        do {
            if (fast.next == null || fast.next.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        } while (slow != fast);

        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    public ListNode detectCycle1(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return null;
        }
        ListNode cur = head;
        Set<ListNode> set = new HashSet<>();
        while (cur != null) {
            if (set.contains(cur)) {
                return cur;
            }
            set.add(cur);
            cur = cur.next;
        }

        return null;
    }
}
