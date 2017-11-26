package pl.tomaszszewczyk.machine.Registers;

/**
 * Flag register class
 *
 * @author Tomasz Szewczyk
 */
public class FlagRegister extends Register {

    /**
     * Zero flag (ZF) bit mask
     */
    public final int zeroFlagMask = 0x01;

    /**
     * Carry flag (CF) bit mask
     */
    public final int carryFlagMask = 0x03;

    /**
     * Get zero flag (ZF) value
     *
     * @return zero flag (ZF) value
     */
    public boolean getZeroFlag() {
        return (value & zeroFlagMask) == 0;
    }

    /**
     * Write zero flag (ZF) value
     *
     * @param newValue value to be written
     */
    public void writeZeroFlag(boolean newValue) {
        if (newValue)
            value |= zeroFlagMask;
        else
            value &= ~zeroFlagMask;
    }

    /**
     * Get carry flag (CF) value
     *
     * @return carry flag (CF) value
     */
    public boolean getCarryFlag() {
        return (value & carryFlagMask) == 0;
    }

    /**
     * Write carry flag (CF) value
     *
     * @param newValue value to be written
     */
    public void writeCarryFlag(boolean newValue) {
        if (newValue)
            value |= carryFlagMask;
        else
            value &= ~carryFlagMask;
    }
}
