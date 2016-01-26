import java.util.*;

public class PeopleField {

    final class CoordinMan {

        public int row;
        public int column;

        public CoordinMan(int row, int column) {
            this.row = row;
            this.column = column;
        }
    }
    private String[][] mapOfGroups;

    public PeopleField(Integer rows, Integer columns, Double fillFactor) {
        if (fillFactor < 0.0 || fillFactor > 1.0 ||
                rows < 1 || columns < 1) {
            throw new IllegalArgumentException();
        }
        mapOfGroups = new String[rows][columns];
        generateField(fillFactor, mapOfGroups);
    }

    public String[][] getPeopleField() {
        String[][] clone = new String[mapOfGroups.length][mapOfGroups[0].length];

        for (int i = 0; i < mapOfGroups.length; ++i) {
            clone[i] = mapOfGroups[0].clone();
        }
        return clone;
    }

    private void generateField(Double fillFactor, String[][] group) {
        for (int i = 0; i < this.mapOfGroups.length; ++i) {
            for (int j = 0; j < this.mapOfGroups[0].length; ++j) {
                if (Math.random() <= fillFactor) {
                    mapOfGroups[i][j] = "|x|";
                }
                else {
                    mapOfGroups[i][j] = "-";
                }
            }
        }
    }

    public void searchGroup() {
        boolean[][] viewedPeople = new boolean[this.mapOfGroups.length][this.mapOfGroups[0].length];
        List<Integer> groups = new ArrayList<>();

        for (int i = 0; i < this.mapOfGroups.length; ++i) {
            for (int j = 0; j < this.mapOfGroups[0].length; ++j) {
                if (viewedPeople[i][j] != true) {
                    groups.add(identifGroup(viewedPeople, new CoordinMan(i, j)));
                }
            }
        }
    }

    private int identifGroup(boolean[][] viewedPeople, CoordinMan man) {
        Queue<CoordinMan> buffer = new LinkedList<>();
        buffer.offer(man);
        CoordinMan tempMan = null;

        int countPersons = 0;

        while (buffer.size() != 0) {
            tempMan = buffer.remove();

            ++countPersons;

            addNeighborInGroup(viewedPeople, buffer, tempMan.row - 1, tempMan.column);
            addNeighborInGroup(viewedPeople, buffer, tempMan.row, tempMan.column + 1);
            addNeighborInGroup(viewedPeople, buffer, tempMan.row + 1, tempMan.column);
            addNeighborInGroup(viewedPeople, buffer, tempMan.row, tempMan.column - 1);
        }
        return countPersons;
    }

    private boolean addNeighborInGroup(boolean[][] viewedPeople, Queue<CoordinMan> buffer,
                                       int row, int column) {
        if(checkNeighborPerson(viewedPeople, row, column)) {
            buffer.offer(new CoordinMan(row, column));
            viewedPeople[row][column] = true;
            return true;
        }
        else {
            return false;
        }
    }

    private boolean checkNeighborPerson(boolean[][] viewedPeople, int row, int column) {
        if ((row >= 0 && row < viewedPeople[0].length) &&
                (column >= 0 && column < viewedPeople.length) &&
                (viewedPeople[row][column] != true)) {
            if (this.mapOfGroups[row][column].equals("|x|")) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }

    public String generateReport() {

        return "";
    }
}
