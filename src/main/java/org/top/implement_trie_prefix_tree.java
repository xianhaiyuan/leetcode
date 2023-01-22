package org.top;
//https://leetcode.cn/problems/implement-trie-prefix-tree
//实现 Trie (前缀树)
public class implement_trie_prefix_tree {

    public static void main(String[] args) {
        implement_trie_prefix_tree node = new implement_trie_prefix_tree();
        node.insert("apple");
        boolean apple = node.search("apple");
        implement_trie_prefix_tree app = node.searchPrefix("app");
        boolean app1 = node.startsWith("app");
        node.insert("app");
        boolean app2 = node.search("app");
    }

    private implement_trie_prefix_tree[] child;
    private boolean isEnd;

    public implement_trie_prefix_tree() {
        child = new implement_trie_prefix_tree[26];
        isEnd = false;
    }

    public void insert(String word) {
        implement_trie_prefix_tree node = this;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = ch - 'a';
            if (node.child[index] == null) {
                node.child[index] = new implement_trie_prefix_tree();
            }
            node = node.child[index];
        }
        node.isEnd = true;
    }

    public boolean search(String word) {
        implement_trie_prefix_tree node = searchPrefix(word);
        return node != null && node.isEnd;
    }

    private implement_trie_prefix_tree searchPrefix(String word) {
        implement_trie_prefix_tree node = this;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = ch - 'a';
            if (node.child[index] == null) {
                return null;
            }
            node = node.child[index];
        }
        return node;
    }

    public boolean startsWith(String prefix) {
        return searchPrefix(prefix) != null;
    }
}
