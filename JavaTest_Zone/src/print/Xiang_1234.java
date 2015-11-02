package print;

import java.util.Scanner;

public class Xiang_1234 {
	private static Dire dire=Dire.RIGHT;
	private static int nowX,nowY,centerX,centerY, r=1;
	private static int[][] fang;
	enum Dire{
		RIGHT,LEFT,UP,DOWN
	}
	public static void main(String[] args) {
		System.out.println("请初始化 想要的值：");
		Scanner sc = new Scanner(System.in);
		String name = sc.nextLine();
		int initValue=0;
		try {
			initValue=Integer.parseInt(name);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			System.out.println("请输入数字");
		}
//		int initValue=26;
		print(initValue);
		sc.close();
		
		
	}
	public static void print(int initValue){
		int b=(int)Math.sqrt(initValue)+1;
		fang=new int[b][b];
		//中心点
		centerX=(b-1)/2;
		centerY=(b-1)/2;
		fang[centerX][centerY]=1;
		nowX=centerX;
		nowY=centerY;
		
		for (int i = 2; i <=b*b; i++) {
			//循环b*b-1次
			if(isTurn()){
			//通过到中心点r判断是否改拐弯
				turnDir();
			}
			//赋值
			nextDir(i);
		}
		for (int vertical = 0; vertical < b; vertical++) {
			for (int horizontal = 0; horizontal < b; horizontal++) {
				if(fang[horizontal][vertical]<=initValue){
					System.out.print(fang[horizontal][vertical]+"\t");
				}
			}
			System.out.println();
			System.out.println();
			System.out.println();
		}
	}
	/**
	 * 判断是否该拐弯
	 * @return
	 */
	private static boolean isTurn() {
		switch (dire) {
		case RIGHT:
			if((nowX-centerX)==r){
				return true;
			}
			break;
		case DOWN:
			if((nowY-centerY)==r){
				return true;
			}
			break;
		case LEFT:
			if((nowX-centerX)==-r){
				return true;
			}
			break;
		case UP:
			if((nowY-centerY)==-r){
				return true;
			}
			break;
		default:
			break;
		}
		return false;
	}
	/**
	 * 拐弯
	 */
	public static void turnDir(){
		switch (dire) {
		case RIGHT:
			if(fang[nowX][nowY+1]==0){
				//如果拐弯的点没值
				dire=Dire.DOWN;
			}else{
				//如果有值  方向不变  r+1
				r++;
			}
			break;
		case DOWN:
			if(fang[nowX-1][nowY]==0){
				//如果拐弯的点没值
				dire=Dire.LEFT;
			}else{
				//如果有值  方向不变  r+1
				r++;
			}
			break;
		case LEFT:
			if(fang[nowX][nowY-1]==0){
				//如果拐弯的点没值
				dire=Dire.UP;
			}else{
				//如果有值  方向不变  r+1
				r++;
			}
			break;
		case UP:
			if(fang[nowX+1][nowY]==0){
				//如果拐弯的点没值
				dire=Dire.RIGHT;
			}else{
				//如果有值  方向不变  r+1
				r++;
			}
			break;
		default:
			break;
		}
	}
	/**
	 * 不拐弯的时候赋值  
	 * @param i
	 */
	public static void nextDir(int i){
		switch (dire) {
		case RIGHT:
			nowX++;
			break;
		case DOWN:
			nowY++;
			break;
		case LEFT:
			nowX--;
			break;
		case UP:
			nowY--;
			break;
		default:
			break;
		}
		fang[nowX][nowY]=i;
	}
}
