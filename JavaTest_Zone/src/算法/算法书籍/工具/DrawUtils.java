package 算法.算法书籍.工具;

import sun.java2d.SunGraphics2D;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

//基础绘制copy from  :https://blog.csdn.net/xietansheng/article/details/55669157
public class DrawUtils {
    /**
     * left<now<right
     */
    public static class Node<K, V> {
        public K k;
        public V v;
        public Node left;
        public Node right;
        public boolean isRed;

        public Node(K k, V v) {
            this.k = k;
            this.v = v;
        }
    }


    public interface NodeCallback<T> {
        public T getLeftNode(T t);

        public T getRightNode(T t);

        public Color getColor(T t);

        public int getRadius();

        public int getTextSize();

        public String getText(T t);

        public int getNodeCount(T t);

        public Point calNextTPoint(int cirX, int cirY, T nextT, boolean isLeft);

    }

    static final int WIDTH = 3000;
    static final int HEIGHT = 2000;

    public static <T> void drawNode2(T t, NodeCallback<T> callback) {
        /*
         * 在 AWT 的事件队列线程中创建窗口和组件, 确保线程安全,
         * 即 组件创建、绘制、事件响应 需要处于同一线程。
         */
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                int x = WIDTH / 2;
                int y = callback.getRadius();
                // 创建窗口对象
                MyFrame frame = new MyFrame(WIDTH, HEIGHT, g -> drawNode(g, x, y, t, callback));
                // 显示窗口
                frame.setVisible(true);
            }
        });
    }

    private static <T> void drawNode(Graphics g, int cirX, int cirY, T t, NodeCallback<T> callback) {
        drawCircle(g, cirX, cirY, t, callback);
        //绘制当前圆 与 文字

        T leftNode = callback.getLeftNode(t);
        T rightNode = callback.getRightNode(t);


        int nextCirX = 0, nextCirY = 0;
        int radius = callback.getRadius();
        if (leftNode != null) {
            Point point = callback.calNextTPoint(cirX, cirY, leftNode, true);
            if (point == null) {
                int nodeCount = callback.getNodeCount(callback.getRightNode(leftNode));
                nextCirX = cirX - radius * 2 * (nodeCount + 1);
                nextCirY = cirY + radius * 4;
            } else {
                nextCirX = point.x;
                nextCirY = point.y;
            }
            drawLine(g, cirX, cirY, nextCirX, nextCirY, radius);
            drawNode(g, nextCirX, nextCirY, leftNode, callback);
        }
        if (rightNode != null) {
            Point point = callback.calNextTPoint(cirX, cirY, rightNode, false);
            if (point == null) {
                int nodeCount = callback.getNodeCount(callback.getLeftNode(rightNode));
                nextCirX = cirX + radius * 2 * (nodeCount + 1);
                nextCirY = cirY + radius * 4;
            } else {
                nextCirX = point.x;
                nextCirY = point.y;
            }

            drawLine(g, cirX, cirY, nextCirX, nextCirY, radius);
            drawNode(g, nextCirX, nextCirY, rightNode, callback);
        }
    }

    private static <T> void drawLine(Graphics g, int cirX, int cirY, int nextCirX, int nextCirY, int radius) {
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //画两个斜线
        g.setColor(Color.black);

        double radiansTan = Math.atan2(nextCirY - cirY, nextCirX - cirX);
        int offsetY = (int) (Math.sin(radiansTan) * radius);
        int offsetX = (int) (Math.cos(radiansTan) * radius);
        //绘制斜线
        g.drawLine(cirX + offsetX, cirY + offsetY, nextCirX - offsetX, nextCirY - offsetY);

        g2d.dispose();

    }

    private static <T> void drawCircle(Graphics g, int cirX, int cirY, T t, NodeCallback<T> callback) {
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(callback.getColor(t));
        // 2. 绘制一个圆: 圆的外切矩形 左上角坐标为(120, 20), 宽高为100
        int radius = callback.getRadius();
        g2d.drawArc(cirX - radius, cirY - radius, radius * 2, radius * 2, 0, 360);


        // 设置字体样式, null 表示使用默认字体, Font.PLAIN 为普通样式, 大小为 25px
        Font font = new Font(null, Font.PLAIN, callback.getTextSize());
        g2d.setFont(font);

        // 绘制文本, 其中坐标参数指的是文本绘制后的 左下角 的位置
        // 首次绘制需要初始化字体, 可能需要较耗时

        String text = callback.getText(t);
        Rectangle2D stringBounds = font.getStringBounds(text, ((SunGraphics2D) g).getFontRenderContext());

        int v = (int) (stringBounds.getWidth() / 2);
        int h = (int) (stringBounds.getCenterY());
        g2d.drawString(text, cirX - v, cirY - h);

        //绘制辅助线
        g2d.setColor(new Color(0, 0, 0, 20));
        g2d.drawLine(0, cirY, WIDTH, cirY);
        g2d.drawLine(cirX, 0, cirX, 1000);

        g2d.dispose();
    }


    /**
     * 窗口
     */
    public static class MyFrame extends JFrame {

        public MyFrame(int width, int height, DrawCalBack calBack) {
            super();
            // 设置 窗口标题 和 窗口大小
            setSize(width, height);

            // 设置窗口关闭按钮的默认操作(点击关闭时退出进程)
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            // 把窗口位置设置到屏幕的中心
            setLocationRelativeTo(null);
            // 设置窗口的内容面板
            MyPanel panel = new MyPanel(this, calBack);
            setContentPane(panel);
        }

    }

    /**
     * 内容面板
     */
    public static class MyPanel extends JPanel {

        private MyFrame frame;
        DrawCalBack calBack;

        public MyPanel(MyFrame frame, DrawCalBack calBack) {
            super();
            this.frame = frame;
            this.calBack = calBack;
        }


        /**
         * 绘制面板的内容: 创建 JPanel 后会调用一次该方法绘制内容,
         * 之后如果数据改变需要重新绘制, 可调用 updateUI() 方法触发
         * 系统再次调用该方法绘制更新 JPanel 的内容。
         */
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (calBack != null) calBack.paintComponent(g);
            // 1. 两点绘制线段: 点(20, 50), 点(200, 50)
            g.setColor(Color.black);
//            g.drawLine(500, 0, 500, 1000);
//            g.drawLine(0, 25, 1000, 25);
        }

    }

    public interface DrawCalBack {
        void paintComponent(Graphics g);
    }

}
