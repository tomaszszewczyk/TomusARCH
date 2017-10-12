package pl.tomaszszewczyk.machine;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Console {
    private Scanner scanner;
    private PrintStream outStream;
    private InputStream inStream;
    private ReadWritePort readWritePort;
    private DataAvailablePort dataAvailablePort;
    private ControlPort controlPort;

    private boolean interruptOnNewDataFlag = false;

    class ReadWritePort implements Port {
        public int read() {
            return Console.this.scanner.findInLine(".").charAt(0);
        }

        public void write(int value) {
            outStream.print((char) value);
        }
    }

    class DataAvailablePort implements Port {
        public int read() {
            return Console.this.scanner.hasNext() ? 0x01 : 0x00;
        }

        public void write(int value) {

        }
    }

    class ControlPort implements Port {

        public int read() {
            return interruptOnNewDataFlag ? 0x01 : 0x00;
        }

        public void write(int value) {
            interruptOnNewDataFlag = value != 0;
        }
    }

    public Console() {
        this(System.out, System.in);
    }

    public Console(PrintStream out, InputStream in) {
        this.outStream = out;
        this.inStream = in;
        this.scanner = new Scanner(in);
        this.readWritePort = new ReadWritePort();
        this.dataAvailablePort = new DataAvailablePort();
        this.controlPort = new ControlPort();
    }

    public ReadWritePort getReadWritePort() {
        return readWritePort;
    }

    public DataAvailablePort getDataAvailablePort() {
        return dataAvailablePort;
    }

    public ControlPort getControlPort() {
        return controlPort;
    }
}
