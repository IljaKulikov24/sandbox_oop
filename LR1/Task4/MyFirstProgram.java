import myfirstpackage.MySecondClass;

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
