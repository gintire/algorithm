import static org.junit.Assert.*;
import org.junit.Test;

public class MainTest {
    @Test
    public void dfsTest() {
        assertEquals(0, Main.doLadder(3, 0, 3, 0));
    }
}
