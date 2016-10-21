package 编码工具;

import java.io.File;
import java.io.FilenameFilter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import Java.Zone.IO.IOUtils;

public class CodeChange {
//	private static Charset charset = Charset.forName("utf-8");
	private static Charset charset = Charset.forName("gbk");

	public static void main(String[] args) throws UnsupportedEncodingException {
//		Charset charsetFolder = JudgeFileCode.getFileCode(new File("D://FixedSpeedScroller.java"));
//		System.out.println(""+charsetFolder);
		String path="/Users/fuzhipeng/Downloads/名词词库.xlsx";
		Charset charsetFolder = JudgeFileCode.getFileCode(new File(path));
		System.out.println(""+charsetFolder);
//		File folder=new File("D:/Users/Zone/Github/GbkToUTf");
//		circlrFolder(folder);
	}

	private static void circlrFolder(File folder) throws UnsupportedEncodingException {
		if(folder.exists()){
			if(folder.isDirectory()){
				File[] files = folder.listFiles(new FilenameFilter() {
					
					@Override
					public boolean accept(File dir, String name) {
						if(name.endsWith(".jar")||name.endsWith(".zip")||name.endsWith(".xml")||name.endsWith(".jpg")||name.endsWith(".png")){
							return false;
						}
						return true;
					}
				});
				for (File item : files) {
					circlrFolder(item);
				}
			}else{
				//不是目录
				Charset charsetFolder = JudgeFileCode.getFileCode(folder);
				if(charsetFolder!=null&&!charset.name().equals(charsetFolder.name())){
					String str=IOUtils.read(folder, charsetFolder.name());
//					IOUtils.write(folder, new String(str.getBytes(charsetFolder.name()),charset), charset.name());
					IOUtils.write(folder, str, charset.name());
					System.out.println("转码成功："+folder.getAbsolutePath());
				}
			}
		}
	}

}
