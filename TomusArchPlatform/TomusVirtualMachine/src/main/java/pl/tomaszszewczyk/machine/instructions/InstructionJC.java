package pl.tomaszszewczyk.machine.instructions;

import pl.tomaszszewczyk.machine.CPU;
import pl.tomaszszewczyk.machine.CPU.Register;
import pl.tomaszszewczyk.machine.Machine;

/**
 * InstructionJB - jump if carry
 * Check if flag CF is set - if yes, perform relative jump to given address
 *
 * @author Tomasz Szewczyk
 */
public class InstructionJC extends Instruction {
    private int imm16;

    /**
     * Instruction constructor
     *
     * @param imm16 Destination address
     */
    public InstructionJC(int imm16) {
        this.imm16 = imm16;
    }

    public static byte getOpcode() {
        return 0x23;
    }

    public void execute(Machine parent) {
        CPU cpu = parent.getCPU();

        boolean flagCF = cpu.getFlagCF();
        int pc = cpu.getRegister(Register.PC) + 3;

        if (flagCF) {
            pc += imm16;
            cpu.setRegister(Register.PC, pc);
        }
    }
}
