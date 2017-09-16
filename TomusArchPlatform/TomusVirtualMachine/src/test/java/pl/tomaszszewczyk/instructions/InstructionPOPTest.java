package pl.tomaszszewczyk.instructions;

import org.mockito.Mockito;
import pl.tomaszszewczyk.CPU;

public class InstructionPOPTest extends InstructionTest {
    private InstructionPOP instruction;

    public void setUp() {
        super.setUp();
        instruction = new InstructionPOP();
    }

    public void testSetup() {
        assertEquals(instruction.getOpcode(), 0x31);
    }

    public void testExecuteBothSet() {
        Mockito.when(cpu.getRegister(CPU.Register.SP)).thenReturn(0x0F);
        Mockito.when(ram.getWord(0x0F)).thenReturn(0xAA);
        instruction.setDestination(CPU.Register.R0);

        instruction.execute(machine);

        Mockito.verify(cpu, Mockito.times(1))
                .setRegister(CPU.Register.SP, 0x13);
        Mockito.verify(cpu, Mockito.times(1))
                .setRegister(CPU.Register.R0, 0xAA);
    }
}