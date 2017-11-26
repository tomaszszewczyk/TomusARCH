package pl.tomaszszewczyk.machine.Registers;

import org.junit.Test;

import static org.junit.Assert.*;

public class ProgramCounterRegisterTest {
    @Test
    public void testIncrement() {
        ProgramCounterRegister register = new ProgramCounterRegister();

        register.setValue(1234);
        register.increment();

        assertEquals(1235, register.getValue());
    }

}