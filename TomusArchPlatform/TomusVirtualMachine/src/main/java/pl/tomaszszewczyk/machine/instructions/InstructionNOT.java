package pl.tomaszszewczyk.machine.instructions;

import pl.tomaszszewczyk.machine.CPU;
import pl.tomaszszewczyk.machine.CPU.Register;
import pl.tomaszszewczyk.machine.Machine;

public class InstructionNOT extends Instruction {
    private Register src;
    private Register dst;

    public byte getOpcode() {
        return 0x18;
    }

    public void setDestination(Register adst) {
        dst = adst;
    }

    public void execute(Machine parent) {
        CPU cpu = parent.getCPU();

        int dst_value = cpu.getRegister(dst);

        int result = ~dst_value;

        cpu.setRegister(dst, result);
    }
}
