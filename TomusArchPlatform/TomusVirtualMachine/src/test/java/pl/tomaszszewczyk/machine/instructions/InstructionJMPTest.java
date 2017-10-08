package pl.tomaszszewczyk.machine.instructions;

import org.mockito.Mockito;
import pl.tomaszszewczyk.machine.CPU;

public class InstructionJMPTest extends InstructionTest {
    private InstructionJMP instruction;

    public void setUp() {
        super.setUp();
    }

    public void testSetup() {
        assertEquals(instruction.getOpcode(), 0x40);
    }

    public void testExecute() {
        Mockito.when(cpu.getRegister(CPU.Register.PC)).thenReturn(0xA0);
        instruction = new InstructionJMP(0x03);

        instruction.execute(machine);

        Mockito.verify(cpu, Mockito.times(1))
                .setRegister(CPU.Register.PC, 0xA6);
    }
}