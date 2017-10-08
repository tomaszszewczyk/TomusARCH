package pl.tomaszszewczyk.machine.instructions;

import pl.tomaszszewczyk.machine.CPU;
import pl.tomaszszewczyk.machine.CPU.Register;
import pl.tomaszszewczyk.machine.Machine;

/**
 * InstructionJMP - jump
 * Perform relative jump to given address (loaded from register)
 *
 * @author Tomasz Szewczyk
 */
public class InstructionJMPR extends Instruction {
    private Register src;

    /**
     * Instruction constructor
     *
     * @param aSrc Destination address
     */
    public InstructionJMPR(Register aSrc) {
        src = aSrc;
    }

    public static byte getOpcode() {
        return 0x41;
    }

    public void setAddressRegister(Register aAddressRegister) {
        src = aAddressRegister;
    }

    public void execute(Machine parent) {
        CPU cpu = parent.getCPU();
        int address = cpu.getRegister(src);
        int pc = cpu.getRegister(Register.PC) + 3 + address;
        cpu.setRegister(Register.PC, pc);
    }
}
