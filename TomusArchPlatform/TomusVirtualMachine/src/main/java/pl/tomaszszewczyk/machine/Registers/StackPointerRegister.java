package pl.tomaszszewczyk.machine.Registers;

/**
 * Stack pointer register class
 *
 * @author Tomasz Szewczyk
 */
public class StackPointerRegister extends Register {

    /**
     * Incremet register value
     */
    public void increment() {
        value ++;
    }

    /**
     * Decrement register value
     */
    public void decrement() {
        value --;
    }
}
