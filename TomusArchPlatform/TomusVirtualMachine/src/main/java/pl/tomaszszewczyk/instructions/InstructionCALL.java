package pl.tomaszszewczyk.instructions;

import pl.tomaszszewczyk.CPU;
import pl.tomaszszewczyk.CPU.Register;
import pl.tomaszszewczyk.Machine;
import pl.tomaszszewczyk.RAM;

public class InstructionCALL extends Instruction {
    private int address;

    public byte getOpcode() {
        return 0x42;
    }

    public void setAddress(int aAddress) {
        address = aAddress;
    }

    public void execute(Machine parent) {
        CPU cpu = parent.getCPU();
        RAM ram = parent.getRAM();

        int pc = cpu.getRegister(Register.PC) + 3;
        push(cpu, ram, pc);

        cpu.setRegister(Register.PC, address);
    }
}
