package myfirstpackage;

public class MySecondClass {
    private int value1;
    private int value2;
    public MySecondClass(int val1, int val2) {
        this.value1 = val1;
        this.value2 = val2;
    }
    public void SETval1(int val1) {
        this.value1 = val1;
    }
    public void SETval2(int val2) {
        this.value2 = val2;
    }
    public int xor() {
        return this.value1 ^ this.value2;
    }
}