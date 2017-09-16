package pl.tomaszszewczyk.instructions;

import pl.tomaszszewczyk.CPU;
import pl.tomaszszewczyk.CPU.Register;
import pl.tomaszszewczyk.Machine;

public class InstructionJA extends Instruction {
    private int address;

    public byte getOpcode() {
        return 0x26;
    }

    public void setRelativeAddress(int aAddress) {
        address = aAddress;
    }

    public void execute(Machine parent) {
        CPU cpu = parent.getCPU();

        boolean flagCF = cpu.getFlagCF();
        boolean flagZF = cpu.getFlagZF();
        int pc = cpu.getRegister(Register.PC) + 3;

        if (flagCF && (!flagZF)) {
            pc += address;
            cpu.setRegister(Register.PC, pc);
        }
    }
}
