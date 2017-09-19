package pl.tomaszszewczyk.machine.instructions;

import org.mockito.Mockito;
import pl.tomaszszewczyk.machine.CPU;

public class InstructionCALLRTest extends InstructionTest {
    private InstructionCALLR instruction;

    public void setUp() {
        super.setUp();
        instruction = new InstructionCALLR();
    }

    public void testSetup() {
        assertEquals(instruction.getOpcode(), 0x43);
    }

    public void testExecute() {
        Mockito.when(cpu.getRegister(CPU.Register.PC)).thenReturn(0xAA);
        Mockito.when(cpu.getRegister(CPU.Register.SP)).thenReturn(0xBB);
        Mockito.when(cpu.getRegister(CPU.Register.R9)).thenReturn(0x11);
        instruction.setAddressRegister(CPU.Register.R9);

        instruction.execute(machine);

        Mockito.verify(cpu, Mockito.times(1))
                .setRegister(CPU.Register.PC, 0x11);
        Mockito.verify(ram, Mockito.times(1))
                .writeWord(0xB7, 0xAD);
    }
}