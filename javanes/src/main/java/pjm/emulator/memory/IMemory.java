package pjm.emulator.memory;

public interface IMemory {
    void reset();
    byte getByte( int address );
    void setByte( int address, byte value);
}
