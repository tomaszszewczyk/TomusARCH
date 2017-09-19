package pl.tomaszszewczyk.instructions;

import pl.tomaszszewczyk.CPU;
import pl.tomaszszewczyk.CPU.Register;
import pl.tomaszszewczyk.Machine;

public class InstructionJMPR extends Instruction {
    private Register address_register;

    public byte getOpcode() {
        return 0x41;
    }

    public void setAddressRegister(Register aAddressRegister) {
        address_register = aAddressRegister;
    }

    public void execute(Machine parent) {
        CPU cpu = parent.getCPU();
        int address = cpu.getRegister(address_register);
        int pc = cpu.getRegister(Register.PC) + 3 + address;
        cpu.setRegister(Register.PC, pc);
    }
}
