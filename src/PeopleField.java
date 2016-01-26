public class PeopleField {

    private String[][] groups;

    public PeopleField(Integer rows, Integer columns, Double fillFactor) {
        groups = new String[rows][columns];



        Math.random();
    }

    private void generateField(Double fillFactor) {
        for (int i = 0; i < this.groups.length; ++i) {
            for (int j = 0; j < this.groups[0].length; ++j) {
                if (Math.random() <= fillFactor) {
                    groups[i][j] = "|x|";
                }
                else {
                    groups[i][j] = "-";
                }
            }
        }
    }

    public String searchGroup() {


        return "";
    }

    public String generateReport() {

        return "";
    }
}
