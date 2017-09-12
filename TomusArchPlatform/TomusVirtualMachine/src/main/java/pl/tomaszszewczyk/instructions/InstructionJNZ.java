package pl.tomaszszewczyk.instructions;

import pl.tomaszszewczyk.CPU;
import pl.tomaszszewczyk.Machine;
import pl.tomaszszewczyk.CPU.Register;

public class InstructionJNZ extends Instruction {
    private int address;

    public InstructionJNZ() {
    }

    public byte getOpcode() {
        return 0x22;
    }

    public void setRelativeAddress(int aAddress) {
        address = aAddress;
    }

    public void execute(Machine parent) {
        CPU cpu = parent.getCPU();

        boolean flagZF = cpu.getFlagZF();
        int pc = cpu.getRegister(Register.PC) + 3;

        if(!flagZF) {
            pc += address;
            cpu.setRegister(Register.PC, pc);
        }
    }
}
