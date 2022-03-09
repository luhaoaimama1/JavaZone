package hook.javassist;

final class Test {
    private String str="default";

    private void setStr(String str) {
        this.str = str;
    }

    public final void last() {
        System.out.println("last:" + str);
    }
}
