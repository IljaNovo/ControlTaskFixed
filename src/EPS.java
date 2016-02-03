import org.apache.commons.cli.ParseException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EPS {

    public static void main(String[] args) {

        Map<String, Integer> riskGroups = null;
        Input params = null;
        Field sector = null;
        List<RiskGroup> stateRiskGroup = null;

        try {
            params = Parser.parseParams(args);
            sector = new Field(params.getCountRows(), params.getCountColumn(), params.getFillFactor());
            GeneratorField.generate(sector, params.getFillFactor());

            stateRiskGroup = new ArrayList<>();

            stateRiskGroup.add(new RiskGroup("NONE", 1, 2));
            stateRiskGroup.add(new RiskGroup("MINOR", 3, 4));
            stateRiskGroup.add(new RiskGroup("NORMAL", 5, 7));
            stateRiskGroup.add(new RiskGroup("MAJOR", 8, 13));
            stateRiskGroup.add(new RiskGroup("CRITICAL", 14, Integer.MAX_VALUE - 1));

            Locator groupsSearch = new FindInWidthLocator(stateRiskGroup);

            riskGroups = groupsSearch.findRiskGroups(sector.clone());

            System.out.println(Reporter.generate(sector, riskGroups, params.getFillFactor(), stateRiskGroup));
        }
        catch (ParseException e) {
            e.getStackTrace();
        }
        catch (CloneNotSupportedException e) {
            e.getStackTrace();
        }

//        try {
//            valuesParams = parsParams.parseRowsColumnsProbab(args);
//
//            Integer rows = Integer.valueOf(valuesParams.get("Rows"));
//            Integer columns = Integer.valueOf(valuesParams.get("Columns"));
//            Double probability = Double.valueOf(valuesParams.get("Probability"));
//
//            pf = new Field(rows, columns, probability);
//
//            System.out.println(pf.searchGroup());
//        }
//        catch (ParseException e) {
//            System.out.println("Invalid input data");
//        }
//        catch (IllegalArgumentException e) {
//            System.out.println("Invalid input data");
//        }
//        catch (Throwable e) {
//            System.out.println("Invalid input data");
//        }
//
//        int i = 0;
//    }
    }
}