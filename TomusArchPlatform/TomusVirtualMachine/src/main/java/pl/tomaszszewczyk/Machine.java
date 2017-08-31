package pl.tomaszszewczyk;

/**
 * Machine class
 *
 */
public class Machine 
{
	private RandomAccessMemory ram;
	private ProgramMemory	   program_memory;
	private ProccesingUnit	   cpu;
	private TextConsole		   console;

    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
}
