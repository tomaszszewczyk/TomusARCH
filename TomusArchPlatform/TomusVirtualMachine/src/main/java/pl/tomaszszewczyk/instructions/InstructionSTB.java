package pl.tomaszszewczyk.instructions;

import pl.tomaszszewczyk.CPU;
import pl.tomaszszewczyk.Machine;
import pl.tomaszszewczyk.CPU.Register;
import pl.tomaszszewczyk.RAM;

public class InstructionSTB extends Instruction {
    private Register src;
    private Register dst;

    public InstructionSTB() {
    }

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
        ram.writeByte(dst_address, (byte) data);
    }
}
