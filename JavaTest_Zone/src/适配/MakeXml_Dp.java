package 适配;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
//values-xhdpi..  Dp的适配
public class MakeXml_Dp {

    private final static String  rootPath="/Users/fuzhipeng/Documents/layoutroot/{0}/";//Mac
//    private final static String rootPath = "C:\\Users\\Administrator\\Desktop\\layoutroot\\{0}\\";

    private final static String WTemplate = "<dimen name=\"x{0}\">{1}dp</dimen>\n";
    private final static String HTemplate = "<dimen name=\"y{0}\">{1}dp</dimen>\n";

    private final static float dw = 1080f,dh = 1920f;//w h迭代的范围
    private final static DPI standardDpi=DPI.xxhdpi;//参考图的DPI
    //Todo 原理:x100就是standardDpi(参考图的DPI)下的100,其他文件夹(m,l,h,x,xx)下 则被缩放了 下面是缩放的Log

    //经过此次Log日志对比  发现  绿色的按钮的和总是在world字左边一点  适配完美
//    07-18 15:54:03.150 21594-21594/com.zone.screenadapter.screenadapter I/System.out: x100---->>66.65997px
//    07-18 16:09:14.569 27882-27882/com.zone.screenadapter.screenadapter I/System.out: width:720	 height:1280	 dpi:320
//            07-18 16:09:14.569 27882-27882/com.zone.screenadapter.screenadapter I/System.out: Xd:2.0

//            07-18 16:16:06.011 27179-27179/com.zone.screenadapter.screenadapter I/System.out: x100---->99.98996px(参考DPI)
//    07-18 16:16:06.011 27179-27179/com.zone.screenadapter.screenadapter I/System.out: width:1080	 height:1920	 dpi:480
//            07-18 16:16:06.011 27179-27179/com.zone.screenadapter.screenadapter I/System.out: Xd:3.0

//    07-18 16:18:53.411 26574-26574/com.zone.screenadapter.screenadapter I/System.out: x100---->133.31995px
//    07-18 16:18:53.411 26574-26574/com.zone.screenadapter.screenadapter I/System.out: width:1440	 height:2560	 dpi:640
//            07-18 16:18:53.411 26574-26574/com.zone.screenadapter.screenadapter I/System.out: Xd:3.0



    
    public static void main(String[] args) {
        DPI.standardDpi=standardDpi;
    	for (DPI item : DPI.values())
    		 makeFile(item);
    }

    public static void makeFile(DPI item) {
    	
        StringBuffer sb = new StringBuffer();
        sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
        sb.append("<resources>");
        sb.append(WTemplate.replace("{0}", "D").replace("{1}dp",item.radio + "px"));
        for (int i = 1; i <=dw; i++) {
            sb.append(WTemplate.replace("{0}", i + "").replace("{1}",item.dx2dp(i) + ""));
        }
        sb.append("</resources>");

        
        StringBuffer sb2 = new StringBuffer();
        sb2.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
        sb2.append("<resources>");
        for (int i = 1; i <= dh; i++) {
            sb2.append(HTemplate.replace("{0}", i + "").replace("{1}", item.dx2dp(i) + ""));
        }
        sb2.append("</resources>");

        String path = rootPath.replace("{0}", item.fileName );
        File rootFile = new File(path);
        if (!rootFile.exists()) {
            rootFile.mkdirs();
        }
        File layxFile = new File(path + "lay_x.xml");
        File layyFile = new File(path + "lay_y.xml");
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(layxFile));
            pw.print(sb.toString());
            pw.close();
            pw = new PrintWriter(new FileOutputStream(layyFile));
            pw.print(sb2.toString());
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}