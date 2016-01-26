public class main {

    public static void main(String[] args) {
        String[][] groups = new String[2][2];

        String[][] clone = new String[groups.length][groups[0].length];

        for (int i = 0; i < groups.length; ++i) {
            clone[i] = groups[0].clone();
        }
        clone[0][0] = "fdhsf";

        Integer i = 0;
    }
}
