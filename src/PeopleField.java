public class PeopleField {

    private String[][] groups;

    public PeopleField(Integer rows, Integer columns, Double fillFactor) {
        if (fillFactor < 0.0 || fillFactor > 1.0 ||
                rows < 1 || columns < 1) {
            throw new IllegalArgumentException();
        }
        groups = new String[rows][columns];
        generateField(fillFactor, groups);
    }

    public String[][] getGroups() {
        String[][] clone = new String[groups.length][groups[0].length];

        for (int i = 0; i < groups.length; ++i) {
            clone[i] = groups[0].clone();
        }
        return clone;
    }

    private void generateField(Double fillFactor, String[][] group) {
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
