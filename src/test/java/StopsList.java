import org.junit.Assert;
import org.junit.Test;
import ru.vyatsu.selenium.CDS;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class StopsList {

    @Test
    public void route47BusTest() throws IOException {
        CDS cdsStops = new CDS();
        List<String> stopNames = cdsStops.getStopsList(1047);

        try (InputStream is = getClass().getResourceAsStream("stops47.txt")) {
            Assert.assertArrayEquals(String.join("\r\n", stopNames)
                    .getBytes(),
                    is.readAllBytes());
        }
    }

    @Test
    public void route23BusTest() throws IOException {
        CDS cdsStops = new CDS();
        List<String> stopNames = cdsStops.getStopsList(1023);

        try (InputStream is = getClass().getResourceAsStream("stops23.txt")) {
            Assert.assertArrayEquals(String.join("\r\n", stopNames)
                            .getBytes(),
                    is.readAllBytes());
        }
    }

    @Test
    public void route8TrolleybusTest() throws IOException {
        CDS cdsStops = new CDS();
        List<String> stopNames = cdsStops.getStopsList(50080);

        try (InputStream is = getClass().getResourceAsStream("stops8.txt")) {
            Assert.assertArrayEquals(String.join("\r\n", stopNames)
                            .getBytes(),
                    is.readAllBytes());
        }
    }

}
