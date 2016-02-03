import org.junit.Assert;
import org.junit.Test;

public class FieldTest {

    @Test
    public void testGenerateField() throws Exception {
        Field sector = new Field(3, 3, 1.0);
        String[][] answer = pf.getPeopleField();
        boolean flag = true;

        for (int i = 0; i < answer.length; ++i) {
            for (int j = 0; j < answer[0].length; ++j) {
                if (answer[i][j] == null) {
                    flag = false;
                    i = answer.length;
                    break;
                }
            }
        }
        Integer i = 0;
        Assert.assertTrue(flag);
    }

    @Test
    public void testInvalidDateCreateField() throws Exception {
        try {
            Field pf = new Field(-3, 3, 3.0);
        }
        catch (IllegalArgumentException e) {
            Assert.assertTrue(true);
        }
    }
}