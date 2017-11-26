package pl.tomaszszewczyk.machine.Registers;

/**
 * Control registers container class
 *
 * @author Tomasz Szewczyk
 */
public class ControlRegisters {

    /**
     * Address of first register
     */
    public static final int addressOffset = 0x100;

    /**
     * Number of all registers in this class
     */
    public static final int numOfRegisters = 16;

    private GeneralPurposeRegister registers[] = new GeneralPurposeRegister[numOfRegisters];

    /**
     * Default constructor, creates registers
     */
    public ControlRegisters() {
        for (int i = 0; i < numOfRegisters; i++) {
            registers[i] = new GeneralPurposeRegister();
        }
    }

    /**
     * Check of given address is in range of available registers
     *
     * @param address address to be checked
     * @return result
     */
    private boolean isInRange(int address) {
        return addressOffset <= address && address <= addressOffset + numOfRegisters;
    }

    /**
     * Read register at given address
     *
     * @param address address of desired register
     * @return register value
     */
    public int readRegisterByAddress(int address) {
        assert isInRange(address) : "Tried to access not existing register";
        address -= addressOffset;
        return registers[address].getValue();
    }

    /**
     * Write register at given address
     *
     * @param address address of desired register
     * @param value   new register value
     */
    public void writeRegisterByAddress(int address, int value) {
        assert isInRange(address) : "Tried to access not existing register";
        address -= addressOffset;
        registers[address].setValue(value);
    }
}
