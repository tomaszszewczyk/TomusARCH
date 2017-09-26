package pl.tomaszszewczyk.machine;

public class Machine {

    private CPU cpu = new CPU(this);
    private RAM ram;
    private Console console = new Console();
    private Timer timer = new Timer();

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
        return console;
    }

    public Timer getTimer() {
        return timer;
    }
}
