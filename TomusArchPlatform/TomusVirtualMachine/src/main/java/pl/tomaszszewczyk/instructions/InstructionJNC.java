package pl.tomaszszewczyk.instructions;

import pl.tomaszszewczyk.CPU;
import pl.tomaszszewczyk.CPU.Register;
import pl.tomaszszewczyk.Machine;

public class InstructionJNC extends Instruction {
    private int address;

    public byte getOpcode() {
        return 0x24;
    }

    public void setRelativeAddress(int aAddress) {
        address = aAddress;
    }

    public void execute(Machine parent) {
        CPU cpu = parent.getCPU();

        boolean flagCF = cpu.getFlagCF();
        int pc = cpu.getRegister(Register.PC) + 3;

        if (!flagCF) {
            pc += address;
            cpu.setRegister(Register.PC, pc);
        }
    }
}
