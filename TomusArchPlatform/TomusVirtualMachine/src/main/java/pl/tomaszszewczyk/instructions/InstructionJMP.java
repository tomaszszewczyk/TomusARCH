package pl.tomaszszewczyk.instructions;

import pl.tomaszszewczyk.CPU;
import pl.tomaszszewczyk.CPU.Register;
import pl.tomaszszewczyk.Machine;

public class InstructionJMP extends Instruction {
    private int address;

    public byte getOpcode() {
        return 0x40;
    }

    public void setAddress(int aAddress) {
        address = aAddress;
    }

    public void execute(Machine parent) {
        CPU cpu = parent.getCPU();
        int pc = cpu.getRegister(Register.PC) + 3 + address;
        cpu.setRegister(Register.PC, pc);
    }
}
