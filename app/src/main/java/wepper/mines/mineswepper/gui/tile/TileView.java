package wepper.mines.mineswepper.gui.tile;

import android.content.Context;
import androidx.appcompat.widget.AppCompatImageView;

/**
 * Created by philipp on 15.08.16.
 */
public class TileView extends AppCompatImageView {
    public TileView(Context context) {
        super(context);
    }

    public void setDrawable(int id){
        if(id>-1) {
            this.setBackgroundResource(id);
        }
    }
}
