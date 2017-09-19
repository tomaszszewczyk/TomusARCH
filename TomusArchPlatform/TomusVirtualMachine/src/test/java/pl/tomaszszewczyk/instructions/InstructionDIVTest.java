package pl.tomaszszewczyk.instructions;

import org.mockito.Mockito;
import pl.tomaszszewczyk.CPU;

public class InstructionDIVTest extends InstructionTest {
    private InstructionDIV instruction;

    public void setUp() {
        super.setUp();
        instruction = new InstructionDIV();
    }

    public void testSetup() {
        assertEquals(instruction.getOpcode(), 0x13);
    }

    public void testExecute() {
        Mockito.when(cpu.getRegister(CPU.Register.R0)).thenReturn(5);
        Mockito.when(cpu.getRegister(CPU.Register.R1)).thenReturn(100);
        instruction.setSource(CPU.Register.R0);
        instruction.setDestination(CPU.Register.R1);

        instruction.execute(machine);

        Mockito.verify(cpu, Mockito.times(1))
                .setRegister(CPU.Register.R1, 20);
    }
}