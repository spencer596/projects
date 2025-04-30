package tm;
import java.util.HashSet;
import java.util.Set;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Creates a simulation of a Turing Machine (single tape, single head) from 
 * an appropriately formatted input file. 
 * File format:
 *  <number of states (n)>
 *  <size of alphabet (1-9)>
 *  <transition list (one per line)>
 * Transition format:
 *  <read symbol>,<write symbol>,<tape head move direction>
 * Transitions should be numerically ordered by "from state" and "on symbol"
 * with blank being represented by 0. All symbols should be accounted for for
 * all states with the exception of the final state. Start state will be "0",
 * final state will be "n-1". 
 * 
 * @author Joe Shields, Spencer Pattillo
 */

public class TMSimulator
{
    private TMState[] Q; // States
    private TMState q0; // Start state
    private TMState qF; // Final state
    private final int timeLimit = 300000; // Time limit in milliseconds
    private TapeNode readHead; // Current position on tape
    private TMState currState; // Current state of machine
    private String tapeString; // Original input string
    private Set<TMState> rejectStates; // Only add states that only have self-looping transitions. Might resolve some rejects before 5 minutes elapse.
    private boolean hasReject;
    
    public TMSimulator(String filePath) throws FileNotFoundException, IOException
    {

        hasReject = false;
        readFile(filePath);
        createTape(tapeString);
        currState = q0;

    }

    /**Runs the simulation after a machine is loaded from a file
     * 
     */
    public void runSimulation() throws IOException, InterruptedException
    {
        long endTime = System.currentTimeMillis() + timeLimit;
        boolean foundEndState = false;

        while (System.currentTimeMillis() < endTime)
        {
            transition();
            if(accept())
            {
                foundEndState = true;
                printTape();
                break;
            }
            if(reject())
            {
                foundEndState = true;
                System.out.println("Reject state found. Terminating simulation");
                break;
            }
        }

        if(!foundEndState)
        {
            System.out.println("Simulation timed out. Terminating simulation.");
        }
    }

    /**Reads the symbol from the tape and peforms the associated transition
     * 
     */
    private void transition() throws IOException
    {
        int symbol = readHead.readChar();
        //System.out.println("Read char: " + symbol);
        readHead.writeChar(currState.transitWrite(symbol));
        //System.out.println("Write char: " + currState.transitWrite(symbol));
        readHead = readHead.move(currState.transitMove(symbol));
        //System.out.println("Move head: " + currState.transitMove(symbol));
        currState = currState.transitState(symbol);
        //System.out.println("New state: " + currState);
    }

    /**Checks if the current state is the accept state.
     * 
     * @return boolean
     */
    private boolean accept()
    {
        if(currState.equals(qF))
        {
            return true;
        }
        return false;
    }

    /**Checks if the current state is a reject state.
     * Reject states are determined in readFile() by finding every state for which every transition is to itself.
     * @return boolean
     */
    private boolean reject()
    {
        if(!hasReject)
        {
            return false;
        }

        if(rejectStates.contains(currState))
        {
            return true;
        }
        return false;
    }

    /**Takes a filepath, checks if it exists, and then scans it.
     * Creates all states and their transitions.
     * Instantiates the Turing Machine control module variables.
     * Reads the tape input, if any.
     * 
     * @param filePath
     * @throws FileNotFoundException
     * @throws IOException
     */
    private void readFile(String filePath) throws FileNotFoundException, IOException
    {
        File file = new File(filePath);
        Scanner fileScanner = new Scanner(file);

        rejectStates = new HashSet<>();

        // Line 1 = number of states (n). Start = 0, Accept = n-1
        int numStates = Integer.parseInt(fileScanner.nextLine().trim());


        // Line 2 = size of alphabet (1-9, 0 = blank)
        int alphaSize = Integer.parseInt(fileScanner.nextLine().trim()) + 1; // +1 to account for blank (0)

        Q = new TMState[numStates];

        // Create all states
        for(int i = 0; i < numStates; i++)
        {
            Q[i] = new TMState(i, alphaSize);
        }

        // Assign start/final states
        q0 = Q[0];
        qF = Q[numStates - 1];

        // Add all transitions
        for(int i = 0; i < numStates - 1; i++) // Stop iterating before qF
        {
            boolean isDeadEnd = true; // State does not transition to other states
            for(int j = 0; j < alphaSize; j++)
            {
                String line = fileScanner.nextLine().trim();
                String[] parts = line.split(","); // use split to split the whole text file into parts....

                if (parts.length < 3) {
                    System.out.println("Warning: Invalid transition format: " + line);
                    continue;
                }

                int nextState = Integer.parseInt(parts[0]);
                int writeSymbol = Integer.parseInt(parts[1]);
                char move = parts[2].charAt(0);
                Q[i].addTransition(j, Q[nextState], writeSymbol, move);

                if(nextState != i) // if a transition goes to a different state
                {
                    isDeadEnd = false;
                }
            }
            if(isDeadEnd)
            {
                rejectStates.add(Q[i]);
                hasReject = true;
            }
        }

        // Check if there's a tape string
        if(fileScanner.hasNextLine())
        {
            tapeString = fileScanner.nextLine().trim();
            //System.out.println("Input tape: " + tapeString);
        }
        else
        {
            tapeString = null;
        }

        fileScanner.close();
        //System.out.println("File read successfully.");
    }

    /**Takes the tape input read from readFile()
     * builds/connects the individual nodes for the tape.
     * 
     * @param string
     */
    private void createTape(String string)
    {
        if(string == null)
        {
            readHead = new TapeNode();
        }
        else
        {
            readHead = new TapeNode(string.charAt(0));

            TapeNode currNode = readHead;
            TapeNode nextNode;

            for(int i = 1; i < string.length(); i++)
            {
                nextNode = new TapeNode(string.charAt(i));
                currNode.addRight(nextNode);
                currNode = nextNode;
            }
        }

        //System.out.println("Tape created successfully.");
    }

    /**Returns the symbol content of every node in sequential order as a single string with no separators.
     * 
     * @return string
     */
    private void printTape()
    {
        String tape = readHead.getTape();
        System.out.println(tape);
        //Enable the following two lines for output summary
        //System.out.println("output length: " + tape.length());
        //System.out.println("sum of symbols: " + readHead.getSum());
    }

    public static void main(String[] args) 
    {
        if(args.length < 1)
        {
            System.out.println("Usage: $ java TMSimulator.java <filepath>");
        }

        String filepath = args[0];
        //System.out.println("File path received: " + filepath);

        try
        {
            TMSimulator turingMachine = new TMSimulator(filepath);
            turingMachine.runSimulation();
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}