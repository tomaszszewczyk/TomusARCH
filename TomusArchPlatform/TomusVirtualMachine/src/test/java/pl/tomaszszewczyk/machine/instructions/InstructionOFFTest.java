package pl.tomaszszewczyk.machine.instructions;

import org.mockito.Mockito;

public class InstructionOFFTest extends InstructionTest {
    private InstructionOFF instruction;

    public void setUp() {
        super.setUp();
        instruction = new InstructionOFF();
    }

    public void testSetup() {
        assertEquals(instruction.getOpcode(), (byte) 0xF5);
    }

    public void testExecute() {
        instruction.execute(machine);
        Mockito.verify(machine, Mockito.times(1)).powerOFF();
    }
}