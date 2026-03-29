package wepper.mines.mineswepper.utils;

/**
 * Created by philhaus16 on 12.08.2016.
 */
public class LinkedWorld<T extends Node<T>> {
    private int width = 0;
    private int height = 0;

    public LinkedWorld(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void setNodes(T[][] nodes){
        for(int x=0;x<width;x++) {
            for (int y = 0; y < height; y++) {
                nodes[x][y].setLeft(saveGet(nodes,x-1,y));
                nodes[x][y].setRight(saveGet(nodes,x+1,y));
                nodes[x][y].setTop(saveGet(nodes,x,y-1));
                nodes[x][y].setBottom(saveGet(nodes,x,y+1));
                nodes[x][y].setTopLeft(saveGet(nodes,x-1,y-1));
                nodes[x][y].setTopRight(saveGet(nodes,x+1,y-1));
                nodes[x][y].setBottomLeft(saveGet(nodes,x-1,y+1));
                nodes[x][y].setBottomRight(saveGet(nodes,x+1,y+1));
            }
        }
    }

    /*
    Prevent OutOfBoundsExeptions
     */
    private T saveGet(T[][] nodes,int x,int y){
        if(x>=0&&x<width&&y>=0&&y<height){
            return nodes[x][y];
        }else{
            return null;
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
