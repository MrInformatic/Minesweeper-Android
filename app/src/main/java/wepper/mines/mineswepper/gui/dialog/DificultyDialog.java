package wepper.mines.mineswepper.gui.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.Preference;
import androidx.annotation.ArrayRes;
import androidx.annotation.StringRes;
import wepper.mines.mineswepper.R;

import java.util.prefs.Preferences;

/**
 * Created by philipp on 15.08.16.
 */
public class DificultyDialog implements DialogInterface.OnClickListener{
    private AlertDialog dificulty;
    private SharedPreferences preferences;
    private DificultyDialogListener listener;

    public DificultyDialog(Context context,DificultyDialogListener listener, @StringRes int title,@ArrayRes int dificulties){
        initDificultyDialog(context,title,dificulties);
        this.listener = listener;
    }

    private void initDificultyDialog(Context context, @StringRes int title, @ArrayRes int dificulties){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title)
                .setItems(dificulties,this);
        dificulty = builder.create();
    }

    public void showDificultyDialog(){
        dificulty.show();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("dificulty",which);
        editor.commit();
        listener.setDificulty(which);
    }

    public void setPreferences(SharedPreferences preferences) {
        this.preferences = preferences;
    }

    public int getDificulty(){
        return preferences.getInt("dificulty",0);
    }

    public static class DificultyDialogBuilder{
        private Context context;
        private @StringRes int title;
        private @ArrayRes int dificulties;
        private DificultyDialogListener listener;

        public DificultyDialogBuilder(Context context){
            this.context = context;
        }

        public DificultyDialogBuilder setTitle(@StringRes int title) {
            this.title = title;
            return this;
        }

        public DificultyDialogBuilder setItems(@ArrayRes int dificulties) {
            this.dificulties = dificulties;
            return this;
        }

        public DificultyDialogBuilder setListener(DificultyDialogListener listener){
            this.listener = listener;
            return this;
        }

        public DificultyDialog create(){
            return new DificultyDialog(context,listener,title,dificulties);
        }
    }

    public interface DificultyDialogListener{
        public void setDificulty(int dificulty);
    }
}
