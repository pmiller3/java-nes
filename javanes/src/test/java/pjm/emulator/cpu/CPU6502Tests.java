package pjm.emulator.cpu;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import pjm.emulator.cpu.OpCodes.OpCode;
import pjm.emulator.memory.IMemory;
import pjm.emulator.memory.Memory64k;

/**
 * Unit test for simple 6502 CPU emulator.
 */
public class CPU6502Tests 
{
    private static final byte ZERO = 0;

    @Test
    public void shouldInitializeCPU()
    {
        CPU6502 cpu = new CPU6502();
        assertEquals(ZERO, cpu.getA());
        assertEquals(ZERO, cpu.getX());
        assertEquals(ZERO, cpu.getY());
        assertEquals(0xFFFC, cpu.getPC());
        assertEquals(0x0100, cpu.getSP());
        assertEquals(new StatusRegister().getFlags(), cpu.getSR().getFlags());
    }

    @Test
    public void shouldExecuteLDA() {
        // Given
        CPU6502 cpu = new CPU6502();
        IMemory memory = new Memory64k();
        memory.setByte(cpu.getSP(), OpCode.LDA.byteCode());

        // When
        int cyclesExecuted = cpu.execute(2, memory);

        // Then
        assertEquals(2, cyclesExecuted);
        assertEquals(ZERO, cpu.getA());
    }
}
