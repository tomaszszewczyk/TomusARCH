package pl.tomaszszewczyk.machine.Registers;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FlagRegisterTest {
    @Test
    public void testZeroFlag() {
        FlagRegister register = new FlagRegister();
        register.setValue(0);

        assertEquals(false, register.getZeroFlag());

        register.writeZeroFlag(true);

        assertEquals(true, register.getZeroFlag());
        assertEquals(FlagRegister.zeroFlagMask, register.getValue());
    }

    @Test
    public void testCarryFlag() {
        FlagRegister register = new FlagRegister();
        register.setValue(0);

        assertEquals(false, register.getCarryFlag());

        register.writeCarryFlag(true);

        assertEquals(true, register.getCarryFlag());
        assertEquals(FlagRegister.carryFlagMask, register.getValue());
    }

    @Test
    public void testAllFlag() {
        FlagRegister register = new FlagRegister();
        register.setValue(0);

        register.writeCarryFlag(true);
        register.writeZeroFlag(true);

        assertEquals(FlagRegister.carryFlagMask | FlagRegister.zeroFlagMask,
                        register.getValue());
    }

}