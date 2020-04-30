package 算法.每日一题;

//Z 字形变换 官方解法  每行 用的是 stringbuilder 这个可以无限递增 就不用二维数组计算行数了。
// 行数有边界  每次碰撞到边界的时候  反向 得到行数 然后sb.append字符即可。
//https://leetcode-cn.com/problems/zigzag-conversion/solution/z-zi-xing-bian-huan-by-leetcode/
class Z字形变换 {
    public static void main(String[] args) {
        System.out.println("LCIRETOESIIGEDHN".equals(convert("LEETCODEISHIRING",3)));
    }
    public static String convert(String s, int numRows) {

        if (s.length() <= 1||numRows<=1) return s;
        int stepNum = numRows - 1;
        int groupNums = (s.length() - 1) / stepNum;
        int modNum = 0;
        if (groupNums % 2 == 0) {
            modNum = (s.length() - 1) % stepNum;
        }
        int numColumns = (groupNums + 1) / 2 * stepNum + modNum + 1;
        Character[][] resultArray = new Character[numRows][numColumns];
        resultArray[0][0] = s.charAt(0);
        int rowIndex = 0, columnIndex = 0, step = 1;
        boolean isRow = true;
        for (int i = 1; i < s.length(); i++) {
            if (isRow) {
                rowIndex++;
            } else {
                rowIndex--;
                columnIndex++;
            }
            resultArray[rowIndex][columnIndex] = s.charAt(i);
            System.out.println("======");
            System.out.println("======");
            System.out.println(printArray(resultArray));

            if (step % stepNum == 0) {
                step = 1;
                isRow = !isRow;
            } else step++;
        }


        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < resultArray.length; i++) {
            for (int j = 0; j < resultArray[i].length; j++) {
                Character i1 = resultArray[i][j];
                if (i1 != null) {
                    sb.append(i1);
                }
            }
        }
        return sb.toString();
    }

    private static String printArray(Character[][] resultArray) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < resultArray.length; i++) {
            for (int j = 0; j < resultArray[i].length; j++) {
                Character i1 = resultArray[i][j];
                if (i1 != null) {
                    sb.append("\t"+" " +i1+"  ");
                }else{
                    sb.append("\t"+"null");
                }
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}