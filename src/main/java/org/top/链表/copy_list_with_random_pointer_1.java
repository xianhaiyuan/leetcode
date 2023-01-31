package org.top.链表;

import org.top.common.Node;

import java.util.*;

//复制带随机指针的链表
//https://leetcode-cn.com/problems/copy-list-with-random-pointer
/*
给你一个长度为 n 的链表，每个节点包含一个额外增加的随机指针 random ，该指针可以指向链表中的任何节点或空节点。
构造这个链表的 深拷贝。 深拷贝应该正好由 n 个 全新 节点组成，其中每个新节点的值都设为其对应的原节点的值。
新节点的 next 指针和 random 指针也都应指向复制链表中的新节点，并使原链表和复制链表中的这些指针能够表示相同的链表状态。
复制链表中的指针都不应指向原链表中的节点 。
例如，如果原链表中有 X 和 Y 两个节点，其中 X.random --> Y 。那么在复制链表中对应的两个节点 x 和 y ，同样有 x.random --> y 。
返回复制链表的头节点。
用一个由 n 个节点组成的链表来表示输入/输出中的链表。每个节点用一个 [val, random_index] 表示：
    val：一个表示 Node.val 的整数。
    random_index：随机指针指向的节点索引（范围从 0 到 n-1）；如果不指向任何节点，则为  null 。
你的代码 只 接受原链表的头节点 head 作为传入参数。

输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]

输入：head = [[1,1],[2,1]]
输出：[[1,1],[2,1]]

输入：head = [[3,null],[3,0],[3,null]]
输出：[[3,null],[3,0],[3,null]]

提示：

    0 <= n <= 1000
    -10^4 <= Node.val <= 10^4
    Node.random 为 null 或指向链表中的节点。

 */
public class copy_list_with_random_pointer_1 {

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
        map.put(cur, null);
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
