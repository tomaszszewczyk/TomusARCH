package pl.tomaszszewczyk.instructions;

import pl.tomaszszewczyk.CPU;
import pl.tomaszszewczyk.CPU.Register;
import pl.tomaszszewczyk.Machine;
import pl.tomaszszewczyk.RAM;

public class InstructionCALLR extends Instruction {
    private Register address_register;

    public byte getOpcode() {
        return 0x43;
    }

    public void setAddressRegister(Register addressRegister) {
        address_register = addressRegister;
    }

    public void execute(Machine parent) {
        CPU cpu = parent.getCPU();
        RAM ram = parent.getRAM();

        int pc = cpu.getRegister(Register.PC) + 3;
        push(cpu, ram, pc);

        int address = cpu.getRegister(address_register);
        cpu.setRegister(Register.PC, address);
    }
}
