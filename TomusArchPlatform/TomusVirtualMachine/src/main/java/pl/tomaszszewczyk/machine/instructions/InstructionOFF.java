package pl.tomaszszewczyk.machine.instructions;

import pl.tomaszszewczyk.machine.Machine;

/**
 * Instruction OFF - turn machine off
 *
 * @author Tomasz Szewczyk
 */
public class InstructionOFF extends Instruction {

    public static byte getOpcode() {
        return (byte) 0xF5;
    }

    public void execute(Machine parent) {
        parent.powerOFF();
    }

}
