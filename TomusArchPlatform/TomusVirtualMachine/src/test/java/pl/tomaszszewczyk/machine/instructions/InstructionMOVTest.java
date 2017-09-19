package pl.tomaszszewczyk.machine.instructions;

import org.mockito.Mockito;
import pl.tomaszszewczyk.machine.CPU.Register;

import static org.mockito.Mockito.mock;

public class InstructionMOVTest extends InstructionTest {
    private InstructionMOV instruction;

    public void setUp() {
        super.setUp();
        instruction = new InstructionMOV();
    }

    public void testSetup() {
        assertEquals(instruction.getOpcode(), 0x00);
    }

    public void testExecute() {
        Mockito.when(cpu.getRegister(Register.R0)).thenReturn(123);
        instruction.setSource(Register.R0);
        instruction.setDestination(Register.R1);

        instruction.execute(machine);

        Mockito.verify(cpu, Mockito.times(1)).setRegister(Register.R1, 123);
    }
}