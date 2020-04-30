package 算法.每日一题;

//https://leetcode-cn.com/problems/median-of-two-sorted-arrays/
class 寻找两个有序数组的中位数 {
    public static void main(String[] args) {
        findMedianSortedArrays2(new int[]{1,3}, new int[]{2});
    }

    //别人 的优化
    static public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int[] c = new int[nums1.length + nums2.length];
        int i = 0, j = 0, index = 0;
        while (i < nums1.length && j < nums2.length) { //当一个循环执行完毕 代表剩下都是直接添加即可
            if (nums1[i] < nums2[j]) {
                c[index++] = nums1[i++];
            } else {
                c[index++] = nums2[j++];
            }
        }
        while (i < nums1.length) {
            c[index++] = nums1[i++];
        }
        while (j < nums2.length) {
            c[index++] = nums2[j++];
        }
        return (c[(c.length-1)/2]+c[c.length/2])*1F/2;
    }


    static public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] c = new int[nums1.length + nums2.length];
        if (nums1.length == 0) {
            return countMid(nums2);
        }
        if (nums2.length == 0) {
            return countMid(nums1);
        }
        int[] smaller, biger;
        if (nums1[0] < nums2[0]) {
            smaller = nums1;
            biger = nums2;
        } else {
            smaller = nums2;
            biger = nums1;
        }
        int i = 0, j = 0, index = 0;
        int[] willAddArrays = new int[0];
        int willBeginIndex = -1;
        while (i < smaller.length || j < biger.length) {
            if (smaller[i] < biger[j]) {
                if (i < smaller.length - 1) {
                    c[index] = smaller[i];
                    i++;
                } else if (i == smaller.length - 1) {
                    c[index] = smaller[i];
                    willAddArrays = biger;
                    willBeginIndex = j;
                    break;
                }
            } else {
                if (j < biger.length - 1) {
                    c[index] = biger[j];
                    j++;
                } else if (j == biger.length - 1) {
                    c[index] = biger[j];
                    willAddArrays = smaller;
                    willBeginIndex = i;
                    break;
                }
            }
            index++;
        }
        if (willBeginIndex != -1) {
            for (int ha = willBeginIndex; ha < willAddArrays.length; ha++) {
                index++;
                c[index] = willAddArrays[ha];
            }
        }
        return countMid(c);
    }

    private static double countMid(int[] c) {
        int start = (c.length - 1) / 2;
        if (c.length % 2 == 0) {
            return (c[start] + c[start + 1]) * 1F / 2;
        } else {
            return c[start];
        }
    }
}