package pl.tomaszszewczyk.machine;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Console {
    private PrintStream outStream;
    private InputStream inStream;
    private ReadWritePort readWritePort;
    private DataAvailablePort dataAvailablePort;
    private ControlPort controlPort;

    private boolean interruptOnNewDataFlag = false;

    class ReadWritePort implements Port {
        Scanner scanner = new Scanner(Console.this.inStream);

        public int read() {
            return scanner.nextByte();
        }

        public void write(int value) {
            System.out.print((char) value);
        }
    }

    class DataAvailablePort implements Port {

        public int read() {
            int result = 0x00;
            try {
                int availableBytes = inStream.available();
                result = availableBytes != 0 ? 0x01 : 0x00;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

        public void write(int value) {

        }
    }

    class ControlPort implements Port {

        public int read() {
            return interruptOnNewDataFlag ? 0x01 : 0x00;
        }

        public void write(int value) {
            interruptOnNewDataFlag = (value & 0x01) != 0;
        }
    }

    public Console() {
        this(System.out, System.in);
    }

    public Console(PrintStream out, InputStream in) {
        this.outStream = out;
        this.inStream = in;
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
