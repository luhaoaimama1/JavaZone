package Android.Zone.SD;

import java.io.File;

import Java.Zone.CustomException.OperationFailException;
import android.os.Environment;
/**
 * 仅仅是创建文件夹的事情
 * @version 2015.7.15
 * @author Zone
 *
 */
public class FileUtils_SD {
	/**
	 * 关于SD卡下  多层文件的建立
	 * @param arg  参数文件夹路径 
	 * <br><strong> 范例：FolderCreateOrGet("test001","test002","test003"); 文件夹目录
	 * <br> 参数可以为空FolderCreateOrGet("") 表示SD卡根目录  
	 * <br> 参数可以为空FolderCreateOrGet("","a.txt")   文件
	 * </strong>
	 * @return	
	 */
	public static File FolderCreateOrGet(String... arg) {
		return FolderCreateOrGet(true,arg);
	}
	/**
	 * 关于SD卡下  多层文件的建立
	 * @param isNotCreate  当文件不存在的时候是否创建
	 * @param arg  参数文件夹路径 
	 * <br><strong> 范例：FolderCreateOrGet(true,"test001","test002","test003");
	 * <br> 参数可以为空FolderCreateOrGet("") 表示SD卡根目录
	 * <br> 参数可以为空FolderCreateOrGet("","a.txt") 也可以  不过这个默认的是创建 a.txt的父文件夹  即使 isNotCreate false了也会自动修改成true
	 * </strong>
	 * @return	
	 */
	private static File FolderCreateOrGet(boolean isNotCreate,String... arg) {
		String sdStatus = Environment.getExternalStorageState();
		if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
			throw new NullPointerException("sd卡存在否：false!");
		}
		String pathJoin = "";
		String fileEnd=null;
		for (String str : arg) {
			if(str.contains(".")){
				fileEnd= str;
			}else{
				pathJoin += "/" + str;
			}
			
		}
		String f = Environment.getExternalStorageDirectory().getPath();
		File file = new File(f + pathJoin);
		if (fileEnd!=null) 
			isNotCreate=true;
		if (isNotCreate&&!file.exists()) {
			boolean isOk = file.mkdirs();
			if (!isOk) {
				throw new OperationFailException("文件创建失败！");
			}
		}
		if (fileEnd!=null) {
			file = new File(file, fileEnd);
		}
		return file;
	}
	
}
