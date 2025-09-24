package pt.altice;

import io.quarkus.cache.CacheResult;
import jakarta.enterprise.context.ApplicationScoped;

import java.math.BigInteger;


@ApplicationScoped
public class Service {

    //base cases
    public BigInteger computeLabSeq(int n) {
        if (n==0) return BigInteger.ZERO;
        if (n==1) return BigInteger.ONE;
        if (n==2) return BigInteger.ZERO;
        if (n==3) return BigInteger.ONE;

        return computeLabSeq(n-4).add(computeLabSeq(n-3));
    }

    @CacheResult(cacheName = "labSeqCache")
    public BigInteger calcLabSeq(int n) {

        if (n < 0) {
            throw new IllegalArgumentException("Index must be greater or equal to zero");
        }

        return computeLabSeq(n);
    }
}
