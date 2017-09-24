package pl.tomaszszewczyk.machine;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class CPUTest {
    private CPU cpu;

    @Before
    public void setUp() throws Exception {
        cpu = new CPU(mock(Machine.class));
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

    }

    @Test
    public void readPort() throws Exception {
    }

    @Test
    public void setRegister() throws Exception {
    }

    @Test
    public void getRegister() throws Exception {
    }

}