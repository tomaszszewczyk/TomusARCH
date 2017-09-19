package pl.tomaszszewczyk.machine.instructions;

import pl.tomaszszewczyk.machine.CPU;
import pl.tomaszszewczyk.machine.CPU.Register;
import pl.tomaszszewczyk.machine.Machine;
import pl.tomaszszewczyk.machine.RAM;

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
