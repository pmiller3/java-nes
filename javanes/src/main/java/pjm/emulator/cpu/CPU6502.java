package pjm.emulator.cpu;

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
    private short PC; // Program Counter: 2-byte supports 65536 direct (unbanked) memory locations, however not all values are sent to the cartridge. It can be accessed either by allowing CPU's internal fetch logic increment the address bus, an interrupt (NMI, Reset, IRQ/BRQ), and using the RTS/JMP/JSR/Branch instructions.
    private short SP; // Stack Pointer: byte-wide and can be accessed using interrupts, pulls, pushes, and transfers.
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
        PC = (short) 0xFFFC;
        SP = 0x0100;
        SR.reset();
    }

    @Override
    public int execute(int cyclesRequested, IMemory memory) {
        int cyclesExecuted = 0;
        while(cyclesExecuted < cyclesRequested) {
            OpCode instruction = getNextInstruction();
            switch(instruction) {
                case LOAD_ACCUMULATOR:
                    System.out.println("Executing Load Accumulator Instuction");
                    break;
                default:
                    System.err.println("Unhandled Instruction: " + instruction);
                    break;
            }
            cyclesExecuted++;
        }
        return cyclesExecuted;
    }

    public OpCode getNextInstruction() {
        return OpCode.valueOf("LDA");
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
    
    public short getPC() {
        return PC;
    }

    public short getSP() {
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
