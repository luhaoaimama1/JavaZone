package 适配;

public enum DPI{
    ldpi("values-ldpi",0.75F)	,mdpi("values-mdpi",1.0F),hdpi("values-hdpi",1.5F),xhdpi("values-xhdpi",2F),xxhdpi("values-xxhdpi",3F);
    	//  270*480->120dp 0.75    360*640->160dp 1     480*960->240dp 1.5    720*1280->320dp 2      1080*1920->480dp 3
    	public String  fileName;
        public float radio;
        public static DPI standardDpi;
    	DPI(String  fileName,float radio){
    		this.fileName=fileName;
            this.radio=radio;
    	};
    	public float dx2dp(int dx){
    		float resultDp=dx/radio;//转成 dx 通过DPI比率转成 dp
            if(standardDpi==null)
                throw new NullPointerException("standardDpi may be null");
            resultDp= resultDp*radio/standardDpi.radio;//在通过参考图的DPI进行对比 缩放;
    		return change(resultDp);
    	}
    	public float dx2sp(int dx){
    		return dx2dp(dx);
    	}
        //保留两位小数
        public static float change(float a) {
            return Math.round( a * 100 ) / 100.0F;
        }
    }