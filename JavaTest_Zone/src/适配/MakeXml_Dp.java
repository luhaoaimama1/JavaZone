package 适配;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
//values-xhdpi..  Dp的适配
public class MakeXml_Dp {

    private final static String rootPath = "C:\\Users\\Administrator\\Desktop\\layoutroot\\{0}\\";


    private final static String WTemplate = "<dimen name=\"x{0}\">{1}dp</dimen>\n";
    private final static String HTemplate = "<dimen name=\"y{0}\">{1}dp</dimen>\n";

    private final static float dw = 720f;
    //w迭代的像素 720->360dp 2dpi 1080->360dp 3dpi  480->320dp 1.5dpi 360->360 1dpi 320->320dp 0.75dpi
    private final static float dh = 1280f;//h迭代的像素 1920 3
    private final static float dpi=2;

    public enum DPI{
    	ldpi("values-ldpi")	,mdpi("values-mdpi"),hdpi("values-hdpi"),xhdpi("values-xhdpi"),xxhdpi("values-xxhdpi");
    	//  270*480->360dp 0.75dpi    360*640->360 1dpi     480*960->320dp 1.5dpi    720*1280->360dp 2dpi       1080*1920->360dp 3dpi
    	public String  fileName;
    	DPI(String  fileName){
    		this.fileName=fileName;
    	};
    	public float dx2dp(int dx){
    		float resultDp=0F;
    		resultDp=dx/dpi;
//    		switch (this) {
//			case ldpi:
//				resultDp=dx/0.75F;
//				break;
//			case mdpi:
//				resultDp=dx;
//				break;
//			case hdpi:
//				resultDp=dx/1.5F;
//				break;
//			case xhdpi:
//				resultDp=dx/2F;
//				break;
//			case xxhdpi:
//				resultDp= dx/3F ;	
//				break;
//			}
    		return change(resultDp);
    	}
    }
    //保留两位小数
    public static float change(float a) {
    	return Math.round( a * 100 ) / 100.0F;
    }
    
    
    public static void main(String[] args) {
    	System.out.println(change(0.33F));
    	for (DPI item : DPI.values()) 
    		 makeFile(item);
    }

    public static void makeFile(DPI item) {
    	
        StringBuffer sb = new StringBuffer();
        sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
        sb.append("<resources>");
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