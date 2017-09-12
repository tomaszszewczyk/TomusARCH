package pl.tomaszszewczyk.instructions;

import pl.tomaszszewczyk.CPU;
import pl.tomaszszewczyk.Machine;
import pl.tomaszszewczyk.CPU.Register;

public class InstructionJBE extends Instruction {
    private int address;

    public InstructionJBE() {
    }

    public byte getOpcode() {
        return 0x25;
    }

    public void setRelativeAddress(int aAddress) {
        address = aAddress;
    }

    public void execute(Machine parent) {
        CPU cpu = parent.getCPU();

        boolean flagCF = cpu.getFlagCF();
        boolean flagZF = cpu.getFlagZF();
        int pc = cpu.getRegister(Register.PC) + 3;

        if(flagCF || flagZF) {
            pc += address;
            cpu.setRegister(Register.PC, pc);
        }
    }
}
