package pjm.emulator.cpu;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import pjm.emulator.cpu.OpCodes.OpCode;

public class OpCodesTests {
    @Test
    public void HasLDAOpCodes() {
        byte byteCode = (byte) 0xA9;
        OpCode code = OpCodes.getCode(byteCode);
        assertEquals(OpCode.LDA_I, code);
        assertEquals(byteCode, code.byteCode());
        byteCode = (byte) 0xA5;
        code = OpCodes.getCode(byteCode);
        assertEquals(OpCode.LDA_Z, code);
        assertEquals(byteCode, code.byteCode());
        byteCode = (byte) 0xB5;
        code = OpCodes.getCode(byteCode);
        assertEquals(OpCode.LDA_Z_X, code);
        assertEquals(byteCode, code.byteCode());
        byteCode = (byte) 0xAD;
        code = OpCodes.getCode(byteCode);
        assertEquals(OpCode.LDA_A, code);
        assertEquals(byteCode, code.byteCode());
        byteCode = (byte) 0xBD;
        code = OpCodes.getCode(byteCode);
        assertEquals(OpCode.LDA_A_X, code);
        assertEquals(byteCode, code.byteCode());
        byteCode = (byte) 0xB9;
        code = OpCodes.getCode(byteCode);
        assertEquals(OpCode.LDA_A_Y, code);
        assertEquals(byteCode, code.byteCode());
        byteCode = (byte) 0xA1;
        code = OpCodes.getCode(byteCode);
        assertEquals(OpCode.LDA_N_X, code);
        assertEquals(byteCode, code.byteCode());
        byteCode = (byte) 0xB1;
        code = OpCodes.getCode(byteCode);
        assertEquals(OpCode.LDA_N_Y, code);
        assertEquals(byteCode, code.byteCode());
    }
}
