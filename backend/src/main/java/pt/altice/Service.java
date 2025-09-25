package pt.altice;


import io.quarkus.cache.CacheResult;
import jakarta.enterprise.context.ApplicationScoped;

import java.math.BigInteger;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;


@ApplicationScoped
public class Service {

    private static final Logger logger = Logger.getLogger(Service.class.getName());

    private final ConcurrentHashMap<Integer, BigInteger> memo = new ConcurrentHashMap<>();

    public BigInteger computeLabSeq(int n) {

        if (memo.containsKey(n)) {
            return memo.get(n);
        }

        //base cases
        if (n==0) return BigInteger.ZERO;
        if (n==1) return BigInteger.ONE;
        if (n==2) return BigInteger.ZERO;
        if (n==3) return BigInteger.ONE;

        // store only the last 4 values to save memory
        BigInteger nMinus4 = BigInteger.ZERO;
        BigInteger nMinus3 = BigInteger.ONE;
        BigInteger nMinus2 = BigInteger.ZERO;
        BigInteger nMinus1 = BigInteger.ONE;
        BigInteger current = BigInteger.ZERO;

        for (int i = 4; i <= n; i++) {
            current = nMinus4.add(nMinus3);

            memo.put(n, current);

            nMinus4 = nMinus3;
            nMinus3 = nMinus2;
            nMinus2 = nMinus1;
            nMinus1 = current;
        }

        return current;
    }

    @CacheResult(cacheName = "labSeqCache")
    public BigInteger calcLabSeq(int n) {

        logger.info("Calculating LabSeq for n=" + n);

        if (n < 0) {
            throw new IllegalArgumentException("Index must be greater or equal to zero");
        }

        return computeLabSeq(n);
    }
}
