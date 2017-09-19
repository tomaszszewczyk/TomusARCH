package pl.tomaszszewczyk.machine.instructions;

import pl.tomaszszewczyk.machine.CPU;
import pl.tomaszszewczyk.machine.CPU.Register;
import pl.tomaszszewczyk.machine.Machine;

public class InstructionCMP extends Instruction {
    private Register src;
    private Register dst;

    public byte getOpcode() {
        return 0x20;
    }

    public void setSource(Register asrc) {
        src = asrc;
    }

    public void setDestination(Register adst) {
        dst = adst;
    }

    public void execute(Machine parent) {
        CPU cpu = parent.getCPU();

        int dst_value = cpu.getRegister(dst);
        int src_value = cpu.getRegister(src);

        int result = dst_value - src_value;

        cpu.clearFlagZF();
        cpu.clearFlagCF();

        if (result == 0)
            cpu.setFlagZF();
        else if (result < 0)
            cpu.setFlagCF();
    }
}
