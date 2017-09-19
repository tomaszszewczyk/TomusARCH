package pl.tomaszszewczyk.machine.instructions;

import pl.tomaszszewczyk.machine.CPU;
import pl.tomaszszewczyk.machine.CPU.Register;
import pl.tomaszszewczyk.machine.Machine;
import pl.tomaszszewczyk.machine.RAM;

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
