package pl.tomaszszewczyk.machine.instructions;

import org.mockito.Mockito;
import pl.tomaszszewczyk.machine.CPU;

public class InstructionANDTest extends InstructionTest {
    private InstructionAND instruction;

    public void setUp() {
        super.setUp();
    }

    public void testSetup() {
        assertEquals(InstructionAND.getOpcode(), 0x16);
    }

    public void testExecute() {
        Mockito.when(cpu.getRegister(CPU.Register.R0)).thenReturn(0xFFF0);
        Mockito.when(cpu.getRegister(CPU.Register.R1)).thenReturn(0x0FF0);
        instruction = new InstructionAND(CPU.Register.R1, CPU.Register.R0);

        instruction.execute(machine);

        Mockito.verify(cpu, Mockito.times(1))
                .setRegister(CPU.Register.R1, 0x0FF0);
    }
}