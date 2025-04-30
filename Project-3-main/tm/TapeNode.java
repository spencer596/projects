package tm;
import java.io.IOException;

/**
 * Provides a representation of one space on a bidirectionally infinite tape.
 * Provides read/write functionality for the symbol stored at its position.
 * Stores references to the "spaces" on either side of it.
 * Creates double linked nodes as needed in either direction.
 * Can find/return the entire representative string from any position.
 * 
 * @author Joe Shields
 */

public class TapeNode 
{
    public TapeNode left;
    public TapeNode right;
    private int symbol;
    private final int blank = 0;

    public TapeNode()
    {
        this.left = null;
        this.right = null;
        this.symbol = blank;
    }

    public TapeNode(int symbol)
    {
        this.left = null;
        this.right = null;
        this.symbol = symbol;
    }

    /**Write the given character to the tape
     * @param char
     */
    public void writeChar(int symbol)
    {
        this.symbol = symbol;
    }

    /**Read the existing character from the tape
     * @return char
     */
    public int readChar()
    {
        return this.symbol;
    }

    /**Adds a blank node to the right of this one and links them together.
     * 
     * @param node
     */
    public void addRight(TapeNode node)
    {
        this.right = node;
        node.left = this;
    }

    /**Adds a blank node to the left of this one and links them together.
     * 
     * @param node
     */
    public void addLeft(TapeNode node)
    {
        this.left = node;
        node.right = this;
    }

    /**Returns the node to the right of this one. 
     * If none exists, it creates one and returns that.
     * 
     * @return TapeNode
     */
    public TapeNode moveRight()
    {
        if(this.right == null)
        {
            TapeNode node = new TapeNode(blank);
            addRight(node);
            return node;
        }

        return right;
    }

    /**Returns the node to the left of this one. 
     * If none exists, it creates one and returns that.
     * 
     * @return TapeNode
     */
    public TapeNode moveLeft()
    {
        if(this.left == null)
        {
            TapeNode node = new TapeNode(blank);
            addLeft(node);
            return node;
        }

        return left;
    }

    /**Takes a character representing a direction to move and then
     * calls the appropriate method.
     * 
     * @param move
     * @return TapeNode
     */
    public TapeNode move(char move) throws IOException
    {
        switch(move)
        {
            case 'R':
            case 'r':
                return moveRight();
            case 'L':
            case 'l':
                return moveLeft();
            default:
                throw new IOException("Invalid move " + move + "passed to tape node.");
        }
    }

    /**Returns the symbol at this position as an int.
     * 
     * @return int
     */
    public int getSymbol()
    {
        return symbol;
    }

    /**Returns the symbol at this position as a string.
     * 
     * @return string
     */
    public String toString()
    {
        return symbol+"";
    }

    /**Navigates to the left-most node and builds the string of characters contained in all nodes from left to right.
     * 
     * @return string
     */
    public String getTape()
    {
        TapeNode node = this;
        String tape = "";

        while(node.left != null)
        {
            node = node.left;
        }

        while(node.right != null)
        {
            tape += node.toString();
            node = node.right;
        }

        tape += node.toString(); // Add final node

        return tape;
    }

    public int getSum()
    {
        TapeNode node = this;
        int tapeSum = 0;

        while(node.left != null)
        {
            node = node.left;
        }

        while(node.right != null)
        {
            tapeSum += node.getSymbol();
            node = node.right;
        }

        tapeSum += node.getSymbol(); // Add final node

        return tapeSum;
    }
}
