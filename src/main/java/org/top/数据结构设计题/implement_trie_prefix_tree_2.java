package org.top.数据结构设计题;
//https://leetcode.cn/problems/implement-trie-prefix-tree
//实现 Trie (前缀树)
/*
Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补完和拼写检查。

请你实现 Trie 类：

    Trie() 初始化前缀树对象。
    void insert(String word) 向前缀树中插入字符串 word 。
    boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
    boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。

输入
["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
[[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
输出
[null, null, true, false, true, null, true]

解释
Trie trie = new Trie();
trie.insert("apple");
trie.search("apple");   // 返回 True
trie.search("app");     // 返回 False
trie.startsWith("app"); // 返回 True
trie.insert("app");
trie.search("app");     // 返回 True

提示：

    1 <= word.length, prefix.length <= 2000
    word 和 prefix 仅由小写英文字母组成
    insert、search 和 startsWith 调用次数 总计 不超过 3 * 10^4 次

 */
public class implement_trie_prefix_tree_2 {

    public static void main(String[] args) {
        implement_trie_prefix_tree_2 node = new implement_trie_prefix_tree_2();
        node.insert("apple");
        boolean apple = node.search("apple");
        implement_trie_prefix_tree_2 app = node.searchPrefix("app");
        boolean app1 = node.startsWith("app");
        node.insert("app");
        boolean app2 = node.search("app");
    }

    private implement_trie_prefix_tree_2[] child;
    private boolean isEnd;

    public implement_trie_prefix_tree_2() {
        child = new implement_trie_prefix_tree_2[26];
        isEnd = false;
    }

    public void insert(String word) {
        implement_trie_prefix_tree_2 node = this;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = ch - 'a';
            if (node.child[index] == null) {
                node.child[index] = new implement_trie_prefix_tree_2();
            }
            node = node.child[index];
        }
        node.isEnd = true;
    }

    public boolean search(String word) {
        implement_trie_prefix_tree_2 node = searchPrefix(word);
        return node != null && node.isEnd;
    }

    private implement_trie_prefix_tree_2 searchPrefix(String word) {
        implement_trie_prefix_tree_2 node = this;
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
