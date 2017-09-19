package pl.tomaszszewczyk.instructions;

import pl.tomaszszewczyk.CPU;
import pl.tomaszszewczyk.CPU.Register;
import pl.tomaszszewczyk.Machine;
import pl.tomaszszewczyk.RAM;

public class InstructionRET extends Instruction {

    public byte getOpcode() {
        return 0x44;
    }

    public void execute(Machine parent) {
        CPU cpu = parent.getCPU();
        RAM ram = parent.getRAM();

        int return_address = pop(cpu, ram);

        cpu.setRegister(Register.PC, return_address);
    }
}
