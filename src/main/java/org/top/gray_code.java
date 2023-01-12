package org.top;

import java.util.*;

//https://leetcode-cn.com/problems/gray-code
//格雷编码
public class gray_code {

    public List<Integer> grayCode(int n) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < (1<<n); i++) {
            // 格雷编码公式
            ans.add((i^(i>>1)));
        }
        return ans;
    }
}
