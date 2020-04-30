package 算法.每日一题;

import java.util.HashMap;

/**
 *  两数之和
 * https://leetcode-cn.com/problems/two-sum/
 */
class 两数之和 {
    //暴力解法
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if(nums[i]+nums[j]==target){
                    return new int[]{i,j};
                }
            }
        }
        return new int[]{0,0};
    }
    //优化解法 因为是两个数的和所以  知道第一个数 与结果 那么相减就知道需要的数了 ，判断需要的数是否在列表中即可
    public int[] twoSumOptimize1(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            int needValue = target - nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                if (needValue == nums[j]) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{0, 0};
    }

    //优化解法2 因为优化1的解法是双层循环 其实完全不需要  第二个数 就是在第一层循环里算就行了
    //  [2,7,11,15]  17的 话 结果应该是2，15 那么第一层用2的时候 15就是key,2就是value 那么在判断15的时候可以通过key取出看有没有
    public int[] twoSumOptimize2(int[] nums, int target) {
        int[] result=new int[2];
        HashMap<Integer,Integer> map=new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int numi=nums[i];
            if(map.containsKey(numi)){
                result[0]=map.get(numi);
                result[1]=i;
                return result;
            }
            map.put(target-numi,i);
        }
        return result;
    }
}