package pl.tomaszszewczyk.machine;

import pl.tomaszszewczyk.machine.Registers.*;

import java.io.Serializable;
import java.util.*;

/**
 * CPU (Central Processing Unit) emulator class
 *
 * @author Tomasz Szewczyk
 */
public class CPU {
    /**
     * Register R0
     */
    private GeneralPurposeRegister R0 = new GeneralPurposeRegister();
    /**
     * Register R1
     */
    private GeneralPurposeRegister R1 = new GeneralPurposeRegister();
    /**
     * Register R2
     */
    private GeneralPurposeRegister R2 = new GeneralPurposeRegister();
    /**
     * Register R3
     */
    private GeneralPurposeRegister R3 = new GeneralPurposeRegister();
    /**
     * Register R4
     */
    private GeneralPurposeRegister R4 = new GeneralPurposeRegister();
    /**
     * Register R5
     */
    private GeneralPurposeRegister R5 = new GeneralPurposeRegister();
    /**
     * Register R6
     */
    private GeneralPurposeRegister R6 = new GeneralPurposeRegister();
    /**
     * Register R7
     */
    private GeneralPurposeRegister R7 = new GeneralPurposeRegister();
    /**
     * Register R8
     */
    private GeneralPurposeRegister R8 = new GeneralPurposeRegister();
    /**
     * Register R9
     */
    private GeneralPurposeRegister R9 = new GeneralPurposeRegister();
    /**
     * Register R10
     */
    private GeneralPurposeRegister R10 = new GeneralPurposeRegister();
    /**
     * Register R11
     */
    private GeneralPurposeRegister R11 = new GeneralPurposeRegister();
    /**
     * Register R12
     */
    private GeneralPurposeRegister R12 = new GeneralPurposeRegister();
    /**
     * Register R13
     */
    private GeneralPurposeRegister R13 = new GeneralPurposeRegister();
    /**
     * Register R14
     */
    private GeneralPurposeRegister R14 = new GeneralPurposeRegister();
    /**
     * Register R15
     */
    private GeneralPurposeRegister R15 = new GeneralPurposeRegister();
    /**
     * Register PC, program counter
     */
    private ProgramCounterRegister PC = new ProgramCounterRegister();
    /**
     * Register SP, stack pointer
     */
    private StackPointerRegister SP = new StackPointerRegister();
    /**
     * Register FR, flag register
     */
    private FlagRegister FR = new FlagRegister();
    /**
     * Registers CR, control registers
     */
    private ControlRegisters CR = new ControlRegisters();

    /**
     * Registers map
     */
    private Map<Byte, Register> registers = new HashMap<>();

    /**
     * Read only memory (program memory)
     */
    private ReadOnlyMemory rom;

    /**
     * Random access memory
     */
    private RandomAccessMemory ram;

    /**
     * Interrupt controller
     */
    private InterruptController int_controller;

    /**
     * IO controller
     */
    private IOController io_controller;

    /**
     * Interrupt service routine address registers addresses
     */
    private static abstract class ISRRegisterAddr {
        public static final int MemoryRangeExceededIntRegisterAddr = 0x100;
        public static final int DivideByZeroIntRegisterAddr = 0x101;
        public static final int GeneralErrorIntRegisterAddr = 0x102;
        public static final int TimerInterruptIntRegisterAddr = 0x103;
        public static final int ConsoleInterruptIntRegisterAddr = 0x104;
    }

    /**
     * Register addresses
     */
    public static abstract class RegisterAddresses {
        public static final byte R0 = 0x00;
        public static final byte R1 = 0x01;
        public static final byte R2 = 0x02;
        public static final byte R3 = 0x03;
        public static final byte R4 = 0x04;
        public static final byte R5 = 0x05;
        public static final byte R6 = 0x06;
        public static final byte R7 = 0x07;
        public static final byte R8 = 0x08;
        public static final byte R9 = 0x09;
        public static final byte R10 = 0x10;
        public static final byte R11 = 0x11;
        public static final byte R12 = 0x12;
        public static final byte R13 = 0x13;
        public static final byte R14 = 0x14;
        public static final byte R15 = 0x15;
        public static final byte PC = 0x16;
        public static final byte SP = 0x17;
        public static final byte FR = 0x18;
    }

    /**
     * CPU constructor. Initialize register values and registers RAM, ROM,
     * INT controller and IO controller
     *
     * @param newRom           ROM from which CPU will read program
     * @param newRam           RAM on which CPU will be operating
     * @param newIntController Interrupt controller from which CPU will be reading interrupts
     * @param newIOController  IO controller on which CPU will be operating
     */
    public CPU(ReadOnlyMemory newRom,
               RandomAccessMemory newRam,
               InterruptController newIntController,
               IOController newIOController) {
        rom = newRom;
        ram = newRam;
        int_controller = newIntController;
        io_controller = newIOController;

        registers.put(RegisterAddresses.R0, R0);
        registers.put(RegisterAddresses.R1, R1);
        registers.put(RegisterAddresses.R2, R2);
        registers.put(RegisterAddresses.R3, R3);
        registers.put(RegisterAddresses.R4, R4);
        registers.put(RegisterAddresses.R5, R5);
        registers.put(RegisterAddresses.R6, R6);
        registers.put(RegisterAddresses.R7, R7);
        registers.put(RegisterAddresses.R8, R8);
        registers.put(RegisterAddresses.R9, R9);
        registers.put(RegisterAddresses.R10, R10);
        registers.put(RegisterAddresses.R11, R11);
        registers.put(RegisterAddresses.R12, R12);
        registers.put(RegisterAddresses.R13, R13);
        registers.put(RegisterAddresses.R14, R14);
        registers.put(RegisterAddresses.R15, R15);
        registers.put(RegisterAddresses.PC, PC);
        registers.put(RegisterAddresses.SP, SP);
        registers.put(RegisterAddresses.FR, FR);
    }

    /**
     * Trigger next processor cycle
     * Check for interrupt, read instruction from rom and execute
     */
    public void triggerTick() {
        if (int_controller.isAny()) {
            List<Byte> key_list = new LinkedList<>();
            key_list.addAll(registers.keySet());
            Collections.sort(key_list);
            Collections.reverse(key_list);

            for (byte register_id : registers.keySet()) {
                int value = registers.get(register_id).getValue();

                SP.decrement();
                ram.writeByte(SP.getValue(), (byte) value);
                SP.decrement();
                ram.writeByte(SP.getValue(), (byte) (value >> 8));
                SP.decrement();
                ram.writeByte(SP.getValue(), (byte) (value >> 16));
                SP.decrement();
                ram.writeByte(SP.getValue(), (byte) (value >> 24));
            }
            Interrupt interrupt = int_controller.getInt();

            switch (interrupt) {
                case MemoryRangeExceeded: {
                    PC.setValue(CR.readRegisterByAddress(ISRRegisterAddr.MemoryRangeExceededIntRegisterAddr));
                    break;
                }
                case DivideByZero: {
                    PC.setValue(CR.readRegisterByAddress(ISRRegisterAddr.DivideByZeroIntRegisterAddr));
                    break;
                }
                case GeneralError: {
                    PC.setValue(CR.readRegisterByAddress(ISRRegisterAddr.GeneralErrorIntRegisterAddr));
                    break;
                }
                case TimerInterrupt: {
                    PC.setValue(CR.readRegisterByAddress(ISRRegisterAddr.TimerInterruptIntRegisterAddr));
                    break;
                }
                case ConsoleInterrupt: {
                    PC.setValue(CR.readRegisterByAddress(ISRRegisterAddr.ConsoleInterruptIntRegisterAddr));
                    break;
                }
            }

        }

        Instruction instruction = rom.getInstruction(PC.getValue());
        PC.increment();
        execute(instruction);
    }


    /**
     * Execute instruction
     *
     * @param instruction Instruction to be executed
     */
    private void execute(Instruction instruction) {
        switch (instruction.getOpcode()) {

            case Instruction.MOV: {
                executeMOV(instruction);
                break;
            }
            case Instruction.SET: {
                executeSET(instruction);
                break;
            }
            case Instruction.LD: {
                executeLD(instruction);
                break;
            }
            case Instruction.ST: {
                executeST(instruction);
                break;
            }
            case Instruction.LBD: {
                executeLBD(instruction);
                break;
            }
            case Instruction.STB: {
                executeSTB(instruction);
                break;
            }
            case Instruction.ADD: {
                executeADD(instruction);
                break;
            }
            case Instruction.SUB: {
                executeSUB(instruction);
                break;
            }
            case Instruction.MUL: {
                executeMUL(instruction);
                break;
            }
            case Instruction.DIV: {
                executeDIV(instruction);
                break;
            }
            case Instruction.MOD: {
                executeMOD(instruction);
                break;
            }
            case Instruction.OR: {
                executeOR(instruction);
                break;
            }
            case Instruction.AND: {
                executeAND(instruction);
                break;
            }
            case Instruction.XOR: {
                executeXOR(instruction);
                break;
            }
            case Instruction.NOT: {
                executeNOT(instruction);
                break;
            }
            case Instruction.SHL: {
                executeSHL(instruction);
                break;
            }
            case Instruction.SHR: {
                executeSHR(instruction);
                break;
            }
            case Instruction.CMP: {
                executeCMP(instruction);
                break;
            }
            case Instruction.JZ: {
                executeJZ(instruction);
                break;
            }
            case Instruction.JNZ: {
                executeJNZ(instruction);
                break;
            }
            case Instruction.JC: {
                executeJC(instruction);
                break;
            }
            case Instruction.JNC: {
                executeJNC(instruction);
                break;
            }
            case Instruction.JBE: {
                executeJBE(instruction);
                break;
            }
            case Instruction.JA: {
                executeJA(instruction);
                break;
            }
            case Instruction.PUSH: {
                executePUSH(instruction);
                break;
            }
            case Instruction.POP: {
                executePOP(instruction);
                break;
            }
            case Instruction.JMP: {
                executeJMP(instruction);
                break;
            }
            case Instruction.JMPR: {
                executeJMPR(instruction);
                break;
            }
            case Instruction.CALL: {
                executeCALL(instruction);
                break;
            }
            case Instruction.CALLR: {
                executeCALLR(instruction);
                break;
            }
            case Instruction.RET: {
                executeRET(instruction);
                break;
            }
            case Instruction.CRL: {
                executeCRL(instruction);
                break;
            }
            case Instruction.CRS: {
                executeCRS(instruction);
                break;
            }
            case Instruction.OUTB: {
                executeOUTB(instruction);
                break;
            }
            case Instruction.INB: {
                executeINB(instruction);
                break;
            }
            case Instruction.IRET: {
                executeIRET(instruction);
                break;
            }
            case Instruction.OFF: {
                executeOFF(instruction);
                break;
            }
        }
    }

    /**
     * Execute instruction MOV
     * Copy src to dst
     *
     * @param instruction Instruction to be executed
     */
    private void executeMOV(Instruction instruction) {
        assert instruction.getOpcode() == Instruction.MOV : "Wrong instruction";

        registers.get(instruction.getRdst()).setValue(instruction.getRsrc());
    }

    /**
     * Execute instruction SET
     * Sets dst value to immediate value
     *
     * @param instruction Instruction to be executed
     */
    private void executeSET(Instruction instruction) {
        assert instruction.getOpcode() == Instruction.SET : "Wrong instruction";

        registers.get(instruction.getRdst()).setValue(instruction.getImmediate());
    }

    /**
     * Execute instruction LD
     * Copy word from RAM to dst register. Address is being read from src register
     *
     * @param instruction Instruction to be executed
     */
    private void executeLD(Instruction instruction) {
        assert instruction.getOpcode() == Instruction.LD : "Wrong instruction";

        int value;
        int address = registers.get(instruction.getRsrc()).getValue();
        value = (int) ram.getByte(address);
        value |= (int) ram.getByte(address + 1) << 8;
        value |= (int) ram.getByte(address + 2) << 16;
        value |= (int) ram.getByte(address + 3) << 24;

        registers.get(instruction.getRdst()).setValue(value);
    }

    /**
     * Execute instruction ST
     * Store word in RAM from src register. Address is being read from dst register
     *
     * @param instruction Instruction to be executed
     */
    private void executeST(Instruction instruction) {
        assert instruction.getOpcode() == Instruction.ST : "Wrong instruction";

        int address = registers.get(instruction.getRsrc()).getValue();
        int value = registers.get(instruction.getRsrc()).getValue();

        ram.writeByte(address, (byte) value);
        ram.writeByte(address + 1, (byte) (value >> 8));
        ram.writeByte(address + 2, (byte) (value >> 16));
        ram.writeByte(address + 3, (byte) (value >> 24));
    }

    /**
     * Execute instruction LBD
     * Load byte from RAM to dst register. Address is being read from src register
     *
     * @param instruction Instruction to be executed
     */
    private void executeLBD(Instruction instruction) {
        assert instruction.getOpcode() == Instruction.LBD : "Wrong instruction";

        int address = registers.get(instruction.getRdst()).getValue();
        int value = ram.getByte(address);
        registers.get(instruction.getRsrc()).setValue(value);
    }

    /**
     * Execute instruction STB
     * Store byte in RAM from src register. Address is being read from dst register
     *
     * @param instruction Instruction to be executed
     */
    private void executeSTB(Instruction instruction) {
        assert instruction.getOpcode() == Instruction.STB : "Wrong instruction";

        int address = registers.get(instruction.getRdst()).getValue();
        int value = registers.get(instruction.getRsrc()).getValue();
        ram.writeByte(address, (byte) value);
    }

    /**
     * Execute instruction ADD
     * Sum src and dst registers and save result in dst register
     *
     * @param instruction Instruction to be executed
     */
    private void executeADD(Instruction instruction) {
        assert instruction.getOpcode() == Instruction.ADD : "Wrong instruction";

        int a = registers.get(instruction.getRsrc()).getValue();
        int b = registers.get(instruction.getRdst()).getValue();
        registers.get(instruction.getRdst()).setValue(a + b);
    }

    /**
     * Execute instruction SUB
     * Subtract src and dst registers and save result in dst register
     *
     * @param instruction Instruction to be executed
     */
    private void executeSUB(Instruction instruction) {
        assert instruction.getOpcode() == Instruction.SUB : "Wrong instruction";

        int a = registers.get(instruction.getRdst()).getValue();
        int b = registers.get(instruction.getRsrc()).getValue();
        registers.get(instruction.getRdst()).setValue(a - b);
    }

    /**
     * Execute instruction MUL
     * Multiply src and dst registers and save result in dst register
     *
     * @param instruction Instruction to be executed
     */
    private void executeMUL(Instruction instruction) {
        assert instruction.getOpcode() == Instruction.MUL : "Wrong instruction";

        int a = registers.get(instruction.getRdst()).getValue();
        int b = registers.get(instruction.getRsrc()).getValue();
        registers.get(instruction.getRdst()).setValue(a * b);
    }

    /**
     * Execute instruction DIV
     * Divide src and dst registers and save result in dst register
     *
     * @param instruction Instruction to be executed
     */
    private void executeDIV(Instruction instruction) {
        assert instruction.getOpcode() == Instruction.DIV : "Wrong instruction";

        int a = registers.get(instruction.getRdst()).getValue();
        int b = registers.get(instruction.getRsrc()).getValue();
        registers.get(instruction.getRdst()).setValue(a / b);

    }

    /**
     * Execute instruction MOD
     * Modulo src and dst registers and save result in dst register
     *
     * @param instruction Instruction to be executed
     */
    private void executeMOD(Instruction instruction) {
        assert instruction.getOpcode() == Instruction.MOD : "Wrong instruction";

        int a = registers.get(instruction.getRdst()).getValue();
        int b = registers.get(instruction.getRsrc()).getValue();
        registers.get(instruction.getRdst()).setValue(a % b);
    }

    /**
     * Execute instruction OR
     * OR src and dst registers and save result in dst register
     *
     * @param instruction Instruction to be executed
     */
    private void executeOR(Instruction instruction) {
        assert instruction.getOpcode() == Instruction.OR : "Wrong instruction";

        int a = registers.get(instruction.getRdst()).getValue();
        int b = registers.get(instruction.getRsrc()).getValue();
        registers.get(instruction.getRdst()).setValue(a | b);
    }

    /**
     * Execute instruction AND
     * AND src and dst registers and save result in dst register
     *
     * @param instruction Instruction to be executed
     */
    private void executeAND(Instruction instruction) {
        assert instruction.getOpcode() == Instruction.AND : "Wrong instruction";

        int a = registers.get(instruction.getRdst()).getValue();
        int b = registers.get(instruction.getRsrc()).getValue();
        registers.get(instruction.getRdst()).setValue(a & b);
    }

    /**
     * Execute instruction XOR
     * XOR src and dst registers and save result in dst register
     *
     * @param instruction Instruction to be executed
     */
    private void executeXOR(Instruction instruction) {
        assert instruction.getOpcode() == Instruction.XOR : "Wrong instruction";

        int a = registers.get(instruction.getRdst()).getValue();
        int b = registers.get(instruction.getRsrc()).getValue();
        registers.get(instruction.getRdst()).setValue(a ^ b);
    }

    /**
     * Execute instruction NOT
     * Negate dst register
     *
     * @param instruction Instruction to be executed
     */
    private void executeNOT(Instruction instruction) {
        assert instruction.getOpcode() == Instruction.NOT : "Wrong instruction";

        int a = registers.get(instruction.getRdst()).getValue();
        registers.get(instruction.getRdst()).setValue(~a);
    }

    /**
     * Execute instruction SHL
     * Shifts dst left by numbers of steps from src
     *
     * @param instruction Instruction to be executed
     */
    private void executeSHL(Instruction instruction) {
        assert instruction.getOpcode() == Instruction.SHL : "Wrong instruction";

        int a = registers.get(instruction.getRdst()).getValue();
        int b = registers.get(instruction.getRsrc()).getValue();
        registers.get(instruction.getRdst()).setValue(a << b);
    }

    /**
     * Execute instruction SHR
     * Shifts dst right by numbers of steps from src
     *
     * @param instruction Instruction to be executed
     */
    private void executeSHR(Instruction instruction) {
        assert instruction.getOpcode() == Instruction.SHR : "Wrong instruction";

        int a = registers.get(instruction.getRdst()).getValue();
        int b = registers.get(instruction.getRsrc()).getValue();
        registers.get(instruction.getRdst()).setValue(a >> b);
    }

    /**
     * Execute instruction CMP
     * Compare src and dst registers and saves result to FR register
     *
     * @param instruction Instruction to be executed
     */
    private void executeCMP(Instruction instruction) {
        assert instruction.getOpcode() == Instruction.CMP : "Wrong instruction";

        int a = registers.get(instruction.getRdst()).getValue();
        int b = registers.get(instruction.getRsrc()).getValue();
        FR.writeZeroFlag(a == b);
    }

    /**
     * Execute instruction JZ / JE
     * Jump if zero / Jump if equal
     * Check if flag ZF is set. If so increase PC by immediate
     *
     * @param instruction Instruction to be executed
     */
    private void executeJZ(Instruction instruction) {
        assert instruction.getOpcode() == Instruction.JZ : "Wrong instruction";

        if (FR.getZeroFlag())
            PC.setValue(PC.getValue() + instruction.getImmediate());
    }

    /**
     * Execute instruction JNZ / JNE
     * Jump if not zero / Jump if not equal
     * Check if flag ZF is cleared. If so increase PC by immediate
     *
     * @param instruction Instruction to be executed
     */
    private void executeJNZ(Instruction instruction) {
        assert instruction.getOpcode() == Instruction.JNZ : "Wrong instruction";

        if (!FR.getZeroFlag())
            PC.setValue(PC.getValue() + instruction.getImmediate());
    }

    /**
     * Execute instruction JC / JB
     * Jump if carry / Jump if below
     * Check if flag CF is set. If so increase PC by immediate
     *
     * @param instruction Instruction to be executed
     */
    private void executeJC(Instruction instruction) {
        assert instruction.getOpcode() == Instruction.JC : "Wrong instruction";

        if (FR.getCarryFlag())
            PC.setValue(PC.getValue() + instruction.getImmediate());
    }

    /**
     * Execute instruction JNC / JNB
     * Jump if not carry / Jump if not below
     * Check if flag CF is cleared. If so increase PC by immediate
     *
     * @param instruction Instruction to be executed
     */
    private void executeJNC(Instruction instruction) {
        assert instruction.getOpcode() == Instruction.JNC : "Wrong instruction";

        if (!FR.getCarryFlag())
            PC.setValue(PC.getValue() + instruction.getImmediate());
    }

    /**
     * Execute instruction JBE
     * Jump if below or equal
     * Check if flag CF or ZF is set. If so increase PC by immediate
     *
     * @param instruction Instruction to be executed
     */
    private void executeJBE(Instruction instruction) {
        assert instruction.getOpcode() == Instruction.JBE : "Wrong instruction";

        if (FR.getCarryFlag() | FR.getZeroFlag())
            PC.setValue(PC.getValue() + instruction.getImmediate());
    }

    /**
     * Execute instruction JA
     * Jump if above
     * Check if flag CF is set and ZF is clear. If so increase PC by immediate
     *
     * @param instruction Instruction to be executed
     */
    private void executeJA(Instruction instruction) {
        assert instruction.getOpcode() == Instruction.JBE : "Wrong instruction";

        if (FR.getCarryFlag() & !FR.getZeroFlag())
            PC.setValue(PC.getValue() + instruction.getImmediate());
    }

    /**
     * Execute instruction PUSH
     * Push src register on stack
     * Decrease SP register and saves src on stack
     *
     * @param instruction Instruction to be executed
     */
    private void executePUSH(Instruction instruction) {
        assert instruction.getOpcode() == Instruction.PUSH : "Wrong instruction";

        int value = registers.get(instruction.getRsrc()).getValue();

        SP.decrement();
        ram.writeByte(SP.getValue(), (byte) value);
        SP.decrement();
        ram.writeByte(SP.getValue(), (byte) (value >> 8));
        SP.decrement();
        ram.writeByte(SP.getValue(), (byte) (value >> 16));
        SP.decrement();
        ram.writeByte(SP.getValue(), (byte) (value >> 24));
    }

    /**
     * Execute instruction POP
     * Pop value from stack and saves it to dst register
     * Increase SP register and pop value from stack
     *
     * @param instruction Instruction to be executed
     */
    private void executePOP(Instruction instruction) {
        assert instruction.getOpcode() == Instruction.POP : "Wrong instruction";

        int value;
        value = (int) ram.getByte(SP.getValue());
        SP.increment();
        value |= (int) ram.getByte(SP.getValue()) << 8;
        SP.increment();
        value |= (int) ram.getByte(SP.getValue()) << 16;
        SP.increment();
        value |= (int) ram.getByte(SP.getValue()) << 24;
        SP.increment();

        registers.get(instruction.getRdst()).setValue(value);
    }

    /**
     * Execute instruction JMP
     * Relative jump to address given in immediate
     *
     * @param instruction Instruction to be executed
     */
    private void executeJMP(Instruction instruction) {
        assert instruction.getOpcode() == Instruction.JMP : "Wrong instruction";

        PC.setValue(PC.getValue() + instruction.getImmediate());
    }

    /**
     * Execute instruction JMPR
     * Relative jump to address given in src register
     *
     * @param instruction Instruction to be executed
     */
    private void executeJMPR(Instruction instruction) {
        assert instruction.getOpcode() == Instruction.JMPR : "Wrong instruction";

        int imm = registers.get(instruction.getRsrc()).getValue();
        PC.setValue(PC.getValue() + imm);
    }

    /**
     * Execute instruction CALL
     * Save next instruction address on stack, and relative jump to immediate
     *
     * @param instruction Instruction to be executed
     */
    private void executeCALL(Instruction instruction) {
        assert instruction.getOpcode() == Instruction.CALL : "Wrong instruction";

        SP.decrement();
        int address = SP.getValue();
        int value = registers.get(instruction.getRsrc()).getValue();

        ram.writeByte(address, (byte) value);
        ram.writeByte(address + 1, (byte) (value >> 8));
        ram.writeByte(address + 2, (byte) (value >> 16));
        ram.writeByte(address + 3, (byte) (value >> 24));

        PC.setValue(PC.getValue() + instruction.getImmediate());
    }

    /**
     * Execute instruction CALLR
     * Save next instruction address on stack, and relative jump to src register
     *
     * @param instruction Instruction to be executed
     */
    private void executeCALLR(Instruction instruction) {
        assert instruction.getOpcode() == Instruction.CALLR : "Wrong instruction";

        SP.decrement();
        int address = SP.getValue();
        int value = registers.get(instruction.getRsrc()).getValue();

        ram.writeByte(address, (byte) value);
        ram.writeByte(address + 1, (byte) (value >> 8));
        ram.writeByte(address + 2, (byte) (value >> 16));
        ram.writeByte(address + 3, (byte) (value >> 24));

        int imm = registers.get(instruction.getRsrc()).getValue();
        PC.setValue(PC.getValue() + imm);
    }

    /**
     * Execute instruction RET
     * Pop address from stack and relative jump to it
     *
     * @param instruction Instruction to be executed
     */
    private void executeRET(Instruction instruction) {
        assert instruction.getOpcode() == Instruction.RET : "Wrong instruction";

        SP.increment();
        int value;
        int address = SP.getValue();
        value = (int) ram.getByte(address);
        value |= (int) ram.getByte(address + 1) << 8;
        value |= (int) ram.getByte(address + 2) << 16;
        value |= (int) ram.getByte(address + 3) << 24;

        PC.setValue(PC.getValue() + value);
    }

    /**
     * Execute instruction CRL
     * Control register load. Copy src register to special register at address read from immediate
     *
     * @param instruction Instruction to be executed
     */
    private void executeCRL(Instruction instruction) {
        assert instruction.getOpcode() == Instruction.CRL : "Wrong instruction";

        int address = registers.get(instruction.getRsrc()).getValue();
        int value = instruction.getImmediate();
        CR.getRegister(address).setValue(value);
    }

    /**
     * Execute instruction CRS
     * Control register store. Copy special register at address from immediate value to dst register.
     *
     * @param instruction Instruction to be executed
     */
    private void executeCRS(Instruction instruction) {
        assert instruction.getOpcode() == Instruction.CRS : "Wrong instruction";

        int address = registers.get(instruction.getRsrc()).getValue();
        int value = CR.getRegister(address).getValue();
        registers.get(instruction.getRdst()).setValue(value);
    }

    /**
     * Execute instruction OUTB
     * Send byte from src register to port given by immediate
     *
     * @param instruction Instruction to be executed
     */
    private void executeOUTB(Instruction instruction) {
        assert instruction.getOpcode() == Instruction.OUTB : "Wrong instruction";

        int address = instruction.getImmediate();
        byte value = (byte) registers.get(instruction.getRsrc()).getValue();
        io_controller.writePort(address, value);
    }

    /**
     * Execute instruction INB
     * Send byte from port given by immediate to special register
     *
     * @param instruction Instruction to be executed
     */
    private void executeINB(Instruction instruction) {
        assert instruction.getOpcode() == Instruction.INB : "Wrong instruction";

        int address = instruction.getImmediate();
        byte value = io_controller.readPort(address);
        registers.get(instruction.getRdst()).setValue(value);
    }

    /**
     * Execute instruction IRET
     * Pop all registers back
     *
     * @param instruction Instruction to be executed
     */
    private void executeIRET(Instruction instruction) {
        assert instruction.getOpcode() == Instruction.IRET : "Wrong instruction";

        List<Byte> key_list = new LinkedList<>();
        key_list.addAll(registers.keySet());
        Collections.sort(key_list);

        assert instruction.getOpcode() == Instruction.IRET : "Wrong instruction";
        for (byte register_id : key_list) {
            int value;
            value = (int) ram.getByte(SP.getValue());
            SP.increment();
            value |= (int) ram.getByte(SP.getValue()) << 8;
            SP.increment();
            value |= (int) ram.getByte(SP.getValue()) << 16;
            SP.increment();
            value |= (int) ram.getByte(SP.getValue()) << 24;
            SP.increment();

            registers.get(register_id).setValue(value);
        }
    }

    /**
     * Execute instruction OFF
     *
     * @param instruction Instruction to be executed
     */
    private void executeOFF(Instruction instruction) {
        assert instruction.getOpcode() == Instruction.IRET : "Wrong instruction";

        assert false : "Got instruction OFF";
    }
}
