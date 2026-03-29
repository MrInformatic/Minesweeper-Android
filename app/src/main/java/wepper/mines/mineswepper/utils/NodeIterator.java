package wepper.mines.mineswepper.utils;

import wepper.mines.mineswepper.utils.Node;

/**
 * Created by philhaus16 on 12.08.2016.
 */
public interface NodeIterator {
    public Node left();
    public Node right();
    public Node top();
    public Node bottom();
    public Node topLeft();
    public Node topRight();
    public Node bottomLeft();
    public Node bottomRight();

    public boolean hasLeft();
    public boolean hasRight();
    public boolean hasTop();
    public boolean hasBottom();
    public boolean hasTopLeft();
    public boolean hasTopRight();
    public boolean hasBottomLeft();
    public boolean hasBottomRight();
}
