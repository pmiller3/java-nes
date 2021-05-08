package pjm.emulator.cpu;

public enum OpCode {
    LOAD_ACCUMULATOR("LDA", (short) 0xA9);
    private final String code;
    private final short value;

    OpCode(String code, short value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public short getValue() {
        return value;
    }
}
