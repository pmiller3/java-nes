package pjm.emulator.cpu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import pjm.emulator.cpu.OpCodes.OpCode;
import pjm.emulator.memory.IMemory;
import pjm.emulator.memory.Memory64k;

/**
 * Unit test for simple 6502 CPU emulator.
 */
@RunWith(Theories.class)
public class CPU6502Tests 
{
    private static final byte ZERO = 0;
    @DataPoints
    public static char[] chars = 
        {Character.MIN_VALUE, 0, 169, Character.MAX_VALUE};
    @DataPoints
    public static byte[] bytes = 
        {Byte.MIN_VALUE, -5, ZERO, 12, (byte) 0xA9, Byte.MAX_VALUE};

    @Test
    public void shouldInitializeCPU()
    {
        CPU6502 cpu = new CPU6502();
        assertEquals(ZERO, cpu.getA());
        assertEquals(ZERO, cpu.getX());
        assertEquals(ZERO, cpu.getY());
        assertEquals(0xFFFC, cpu.getPC());
        assertEquals(65532, cpu.getPC());
        assertEquals(0x0100, cpu.getSP());
        assertEquals(256, cpu.getSP());
        assertEquals(new StatusRegister().getFlags(), cpu.getSR().getFlags());
    }

    @Test
    public void noCyclesIsANoOp() {
        // Given
        CPU6502 cpu = new CPU6502();
        IMemory memory = new Memory64k();
        char location = cpu.getPC();
        memory.setByte(location, OpCode.LDA_I.byteCode());
        memory.setByte(++location, (byte) 0xA9);

        // When
        int cyclesExecuted = cpu.execute(0, memory);

        // Then
        assertEquals(0, cyclesExecuted);
        // TODO : This won't quite work, as the AC is zero,
        // but the ZERO bit on the SR is still false
        //assertRegisters(cpu, ZERO);
    }

    @Theory
    public void shouldExecuteLDA_I(byte value) {
        // Given
        CPU6502 cpu = new CPU6502();
        IMemory memory = new Memory64k();
        char location = cpu.getPC();
        memory.setByte(location, OpCode.LDA_I.byteCode());
        memory.setByte(++location, value);

        // When
        int cyclesExecuted = cpu.execute(1, memory);

        // Then
        assertEquals(2, cyclesExecuted);
        assertRegisters(cpu, value);
    }

    // zero page addresses are, by definition, only a byte long I believe
    @Theory
    public void shouldExecuteLDA_Z(byte value) {
        // Given
        CPU6502 cpu = new CPU6502();
        IMemory memory = new Memory64k();
        char location = cpu.getPC();
        memory.setByte(location, OpCode.LDA_Z.byteCode());
        memory.setByte(++location, value);
        memory.setByte((char) value, value);

        // When
        int cyclesExecuted = cpu.execute(1, memory);

        // Then
        assertEquals(3, cyclesExecuted);
        assertRegisters(cpu, value);
    }

    // zero page addresses are, by definition, only a byte long I believe
    @Theory
    public void shouldExecuteLDA_Z_X(byte value) {
        // Given
        CPU6502 cpu = new CPU6502();
        IMemory memory = new Memory64k();
        char location = cpu.getPC();
        memory.setByte(location, OpCode.LDA_Z_X.byteCode());
        memory.setByte(++location, value);
        memory.setByte((char) value, value);

        // When
        int cyclesExecuted = cpu.execute(1, memory);

        // Then
        assertEquals(4, cyclesExecuted);
        assertRegisters(cpu, value);
    }

    @Theory
    public void shouldExecuteLDA_A(byte value) {
        // Given
        CPU6502 cpu = new CPU6502();
        IMemory memory = new Memory64k();
        char location = cpu.getPC();
        char finalLocation = (char) (value << 8);
        finalLocation |= value;
        memory.setByte(location, OpCode.LDA_A.byteCode());
        memory.setByte(++location, value);
        memory.setByte(++location, value);
        memory.setByte(finalLocation, value);

        // When
        int cyclesExecuted = cpu.execute(1, memory);

        // Then
        assertEquals(4, cyclesExecuted);
        assertRegisters(cpu, value);
    }

    private void scanMemory(IMemory memory) {
        for(char i = 0; i < Character.MAX_VALUE; i++) {
            if(memory.getByte(i) != ZERO)
                System.out.println("Non-zero memory address: " + (int)i);
        }
    }

    private void assertRegisters(CPU6502 cpu, byte value) {
        assertEquals(value, cpu.getA());
        assertEquals(ZERO, cpu.getX());
        assertEquals(ZERO, cpu.getY());
        if(value == 0)
            assert(cpu.getSR().getZero());
        else
            assertFalse(cpu.getSR().getZero());
        if(value < 0)
            assert(cpu.getSR().getNegative());
        else
            assertFalse(cpu.getSR().getNegative());
    }
}
