import java.util.*;

public class PeopleField {

    private final class RiskGroup {
        public String name;
        public Integer countGroup;

        public RiskGroup(String name, Integer countGroup) {
            this.name = name;
            this.countGroup = countGroup;
        }
    }

    private final class CoordinMan {

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

    public String searchGroup() {
        boolean[][] viewedPeople = new boolean[this.mapOfGroups.length][this.mapOfGroups[0].length];
        List<Integer> countPersInGroups = new ArrayList<>();

        for (int i = 0; i < this.mapOfGroups.length; ++i) {
            for (int j = 0; j < this.mapOfGroups[0].length; ++j) {
                if (mapOfGroups[i][j].equals("|x|") && viewedPeople[i][j] != true) {
                    countPersInGroups.add(identifGroup(viewedPeople, new CoordinMan(i, j)));
                }
            }
        }
        return generateReport(countPersInGroups);
    }

    private int identifGroup(boolean[][] viewedPeople, CoordinMan man) {
        Queue<CoordinMan> buffer = new LinkedList<>();
        buffer.offer(man);
        CoordinMan tempMan = null;
        viewedPeople[man.row][man.column] = true;

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

    public String printMap() {
        StringBuilder answer = new StringBuilder();

        for (int i = 0; i < mapOfGroups[0].length; ++i) {
            answer.append("   ");
            answer.append(i);
        }
        answer.append('\n');

        for (int i = 0; i < mapOfGroups.length; ++i) {
            answer.append(i);

            if (mapOfGroups[i][0].equals("|x|")) {
                answer.append(" ");
                answer.append(mapOfGroups[i][0]);
            }
            else {
                answer.append("  ");
                answer.append(mapOfGroups[i][0]);
            }
            for (int j = 1; j < mapOfGroups[0].length; ++j) {
                if (mapOfGroups[i][j - 1].equals(mapOfGroups[i][j])) {
                    if (mapOfGroups[i][j].equals("|x|")) {
                        answer.append(" ");
                        answer.append(mapOfGroups[i][j]);
                    }
                    else {
                        answer.append("   ");
                        answer.append(mapOfGroups[i][j]);
                    }
                }
                else {
                    answer.append("  ");
                    answer.append(mapOfGroups[i][j]);
                }
            }
            answer.append('\n');
        }
        return answer.toString();
    }

    private String generateReport(List<Integer> countPersInGroups) {
        List<RiskGroup> allRiskGroups = calculCountGroup(countPersInGroups);

        String report = "EMERGENCY PREVENTION SYSTEM\n";
        report += "-------------------------------------------------------------\n";
        report += "-------------------------------------------------------------\n";

        report += printMap() + "\n";

        report += "Risk group report:\n";
        report += "-------------------------------------------------------------\n";

        for (RiskGroup item : allRiskGroups) {
            report += String.format("%s: %d groups;\n", item.name, item.countGroup);
        }
        return report;
    }

    private List<RiskGroup> calculCountGroup(List<Integer> countPersInGroups) {
        int countGroup = 0;
        List<RiskGroup> allRiskGroups = new ArrayList<>();

        allRiskGroups.add(new RiskGroup("NONE", 0));
        allRiskGroups.add(new RiskGroup("MINOR", 0));
        allRiskGroups.add(new RiskGroup("NORMAL", 0));
        allRiskGroups.add(new RiskGroup("MAJOR", 0));
        allRiskGroups.add(new RiskGroup("CRITICAL", 0));

        for (Integer item : countPersInGroups) {
            if (item > 13) {
                ++allRiskGroups.get(4).countGroup;
            }
            if (item >= 8 && item <= 13) {
                ++allRiskGroups.get(3).countGroup;
            }
            if (item >= 5 && item <= 7) {
                ++allRiskGroups.get(2).countGroup;
            }
            if (item >= 3 && item <= 4) {
                ++allRiskGroups.get(1).countGroup;
            }
            if (item >= 1 && item <= 2) {
                ++allRiskGroups.get(0).countGroup;
            }
        }
        return allRiskGroups;
    }
}