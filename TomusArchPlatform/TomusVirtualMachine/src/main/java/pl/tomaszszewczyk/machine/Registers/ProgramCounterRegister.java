package pl.tomaszszewczyk.machine.Registers;

/**
 * Program counter register class
 *
 * @author Tomasz Szewczyk
 */
public class ProgramCounterRegister extends Register {

    /**
     * Increment register value
     */
    public void increment() {
        value++;
    }
}
