package pl.tomaszszewczyk.machine.instructions;

/**
 * InstructionJNE - jump if not equal
 * Check if flag ZF is clear - if yes, perform relative jump to given address
 *
 * @author Tomasz Szewczyk
 */
public class InstructionJNE extends InstructionJNZ {
    /**
     * Instruction constructor
     *
     * @param imm16 Destination address
     */
    public InstructionJNE(int imm16) {
        super(imm16);
    }
}
