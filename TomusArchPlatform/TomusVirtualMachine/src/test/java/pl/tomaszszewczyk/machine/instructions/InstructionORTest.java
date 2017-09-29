package pl.tomaszszewczyk.machine.instructions;

import org.mockito.Mockito;
import pl.tomaszszewczyk.machine.CPU;

public class InstructionORTest extends InstructionTest {
    private InstructionOR instruction;

    public void setUp() {
        super.setUp();
    }

    public void testSetup() {
        assertEquals(InstructionOR.getOpcode(), 0x15);
    }

    public void testExecute() {
        Mockito.when(cpu.getRegister(CPU.Register.R0)).thenReturn(0xF00F);
        Mockito.when(cpu.getRegister(CPU.Register.R1)).thenReturn(0x0FF0);
        instruction = new InstructionOR(CPU.Register.R1, CPU.Register.R0);

        instruction.execute(machine);

        Mockito.verify(cpu, Mockito.times(1))
                .setRegister(CPU.Register.R1, 0xFFFF);
    }
}