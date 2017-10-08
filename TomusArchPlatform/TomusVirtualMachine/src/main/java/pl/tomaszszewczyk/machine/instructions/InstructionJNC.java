package pl.tomaszszewczyk.machine.instructions;

import pl.tomaszszewczyk.machine.CPU;
import pl.tomaszszewczyk.machine.CPU.Register;
import pl.tomaszszewczyk.machine.Machine;

/**
 * InstructionJNC - jump if not carry
 * Check if flag CF is clear - if yes, perform relative jump to given address
 *
 * @author Tomasz Szewczyk
 */
public class InstructionJNC extends Instruction {
    private int imm16;

    /**
     * Instruction constructor
     *
     * @param imm16 Destination address
     */
    public InstructionJNC(int imm16) {
        this.imm16 = imm16;
    }

    public static byte getOpcode() {
        return 0x24;
    }

    public void execute(Machine parent) {
        CPU cpu = parent.getCPU();

        boolean flagCF = cpu.getFlagCF();
        int pc = cpu.getRegister(Register.PC) + 3;

        if (!flagCF) {
            pc += imm16;
            cpu.setRegister(Register.PC, pc);
        }
    }
}
