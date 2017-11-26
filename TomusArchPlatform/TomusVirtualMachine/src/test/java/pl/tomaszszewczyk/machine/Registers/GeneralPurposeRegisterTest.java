package pl.tomaszszewczyk.machine.Registers;

import org.junit.Test;

import static org.junit.Assert.*;

public class GeneralPurposeRegisterTest {
    @Test
    public void writeAndReadRegister() {
        GeneralPurposeRegister register = new GeneralPurposeRegister();
        register.setValue(1234);

        assertEquals(1234, register.getValue());
    }
}