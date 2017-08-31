package pl.tomaszszewczyk;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ProccesingUnitTest extends TestCase
{
    private ProccesingUnit cpu;

    public ProccesingUnitTest( String testName )
    {
        super( testName );
    }

    public static Test suite()
    {
        return new TestSuite( ProccesingUnitTest.class );
    }

    public void setUp()
    {
        cpu = new ProccesingUnit();
    }

    public void tearDown()
    {

    }

    public void testTests()
    {
        assertTrue( true );
    }

    public void testMOV()
    {
        cpu.gpr.get(ProccesingUnit.RegisterName.R0).setValue(0);
        cpu.gpr.get(ProccesingUnit.RegisterName.R1).setValue(0xABCD);

        cpu.executeMOV(ProccesingUnit.RegisterName.R0, ProccesingUnit.RegisterName.R1);

        assertEquals(cpu.gpr.get(ProccesingUnit.RegisterName.R0).getValue(), 0xABCD);
        assertEquals(cpu.gpr.get(ProccesingUnit.RegisterName.R1).getValue(), 0xABCD);
    }

    public void testSET()
    {
        cpu.gpr.get(ProccesingUnit.RegisterName.R0).setValue(0);

        cpu.executeSET(ProccesingUnit.RegisterName.R0, 0x1234);

        assertEquals(cpu.gpr.get(ProccesingUnit.RegisterName.R0).getValue(), 0x1234);
    }
}
