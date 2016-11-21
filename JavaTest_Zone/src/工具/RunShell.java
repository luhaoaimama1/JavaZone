package 工具;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RunShell {
    public static void main(String[] args){
        try {
            String shpath="/Users/fuzhipeng/Library/Android/sdk/tools/proguard/bin/proguardgui.sh";
            Runtime.getRuntime().exec(shpath).waitFor();
//            BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));
//            StringBuffer sb = new StringBuffer();
//            String line;
//            while ((line = br.readLine()) != null) {
//                sb.append(line).append("\n");
//            }
//            String result = sb.toString();
//            System.out.println(result);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}