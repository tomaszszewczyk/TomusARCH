package pl.tomaszszewczyk.machine.Registers;

import pl.tomaszszewczyk.machine.Instruction;

/**
 * Program counter register class
 *
 * @author Tomasz Szewczyk
 */
public class ProgramCounterRegister extends Register {

    /**
     * Increment register value by the size of instruction
     */
    public void increment() {
        value += Instruction.size;
    }
}
