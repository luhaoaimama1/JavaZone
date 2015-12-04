package print游戏;

public class ImageSizeCalculation {
	public static void main(String[] args) {
//		ALPHA_8：每个像素占用1byte内存
//		ARGB_4444:每个像素占用2byte内存
//		ARGB_8888:每个像素占用4byte内存
//		RGB_565:每个像素占用2byte内存
		System.out.println(2048 * 1536 * 4.0 / (1024 * 1024));
		System.out.println(2048 * 1536 * 4.0 / (1024 * 1024*16));
	}
}
