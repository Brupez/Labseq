package pt.altice;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.time.Duration;
import java.time.Instant;

@QuarkusTest
public class PerformanceTest {

    @Test
    public void testPerformance() {

        Service service = new Service();

        Instant start = Instant.now();
        BigInteger res = service.calcLabSeq(100000);
        Instant finish = Instant.now();

        long time = Duration.between(start, finish).toSeconds();

        System.out.println("LabSeq(100000) = " + res);
        System.out.println("Execution time: " + time + " seconds");
    }
}
