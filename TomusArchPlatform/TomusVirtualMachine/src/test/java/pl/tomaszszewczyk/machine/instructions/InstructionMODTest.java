package pl.tomaszszewczyk.machine.instructions;

import org.mockito.Mockito;
import pl.tomaszszewczyk.machine.CPU;

public class InstructionMODTest extends InstructionTest {
    private InstructionMOD instruction;

    public void setUp() {
        super.setUp();
    }

    public void testSetup() {
        assertEquals(InstructionMOD.getOpcode(), 0x14);
    }

    public void testExecute() {
        Mockito.when(cpu.getRegister(CPU.Register.R0)).thenReturn(99);
        Mockito.when(cpu.getRegister(CPU.Register.R1)).thenReturn(100);
        instruction = new InstructionMOD(CPU.Register.R1, CPU.Register.R0);

        instruction.execute(machine);

        Mockito.verify(cpu, Mockito.times(1))
                .setRegister(CPU.Register.R1, 1);
    }
}