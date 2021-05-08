package pjm.emulator.memory;

public class Memory64k implements IMemory {
    // 64 K of memory
    private static final int MAX_MEMORY = 1024 * 64;
    private static final byte EMPTY = 0;
    private byte[] memory = new byte[MAX_MEMORY];

    public Memory64k() {
        reset();
    }

    @Override
    public void reset() {
        for(int i = 0; i < MAX_MEMORY; i++)
            memory[i] = EMPTY;
    }

    // chars are two bytes wide, unsigned
    // therefore guaranteed to fit in memory
    // of precisely this size.
    @Override
    public byte getByte(char address) {
        return memory[address];
    }

    @Override
    public void setByte(char address, byte value) {
        memory[address] = value;
    }
}
