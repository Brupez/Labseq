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

    /**
     * Computes the n-th term of the Lab sequence using memoization
     *
     * @param n
     * @return
     */
    public BigInteger computeLabSeq(int n) {

        if (memo.containsKey(n)) {
            logger.info("Cache for n=" + n);
            return memo.get(n);
        }

        //base cases
        if (n==0) return cacheHelper(0, BigInteger.ZERO);
        if (n==1) return cacheHelper(1, BigInteger.ONE);
        if (n==2) return cacheHelper(2, BigInteger.ZERO);
        if (n==3) return cacheHelper(3, BigInteger.ONE);

        int start = largestCachedValue(n);

        BigInteger nMinus4, nMinus3, nMinus2, nMinus1;

        if (start >= 3) {
            nMinus4 = memo.get(start - 4);
            nMinus3 = memo.get(start - 3);
            nMinus2 = memo.get(start - 2);
            nMinus1 = memo.get(start - 1);
        } else {
            nMinus4 = BigInteger.ZERO;
            nMinus3 = BigInteger.ONE;
            nMinus2 = BigInteger.ZERO;
            nMinus1 = BigInteger.ONE;
            start = 3;
        }

        BigInteger current = BigInteger.ZERO;

        for (int i = start + 1; i <= n; i++) {
            current = nMinus4.add(nMinus3);

            cacheHelper(i, current);

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

    /**
     * Helper method to cache computed values
     *
     * @param n
     * @param value
     * @return
     */
    public BigInteger cacheHelper(int n, BigInteger value) {
        memo.put(n, value);
        return value;
    }

    /**
     * Returns the largest cached value less than n
     *
     * @param n
     * @return
     */
    public int largestCachedValue(int n) {

        for (int i = n - 1; i >= 0; i--) {
            if (memo.containsKey(i)) {
                return i;
            }
        }

        return -1;
    }


}
