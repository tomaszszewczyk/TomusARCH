package pl.tomaszszewczyk.machine;

import java.util.HashMap;
import java.util.Map;

import static pl.tomaszszewczyk.machine.CPU.Register.FR;

/**
 * Representation of CPU with registers and ports.
 */
public class CPU {
    private Map<Register, Integer> registerMap;
    private ControlRegisters controlRegisters;
    private Map<Integer, Port> ports;

    static private class ControlRegisters {
        private int[] registers = new int[17];

        public void set(int address, int value) throws WrongRegisterAddressException {
            if (validateAddress(address))
                registers[translateAddress(address)] = value;
            else
                throw new WrongRegisterAddressException();
        }

        public int get(int address) throws WrongRegisterAddressException {
            if (validateAddress(address))
                return registers[translateAddress(address)];
            else
                throw new WrongRegisterAddressException();
        }

        private boolean validateAddress(int address) {
            return (address >= 0x100 && address <= 0x110);
        }

        private int translateAddress(int address) {
            return address - 0x100;
        }
    }

    public static class WrongRegisterAddressException extends Exception {
    }

    public static class WrongPortAddressException extends Exception {
    }

    /**
     * Registers set.
     * <p>
     * R0-R15   General purpose registers
     * FR       Flag register.
     * Bit 0 - ZF [zero flag]
     * Bit 1 - CF [carry flag]
     * SP       Stack pointer
     * PC       Program counter
     */
    public enum Register {
        R0, R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12, R13, R14, R15,
        FR, SP, PC
    }

    public CPU(Machine parent) {
        registerMap = new HashMap<Register, Integer>();
        for (Register register : Register.values()) {
            registerMap.put(register, 0);
        }
        controlRegisters = new ControlRegisters();

        ports.put(0x20, parent.getConsole().getReadWritePort());
        ports.put(0x21, parent.getConsole().getDataAvailablePort());
        ports.put(0x22, parent.getConsole().getControlPort());
    }

    public void setFlagZF() {
        int zf = getRegister(FR);
        zf |= 0x00000001;
        setRegister(FR, zf);
    }

    public void clearFlagZF() {
        int zf = getRegister(FR);
        zf &= ~0x00000001;
        setRegister(FR, zf);
    }

    public boolean getFlagZF() {
        int zf = getRegister(FR);
        return (zf & 0x00000001) != 0;
    }

    public void setFlagCF() {
        int cf = getRegister(FR);
        cf |= 0x00000010;
        setRegister(FR, cf);
    }

    public void clearFlagCF() {
        int cf = getRegister(FR);
        cf &= ~0x00000010;
        setRegister(FR, cf);
    }

    public boolean getFlagCF() {
        int cf = getRegister(FR);
        return (cf & 0x00000010) != 0;
    }

    public void setSpecialRegister(int destinationRegister, int value) throws WrongRegisterAddressException {
        controlRegisters.set(destinationRegister, value);
    }

    public int getSpecialRegister(int source) throws WrongRegisterAddressException {
        return controlRegisters.get(source);
    }

    public void setRegister(Register dst, int val) {
        registerMap.put(dst, val);
    }

    public int getRegister(Register name) {
        return registerMap.get(name);
    }

    public void writePort(int destination, int value) throws WrongPortAddressException {
        Port port = ports.get(destination);
        if (port == null)
            throw new WrongPortAddressException();
        else
            port.write(value);
    }

    public int readPort(int source) throws WrongPortAddressException {
        Port port = ports.get(source);
        if (port == null)
            throw new WrongPortAddressException();
        else
            return port.read();
    }
}
