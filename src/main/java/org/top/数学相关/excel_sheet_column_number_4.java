package org.top.数学相关;
//https://leetcode.cn/problems/excel-sheet-column-number
//Excel 表列序号
/*
给你一个字符串 columnTitle ，表示 Excel 表格中的列名称。返回 该列名称对应的列序号 。

A -> 1
B -> 2
C -> 3
...
Z -> 26
AA -> 27
AB -> 28
...

输入: columnTitle = "A"
输出: 1

输入: columnTitle = "AB"
输出: 28

输入: columnTitle = "ZY"
输出: 701

提示：

    1 <= columnTitle.length <= 7
    columnTitle 仅由大写英文组成
    columnTitle 在范围 ["A", "FXSHRXW"] 内

 */
public class excel_sheet_column_number_4 {
    public static int titleToNumber(String columnTitle) {
        int res = 0;
        int mul = 1;
        for (int i = columnTitle.length() - 1; i >= 0; i--) {
            int num = columnTitle.charAt(i) - 'A' + 1;
            res += num * mul;
            mul *= 26;
        }
        return res;
    }
}
