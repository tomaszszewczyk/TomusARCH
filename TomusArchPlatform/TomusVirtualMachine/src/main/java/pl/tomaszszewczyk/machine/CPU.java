package pl.tomaszszewczyk.machine;

import pl.tomaszszewczyk.machine.Registers.*;

import java.util.HashMap;
import java.util.Map;

public class CPU {
    private GeneralPurposeRegister R0  = new GeneralPurposeRegister();
    private GeneralPurposeRegister R1  = new GeneralPurposeRegister();
    private GeneralPurposeRegister R2  = new GeneralPurposeRegister();
    private GeneralPurposeRegister R3  = new GeneralPurposeRegister();
    private GeneralPurposeRegister R4  = new GeneralPurposeRegister();
    private GeneralPurposeRegister R5  = new GeneralPurposeRegister();
    private GeneralPurposeRegister R6  = new GeneralPurposeRegister();
    private GeneralPurposeRegister R7  = new GeneralPurposeRegister();
    private GeneralPurposeRegister R8  = new GeneralPurposeRegister();
    private GeneralPurposeRegister R9  = new GeneralPurposeRegister();
    private GeneralPurposeRegister R10 = new GeneralPurposeRegister();
    private GeneralPurposeRegister R11 = new GeneralPurposeRegister();
    private GeneralPurposeRegister R12 = new GeneralPurposeRegister();
    private GeneralPurposeRegister R13 = new GeneralPurposeRegister();
    private GeneralPurposeRegister R14 = new GeneralPurposeRegister();
    private GeneralPurposeRegister R15 = new GeneralPurposeRegister();
    private ProgramCounterRegister PC  = new ProgramCounterRegister();
    private StackPointerRegister   SP  = new StackPointerRegister();
    private FlagRegister           FR  = new FlagRegister();
    private ControlRegisters       CR  = new ControlRegisters();

    public class RegisterAddresses {
        public static final int R0 =  0x00;
        public static final int R1 =  0x01;
        public static final int R2 =  0x02;
        public static final int R3 =  0x03;
        public static final int R4 =  0x04;
        public static final int R5 =  0x05;
        public static final int R6 =  0x06;
        public static final int R7 =  0x07;
        public static final int R8 =  0x08;
        public static final int R9 =  0x09;
        public static final int R10 = 0x10;
        public static final int R11 = 0x11;
        public static final int R12 = 0x12;
        public static final int R13 = 0x13;
        public static final int R14 = 0x14;
        public static final int R15 = 0x15;
        public static final int PC =  0x16;
        public static final int SP =  0x17;
        public static final int FR =  0x18;
        public static final int CR =  ControlRegisters.addressOffset;
    }

    private Map<Integer, Register> registers = new HashMap<>();

    private ReadOnlyMemory rom;
    private RandomAccessMemory ram;
    private InterruptController int_controller;
    private IOController io_controller;

    public CPU(ReadOnlyMemory newRom,
               RandomAccessMemory newRam,
               InterruptController newIntController,
               IOController newIOController) {
        rom = newRom;
        ram = newRam;
        int_controller = newIntController;
        io_controller = newIOController;

        registers.put(RegisterAddresses.R0,  R0); 
        registers.put(RegisterAddresses.R1,  R1); 
        registers.put(RegisterAddresses.R2,  R2); 
        registers.put(RegisterAddresses.R3,  R3); 
        registers.put(RegisterAddresses.R4,  R4); 
        registers.put(RegisterAddresses.R5,  R5); 
        registers.put(RegisterAddresses.R6,  R6); 
        registers.put(RegisterAddresses.R7,  R7); 
        registers.put(RegisterAddresses.R8,  R8); 
        registers.put(RegisterAddresses.R9,  R9); 
        registers.put(RegisterAddresses.R10, R10);
        registers.put(RegisterAddresses.R11, R11);
        registers.put(RegisterAddresses.R12, R12);
        registers.put(RegisterAddresses.R13, R13);
        registers.put(RegisterAddresses.R14, R14);
        registers.put(RegisterAddresses.R15, R15);
        registers.put(RegisterAddresses.PC,  PC); 
        registers.put(RegisterAddresses.SP,  SP);
        registers.put(RegisterAddresses.FR,  FR);

        for(int i = 0; i < ControlRegisters.numOfRegisters; i++) {
            registers.put(RegisterAddresses.CR + i, CR.getRegister(RegisterAddresses.CR + i));
        }
    }

    public void triggerTick() {
        if (int_controller.isAny()) {
            
        } else {
            Instruction instruction = rom.getInstruction(PC.getValue());
            PC.increment();
            execute(instruction);
        }
    }

    private void execute(Instruction instruction) {
        switch (instruction.getOpcode()) {

            case Instruction.MOV: {
                executeMOV(instruction); break;
            }
            case Instruction.SET: {
                executeSET(instruction); break;
            }
            case Instruction.LD: {
                executeLD(instruction); break;
            }
            case Instruction.ST: {
                executeST(instruction); break;
            }
            case Instruction.LBD: {
                executeLBD(instruction); break;
            }
            case Instruction.STB: {
                executeSTB(instruction); break;
            }
            case Instruction.ADD: {
                executeADD(instruction); break;
            }
            case Instruction.SUB: {
                executeSUB(instruction); break;
            }
            case Instruction.MUL: {
                executeMUL(instruction); break;
            }
            case Instruction.DIV: {
                executeDIV(instruction); break;
            }
            case Instruction.MOD: {
                executeMOD(instruction); break;
            }
            case Instruction.OR: {
                executeOR(instruction); break;
            }
            case Instruction.AND: {
                executeAND(instruction); break;
            }
            case Instruction.XOR: {
                executeXOR(instruction); break;
            }
            case Instruction.NOT: {
                executeNOT(instruction); break;
            }
            case Instruction.SHL: {
                executeSHL(instruction); break;
            }
            case Instruction.SHR: {
                executeSHR(instruction); break;
            }
            case Instruction.CMP: {
                executeCMP(instruction); break;
            }
            case Instruction.JZ: {
                executeJZ(instruction); break;
            }
            case Instruction.JNZ: {
                executeJNZ(instruction); break;
            }
            case Instruction.JC: {
                executeJC(instruction); break;
            }
            case Instruction.JNC: {
                executeJNC(instruction); break;
            }
            case Instruction.JBE: {
                executeJBE(instruction); break;
            }
            case Instruction.PUSH: {
                executePUSH(instruction); break;
            }
            case Instruction.POP: {
                executePOP(instruction); break;
            }
            case Instruction.JMP: {
                executeJMP(instruction); break;
            }
            case Instruction.JMPR: {
                executeJMPR(instruction); break;
            }
            case Instruction.CALL: {
                executeCALL(instruction); break;
            }
            case Instruction.CALLR: {
                executeCALLR(instruction); break;
            }
            case Instruction.RET: {
                executeRET(instruction); break;
            }
            case Instruction.CRL: {
                executeCRL(instruction); break;
            }
            case Instruction.CRS: {
                executeCRS(instruction); break;
            }
            case Instruction.OUTB: {
                executeOUTB(instruction); break;
            }
            case Instruction.INB: {
                executeINB(instruction); break;
            }
            case Instruction.IRET: {
                executeIRET(instruction); break;
            }
            case Instruction.OFF: {
                executeOFF(instruction); break;
            }
        }
    }

    /**
     *  Execute instruction MOV
     *
     *  @param instruction  Instruction to be executed
     */
    private void executeMOV(Instruction instruction) {

    }

    /**
     *  Execute instruction SET
     *
     *  @param instruction  Instruction to be executed
     */
    private void executeSET(Instruction instruction) {

    }

    /**
     *  Execute instruction LD
     *
     *  @param instruction  Instruction to be executed
     */
    private void executeLD(Instruction instruction) {

    }

    /**
     *  Execute instruction ST
     *
     *  @param instruction  Instruction to be executed
     */
    private void executeST(Instruction instruction) {

    }

    /**
     *  Execute instruction LBD
     *
     *  @param instruction  Instruction to be executed
     */
    private void executeLBD(Instruction instruction) {

    }

    /**
     *  Execute instruction STB
     *
     *  @param instruction  Instruction to be executed
     */
    private void executeSTB(Instruction instruction) {

    }

    /**
     *  Execute instruction ADD
     *
     *  @param instruction  Instruction to be executed
     */
    private void executeADD(Instruction instruction) {

    }

    /**
     *  Execute instruction SUB
     *
     *  @param instruction  Instruction to be executed
     */
    private void executeSUB(Instruction instruction) {

    }

    /**
     *  Execute instruction MUL
     *
     *  @param instruction  Instruction to be executed
     */
    private void executeMUL(Instruction instruction) {

    }

    /**
     *  Execute instruction DIV
     *
     *  @param instruction  Instruction to be executed
     */
    private void executeDIV(Instruction instruction) {

    }

    /**
     *  Execute instruction MOD
     *
     *  @param instruction  Instruction to be executed
     */
    private void executeMOD(Instruction instruction) {

    }

    /**
     *  Execute instruction OR
     *
     *  @param instruction  Instruction to be executed
     */
    private void executeOR(Instruction instruction) {

    }

    /**
     *  Execute instruction AND
     *
     *  @param instruction  Instruction to be executed
     */
    private void executeAND(Instruction instruction) {

    }

    /**
     *  Execute instruction XOR
     *
     *  @param instruction  Instruction to be executed
     */
    private void executeXOR(Instruction instruction) {

    }

    /**
     *  Execute instruction NOT
     *
     *  @param instruction  Instruction to be executed
     */
    private void executeNOT(Instruction instruction) {

    }

    /**
     *  Execute instruction SHL
     *
     *  @param instruction  Instruction to be executed
     */
    private void executeSHL(Instruction instruction) {

    }

    /**
     *  Execute instruction SHR
     *
     *  @param instruction  Instruction to be executed
     */
    private void executeSHR(Instruction instruction) {

    }

    /**
     *  Execute instruction CMP
     *
     *  @param instruction  Instruction to be executed
     */
    private void executeCMP(Instruction instruction) {

    }

    /**
     *  Execute instruction JZ
     *
     *  @param instruction  Instruction to be executed
     */
    private void executeJZ(Instruction instruction) {

    }

    /**
     *  Execute instruction JNZ
     *
     *  @param instruction  Instruction to be executed
     */
    private void executeJNZ(Instruction instruction) {

    }

    /**
     *  Execute instruction JC
     *
     *  @param instruction  Instruction to be executed
     */
    private void executeJC(Instruction instruction) {

    }

    /**
     *  Execute instruction JNC
     *
     *  @param instruction  Instruction to be executed
     */
    private void executeJNC(Instruction instruction) {

    }

    /**
     *  Execute instruction JBE
     *
     *  @param instruction  Instruction to be executed
     */
    private void executeJBE(Instruction instruction) {

    }

    /**
     *  Execute instruction PUSH
     *
     *  @param instruction  Instruction to be executed
     */
    private void executePUSH(Instruction instruction) {

    }

    /**
     *  Execute instruction POP
     *
     *  @param instruction  Instruction to be executed
     */
    private void executePOP(Instruction instruction) {

    }

    /**
     *  Execute instruction JMP
     *
     *  @param instruction  Instruction to be executed
     */
    private void executeJMP(Instruction instruction) {

    }

    /**
     *  Execute instruction JMPR
     *
     *  @param instruction  Instruction to be executed
     */
    private void executeJMPR(Instruction instruction) {

    }

    /**
     *  Execute instruction CALL
     *
     *  @param instruction  Instruction to be executed
     */
    private void executeCALL(Instruction instruction) {

    }

    /**
     *  Execute instruction CALLR
     *
     *  @param instruction  Instruction to be executed
     */
    private void executeCALLR(Instruction instruction) {

    }

    /**
     *  Execute instruction RET
     *
     *  @param instruction  Instruction to be executed
     */
    private void executeRET(Instruction instruction) {

    }

    /**
     *  Execute instruction CRL
     *
     *  @param instruction  Instruction to be executed
     */
    private void executeCRL(Instruction instruction) {

    }

    /**
     *  Execute instruction CRS
     *
     *  @param instruction  Instruction to be executed
     */
    private void executeCRS(Instruction instruction) {

    }

    /**
     *  Execute instruction OUTB
     *
     *  @param instruction  Instruction to be executed
     */
    private void executeOUTB(Instruction instruction) {

    }

    /**
     *  Execute instruction INB
     *
     *  @param instruction  Instruction to be executed
     */
    private void executeINB(Instruction instruction) {

    }

    /**
     *  Execute instruction IRET
     *
     *  @param instruction  Instruction to be executed
     */
    private void executeIRET(Instruction instruction) {

    }

    /**
     *  Execute instruction OFF
     *
     *  @param instruction  Instruction to be executed
     */
    private void executeOFF(Instruction instruction) {

    }


}
