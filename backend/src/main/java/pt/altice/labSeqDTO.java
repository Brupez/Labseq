package pt.altice;


public class labSeqDTO {
    private int index;
    private String value;

    public labSeqDTO() {}

    public labSeqDTO(int index, String value) {
        this.index=index;
        this.value=value;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
