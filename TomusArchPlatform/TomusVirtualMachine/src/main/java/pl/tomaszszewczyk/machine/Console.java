package pl.tomaszszewczyk.machine;

public class Console {
    private ReadWritePort readWritePort = new ReadWritePort();
    private DataAvailablePort dataAvailablePort = new DataAvailablePort();
    private ControlPort controlPort = new ControlPort();

    private boolean interruptOnNewDataFlag = false;

    class ReadWritePort implements Port {

        public int read() {
            return 0;
        }

        public void write(int value) {
            System.out.print((char) value);
        }
    }

    class DataAvailablePort implements Port {

        public int read() {
            return 0;
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

    public Port getReadWritePort() {
        return readWritePort;
    }

    public Port getDataAvailablePort() {
        return dataAvailablePort;
    }

    public Port getControlPort() {
        return controlPort;
    }
}
