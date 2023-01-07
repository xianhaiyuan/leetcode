package org.top;

import org.top.common.Node;

import java.util.*;

//复制带随机指针的链表
//https://leetcode-cn.com/problems/copy-list-with-random-pointer
public class copy_list_with_random_pointer {

    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);

        n1.next = n2;
        n2.next = n3;
        n1.random = null;
        n2.random = n1;
        n3.random = null;

        Node node = copyRandomList(n1);
        System.out.println(node.val);


    }

    public static Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        Node cur = head;

        // 记录原链表每个节点的next节点
        Map<Node, Node> map = new HashMap<>();

        while (cur.next != null) {
            Node n = new Node(cur.val);
            Node nt = cur.next;

            map.put(cur, nt);

            cur.next = n;
            n.next = nt;
            cur = nt;
        }
        map.put(cur, cur.next);
        Node n = new Node(cur.val);
        cur.next = n;
        n.next = null;

        cur = head;
        Node res = cur.next;
        while (cur.next.next != null) {
            Node cp = cur.next;
            Node nt = cur.next.next;

            cp.next = nt.next;
            cp.random = cur.random != null ? cur.random.next : null;

            cur = nt;

        }

        Node nt = cur.next;
        nt.next = cur.next.next;
        nt.random = cur.random != null ? cur.random.next : null;

        cur = head;

        // 通过map恢复原链表
        while (cur != null) {
            nt = map.get(cur);
            cur.next = nt;
            cur = nt;
        }

        return res;

    }

}
