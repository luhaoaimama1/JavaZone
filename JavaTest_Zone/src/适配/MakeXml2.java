package 适配;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

//values-1024x600... 适配
public class MakeXml2 {

    private final static String rootPath = "C:\\Users\\Administrator\\Desktop\\layoutroot\\values-{0}x{1}\\";

    private final static float dw = 720f;//不当成像素 当成多少份
    private final static float dh = 1280f;

    private final static String WTemplate = "<dimen name=\"x{0}\">{1}px</dimen>\n";
    private final static String HTemplate = "<dimen name=\"y{0}\">{1}px</dimen>\n";

    public static void main(String[] args) {
        makeFile(320, 480);//mdpi
        makeFile(480,800);
        makeFile(480, 854);//hdpi
        makeFile(540, 960);
        makeFile(600, 1024);
        makeFile(720, 1184);
        makeFile(720, 1196);
        makeFile(720, 1280);//xdpi
        makeFile(768, 1024);
        makeFile(800, 1280);
        makeFile(1080, 1812);
        makeFile(1080, 1920);//xxdpi x1(代表 基准中的一份既  1/320) :1080/320*1(以1080 分成320份在取一份)=3.375px;
        makeFile(1440, 2560);
    }

    public static void makeFile(int w, int h) {

        StringBuffer sb = new StringBuffer();
        sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
        sb.append("<resources>");
        float cellw = w / dw;
        for (int i = 1; i <= dw; i++) {
            sb.append(WTemplate.replace("{0}", i + "").replace("{1}",
                    change(cellw * i) + ""));
        }
        sb.append("</resources>");

        StringBuffer sb2 = new StringBuffer();
        sb2.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
        sb2.append("<resources>");
        float cellh = h / dh;
        for (int i = 1; i <=dh; i++) {
            sb2.append(HTemplate.replace("{0}", i + "").replace("{1}",
                    change(cellh * i) + ""));
        }
        sb2.append("</resources>");

        String path = rootPath.replace("{0}", h + "").replace("{1}", w + "");
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

    //保留两位小数
    public static float change(float a) {
    	return Math.round( a * 100 ) / 100.0F;
    }
}