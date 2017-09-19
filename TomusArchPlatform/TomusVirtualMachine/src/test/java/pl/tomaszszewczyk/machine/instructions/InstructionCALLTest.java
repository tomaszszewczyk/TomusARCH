package pl.tomaszszewczyk.machine.instructions;

import org.mockito.Mockito;
import pl.tomaszszewczyk.machine.CPU;

public class InstructionCALLTest extends InstructionTest {
    private InstructionCALL instruction;

    public void setUp() {
        super.setUp();
        instruction = new InstructionCALL();
    }

    public void testSetup() {
        assertEquals(instruction.getOpcode(), 0x42);
    }

    public void testExecute() {
        Mockito.when(cpu.getRegister(CPU.Register.PC)).thenReturn(0xAA);
        Mockito.when(cpu.getRegister(CPU.Register.SP)).thenReturn(0xBB);
        instruction.setAddress(0x11);

        instruction.execute(machine);

        Mockito.verify(cpu, Mockito.times(1))
                .setRegister(CPU.Register.PC, 0x11);
        Mockito.verify(ram, Mockito.times(1))
                .writeWord(0xB7, 0xAD);
    }
}