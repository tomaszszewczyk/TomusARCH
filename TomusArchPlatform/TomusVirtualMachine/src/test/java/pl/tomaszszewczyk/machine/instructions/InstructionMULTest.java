package pl.tomaszszewczyk.machine.instructions;

import org.mockito.Mockito;
import pl.tomaszszewczyk.machine.CPU;

public class InstructionMULTest extends InstructionTest {
    private InstructionMUL instruction;

    public void setUp() {
        super.setUp();
    }

    public void testSetup() {
        assertEquals(InstructionMUL.getOpcode(), 0x12);
    }

    public void testExecute() {
        Mockito.when(cpu.getRegister(CPU.Register.R0)).thenReturn(10);
        Mockito.when(cpu.getRegister(CPU.Register.R1)).thenReturn(20);
        instruction = new InstructionMUL(CPU.Register.R1, CPU.Register.R0);

        instruction.execute(machine);

        Mockito.verify(cpu, Mockito.times(1))
                .setRegister(CPU.Register.R1, 200);
    }
}