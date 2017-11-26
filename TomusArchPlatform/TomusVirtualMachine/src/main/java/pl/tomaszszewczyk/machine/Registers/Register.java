package pl.tomaszszewczyk.machine.Registers;

/**
 * Most basic representation of register
 *
 * @author Tomasz Szewczyk
 */
public abstract class Register {
    protected int value;

    /**
     * Get register value
     *
     * @return register value
     */
    public int getValue() {
        return value;
    }

    /**
     * Sets register value
     *
     * @param newValue value to be written into register
     */
    public void setValue(int newValue) {
        value = newValue;
    }
}
