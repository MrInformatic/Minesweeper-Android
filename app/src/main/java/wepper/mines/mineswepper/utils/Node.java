package wepper.mines.mineswepper.utils;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by philhaus16 on 08.08.2016.
 */
public class Node<T extends Node> {
    private T top;
    private T bottom;
    private T left;
    private T right;
    private T topLeft;
    private T topRight;
    private T bottomLeft;
    private T bottomRight;
    private int x;
    private int y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public T getTop(){
        return top;
    }

    public T getBottom(){
        return bottom;
    }

    public T getLeft(){
        return left;
    }

    public T getRight(){
        return right;
    }

    public T getTopLeft(){
        return topLeft;
    }

    public T getTopRight(){
        return topRight;
    }

    public T getBottomLeft(){
        return bottomLeft;
    }

    public T getBottomRight(){
        return bottomRight;
    }

    public void setTop(T top){
        this.top = top;
    }

    public void setBottom(T bottom) {
        this.bottom = bottom;
    }

    public void setLeft(T left) {
        this.left = left;
    }

    public void setRight(T right) {
        this.right = right;
    }

    public void setTopLeft(T topLeft) {
        this.topLeft = topLeft;
    }

    public void setTopRight(T topRight) {
        this.topRight = topRight;
    }

    public void setBottomLeft(T bottomLeft) {
        this.bottomLeft = bottomLeft;
    }

    public void setBottomRight(T bottomRight) {
        this.bottomRight = bottomRight;
    }

    public Collection<T> getNeighbors(){
        LinkedList<T> result = new LinkedList<>();
        saveAdd(result,top);
        saveAdd(result,bottom);
        saveAdd(result,left);
        saveAdd(result,right);
        saveAdd(result,topLeft);
        saveAdd(result,topRight);
        saveAdd(result,bottomLeft);
        saveAdd(result,bottomRight);
        return result;
    }

    /*
    Prvent NullPointerExeption
     */
    private void saveAdd(LinkedList<T> list,T type){
        if(type!=null){
            list.add(type);
        }
    }
}
