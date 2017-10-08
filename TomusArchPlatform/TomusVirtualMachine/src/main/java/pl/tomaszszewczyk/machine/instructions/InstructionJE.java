package pl.tomaszszewczyk.machine.instructions;

/**
 * InstructionJE - jump if equal
 * Check if flag ZF is set - if yes, perform relative jump to given address
 *
 * @author Tomasz Szewczyk
 */
public class InstructionJE extends InstructionJZ {

    public InstructionJE(int imm16) {
        super(imm16);
    }
}
