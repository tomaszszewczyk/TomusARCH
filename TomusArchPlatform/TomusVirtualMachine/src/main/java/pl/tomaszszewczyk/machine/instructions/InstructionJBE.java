package pl.tomaszszewczyk.machine.instructions;

import pl.tomaszszewczyk.machine.CPU;
import pl.tomaszszewczyk.machine.CPU.Register;
import pl.tomaszszewczyk.machine.Machine;

/**
 * InstructionJBE - jump if below or equal
 * Check if flag CF or ZF (or both) is set - if yes, perform relative jump to given address
 *
 * @author Tomasz Szewczyk
 */
public class InstructionJBE extends Instruction {
    private int imm16;

    /**
     * Instruction constructor
     *
     * @param imm16 Destination address
     */
    public InstructionJBE(int imm16) {
        this.imm16 = imm16;
    }

    public static byte getOpcode() {
        return 0x25;
    }

    public void execute(Machine parent) {
        CPU cpu = parent.getCPU();

        boolean flagCF = cpu.getFlagCF();
        boolean flagZF = cpu.getFlagZF();
        int pc = cpu.getRegister(Register.PC) + 3;

        if (flagCF || flagZF) {
            pc += imm16;
            cpu.setRegister(Register.PC, pc);
        }
    }
}
