package pl.tomaszszewczyk.instructions;

public class InstructionIRETTest extends InstructionTest {
    private InstructionIRET instruction;

    public void setUp() {
        super.setUp();
        instruction = new InstructionIRET();
    }

    public void testSetup() {
        assertEquals(instruction.getOpcode(), (byte) 0xF4);
    }

    public void testExecute() {
        //Need internet for this
    }
}