package pjm.emulator.cpu;

import java.util.concurrent.atomic.AtomicInteger;

import pjm.emulator.cpu.OpCodes.OpCode;
import pjm.emulator.memory.IMemory;

/**
 * This class is to represent the NES version of the 6502 CPU it contained.
 * https://wiki.nesdev.com/w/index.php/CPU
 */
public class CPU6502 implements ICPU
{
    private byte AC; // Accumulator: byte-wide and along with the arithmetic logic unit (ALU), supports using the status register for carrying, overflow detection, and so on.
    private byte X; // Indexes X and Y are byte-wide and used for several addressing modes. 
    private byte Y; // They can be used as loop counters easily, using INC/DEC and branch instructions. Not being the accumulator, they have limited addressing modes themselves when loading and saving.
    private char PC; // Program Counter: 2-byte supports 65536 direct (unbanked) memory locations, however not all values are sent to the cartridge. It can be accessed either by allowing CPU's internal fetch logic increment the address bus, an interrupt (NMI, Reset, IRQ/BRQ), and using the RTS/JMP/JSR/Branch instructions.
    private char SP; // Stack Pointer: byte-wide and can be accessed using interrupts, pulls, pushes, and transfers.
    private StatusRegister SR; // Status Register: 6 bits used by the ALU but is byte-wide. PHP, PLP, arithmetic, testing, and branch instructions can access this register.

    public CPU6502() {
        SR = new StatusRegister();
        reset();
    }

    @Override
    public void reset() {
        AC = 0;
        X = 0;
        Y = 0;
        PC = 0xFFFC;
        SP = 0x0100;
        SR.reset();
    }

    @Override
    public int execute(int cyclesRequested, IMemory memory) {
        AtomicInteger cyclesExecuted = new AtomicInteger(0); // Use proper object for updating
        while(cyclesExecuted.get() < cyclesRequested) {
            OpCode instruction = getNextInstruction(cyclesExecuted, memory.getByte(PC));
            switch(instruction) {
                case LDA_I: {
                    performLDA(cyclesExecuted, PC, memory);
                    break;
                }
                case LDA_Z: {
                    char address = fetchZeroPageAsWord(cyclesExecuted, PC, memory);
                    performLDA(cyclesExecuted, address, memory);
                    break;
                }
                case LDA_Z_X: {
                    char address = fetchZeroPageAsWord(cyclesExecuted, PC, memory);
                    address += fetchX(cyclesExecuted);
                    performLDA(cyclesExecuted, address, memory);
                    break;
                }
                case LDA_A: {
                    char address = fetchWord(cyclesExecuted, PC, memory);
                    performLDA(cyclesExecuted, address, memory);
                    break;
                }
                default: {
                    System.err.println("Unhandled Instruction: " + instruction);
                    cyclesExecuted.incrementAndGet();
                    break;
                }
            }
        }
        return cyclesExecuted.get();
    }

    private void performLDA(AtomicInteger cyclesExecuted, char address, IMemory memory) {
        setAC(fetchByte(cyclesExecuted, address, memory));
    }

    private OpCode getNextInstruction(AtomicInteger cyclesExecuted, byte value) {
        cyclesExecuted.incrementAndGet();
        PC++;
        return OpCodes.getCode(value);
    }

    private byte fetchByte(AtomicInteger cyclesExecuted, char location, IMemory memory) {
        cyclesExecuted.incrementAndGet();
        PC++;
        return memory.getByte(location);
    }

    private char fetchWord(AtomicInteger cyclesExecuted, char location, IMemory memory) {
        // 6502 is little endian - the low byte is first
        byte lowByte = fetchByte(cyclesExecuted, location, memory);
        byte highByte = fetchByte(cyclesExecuted, ++location, memory);
        char word = (char) (highByte << 8);
        word |= lowByte;
        System.out.println("Bytes: " + highByte + " | " + lowByte + " = int " + (int) word + " or char " + word);
        return word; // bitwise or
    }
    
    private char fetchZeroPageAsWord(AtomicInteger cyclesExecuted, char location, IMemory memory) {
        return (char) fetchByte(cyclesExecuted, location, memory);
    }

    // Zero page reads don't increment Program Counter, but do take a clock cycle
    private byte readByte(AtomicInteger cyclesExecuted, byte location, IMemory memory) {
        cyclesExecuted.incrementAndGet();
        return memory.getByte((char) location);
    }

    private void setX(AtomicInteger cyclesExecuted, byte value) {
        X = value;
        cyclesExecuted.incrementAndGet();
    }

    private byte fetchX(AtomicInteger cyclesExecuted) {
        cyclesExecuted.incrementAndGet();
        return X;
    }

    private void setY(AtomicInteger cyclesExecuted, byte value) {
        Y = value;
        cyclesExecuted.incrementAndGet();
    }

    private void setAC(byte value) {
        AC = value;
        SR.setZero(AC == 0);
        SR.setNegative(AC < 0);
    }

    public byte getA() {
        return AC;
    }

    public byte getX() {
        return X;
    }

    public byte getY() {
        return Y;
    }
    
    public char getPC() {
        return PC;
    }

    public char getSP() {
        return SP;
    }

    public StatusRegister getSR() {
        return SR;
    }

    public String toString() {
        return new StringBuilder().append(AC).append(X).append(Y)
            .append(PC).append(SP).append(SR).toString();
    }
}
