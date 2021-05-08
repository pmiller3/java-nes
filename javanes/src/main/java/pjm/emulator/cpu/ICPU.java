package pjm.emulator.cpu;

import pjm.emulator.memory.IMemory;

public interface ICPU {
    int execute(int cyclesRequested, IMemory memory);
    void reset();
}
