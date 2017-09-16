package pl.tomaszszewczyk.instructions;

import pl.tomaszszewczyk.CPU;
import pl.tomaszszewczyk.Machine;
import pl.tomaszszewczyk.RAM;

public class InstructionIRET extends Instruction {

    public byte getOpcode() {
        return (byte) 0xF4;
    }

    public void execute(Machine parent) {
        CPU cpu = parent.getCPU();
        RAM ram = parent.getRAM();

        for (CPU.Register register : CPU.Register.values()) {
            int value = pop(cpu, ram);
            cpu.setRegister(register, value);
        }
    }

}
