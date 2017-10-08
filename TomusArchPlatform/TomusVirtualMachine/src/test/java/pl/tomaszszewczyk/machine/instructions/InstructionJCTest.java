package pl.tomaszszewczyk.machine.instructions;

import org.mockito.Mockito;
import pl.tomaszszewczyk.machine.CPU;

public class InstructionJCTest extends InstructionTest {
    private InstructionJC instruction;

    public void setUp() {
        super.setUp();
    }

    public void testSetup() {
        assertEquals(instruction.getOpcode(), 0x23);
    }

    public void testExecuteCFSet() {
        Mockito.when(cpu.getRegister(CPU.Register.PC)).thenReturn(0x0F);
        Mockito.when(cpu.getFlagCF()).thenReturn(true);
        instruction = new InstructionJC(0xED);

        instruction.execute(machine);

        Mockito.verify(cpu, Mockito.times(1))
                .setRegister(CPU.Register.PC, 0xFF);
    }

    public void testExecuteCFClear() {
        Mockito.when(cpu.getRegister(CPU.Register.PC)).thenReturn(0x0F);
        Mockito.when(cpu.getFlagCF()).thenReturn(false);
        instruction = new InstructionJC(0xED);

        instruction.execute(machine);

        Mockito.verify(cpu, Mockito.times(0))
                .setRegister(CPU.Register.PC, 0x0F);
    }
}