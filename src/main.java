public class main {

    public static void main(String[] args) {
        String[][] temp = new String[2][2];

        temp[1][1] = "flf";

        foo(temp);

        Integer i = 9;
    }

    private static void foo(String[][] temp) {
        temp[1][0] = "fdff";
    }
}
