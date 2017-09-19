package pl.tomaszszewczyk.machine.instructions;

import pl.tomaszszewczyk.machine.CPU;
import pl.tomaszszewczyk.machine.CPU.Register;
import pl.tomaszszewczyk.machine.Machine;

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
