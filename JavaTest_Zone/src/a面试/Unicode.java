package a面试;

/**
 * Created by fuzhipeng on 2017/3/10.
 */
public class Unicode {
    /**
     *   Jvm字符分为两部分:
     *   JVM内部统一使用Unicode表示 当字符从JVM内部移到外部(既保存文件系统中的文件内容时),就发生了编码转换。
     *   因此可以说编码转换只发生JVM于OS文件系统的交界处
     *
     *
     *   I/O基本分为两种
     *   面向字节 : 保持文件二进制内容与读入JVM内部的二进制内容一样。既不做转换。和适合音频 视频。
     *
     *   面向字符 : 要做转换 既编码。字节与字符的转换。
     *   Writer/Reader 默认的编码 转换 而不能随意指定 比较傻瓜
     *   Writer(输出):将内存的Unicode字符进行 默认的编码。
     *   Reader(输入):将默认的编码进行还原 。
     *   特定编码转换用:用InputStreamReader于OutputStreamWriter
     */

    /*
    疑问为什么 Unicode编码 用16进制?而不是  二进制呢?
     */
    public static void main(String[] args) {
        char han='永';
        //%x代表十六进制
        System.out.format("%x",han);//因为char是两个字节  short也是   但是和十六进制有啥关系?
        System.out.format("%x",(short)han);//因为char是两个字节  short也是
        System.out.println();
        char han2=0x6c38;
        System.out.println(han2);

    }


}
