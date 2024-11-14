class MyFirstClass {
    public static void main(String[] args) {
        MySecondClass o = new MySecondClass(0, 0);
        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                o.SETval1(i);
                o.SETval2(j);
                System.out.print(o.xor());
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
class MySecondClass {
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