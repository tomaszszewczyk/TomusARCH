package pl.tomaszszewczyk.machine;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class CPUTest {
    private Machine machine;
    private Console console;
    private Port port;
    private CPU cpu;

    @Before
    public void setUp() throws Exception {
        machine = mock(Machine.class);
        console = mock(Console.class);
        port = mock(Port.class);
        Mockito.when(machine.getConsole()).thenReturn(console);

        Mockito.when(console.getControlPort()).thenReturn(port);
        Mockito.when(console.getDataAvailablePort()).thenReturn(port);
        Mockito.when(console.getReadWritePort()).thenReturn(port);

        Mockito.when(port.read()).thenReturn(0x1234);

        cpu = new CPU(machine);
    }

    @Test
    public void setFlagZF() throws Exception {
        cpu.setFlagZF();
        int flagRegister = cpu.getRegister(CPU.Register.FR);
        assertEquals(flagRegister, 0x00000001);
    }

    @Test
    public void clearFlagZF() throws Exception {
        setFlagZF();
        cpu.clearFlagZF();
        int flagRegister = cpu.getRegister(CPU.Register.FR);
        assertEquals(flagRegister, 0x00000000);
    }

    @Test
    public void getFlagZF() throws Exception {
        setFlagZF();
        boolean result = cpu.getFlagZF();
        assertTrue(result);

        clearFlagZF();
        result = cpu.getFlagZF();
        assertFalse(result);
    }

    @Test
    public void setFlagCF() throws Exception {
        cpu.setFlagCF();
        int flagRegister = cpu.getRegister(CPU.Register.FR);
        assertEquals(flagRegister, 0x00000010);
    }

    @Test
    public void clearFlagCF() throws Exception {
        setFlagCF();
        cpu.clearFlagCF();
        int flagRegister = cpu.getRegister(CPU.Register.FR);
        assertEquals(flagRegister, 0x00000000);
    }

    @Test
    public void getFlagCF() throws Exception {
        setFlagCF();
        boolean result = cpu.getFlagCF();
        assertTrue(result);

        clearFlagCF();
        result = cpu.getFlagCF();
        assertFalse(result);
    }

    @Test
    public void setAndGetSpecialRegisterCorrectAddress() throws Exception {
        cpu.setSpecialRegister(0x100, 0xCAFE);
        assertEquals(cpu.getSpecialRegister(0x100), 0xCAFE);
    }

    @Test
    public void writePort() throws Exception {
        cpu.writePort(0x20, 0xFF);
        Mockito.verify(port, Mockito.times(1)).write(0xff);
    }

    @Test
    public void readPort() throws Exception {
        int result = cpu.readPort(0x20);
        assertEquals(result, 0x1234);
    }
}