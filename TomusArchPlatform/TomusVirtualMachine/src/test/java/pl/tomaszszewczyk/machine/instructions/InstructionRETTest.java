package pl.tomaszszewczyk.machine.instructions;

import org.mockito.Mockito;
import pl.tomaszszewczyk.machine.CPU;

public class InstructionRETTest extends InstructionTest {
    private InstructionRET instruction;

    public void setUp() {
        super.setUp();
        instruction = new InstructionRET();
    }

    public void testSetup() {
        assertEquals(instruction.getOpcode(), 0x44);
    }

    public void testExecute() {
        Mockito.when(cpu.getRegister(CPU.Register.SP)).thenReturn(0xBB);
        Mockito.when(ram.getWord(0xBB)).thenReturn(0xAA);

        instruction.execute(machine);

        Mockito.verify(cpu, Mockito.times(1))
                .setRegister(CPU.Register.PC, 0xAA);

        Mockito.verify(cpu, Mockito.times(1))
                .setRegister(CPU.Register.SP, 0xBF);
    }
}