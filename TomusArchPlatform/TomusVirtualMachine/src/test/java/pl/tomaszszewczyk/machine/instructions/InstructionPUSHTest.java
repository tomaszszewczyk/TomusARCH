package pl.tomaszszewczyk.machine.instructions;

import org.mockito.Mockito;
import pl.tomaszszewczyk.machine.CPU;

public class InstructionPUSHTest extends InstructionTest {
    private InstructionPUSH instruction;

    public void setUp() {
        super.setUp();
        instruction = new InstructionPUSH();
    }

    public void testSetup() {
        assertEquals(instruction.getOpcode(), 0x30);
    }

    public void testExecuteBothSet() {
        Mockito.when(cpu.getRegister(CPU.Register.SP)).thenReturn(0x0F);
        Mockito.when(cpu.getRegister(CPU.Register.R0)).thenReturn(0xAB);
        instruction.setSource(CPU.Register.R0);

        instruction.execute(machine);

        Mockito.verify(cpu, Mockito.times(1))
                .setRegister(CPU.Register.SP, 0x0B);
        Mockito.verify(ram, Mockito.times(1))
                .writeWord(0x0B, 0xAB);
    }
}