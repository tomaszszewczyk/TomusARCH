package pl.tomaszszewczyk.machine.instructions;

import pl.tomaszszewczyk.machine.CPU;
import pl.tomaszszewczyk.machine.CPU.Register;
import pl.tomaszszewczyk.machine.Machine;
import pl.tomaszszewczyk.machine.RAM;

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
