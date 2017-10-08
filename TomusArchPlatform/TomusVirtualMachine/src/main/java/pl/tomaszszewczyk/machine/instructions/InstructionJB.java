package pl.tomaszszewczyk.machine.instructions;

/**
 * InstructionJB - jump if below
 * Check if flag CF is set - if yes, perform relative jump to given address
 *
 * @author Tomasz Szewczyk
 */
public class InstructionJB extends InstructionJC {
    /**
     * Instruction constructor
     *
     * @param imm16 Destination address
     */
    public InstructionJB(int imm16) {
        super(imm16);
    }
}
