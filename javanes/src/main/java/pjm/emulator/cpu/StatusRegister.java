package pjm.emulator.cpu;

import java.util.BitSet;

/**
 * Represents the 6502 status register flags, 1 byte NV-BDIZC
 * N - Negative
 * V - Overflow
 * - - Ignored
 * B - Break
 * D - Decimal
 * I - Interrupt
 * Z - Zero
 * C - Carry
 */
public class StatusRegister {
    private BitSet bits;
    private static final String EMPTY = "00000000";
    private static final char ZERO = '0';
    private static final char ONE = '1';

    public StatusRegister() {
        bits = new BitSet(8);
        reset();
    }

    public void reset() {
        bits.clear();
    }

    public String toString() {
        if (bits.isEmpty())
            return EMPTY;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 8; i++)
            sb.append(bits.get(i) ? ONE : ZERO);
        return sb.toString();
    }

    public Byte getFlags() {
        return bits.isEmpty() ? 0 : 
            bits.toByteArray()[0];
    }

    public boolean getNegative() {
        return bits.get(0);
    }

    public boolean getOverflow() {
        return bits.get(1);
    }

    public boolean getIgnored() {
        return bits.get(2);
    }

    public boolean getBreak() {
        return bits.get(3);
    }

    public boolean getDecimal() {
        return bits.get(4);
    }

    public boolean getInterrupt() {
        return bits.get(5);
    }

    public boolean getZero() {
        return bits.get(6);
    }

    public boolean getCarry() {
        return bits.get(7);
    }

    public void setNegative() {
        bits.set(0);
    }

    public void setNegative(boolean bit) {
        bits.set(0, bit);
    }

    public void setOverflow() {
        bits.set(1);
    }

    public void setOverflow(boolean bit) {
        bits.set(1, bit);
    }

    public void setIgnored() {
        bits.set(2);
    }

    public void setIgnored(boolean bit) {
        bits.set(2, bit);
    }

    public void setBreak() {
        bits.set(3);
    }

    public void setBreak(boolean bit) {
        bits.set(3, bit);
    }

    public void setDecimal() {
        bits.set(4);
    }

    public void setDecimal(boolean bit) {
        bits.set(4, bit);
    }

    public void setInterrupt() {
        bits.set(5);
    }

    public void setInterrupt(boolean bit) {
        bits.set(5, bit);
    }

    public void setZero() {
        bits.set(6);
    }

    public void setZero(boolean bit) {
        bits.set(6, bit);
    }

    public void setCarry() {
        bits.set(7);
    }

    public void setCarry(boolean bit) {
        bits.set(7, bit);
    }

    public void flipNegative() {
        bits.flip(0);
    }

    public void flipOverflow() {
        bits.flip(1);
    }

    public void flipIgnored() {
        bits.flip(2);
    }

    public void flipBreak() {
        bits.flip(3);
    }

    public void flipDecimal() {
        bits.flip(4);
    }

    public void flipInterrupt() {
        bits.flip(5);
    }

    public void flipZero() {
        bits.flip(6);
    }

    public void flipCarry() {
        bits.flip(7);
    }

    public void clearNegative() {
        bits.clear(0);
    }

    public void clearOverflow() {
        bits.clear(1);
    }

    public void clearIgnored() {
        bits.clear(2);
    }

    public void clearBreak() {
        bits.clear(3);
    }

    public void clearDecimal() {
        bits.clear(4);
    }

    public void clearInterrupt() {
        bits.clear(5);
    }

    public void clearZero() {
        bits.clear(6);
    }

    public void clearCarry() {
        bits.clear(7);
    }
}
