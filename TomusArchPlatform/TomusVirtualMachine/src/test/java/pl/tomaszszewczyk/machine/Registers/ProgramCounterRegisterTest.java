package pl.tomaszszewczyk.machine.Registers;

import org.junit.Test;
import pl.tomaszszewczyk.machine.Instruction;

import static org.junit.Assert.*;

public class ProgramCounterRegisterTest {
    @Test
    public void testIncrement() {
        ProgramCounterRegister register = new ProgramCounterRegister();

        register.setValue(1234);
        register.increment();

        assertEquals(1234 + Instruction.size, register.getValue());
    }

}