package pl.tomaszszewczyk.machine.instructions;

import pl.tomaszszewczyk.machine.CPU;
import pl.tomaszszewczyk.machine.Machine;
import pl.tomaszszewczyk.machine.RAM;

public abstract class Instruction {
    protected void push(CPU cpu, RAM ram, int data) {
        int sp = cpu.getRegister(CPU.Register.SP) - 4;
        ram.writeWord(sp, data);
        cpu.setRegister(CPU.Register.SP, sp);
    }

    protected int pop(CPU cpu, RAM ram) {
        int sp = cpu.getRegister(CPU.Register.SP);
        int result = ram.getWord(sp);
        cpu.setRegister(CPU.Register.SP, sp + 4);
        return result;
    }

    public abstract byte getOpcode();

    public abstract void execute(Machine parent) throws InstructionExecutionException;

    public static class InstructionExecutionException extends Exception {

    }
}
