package pl.tomaszszewczyk.instructions;

import pl.tomaszszewczyk.CPU;
import pl.tomaszszewczyk.CPU.Register;
import pl.tomaszszewczyk.Machine;
import pl.tomaszszewczyk.RAM;

public class InstructionST extends Instruction {
    private Register src;
    private Register dst;

    public byte getOpcode() {
        return 0x04;
    }

    public void setSource(Register asrc) {
        src = asrc;
    }

    public void setDestination(Register adst) {
        dst = adst;
    }

    public void execute(Machine parent) {
        CPU cpu = parent.getCPU();
        RAM ram = parent.getRAM();

        int dst_address = cpu.getRegister(dst);
        int data = cpu.getRegister(src);
        ram.writeWord(dst_address, data);
    }
}
