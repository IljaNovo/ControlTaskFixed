public class GeneratorField {

    /*
    * in, out
    * */
    public static void generate(Field sector, double fillFactor) {
        for (int i = 0; i < sector.getRows(); ++i) {
            for (int j = 0; j < sector.getColumn(); ++j) {
                if (Math.random() <= fillFactor) {
                    sector.setCellOfIndex(i, j, CellStateSector.PEOPLE);
                }
                else {
                    sector.setCellOfIndex(i, j, CellStateSector.EMPTY);
                }
            }
        }
    }
}
