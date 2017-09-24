package pl.tomaszszewczyk.machine;

public class Machine {

    private CPU cpu;
    private RAM ram;

    public Machine() {
        cpu = new CPU(this);
    }

    public CPU getCPU() {
        return cpu;
    }

    public RAM getRAM() {
        return ram;
    }

    public void execute() throws Exception {
        throw new Exception("Not implemented");
    }

    public void powerOFF() {

    }

    public Console getConsole() {
        return null;
    }
}
