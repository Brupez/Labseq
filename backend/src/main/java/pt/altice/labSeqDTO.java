package pt.altice;

import java.math.BigInteger;

public class labSeqDTO {
    private int index;
    private BigInteger value;

    public labSeqDTO() {}

    public labSeqDTO(int index, BigInteger value) {
        this.index=index;
        this.value=value;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public BigInteger getValue() {
        return value;
    }

    public void setValue(BigInteger value) {
        this.value = value;
    }
}
