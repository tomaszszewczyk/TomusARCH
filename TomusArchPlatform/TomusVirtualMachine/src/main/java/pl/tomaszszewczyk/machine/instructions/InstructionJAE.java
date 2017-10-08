package pl.tomaszszewczyk.machine.instructions;

/**
 * InstructionJA - jump if bigger of equal
 * Check if flag CF is clear - if yes, perform relative jump to given address
 *
 * @author Tomasz Szewczyk
 */
public class InstructionJAE extends InstructionJNC {
    /**
     * Instruction constructor
     *
     * @param imm16 Destination address
     */
    public InstructionJAE(int imm16) {
        super(imm16);
    }
}
