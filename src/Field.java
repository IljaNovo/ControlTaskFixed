import javafx.scene.control.Cell;

import java.util.*;

public class Field implements Cloneable {

    private CellStateSector[][] sector;

    public Field(int rows, int columns, double fillFactor) {
        if (fillFactor < 0.0 || fillFactor > 1.0 ||
                rows < 1 || columns < 1) {
            throw new IllegalArgumentException();
        }
        sector = new CellStateSector[rows][columns];
    }

    public Field(CellStateSector[][] sector) {
        if (sector == null) {
            throw new IllegalArgumentException();
        }
        this.sector = new CellStateSector[sector.length][sector[0].length];

        for (int i = 0; i < sector.length; ++i) {
            this.sector[i] = sector[i].clone();
        }
    }

    public int getRows() {
        return sector[0].length;
    }

    public int getColumn() {
        return sector.length;
    }

    public CellStateSector getCell(int row, int column) {
        if (row < 0 || row >= sector[0].length
                || column < 0 || column > sector.length)
        {
            throw new IndexOutOfBoundsException();
        }
        return sector[row][column];
    }

    public void setCell(int row, int column, CellStateSector state) {
        if (row < 0 || row >= sector[0].length
                || column < 0 || column > sector.length)
        {
            throw new IndexOutOfBoundsException();
        }
        sector[row][column] = state;
    }

    public Field clone() throws CloneNotSupportedException{
        CellStateSector[][] cloneSector = new CellStateSector[sector.length][sector[0].length];

        for (int i = 0; i < sector.length; ++i) {
            cloneSector[i] = sector[0].clone();
        }
        Field clone = new Field(cloneSector);

        return clone;
    }

    public String printMap() {
        StringBuilder answer = new StringBuilder();

        for (int i = 0; i < sector[0].length; ++i) {
            answer.append("   ");
            answer.append(i);
        }
        answer.append('\n');

        for (int i = 0; i < sector.length; ++i) {
            answer.append(i);

            if (sector[i][0].equals("|x|")) {
                answer.append(" ");
                answer.append(sector[i][0]);
            }
            else {
                answer.append("  ");
                answer.append(sector[i][0]);
            }
            for (int j = 1; j < sector[0].length; ++j) {
                if (sector[i][j - 1].equals(sector[i][j])) {
                    if (sector[i][j].equals("|x|")) {
                        answer.append(" ");
                        answer.append(sector[i][j]);
                    }
                    else {
                        answer.append("   ");
                        answer.append(sector[i][j]);
                    }
                }
                else {
                    answer.append("  ");
                    answer.append(sector[i][j]);
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