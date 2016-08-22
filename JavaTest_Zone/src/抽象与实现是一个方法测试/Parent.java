package 抽象与实现是一个方法测试;

public abstract class Parent<T extends Parent> {

    protected T child;

    public T hei() {
        return child;
    }

    public static class Child extends Parent<Child> {

        public Child() {
            child = this;
        }

        public void fei() {
            System.out.println("飞起来");
        }
    }

    public static void main(String[] args) {
        new Parent.Child().hei().fei();
    }
}
