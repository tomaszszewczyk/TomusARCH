package pl.tomaszszewczyk.machine;

import pl.tomaszszewczyk.machine.Registers.*;

public class CPU {
    private GeneralPurposeRegister R0 = new GeneralPurposeRegister();
    private GeneralPurposeRegister R1 = new GeneralPurposeRegister();
    private GeneralPurposeRegister R2 = new GeneralPurposeRegister();
    private GeneralPurposeRegister R3 = new GeneralPurposeRegister();
    private GeneralPurposeRegister R4 = new GeneralPurposeRegister();
    private GeneralPurposeRegister R5 = new GeneralPurposeRegister();
    private GeneralPurposeRegister R6 = new GeneralPurposeRegister();
    private GeneralPurposeRegister R7 = new GeneralPurposeRegister();
    private GeneralPurposeRegister R8 = new GeneralPurposeRegister();
    private GeneralPurposeRegister R9 = new GeneralPurposeRegister();
    private GeneralPurposeRegister R10 = new GeneralPurposeRegister();
    private GeneralPurposeRegister R11 = new GeneralPurposeRegister();
    private GeneralPurposeRegister R12 = new GeneralPurposeRegister();
    private GeneralPurposeRegister R13 = new GeneralPurposeRegister();
    private GeneralPurposeRegister R14 = new GeneralPurposeRegister();
    private GeneralPurposeRegister R15 = new GeneralPurposeRegister();
    private ProgramCounterRegister PC = new ProgramCounterRegister();
    private StackPointerRegister SP = new StackPointerRegister();
    private FlagRegister FR = new FlagRegister();
    private ControlRegisters CR = new ControlRegisters();

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

    private void executeMOV(Instruction instruction) {

    }

    private void executeSET(Instruction instruction) {

    }

    private void executeLD(Instruction instruction) {

    }

    private void executeST(Instruction instruction) {

    }

    private void executeLBD(Instruction instruction) {

    }

    private void executeSTB(Instruction instruction) {

    }

    private void executeADD(Instruction instruction) {

    }

    private void executeSUB(Instruction instruction) {

    }

    private void executeMUL(Instruction instruction) {

    }

    private void executeDIV(Instruction instruction) {

    }

    private void executeMOD(Instruction instruction) {

    }

    private void executeOR(Instruction instruction) {

    }

    private void executeAND(Instruction instruction) {

    }

    private void executeXOR(Instruction instruction) {

    }

    private void executeNOT(Instruction instruction) {

    }

    private void executeSHL(Instruction instruction) {

    }

    private void executeSHR(Instruction instruction) {

    }

    private void executeCMP(Instruction instruction) {

    }

    private void executeJZ(Instruction instruction) {

    }

    private void executeJNZ(Instruction instruction) {

    }

    private void executeJC(Instruction instruction) {

    }

    private void executeJNC(Instruction instruction) {

    }

    private void executeJBE(Instruction instruction) {

    }

    private void executePUSH(Instruction instruction) {

    }

    private void executePOP(Instruction instruction) {

    }

    private void executeJMP(Instruction instruction) {

    }

    private void executeJMPR(Instruction instruction) {

    }

    private void executeCALL(Instruction instruction) {

    }

    private void executeCALLR(Instruction instruction) {

    }

    private void executeRET(Instruction instruction) {

    }

    private void executeCRL(Instruction instruction) {

    }

    private void executeCRS(Instruction instruction) {

    }

    private void executeOUTB(Instruction instruction) {

    }

    private void executeINB(Instruction instruction) {

    }

    private void executeIRET(Instruction instruction) {

    }

    private void executeOFF(Instruction instruction) {

    }


}
