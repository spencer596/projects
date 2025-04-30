package tm;
import java.io.IOException;

/**
 * Models a single state in a Turing Machine.
 * States are identified by integers.
 * Transition alphabet is assumed to be excludively integers which represent the index of their associated transition values.
 * 
 * @author Joe Shields
 */

public class TMState 
{
    private int name;
    private TMState[] toState;
    private int[] writeSymbol;
    private char[] tapeMove;

    public TMState(int name, int alphabetSize)
    {
        this.name = name;
        this.toState = new TMState[alphabetSize];
        this.writeSymbol = new int[alphabetSize];
        this.tapeMove = new char[alphabetSize];
    }

    /**
     * Returns the name of the TMState as an integer
     * @return int
     */
    public int getName()
    {
        return name;
    }

    /**
     * Returns the name of the TMState as a string
     * @return string
     */
    public String toString()
    {
        return name+"";
    }

    /**
     * adds a transition to the TMState
     * @param read
     * @param toState
     * @param write
     * @param tapeMove
     * @throws IOException
     */
    public void addTransition(int read, TMState toState, int write, char tapeMove) throws IOException
    {
        if(tapeMove != 'R' && tapeMove != 'L')
        {
            throw new IOException("Invalid move direction: " + tapeMove);
        }
        
        this.toState[read] = toState;
        this.writeSymbol[read] = write;
        this.tapeMove[read] = tapeMove;
    }

    /**
     * Returns the next state after the supplied symbol is read.
     * 
     * @param read
     * @return TMState
     */
    public TMState transitState(int read)
    {
        return toState[read];
    }

    /**
     * Returns the symbol to be written after the supplied symbol is read.
     * 
     * @param read
     * @return
     */
    public int transitWrite(int read)
    {
        return writeSymbol[read];
    }

    /**
     * Returns the direction in which the tape head should move after the supplied symbol is read.
     * 
     * @param read
     * @return
     */
    public char transitMove(int read)
    {
        return tapeMove[read];
    }
}
