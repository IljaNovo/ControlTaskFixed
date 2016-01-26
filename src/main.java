import java.util.Map;

public class main {

    public static void main(String[] args) {
        ParserParams parsParams = new ParserParams();
        Map<String, String> valuesParams = null;
        PeopleField pf = null;

        try {
            valuesParams = parsParams.parseRowsColumnsProbab(args);

            Integer rows = Integer.valueOf(valuesParams.get("Rows"));
            Integer columns = Integer.valueOf(valuesParams.get("Columns"));
            Double probability = Double.valueOf(valuesParams.get("Probability"));

            pf = new PeopleField(rows, columns, probability);

            System.out.println(pf.searchGroup());
        }
        catch (ParseCommandLineException e) {
            System.out.println("Invalid input data");
        }
        catch (IllegalArgumentException e) {
            System.out.println("Invalid input data");
        }
        catch (Throwable e) {
            System.out.println("Invalid input data");
        }

        int i = 0;
    }
}
