import org.apache.commons.cli.ParseException;
public class main {

    public static void main(String[] args) {

        // Map<String, String> valuesParams = null;
        Input params;
        Field sector = null;
        try {
            params = Parser.parseParams(args);
            sector = new Field(params.getCountRows(), params.getCountColumn(), params.getFillFactor());
            GeneratorField.generate(sector, params.getFillFactor());
        }
        catch (ParseException e) {
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