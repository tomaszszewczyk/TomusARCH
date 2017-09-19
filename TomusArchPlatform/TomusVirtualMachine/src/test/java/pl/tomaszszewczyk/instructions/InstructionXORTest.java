package pl.tomaszszewczyk.instructions;

import org.mockito.Mockito;
import pl.tomaszszewczyk.CPU;

public class InstructionXORTest extends InstructionTest {
    private InstructionXOR instruction;

    public void setUp() {
        super.setUp();
        instruction = new InstructionXOR();
    }

    public void testSetup() {
        assertEquals(instruction.getOpcode(), 0x17);
    }

    public void testExecute() {
        Mockito.when(cpu.getRegister(CPU.Register.R0)).thenReturn(0xF00F);
        Mockito.when(cpu.getRegister(CPU.Register.R1)).thenReturn(0xFFFF);
        instruction.setSource(CPU.Register.R0);
        instruction.setDestination(CPU.Register.R1);

        instruction.execute(machine);

        Mockito.verify(cpu, Mockito.times(1))
                .setRegister(CPU.Register.R1, 0x0FF0);
    }
}