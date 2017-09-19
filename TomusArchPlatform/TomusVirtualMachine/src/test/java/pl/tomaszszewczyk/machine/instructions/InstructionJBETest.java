package pl.tomaszszewczyk.machine.instructions;

import org.mockito.Mockito;
import pl.tomaszszewczyk.machine.CPU;

public class InstructionJBETest extends InstructionTest {
    private InstructionJBE instruction;

    public void setUp() {
        super.setUp();
        instruction = new InstructionJBE();
    }

    public void testSetup() {
        assertEquals(instruction.getOpcode(), 0x25);
    }

    public void testExecuteBothSet() {
        Mockito.when(cpu.getRegister(CPU.Register.PC)).thenReturn(0x0F);
        Mockito.when(cpu.getFlagCF()).thenReturn(true);
        Mockito.when(cpu.getFlagZF()).thenReturn(true);
        instruction.setRelativeAddress(0xED);

        instruction.execute(machine);

        Mockito.verify(cpu, Mockito.times(1))
                .setRegister(CPU.Register.PC, 0xFF);
    }

    public void testExecuteCFSet() {
        Mockito.when(cpu.getRegister(CPU.Register.PC)).thenReturn(0x0F);
        Mockito.when(cpu.getFlagCF()).thenReturn(true);
        Mockito.when(cpu.getFlagZF()).thenReturn(false);
        instruction.setRelativeAddress(0xED);

        instruction.execute(machine);

        Mockito.verify(cpu, Mockito.times(1))
                .setRegister(CPU.Register.PC, 0xFF);
    }

    public void testExecuteZFSet() {
        Mockito.when(cpu.getRegister(CPU.Register.PC)).thenReturn(0x0F);
        Mockito.when(cpu.getFlagCF()).thenReturn(false);
        Mockito.when(cpu.getFlagZF()).thenReturn(true);
        instruction.setRelativeAddress(0xED);

        instruction.execute(machine);

        Mockito.verify(cpu, Mockito.times(1))
                .setRegister(CPU.Register.PC, 0xFF);
    }

    public void testExecuteBothClear() {
        Mockito.when(cpu.getRegister(CPU.Register.PC)).thenReturn(0x0F);
        Mockito.when(cpu.getFlagCF()).thenReturn(false);
        Mockito.when(cpu.getFlagZF()).thenReturn(false);
        instruction.setRelativeAddress(0xED);

        instruction.execute(machine);

        Mockito.verify(cpu, Mockito.times(0))
                .setRegister(CPU.Register.PC, 0x0F);
    }
}