package a未分类;

class Prototype implements Cloneable {
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    //    public Prototype clone(){
//        Prototype prototype = null;
//        try{
//            prototype = (Prototype)super.clone();
//        }catch(CloneNotSupportedException e){
//            e.printStackTrace();
//        }
//        return prototype;
//    }
}  
  