package pl.tomaszszewczyk.instructions;

import org.mockito.Mockito;
import pl.tomaszszewczyk.CPU;

public class InstructionJMPTest extends InstructionTest {
    private InstructionJMP instruction;

    public void setUp() {
        super.setUp();
        instruction = new InstructionJMP();
    }

    public void testSetup() {
        assertEquals(instruction.getOpcode(), 0x40);
    }

    public void testExecute() {
        Mockito.when(cpu.getRegister(CPU.Register.PC)).thenReturn(0xA0);
        instruction.setAddress(0x03);

        instruction.execute(machine);

        Mockito.verify(cpu, Mockito.times(1))
                .setRegister(CPU.Register.PC, 0xA6);
    }
}