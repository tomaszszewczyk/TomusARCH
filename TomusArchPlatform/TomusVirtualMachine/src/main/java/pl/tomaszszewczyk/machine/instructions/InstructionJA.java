package pl.tomaszszewczyk.machine.instructions;

import pl.tomaszszewczyk.machine.CPU;
import pl.tomaszszewczyk.machine.CPU.Register;
import pl.tomaszszewczyk.machine.Machine;

/**
 * InstructionJA - jump if above
 * Check if flag CF is set and ZF is clear simultaneously - if yes, perform relative jump to
 * given address
 *
 * @author Tomasz Szewczyk
 */
public class InstructionJA extends Instruction {
    private int imm16;

    /**
     * Instruction constructor
     *
     * @param imm16 Destination address
     */
    public InstructionJA(int imm16) {
        this.imm16 = imm16;
    }

    public static byte getOpcode() {
        return 0x26;
    }

    public void setImm16(int aAddress) {
        imm16 = aAddress;
    }

    public void execute(Machine parent) {
        CPU cpu = parent.getCPU();

        boolean flagCF = cpu.getFlagCF();
        boolean flagZF = cpu.getFlagZF();
        int pc = cpu.getRegister(Register.PC) + 3;

        if (flagCF && (!flagZF)) {
            pc += imm16;
            cpu.setRegister(Register.PC, pc);
        }
    }
}
