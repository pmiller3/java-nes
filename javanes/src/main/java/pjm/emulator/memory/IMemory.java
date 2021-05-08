package pjm.emulator.memory;

public interface IMemory {
    void reset();
    byte getByte( char address );
    void setByte( char address, byte value);
}
