package pjm.emulator.cpu;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class OpCodeTests {
    @Test
    public void HasLDAOpCode() {
        OpCode code = OpCode.valueOf("LOAD_ACCUMULATOR");
        assert(OpCode.LOAD_ACCUMULATOR == code);
        assertEquals("LDA", code.getCode());
        assertEquals(0xA9, code.getValue());
    }
}
