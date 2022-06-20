import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

class MainTest {

    @Test
    @Timeout(21)
    @Disabled
    public void timeMain() throws Exception {
        Main.main(null);
    }

}