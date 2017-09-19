package pl.tomaszszewczyk.instructions;

import pl.tomaszszewczyk.CPU;
import pl.tomaszszewczyk.CPU.Register;
import pl.tomaszszewczyk.Machine;
import pl.tomaszszewczyk.RAM;

public class InstructionPOP extends Instruction {
    private Register destination;

    public byte getOpcode() {
        return 0x31;
    }

    public void setDestination(Register aDestination) {
        destination = aDestination;
    }

    public void execute(Machine parent) {
        CPU cpu = parent.getCPU();
        RAM ram = parent.getRAM();

        int sp = cpu.getRegister(Register.SP);
        int value = ram.getWord(sp);
        sp += 4;
        cpu.setRegister(destination, value);
        cpu.setRegister(Register.SP, sp);
    }
}
