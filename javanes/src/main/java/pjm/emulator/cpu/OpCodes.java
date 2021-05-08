package pjm.emulator.cpu;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class OpCodes {
    private static final Map<Byte, OpCode> codeMap;
    static {
        Map<Byte, OpCode> aMap = new HashMap<>();
        aMap.put((byte) 0xA9, OpCode.LDA_I);
        aMap.put((byte) 0xA5, OpCode.LDA_Z);
        aMap.put((byte) 0xB5, OpCode.LDA_Z_X);
        aMap.put((byte) 0xAD, OpCode.LDA_A);
        aMap.put((byte) 0xBD, OpCode.LDA_A_X);
        aMap.put((byte) 0xB9, OpCode.LDA_A_Y);
        aMap.put((byte) 0xA1, OpCode.LDA_N_X);
        aMap.put((byte) 0xB1, OpCode.LDA_N_Y);
        codeMap = Collections.unmodifiableMap(aMap);
    }

    public static OpCode getCode(byte value) {
        if(codeMap.containsKey(value))
            return codeMap.get(value);
        throw new IllegalArgumentException("Illegal Opcode requested: " + value);
    }

    public enum OpCode {
        // LDA - Load Accumulator with Memory -> AC, N, Z
        LDA_I((byte) 0xA9),
        LDA_Z((byte) 0xA5),
        LDA_Z_X((byte) 0xB5),
        LDA_A((byte) 0xAD),
        LDA_A_X((byte) 0xBD),
        LDA_A_Y((byte) 0xB9),
        LDA_N_X((byte) 0xA1),
        LDA_N_Y((byte) 0xB1);

        private final byte byteCode;
        
        OpCode(byte byteCode) {
            this.byteCode = byteCode;
        }

        byte byteCode() {
            return byteCode;
        }
    }
}
