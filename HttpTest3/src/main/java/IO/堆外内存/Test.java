//package IO.堆外内存;
//
//import java.lang.reflect.Field;
//import java.nio.Buffer;
//import java.nio.ByteBuffer;
////https://blog.csdn.net/tyrroo/article/details/99550545
//public class Test {
//    public static void main(String[] args) {
//        ByteBuffer allocate = ByteBuffer.allocate(10);
//        printMarkPositionLimitCapacity("1",allocate);
//        for (int i = 0; i < 10; i++) {
//            allocate.put((byte) i);
//        }
//        printMarkPositionLimitCapacity("2",allocate);
//
//        allocate.flip();
//        printMarkPositionLimitCapacity("3",allocate);
//        for (int i = 0; i < 3; i++) {
//            System.out.println("->" + allocate.get());
//        }
//        printMarkPositionLimitCapacity("读取三个",allocate);
////
//        allocate.clear();
//        printMarkPositionLimitCapacity("5",allocate);
//        byte[] byteAry = new byte[10];
//        for (int i = 0; i < 10; i++) {
//            byteAry[i] = ((byte) (i));
//        }
//        allocate.put(byteAry);
//        printMarkPositionLimitCapacity("6",allocate);
//
//
////        public final Buffer flip() {
////            limit = position;
////            position = 0;
////            mark = -1;
////            return this;
////        }
//        allocate.flip();
//        printMarkPositionLimitCapacity("filp",allocate);
////        public final Buffer clear() {
////            position = 0;
////            limit = capacity;
////            mark = -1;
////            return this;
////        }
//        allocate.clear();
//        printMarkPositionLimitCapacity("clear",allocate);
//
//        allocate.rewind();
////        public final Buffer rewind() {
////            position = 0;
////            mark = -1;
////            return this;
////        }
//        printMarkPositionLimitCapacity("rewind",allocate);
//        for (int i = 0; i < 10; i++) {
//            System.out.println("2->" + allocate.get());
//        }
//
//        System.out.println();
//    }
//
//    private static void printMarkPositionLimitCapacity(String msg,ByteBuffer buff) {
//        System.out.println(msg+"mark:" + mark(buff) + ",posi:" + buff.position() + ",limit:" + buff.limit() + ",capactiy:" + buff.capacity());
//    }
//
//    private static int mark(ByteBuffer buff) {
//        try {
//            Field field = buff.getClass().getSuperclass().getSuperclass().getDeclaredField("mark");
//            field.setAccessible(true);
//            return field.getInt(buff);
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//        return -100;
//    }
//}
