import java.util.List;
import java.util.Map;

public class Reporter {

    public static String generate(Field sector, Map<String, Integer> riskGroups,
                                  double fillFactor, List<RiskGroup> stateRiskGroups) {
        StringBuilder answer = new StringBuilder();

        answer.append(generateSizeField(sector));
        answer.append(generateLine());
        answer.append(generateLine());
        answer.append(generateLineBreak());
        answer.append(generateFillFactor(fillFactor));
        answer.append(generateLine());
        answer.append(generateLine());
        answer.append(generateLineBreak());
        answer.append(generateStateRiskGroup(stateRiskGroups));
        answer.append(generateLine());
        answer.append(generateLine());
        answer.append(generateLineBreak());
        answer.append(generateHeader());
        answer.append(generateLine());
        answer.append(generateLine());
        answer.append(generateField(sector));
        answer.append(generateListFindGroup(riskGroups));

        return answer.toString();
    }

    private static String generateLineBreak() {
        return "\n";
    }

    private static String generateStateRiskGroup(List<RiskGroup> stateRiskGroups) {
        StringBuilder answer = new StringBuilder();

        for (RiskGroup state : stateRiskGroups) {
            answer.append(state.getName());
            answer.append(" ");
            answer.append(state.getMax());
            answer.append(" person(s)\n");
        }
        return answer.toString();
    }

    private static String generateSizeField(Field sector) {
        return String.format("Field: rows - %d, columns - %d", sector.getRows(), sector.getColumn());
    }

    private static String generateFillFactor(double fillFactor) {
        return String.format("FILL_FACTOR = %f\n", fillFactor);
    }

    private static String generateHeader() {
        return "EMERGENCY PREVENTION SYSTEM\n";
    }

    private static String generateField(Field sector) {
        StringBuilder answer = new StringBuilder();

        for (int i = 0; i < sector.getColumn(); ++i) {
            answer.append("   ");
            answer.append(i);
        }
        answer.append("\n");

        for (int i = 0; i < sector.getRows(); ++i) {
            answer.append(i);

            for (int j = 0; j < sector.getColumn(); ++j) {
                answer.append("  ");

                if (sector.getCell(i, j).equals(CellStateSector.PEOPLE)) {
                    answer.append("|x|");
                }
                else {
                    answer.append("|-|");
                }
            }
        }
        return answer.toString();
    }

    private static String generateListFindGroup(Map<String, Integer> riskGroup) {
        StringBuilder answer = new StringBuilder();

        answer.append("Risk group report:");

        answer.append(generateLine());

        for (Map.Entry<String, Integer> group : riskGroup.entrySet()) {
            answer.append(group.getKey());
            answer.append(": ");
            answer.append(group.getValue());
            answer.append(" groups;");
            answer.append("\n");
        }
        return answer.toString();
    }

    private static String generateLine() {
        return "-----------------------------------------------------------------------\n";
    }
}
