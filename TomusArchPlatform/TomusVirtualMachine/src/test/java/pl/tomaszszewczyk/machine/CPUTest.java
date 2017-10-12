package pl.tomaszszewczyk.machine;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CPUTest {
    private Machine machine;
    private Console console;
    private Timer timer;
    private Port port;
    private Console.ReadWritePort consoleReadWritePort;
    private Console.DataAvailablePort consoleDataAvailablePort;
    private Console.ControlPort consoleControlPort;
    private CPU cpu;

    @Before
    public void setUp() throws Exception {
        machine = mock(Machine.class);
        console = mock(Console.class);
        timer = mock(Timer.class);
        port = mock(Port.class);
        consoleReadWritePort = mock(Console.ReadWritePort.class);
        consoleDataAvailablePort = mock(Console.DataAvailablePort.class);
        consoleControlPort = mock(Console.ControlPort.class);

        when(machine.getConsole())
                .thenReturn(console);
        when(machine.getTimer())
                .thenReturn(timer);

        when(console.getControlPort())
                .thenReturn(consoleControlPort);
        when(console.getDataAvailablePort())
                .thenReturn(consoleDataAvailablePort);
        when(console.getReadWritePort())
                .thenReturn(consoleReadWritePort);
        when(timer.getControlPort())
                .thenReturn(port);
        when(timer.getCounterPort())
                .thenReturn(port);

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
        verify(consoleReadWritePort, times(1)).write(0xff);
    }

    @Test
    public void readPort() throws Exception {
        when(consoleReadWritePort.read())
                .thenReturn(0x1234);

        int result = cpu.readPort(0x20);
        assertEquals(result, 0x1234);
    }
}