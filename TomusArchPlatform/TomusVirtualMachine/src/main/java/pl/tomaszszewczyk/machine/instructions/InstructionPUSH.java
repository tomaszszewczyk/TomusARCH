package pl.tomaszszewczyk.machine.instructions;

import pl.tomaszszewczyk.machine.CPU;
import pl.tomaszszewczyk.machine.CPU.Register;
import pl.tomaszszewczyk.machine.Machine;
import pl.tomaszszewczyk.machine.RAM;

public class InstructionPUSH extends Instruction {
    private Register source;

    public byte getOpcode() {
        return 0x30;
    }

    public void setSource(Register aSource) {
        source = aSource;
    }

    public void execute(Machine parent) {
        CPU cpu = parent.getCPU();
        RAM ram = parent.getRAM();

        int sp = cpu.getRegister(Register.SP);
        sp -= 4;
        cpu.setRegister(Register.SP, sp);

        int value = cpu.getRegister(source);
        ram.writeWord(sp, value);
    }
}
