package pl.tomaszszewczyk.machine.Registers;

import org.junit.Test;

import static org.junit.Assert.*;

public class ControlRegistersTest {
    @Test
    public void testAccess() {
        ControlRegisters registers = new ControlRegisters();

        registers.writeRegisterByAddress(0x101, 1234);
        assertEquals(1234, registers.readRegisterByAddress(0x101));
    }

}