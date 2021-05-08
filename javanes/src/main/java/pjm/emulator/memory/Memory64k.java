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

    @Override
    public byte getByte(int address) {
        checkAddress(address);
        return memory[address];
    }

    @Override
    public void setByte(int address, byte value) {
        checkAddress(address);
        memory[address] = value;
    }

    private void checkAddress(int address) {
        if(address < 0 || address >= MAX_MEMORY)
            throw new IllegalArgumentException("Memory access out of bounds: " + address);
    }
}
