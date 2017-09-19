package pl.tomaszszewczyk.machine.instructions;

import pl.tomaszszewczyk.machine.CPU;
import pl.tomaszszewczyk.machine.CPU.Register;
import pl.tomaszszewczyk.machine.Machine;

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
