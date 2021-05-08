package pjm.emulator.memory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class Memory64kTests {
    private static final int MAX_MEMORY = 64 * 1024;
    private static final Byte ZERO = 0;

    @Test
    public void shouldStartEmpty() {
        IMemory memory = new Memory64k();
        for(int i = 0; i < MAX_MEMORY; i++) {
            assertEquals(0, ZERO.compareTo(memory.getByte((char)i)));
        }
    }
    
    @Test
    public void shouldBeAbleToUseMemory() {
        char randomLocation = 12345;
        Byte value = (byte) 0xA9; // 169
        IMemory memory = new Memory64k();
        memory.setByte(randomLocation, value);
        for(char i = 0; i < randomLocation; i++) {
            assertEquals(0, ZERO.compareTo(memory.getByte(i)));
        }
        assertEquals(0, value.compareTo(memory.getByte(randomLocation)));
        assertNotEquals(0, ZERO.compareTo(memory.getByte(randomLocation)));
        for(int i = randomLocation + 1; i < MAX_MEMORY; i++) {
            assertEquals(0, ZERO.compareTo(memory.getByte((char)i)));
        }
    }
}
