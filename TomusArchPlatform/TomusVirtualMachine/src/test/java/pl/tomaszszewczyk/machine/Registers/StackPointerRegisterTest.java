package pl.tomaszszewczyk.machine.Registers;

import org.junit.Test;

import static org.junit.Assert.*;

public class StackPointerRegisterTest {

    @Test
    public void testIncrement() {
        StackPointerRegister register = new StackPointerRegister();

        register.setValue(1234);
        register.increment();

        assertEquals(1235, register.getValue());
    }

    @Test
    public void testDecrement() {
        StackPointerRegister register = new StackPointerRegister();

        register.setValue(1234);
        register.decrement();

        assertEquals(1233, register.getValue());
    }
}