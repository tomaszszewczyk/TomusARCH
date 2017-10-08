package pl.tomaszszewczyk.machine.instructions;

import org.mockito.Mockito;
import pl.tomaszszewczyk.machine.CPU;

public class InstructionJMPRTest extends InstructionTest {
    private InstructionJMPR instruction;

    public void setUp() {
        super.setUp();
    }

    public void testSetup() {
        assertEquals(instruction.getOpcode(), 0x41);
    }

    public void testExecute() {
        Mockito.when(cpu.getRegister(CPU.Register.PC)).thenReturn(0xA0);
        Mockito.when(cpu.getRegister(CPU.Register.R5)).thenReturn(0x13);
        instruction = new InstructionJMPR(CPU.Register.R5);

        instruction.execute(machine);

        Mockito.verify(cpu, Mockito.times(1))
                .setRegister(CPU.Register.PC, 0xB6);
    }
}