package org.top;

import java.util.HashMap;
import java.util.Map;

//https://github.com/afatcoder/LeetcodeTop/blob/master/microsoft/SDE.md
public class test {
    public static void main(String[] args) {


    }

    class DLinkedNode {
        DLinkedNode pre;
        DLinkedNode next;

        int key;
        int value;

        public DLinkedNode() {}
        public DLinkedNode(int k, int v) {
            key = k;
            value = v;
        }
    }

    Map<Integer, DLinkedNode> cache = new HashMap<>();
    int size;
    int capacity;

    DLinkedNode head;
    DLinkedNode tail;

    public test(int capacity) {
        size = 0;
        this.capacity = capacity;
        head = new DLinkedNode();
        tail = new DLinkedNode();
        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        DLinkedNode node = cache.get(key);
        if (node == null) {
            return -1;
        }
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        DLinkedNode node = cache.get(key);

        if (node == null) {

            DLinkedNode newNode = new DLinkedNode(key, value);
            cache.put(key, newNode);
            addToHead(newNode);
            ++size;

            if (size > capacity) {
                int removeKey = removeTail();
                --size;
                cache.remove(removeKey);
            }

        } else {

            node.value = value;
            moveToHead(node);

        }

    }

    private int removeTail() {
        int res = tail.pre.key;
        removeNode(tail.pre);
        return res;
    }

    private void moveToHead(DLinkedNode node) {
        removeNode(node);
        addToHead(node);
    }

    private void addToHead(DLinkedNode node) {
        head.next.pre = node;
        node.next = head.next;
        node.pre = head;
        head.next = node;
    }

    private void removeNode(DLinkedNode node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }


}
