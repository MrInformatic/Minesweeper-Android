package wepper.mines.mineswepper.world;

import wepper.mines.mineswepper.utils.LinkedWorld;
import wepper.mines.mineswepper.world.exceptions.TooManyFlagsException;
import wepper.mines.mineswepper.world.exceptions.WinOrLoseException;
import wepper.mines.mineswepper.world.field.Field;
import wepper.mines.mineswepper.world.field.FieldListener;

/**
 * Created by philhaus16 on 08.08.2016.
 */
public class World extends LinkedWorld implements WorldInterface,FieldListener,GameInterface{
    private int flagCount;
    private int mineCount;
    private int width;
    private int height;
    private Field[][] fields;
    private int flagedMinesCount = 0;
    private boolean win;
    private boolean lose;

    public World(int width,int height,int mineCount) {
        super(width,height);
        this.flagCount = mineCount;
        this.mineCount = mineCount;
        this.width = width;
        this.height = height;

        fields = new Field[width][height];

        for(int x=0;x<width;x++){
            for(int y=0;y<height;y++){
                fields[x][y] = new Field(x,y);
                fields[x][y].setListener(this);
            }
        }

        setNodes(fields);

        int addedmines = 0;
        while(addedmines<mineCount){
            int x = (int) Math.floor(Math.random()*width);
            int y = (int) Math.floor(Math.random()*height);

            if(!fields[x][y].isMine()){
                fields[x][y].setMine();
                addedmines++;
            }
        }
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getFlagCount() {
        return flagCount;
    }

    @Override
    public Field getField(int x,int y){
        return fields[x][y];
    }

    @Override
    public boolean isLose(){
        return lose;
    }

    @Override
    public boolean isWin(){
        return win;
    }

    @Override
    public boolean isFinish(){
        return win||lose;
    }

    @Override
    public void addFlag() throws TooManyFlagsException {
        if(flagCount>0) {
            flagCount--;
        }else{
            throw new TooManyFlagsException();
        }
    }

    @Override
    public void removeFlag() {
        flagCount++;
    }

    @Override
    public void flagMine() {
        flagedMinesCount++;
        if(flagedMinesCount==mineCount){
            win = true;
        }
    }

    @Override
    public void deflagMine() {
        flagedMinesCount--;
    }

    @Override
    public void lose() {
        lose = true;
    }
}
