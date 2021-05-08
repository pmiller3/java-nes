package pjm.emulator.cpu;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class CPU6502Tests 
{
    @Test
    public void shouldInitializeCPU()
    {
        CPU6502 cpu = new CPU6502();
        byte zero = 0;
        assertEquals(zero, cpu.getA());
        assertEquals(zero, cpu.getX());
        assertEquals(zero, cpu.getY());
        assertEquals(0, Short.compareUnsigned((short) 0xFFFC, cpu.getPC()));
        assertEquals(0, Short.compareUnsigned((short) 0x0100, cpu.getSP()));
        assertEquals(new StatusRegister().getFlags(), cpu.getSR().getFlags());
    }
}
