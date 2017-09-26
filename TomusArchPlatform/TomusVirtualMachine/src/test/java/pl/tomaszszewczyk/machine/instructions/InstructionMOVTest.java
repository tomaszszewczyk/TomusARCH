package pl.tomaszszewczyk.machine.instructions;

import org.mockito.Mockito;
import pl.tomaszszewczyk.machine.CPU.Register;

public class InstructionMOVTest extends InstructionTest {
    private InstructionMOV instruction;

    public void setUp() {
        super.setUp();
    }

    public void testSetup() {
        assertEquals(instruction.getOpcode(), 0x00);
    }

    public void testExecute() {
        Mockito.when(cpu.getRegister(Register.R0)).thenReturn(123);
        instruction = new InstructionMOV(Register.R1, Register.R0);

        instruction.execute(machine);

        Mockito.verify(cpu, Mockito.times(1)).setRegister(Register.R1, 123);
    }
}