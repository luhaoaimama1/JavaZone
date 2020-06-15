package 算法.算法书籍.字符串.子字符串;

import java.util.ArrayList;
import java.util.List;

public class AAAAAA {

    public static void main(String[] args) {
        for (int abbaaba : computePrefix2("ababaca")) {
            System.out.print(abbaaba + ",");
        }


        System.out.println(kmpMatch("abakabam","aba"));

    }

    // 构造模式串 pattern 的最大匹配数表
    static int[] calculateMaxMatchLengths(String pattern) {
        int[] maxMatchLengths = new int[pattern.length()];
        int maxLength = 0;
        for (int i = 1; i < pattern.length(); i++) {
            while (maxLength > 0 && pattern.charAt(maxLength) != pattern.charAt(i)) {
                maxLength = maxMatchLengths[maxLength - 1]; // ①
            }
            if (pattern.charAt(maxLength) == pattern.charAt(i)) {
                maxLength++; // ②
            }
            maxMatchLengths[i] = maxLength;
        }
        return maxMatchLengths;
    }

    // 构造模式串 p 的最大匹配数表
    static int[] computePrefix(String p) {
        int[] maxMatchs = new int[p.length()];  //维护对应字符的 最大重叠数量
//        for (int i = 0; i < maxMatchs.length; i++) {
//            maxMatchs[i]=-1;
//        }
        maxMatchs[1] = 0;

        //最大匹配数  对于我更好的理解的词语是  最大重叠数量
        //也是最大匹配数的前缀的最后一个位置的索引
        int maxMatchsLength = 0;
        //q代表 后缀的最后一个字符的index
        for (int q = 2; q < p.length(); q++) {
            // Step.3
            //如果有重叠的值，然后 前缀 和后缀匹配 发现不相同。  （为什么前缀要+1因为+1后的值才是要匹配是否相同 ,之前的值都是相同的了）
            //那么我们把重叠的值更新为 之前维护的maxMatchs[maxMatchsLength].
            // 为什么是maxMatchsLength 因为他是既是重叠数量 又是前缀最后一个位置的索引
            // （maxMatchs[maxMatchsLength]这里没有+1.因为我们要把重叠部分缩小。重新匹配[maxMatchsLength+1]）
            while (maxMatchsLength > 0 && p.charAt(maxMatchsLength + 1) != p.charAt(q)) {
                maxMatchsLength = maxMatchs[maxMatchsLength];
            }

            // Step.1
            //重叠数量是 从0开始的。
            // 0=>1  检测后缀==0位置的值
            // 1=>2 检测后缀==1位置的值   (注意因为之前已经检查过0==后缀-1的值相同了 所以我们不需要检查了 仅仅检查1==后缀)
            //..
            // 那么如果 后缀的最后一个字符 ==前缀的最后一个字符  。那么重叠数量+1
            if (p.charAt(maxMatchsLength + 1) == p.charAt(q)) {
                maxMatchsLength++;
            }

            // Step.2
            //记录 对应位置的最大重叠数。 对于  对应位置匹配不同的情况，回复到 最大重叠数量. 重做匹配
            maxMatchs[q] = maxMatchsLength;
        }
        return maxMatchs;
    }

    // 构造模式串 p 的最大匹配数表
    static int[] computePrefix2(String p) {
        int[] maxMatchs = new int[p.length()];  //维护对应字符的 最大重叠数量

        //tip:1
        //前缀最大匹配数   对于我更好的理解的词语是  前缀中最大重叠数量。
        // maxMatchsLength-1：前缀中重叠字符串中最后一个字符的索引
        // maxMatchsLength ：前缀中重叠字符串的后一位字符的索引。这个字符是否相同未知
        //举例 现在重叠数量是3，那么0，1，2 是相同的 3则未知
        int maxMatchsLength = 0;

        //q代表 后缀的最后一个字符的索引
        for (int q = 1; q < p.length(); q++) {
            // Step.3
            //如果有重叠的值 并且  前缀中重叠字符串的后一位字符的索引 【参考 tip:1】和后缀匹配 发现不相同。
            //那么我们应该把其前缀整体向右移动直到 前缀又都重叠了（也就是找到更小的重叠数）。 这样我们才能继续重复Step.3
            //而之前我们已经维护了maxMatchs[maxMatchsLength - 1]位置的最大重叠数。
            //那么 maxMatchsLength = maxMatchs[maxMatchsLength - 1];变成此状态后（patter整体向右移动了） 前缀又都重叠了 那么重复Step.3
            while (maxMatchsLength > 0 && p.charAt(maxMatchsLength) != p.charAt(q)) {
                maxMatchsLength = maxMatchs[maxMatchsLength - 1];
            }

            // Step.1
            //重叠数量是 从0开始的。
            // 0=>1  检测后缀q的字符==0位置的值
            // 1=>2 检测后缀q的字符==1位置的值   (注意因为之前已经检查过0==后缀-1的值相同了 所以我们不需要检查了 仅仅检查1==后缀q的字符)
            //..
            // 那么如果 前缀中重叠字符串的后一位字符的索引 【参考 tip:1】 ==后缀的最后一个字符。那么重叠数量+1
            if (p.charAt(maxMatchsLength) == p.charAt(q)) {
                maxMatchsLength++;
            }

            // Step.2
            //记录 对应位置的最大重叠数。 对于  对应位置匹配不同的情况，恢复到 最大重叠数量. 重做匹配
            maxMatchs[q] = maxMatchsLength;
        }
        return maxMatchs;
    }

    static List<Integer> kmpMatch(String content, String p) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        int[] maxMatchs = computePrefix2(p);
        int matchCount = 0;

        //从0开始 因为这个是真正的文本索引了 如果一个单个字符的pattern 正好和文本第0相匹配所以这种情况是有的所以从0开始了
        for (int q = 0; q < content.length(); q++) {
            while (matchCount > 0 && content.charAt(matchCount) != content.charAt(q)) {
                matchCount = maxMatchs[matchCount - 1];
            }

            if (p.charAt(matchCount) == content.charAt(q)) {
                matchCount++;
            }
            if (matchCount == p.length()) {
                //q从0开始  p.lenght从1开始所以不一样我们的结果是从0开始
                result.add(q - (p.length() - 1));
                //我们此时当作没找到 就回到上一个最大匹配数 继续
                matchCount = maxMatchs[matchCount - 1];
            }
        }
        return result;
    }


}

