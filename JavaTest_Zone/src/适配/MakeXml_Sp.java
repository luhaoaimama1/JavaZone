package 适配;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
//values-xhdpi..   Sp的适配
public class MakeXml_Sp {
    private final static String  rootPath="/Users/fuzhipeng/Documents/layoutroot/{0}/";//Mac
//    private final static String rootPath = "C:\\Users\\Administrator\\Desktop\\layoutroot\\{0}\\";
    private final static String WTemplate = "<dimen name=\"font{0}px\">{1}sp</dimen>\n";

    //font px 迭代 from -to
    private final static int from = 20,to =100;//w h迭代的范围
    private final static DPI standardDpi=DPI.xxhdpi;//参考图的DPI

    //经过此次Log日志对比  发现  绿色的按钮的和总是在world字左边一点  适配完美
//            07-18 16:55:06.396 30293-30293/com.zone.screenadapter.screenadapter I/System.out: Xd:3.0(参考DPI)
//            07-18 16:55:06.396 30293-30293/com.zone.screenadapter.screenadapter I/System.out: font20---->20.009949px

//            07-18 16:56:25.401 2526-2526/com.zone.screenadapter.screenadapter I/System.out: Xd:2.0
//            07-18 16:56:25.401 2526-2526/com.zone.screenadapter.screenadapter I/System.out: font20---->13.339966px
    public static void main(String[] args) {
        DPI.standardDpi=standardDpi;
    	//生成文件
    	for (DPI item : DPI.values()) 
    		 makeFile(item);
    	
    	//单个分辨率的转换
//    	int fontPx=10;//10px转换sp 各个分辨率的
//    	for (DPI item : DPI.values()) {
//    		System.out.println(item.fileName+"------>font"+fontPx+"px-->"+item.dx2sp(fontPx)+"sp");
//    	}
    }

    public static void makeFile(DPI item) {
    	
        StringBuffer sb = new StringBuffer();
        sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
        sb.append("<resources>");
        for (int i = from; i <=to; i++) {
            sb.append(WTemplate.replace("{0}", i + "").replace("{1}",item.dx2sp(i) + ""));
        }
        sb.append("</resources>");

        String path = rootPath.replace("{0}", item.fileName );
        File rootFile = new File(path);
        if (!rootFile.exists()) {
            rootFile.mkdirs();
        }
        File layxFile = new File(path + "font.xml");
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(layxFile));
            pw.print(sb.toString());
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}