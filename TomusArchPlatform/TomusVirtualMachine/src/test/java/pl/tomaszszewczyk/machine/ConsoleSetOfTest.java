package pl.tomaszszewczyk.machine;

import com.nitorcreations.junit.runners.NestedRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(NestedRunner.class)
public class ConsoleSetOfTest {

    public abstract static class ConsoleTest {
        protected PrintStream out;
        protected InputStream in;

        protected Console console;

        protected Console.ReadWritePort readWritePort;
        protected Console.DataAvailablePort dataAvailablePort;
        protected Console.ControlPort controlPort;

        @Before
        public void setUp() {
            out = mock(PrintStream.class);

            console = new Console(out, in);

            readWritePort = console.getReadWritePort();
            dataAvailablePort = console.getDataAvailablePort();
            controlPort = console.getControlPort();
        }
    }

    public static class ConsoleTestDataAvailable extends ConsoleTest {

        @Before
        public void setUp() {
            ByteArrayInputStream stream = new ByteArrayInputStream("Tomek".getBytes());
            System.setIn(stream);
            in = System.in;
            super.setUp();
        }

        @Test
        public void testDataAvailable() throws IOException {
            assertNotEquals(0x00, dataAvailablePort.read());
        }

        @Test
        public void testRead() throws IOException {
            int read = readWritePort.read();
            int should_have_read = "Tomek".getBytes()[0];
            assertEquals(read, should_have_read);

            read = readWritePort.read();
            should_have_read = "Tomek".getBytes()[1];
            assertEquals(read, should_have_read);
        }

        @Test
        public void testWrite() {
            readWritePort.write('a');
            verify(out).print('a');
        }

        @Test
        public void testControlPort () {
            controlPort.write(1);
            assertEquals(0x01, controlPort.read());

            controlPort.write(0);
            assertEquals(0x00, controlPort.read());
        }
    }

    public static class ConsoleTestDataUnavailable extends ConsoleTest {

        @Before
        public void setUp() {
            in = new ByteArrayInputStream("".getBytes());
            super.setUp();
        }

        @Test
        public void testDataNotAvailable() throws IOException {
            assertEquals(0x00, dataAvailablePort.read());
        }
    }
}
