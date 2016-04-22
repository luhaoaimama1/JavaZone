package 适配;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
//values-xhdpi..   Sp的适配
public class MakeXml_Sp {
    private final static String rootPath = "C:\\Users\\Administrator\\Desktop\\layoutroot\\{0}\\";
    private final static String WTemplate = "<dimen name=\"font{0}px\">{1}sp</dimen>\n";

    //font px 迭代 from -to
    private final static int from = 20;//w迭代的像素
    private final static int to =50;//h迭代的像素

    public enum DPI{
    	ldpi("values-ldpi")	,mdpi("values-mdpi"),hdpi("values-hdpi"),xhdpi("values-xhdpi"),xxhdpi("values-xxhdpi");
    	public String  fileName;
    	DPI(String  fileName){
    		this.fileName=fileName;
    	};
    	public float dx2sp(int dx){
    		float resultSp=0F;
    		switch (this) {
			case ldpi:
				resultSp=dx/0.75F;
				break;
			case mdpi:
				resultSp=dx;
				break;
			case hdpi:
				resultSp=dx/1.5F;
				break;
			case xhdpi:
				resultSp=dx/2F;
				break;
			case xxhdpi:
				resultSp= dx/3F;	
				break;
			}
    		return change(resultSp);
    	}
    }
    //保留两位小数
    public static float change(float a) {
    	return Math.round( a * 100 ) / 100.0F;
    }
    
    public static void main(String[] args) {
    	//生成文件
    	for (DPI item : DPI.values()) 
    		 makeFile(item);
    	
    	//单个分辨率的转换
    	int fontPx=10;//10px转换sp 各个分辨率的
    	for (DPI item : DPI.values()) {
    		System.out.println(item.fileName+"------>font"+fontPx+"px-->"+item.dx2sp(fontPx)+"sp");
    	}
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