package pl.tomaszszewczyk.machine.instructions;

import org.mockito.Mockito;
import pl.tomaszszewczyk.machine.CPU;

public class InstructionMODTest extends InstructionTest {
    private InstructionMOD instruction;

    public void setUp() {
        super.setUp();
        instruction = new InstructionMOD();
    }

    public void testSetup() {
        assertEquals(instruction.getOpcode(), 0x14);
    }

    public void testExecute() {
        Mockito.when(cpu.getRegister(CPU.Register.R0)).thenReturn(99);
        Mockito.when(cpu.getRegister(CPU.Register.R1)).thenReturn(100);
        instruction.setSource(CPU.Register.R0);
        instruction.setDestination(CPU.Register.R1);

        instruction.execute(machine);

        Mockito.verify(cpu, Mockito.times(1))
                .setRegister(CPU.Register.R1, 1);
    }
}