package org.top;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.cn/problems/find-in-mountain-array
//山脉数组中查找目标值
public class find_in_mountain_array {

    public static void main(String[] args) {
        MountainArray mountainArray = new MountainArray();
        // 0,1,2,4,2,1
        mountainArray.add(0);
        mountainArray.add(5);
        mountainArray.add(3);
        mountainArray.add(1);

        int res = findInMountainArray(1, mountainArray);
        System.out.println(res);

    }

    static class MountainArray {
        List<Integer> list = new ArrayList<>();
        public int get(int index) {
            return list.get(index);
        }
        public int length() {
            return list.size();
        }
        public void add(int i) {
            list.add(i);
        }
    }

    public static int findInMountainArray(int target, MountainArray mountainArr) {
        int l = 0, r = mountainArr.length() - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (mountainArr.get(mid) < mountainArr.get(mid + 1)) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }

        int peek = l;
        int res = binarySearch(target, mountainArr, 0, peek - 1, 1);
        if (res != -1) {
            return res;
        } else {
            res = binarySearch(target, mountainArr, peek + 1, mountainArr.length() - 1, -1);
        }

        return res == -1 ? (mountainArr.get(peek) == target ? peek : -1) : res;

    }

    public static int binarySearch(int target, MountainArray mountainArr, int l, int r, int flag) {

        // 上坡flag为1，下坡flag为-1

        target = target * flag;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            int cur = mountainArr.get(mid) * flag;
            if (cur < target) {
                l = mid + 1;
            } else if (cur > target) {
                r = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
