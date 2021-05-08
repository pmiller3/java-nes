package pjm.emulator.cpu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class StatusRegisterTests {
    private static final String ZERO = "00000000";

    @Test
    public void shouldInitializeRegister() {
        StatusRegister sr = new StatusRegister();
        assertEquals(0, sr.getFlags().intValue());
    }

    @Test
    public void shouldResetRegister() {
        StatusRegister sr = new StatusRegister();
        sr.setOverflow();
        assertNotEquals(0, sr.getFlags().intValue());
        sr.reset();
        assertEquals(0, sr.getFlags().intValue());
    }

    @Test
    public void canGetAllRegisters() {
        StatusRegister sr = new StatusRegister();
        assertAllRegistersZero(sr);
    }

    @Test
    public void canPrintBits() {
        StatusRegister sr = new StatusRegister();
        assertEquals(ZERO, sr.toString());
        sr.setBreak();
        sr.setInterrupt();
        sr.setOverflow();
        sr.setNegative();
        //NV-BDIZC is bit order
        assertEquals("11010100", sr.toString());
    }

    @Test
    public void testOverflowRegister() {
        StatusRegister sr = new StatusRegister();
        assertFalse(sr.getOverflow());
        sr.setOverflow(true);
        assert(sr.getOverflow());
        assertEquals("01000000", sr.toString());
    }

    @Test
    public void testNegativeRegister() {
        StatusRegister sr = new StatusRegister();
        assertFalse(sr.getNegative());
        sr.flipNegative();
        assert(sr.getNegative());
        assertEquals("10000000", sr.toString());
    }

    @Test
    public void testBreakRegister() {
        StatusRegister sr = new StatusRegister();
        assertFalse(sr.getBreak());
        sr.setBreak();
        assert(sr.getBreak());
        assertEquals("00010000", sr.toString());
    }

    @Test
    public void testDecimalRegister() {
        StatusRegister sr = new StatusRegister();
        assertFalse(sr.getDecimal());
        sr.setDecimal();
        assert(sr.getDecimal());
        assertEquals("00001000", sr.toString());
        sr.setDecimal(false);
        assertFalse(sr.getDecimal());
        assertEquals(ZERO, sr.toString());
    }

    @Test
    public void testInterruptRegister() {
        StatusRegister sr = new StatusRegister();
        assertFalse(sr.getInterrupt());
        sr.setInterrupt();
        assert(sr.getInterrupt());
        assertEquals("00000100", sr.toString());
        sr.clearInterrupt();
        assertFalse(sr.getInterrupt());
        assertEquals(ZERO, sr.toString());
    }

    @Test
    public void testZeroRegister() {
        StatusRegister sr = new StatusRegister();
        assertFalse(sr.getZero());
        sr.setZero();
        assert(sr.getZero());
        assertEquals("00000010", sr.toString());
    }

    @Test
    public void testCarryRegister() {
        StatusRegister sr = new StatusRegister();
        assertFalse(sr.getCarry());
        sr.setCarry();
        assert(sr.getCarry());
        assertEquals("00000001", sr.toString());
    }

    @Test
    public void canSetAllRegisters() {
        StatusRegister sr = new StatusRegister();
        assertAllRegistersZero(sr);
        setAllRegisters(sr);
        assertAllRegistersOne(sr);
        setAllRegisters(sr, false);
        assertAllRegistersZero(sr);
        setAllRegisters(sr, true);
        assertAllRegistersOne(sr);
    }

    @Test
    public void canFlipAllRegisters() {
        StatusRegister sr = new StatusRegister();
        assertAllRegistersZero(sr);
        flipAllRegisters(sr);
        assertAllRegistersOne(sr);
        flipAllRegisters(sr);
        assertAllRegistersZero(sr);
    }

    @Test
    public void canClearAllRegisters() {
        StatusRegister sr = new StatusRegister();
        assertAllRegistersZero(sr);
        clearAllRegisters(sr);
        assertAllRegistersZero(sr);
        flipAllRegisters(sr);
        assertAllRegistersOne(sr);
        clearAllRegisters(sr);
        assertAllRegistersZero(sr);
    }

    private void assertAllRegistersZero(StatusRegister sr) {
        assertFalse(sr.getNegative());
        assertFalse(sr.getOverflow());
        assertFalse(sr.getIgnored());
        assertFalse(sr.getBreak());
        assertFalse(sr.getDecimal());
        assertFalse(sr.getInterrupt());
        assertFalse(sr.getZero());
        assertFalse(sr.getCarry());
    }

    private void assertAllRegistersOne(StatusRegister sr) {
        assert(sr.getNegative());
        assert(sr.getOverflow());
        assert(sr.getIgnored());
        assert(sr.getBreak());
        assert(sr.getDecimal());
        assert(sr.getInterrupt());
        assert(sr.getZero());
        assert(sr.getCarry());
    }

    private void flipAllRegisters(StatusRegister sr) {
        sr.flipBreak();
        sr.flipCarry();
        sr.flipDecimal();
        sr.flipIgnored();
        sr.flipInterrupt();
        sr.flipNegative();
        sr.flipOverflow();
        sr.flipZero();
    }

    private void clearAllRegisters(StatusRegister sr) {
        sr.clearBreak();
        sr.clearCarry();
        sr.clearDecimal();
        sr.clearIgnored();
        sr.clearInterrupt();
        sr.clearNegative();
        sr.clearOverflow();
        sr.clearZero();
    }

    private void setAllRegisters(StatusRegister sr) {
        sr.setBreak();
        sr.setCarry();
        sr.setDecimal();
        sr.setIgnored();
        sr.setInterrupt();
        sr.setNegative();
        sr.setOverflow();
        sr.setZero();
    }

    private void setAllRegisters(StatusRegister sr, boolean bit) {
        sr.setBreak(bit);
        sr.setCarry(bit);
        sr.setDecimal(bit);
        sr.setIgnored(bit);
        sr.setInterrupt(bit);
        sr.setNegative(bit);
        sr.setOverflow(bit);
        sr.setZero(bit);
    }
}
