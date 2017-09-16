package pl.tomaszszewczyk.instructions;

import org.mockito.Mockito;
import pl.tomaszszewczyk.CPU;

public class InstructionANDTest extends InstructionTest {
    private InstructionAND instruction;
    public void setUp() {
        super.setUp();
        instruction = new InstructionAND();
    }

    public void testSetup() {
        assertEquals(instruction.getOpcode(), 0x16);
    }

    public void testExecute() {
        Mockito.when(cpu.getRegister(CPU.Register.R0)).thenReturn(0xFFF0);
        Mockito.when(cpu.getRegister(CPU.Register.R1)).thenReturn(0x0FF0);
        instruction.setSource(CPU.Register.R0);
        instruction.setDestination(CPU.Register.R1);

        instruction.execute(machine);

        Mockito.verify(cpu, Mockito.times(1))
                .setRegister(CPU.Register.R1, 0x0FF0);
    }
}