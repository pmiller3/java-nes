package pjm.emulator.cpu;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import pjm.emulator.cpu.OpCodes.OpCode;

public class OpCodesTests {
    @Test
    public void HasLDAOpCode() {
        byte byteCode = (byte) 0xA9;
        OpCode code = OpCodes.getCode(byteCode);
        assertEquals(OpCode.LDA, code);
        assertEquals(byteCode, code.byteCode());
    }
}
