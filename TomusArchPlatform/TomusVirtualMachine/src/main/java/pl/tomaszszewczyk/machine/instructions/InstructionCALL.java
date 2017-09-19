package pl.tomaszszewczyk.machine.instructions;

import pl.tomaszszewczyk.machine.CPU;
import pl.tomaszszewczyk.machine.CPU.Register;
import pl.tomaszszewczyk.machine.Machine;
import pl.tomaszszewczyk.machine.RAM;

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
