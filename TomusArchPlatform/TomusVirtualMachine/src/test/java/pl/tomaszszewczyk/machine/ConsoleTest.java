package pl.tomaszszewczyk.machine;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.*;

public class ConsoleTest {
    private PrintStream out;
    private InputStream in;

    private Console console;

    private Console.ReadWritePort readWritePort;
    private Console.DataAvailablePort dataAvailablePort;
    private Console.ControlPort controlPort;

    @Before
    public void setUp() {
        out = mock(PrintStream.class);
        in = mock(InputStream.class);

        console = new Console(out, in);

        readWritePort = console.getReadWritePort();
        dataAvailablePort = console.getDataAvailablePort();
        controlPort = console.getControlPort();
    }

    @Test
    public void testDataAvailable() throws IOException {
        when(in.available()).thenReturn(2);
        when(in.read()).thenReturn(0);

        int dataAvailable = dataAvailablePort.read();
        assertNotEquals(0x00, dataAvailablePort.read());
    }

    @Test
    public void testDataNotAvailable() throws IOException {
        when(in.available()).thenReturn(0);
        when(in.read()).thenReturn(0);

        int dataAvailable = dataAvailablePort.read();
        assertEquals(0x00, dataAvailablePort.read());
    }
}
