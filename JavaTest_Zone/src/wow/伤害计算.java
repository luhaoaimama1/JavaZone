package wow;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fuzhipeng on 2018/8/12.
 */
public class 伤害计算 {
    public static void main(String[] args) {
//洞悉弱点  减护甲40%
//        进入暗影之舞 潜行   3秒增加25能量
        //暗影之舞 进入 所有上海增加25%  本身增加12 就是  37%

        //无情打击  总结技  每个连接点 编程 6点能量
        //刽子手  总结及  40%上海

        //暗影之刃 章连击点  增长了 的话 这个技能的50%的额外上海

//死亡符集  +40pow  15%
//3个技能  背刺 -5 但是我却剩下42  一秒恢复15能量 大概 一个技能公共CD一秒


        //死亡符记 和死亡标记 都是 为了没能量的时候续上  为了能量不断。而对于 总结技由于无情打击 反而是张能令
        总伤害 total = new 总伤害(100);
        total.add(new 偷袭());
        total.add(new 背刺());
        total.add(new 背刺());
        total.add(new 背刺());
        total.add(new 夜刃());

        total.add(new 暗影之舞());
        total.add(new 暗影打击());
        total.add(new 死亡符记());
        total.add(new 暗影打击());
        total.add(new 暗影打击());
        total.add(new 刺骨());
        total.add(new 死亡标记());//之后的暗影之舞 这里就不加增益了
        total.add(new 天降正义());

        total.add(new 暗影之舞());
        total.add(new 暗影打击());
        total.add(new 暗影打击());
        total.add(new 暗影打击());
        total.add(new 刺骨());

        total.add(new 背刺());
//        total.add(new 背刺());
//        total.add(new 背刺());
        total.go();

    }

    static class 总伤害 {
        public 总伤害(int totalPow) {
            this.totalPow = totalPow;
            this.OverPow = totalPow;
        }

        int totalPow, OverPow;
        int totalHarm = 0;
        int comboPoint = 0;
        double getAddPer = 0F;

        List<Jineng> arrList = new ArrayList<>();

        public void add(Jineng jineng) {
            arrList.add(jineng);
        }

        public void go() {
            StringBuilder sb = new StringBuilder();
            int delay = 0;
            for (int i = 0; i < arrList.size(); i++) {
                goDetails(arrList.get(i), sb);
                if (arrList.get(i).公共CD()) {
                    totalPow = totalPow + 15;
                    if (totalPow > OverPow) totalPow = OverPow;
                    sb.append("每个公共CD一秒恢复15 \n");
                }
                if (delay > 0 && !(arrList.get(i) instanceof 死亡符记)) {
                    delay--;
                    totalPow = totalPow + 8;
                    if (totalPow > OverPow) totalPow = OverPow;
                    sb.append("暗影之舞后每次加8点pow \n");
                }
                if (arrList.get(i) instanceof 暗影之舞) {
                    delay = 3;
                }
            }
            System.out.println(sb.toString());
        }

        void goDetails(Jineng jineng, StringBuilder sb) {
            totalPow += jineng.getPow(comboPoint);
            if (totalPow > OverPow) totalPow = OverPow;
            totalHarm += jineng.getHarm() * (1 + getAddPer);
            getAddPer += jineng.getAddPer();
            if (jineng.comboPoint() == -1) {
                comboPoint = 0;
            } else {
                comboPoint += jineng.comboPoint();
            }
            sb.append("使技能" + jineng.getClass().getCanonicalName() + "\t  state--->" + "totalPow：" + totalPow
                    + "\t totalHarm：" + totalHarm
                    + "\t comboPoint：" + comboPoint
                    + "\t getAddPer：" + getAddPer + "\n");
        }


    }


    abstract interface Jineng {
        int getPow(int comboPoint);

        //-1 清除
        int comboPoint();

        int getHarm();

        double getAddPer();

        boolean 公共CD();

    }

    static class 暗影之舞 implements Jineng {


        @Override
        public int getPow(int comboPoint) {
            return 0;
        }

        @Override
        public int comboPoint() {
            return 0;
        }

        @Override
        public int getHarm() {
            return 0;
        }

        @Override
        public double getAddPer() {
            return 0.37;
        }

        @Override
        public boolean 公共CD() {
            return false;
        }
    }


    static class 暗影打击 implements Jineng {

        @Override
        public int getPow(int comboPoint) {
            return -40;
        }

        @Override
        public int comboPoint() {
            return 2;
        }

        @Override
        public int getHarm() {
            return (int) (734 * 1.25);
        }

        @Override
        public double getAddPer() {
            return 0;
        }

        @Override
        public boolean 公共CD() {
            return true;
        }
    }

    static class 背刺 implements Jineng {

        @Override
        public int getPow(int comboPoint) {
            return -35;
        }

        @Override
        public int comboPoint() {
            return 1;
        }

        @Override
        public int getHarm() {
            return 435;
        }

        @Override
        public double getAddPer() {
            return 0;
        }

        @Override
        public boolean 公共CD() {
            return true;
        }
    }

    static class 死亡符记 implements Jineng {


        @Override
        public int getPow(int comboPoint) {
            return 40;
        }

        @Override
        public int comboPoint() {
            return 0;
        }

        @Override
        public int getHarm() {
            return 0;
        }

        @Override
        public double getAddPer() {
            return 0.15;
        }

        @Override
        public boolean 公共CD() {
            return false;
        }
    }

    static class 夜刃 implements Jineng {

        @Override
        public int getPow(int comboPoint) {
            return -25 + comboPoint * 8;
        }

        @Override
        public int comboPoint() {
            return -1;
        }

        @Override
        public int getHarm() {
            return 0;
        }

        @Override
        public double getAddPer() {
            return 0.15;
        }

        @Override
        public boolean 公共CD() {
            return true;
        }
    }

    static class 死亡标记 implements Jineng {

        @Override
        public int getPow(int comboPoint) {
            return 0;
        }

        @Override
        public int comboPoint() {
            return 5;
        }

        @Override
        public int getHarm() {
            return 0;
        }

        @Override
        public double getAddPer() {
            return 0;
        }

        @Override
        public boolean 公共CD() {
            return false;
        }
    }

    static class 刺骨 implements Jineng {

        @Override
        public int getPow(int comboPoint) {
            return -35 + comboPoint * 8;
        }

        @Override
        public int comboPoint() {
            return -1;
        }

        @Override
        public int getHarm() {
            return (int) (1935 * 1.4);
        }

        @Override
        public double getAddPer() {
            return 0;
        }

        @Override
        public boolean 公共CD() {
            return true;
        }
    }

    static class 天降正义 implements Jineng {

        @Override
        public int getPow(int comboPoint) {
            return -25 + comboPoint * 8;
        }

        @Override
        public int comboPoint() {
            return -1;
        }

        @Override
        public int getHarm() {
            return (int) ((int) (1935 * 1.4) * 1.1);
        }

        @Override
        public double getAddPer() {
            return 0;
        }

        @Override
        public boolean 公共CD() {
            return true;
        }
    }

    static class 偷袭 implements Jineng {

        @Override
        public int getPow(int comboPoint) {
            return 0;
        }

        @Override
        public int comboPoint() {
            return 2;
        }

        @Override
        public int getHarm() {
            return 0;
        }

        //破甲40
        @Override
        public double getAddPer() {
            return 0.1;
        }

        @Override
        public boolean 公共CD() {
            return true;
        }
    }


}
