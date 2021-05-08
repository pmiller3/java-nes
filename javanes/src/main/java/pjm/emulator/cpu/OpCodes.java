package pjm.emulator.cpu;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class OpCodes {
    public enum OpCode {
        LDA((byte) 0xA9);

        private final byte byteCode;
        
        OpCode(byte byteCode) {
            this.byteCode = byteCode;
        }

        byte byteCode() {
            return byteCode;
        }
    }

    private static final Map<Byte, OpCode> codeMap;
    static {
        Map<Byte, OpCode> aMap = new HashMap<>();
        aMap.put((byte) 0xA9, OpCode.LDA);
        codeMap = Collections.unmodifiableMap(aMap);
    }

    public static OpCode getCode(byte value) {
        if(codeMap.containsKey(value))
            return codeMap.get(value);
        throw new IllegalArgumentException("Illegal Opcode requested: " + value);
    }
}
