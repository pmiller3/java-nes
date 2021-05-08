package pjm.emulator.nes;

import pjm.emulator.cpu.CPU6502;
import pjm.emulator.cpu.ICPU;
import pjm.emulator.memory.IMemory;
import pjm.emulator.memory.Memory64k;

/**
 * Hello world!
 *
 */
public class Emulator
{
    private ICPU cpu;
    private IMemory memory;

    public Emulator() {
        cpu = new CPU6502();
        memory = new Memory64k();
    }

    public static void main( String[] args )
    {
        Emulator emulator = new Emulator();
        emulator.cpu.execute(2, emulator.memory);
    }
}
