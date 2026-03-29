package wepper.mines.mineswepper.gui.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import androidx.annotation.StringRes;

/**
 * Created by philipp on 15.08.16.
 */
public class GameDialog implements DialogInterface.OnClickListener{
    private AlertDialog winDialog;
    private AlertDialog loseDialog;
    private GameDialogListener listener;

    public GameDialog(Context context,GameDialogListener listener,@StringRes int winMessage,@StringRes int loseMessage,@StringRes int positiveButton,@StringRes int negativeButton) {
        initWinDialog(context,winMessage,positiveButton,negativeButton);
        initLoseDialog(context,loseMessage,positiveButton,negativeButton);
        this.listener = listener;
    }

    private void initWinDialog(Context context,@StringRes int winMessage,@StringRes int positiveButton,@StringRes int negativeButton){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(winMessage)
                .setPositiveButton(positiveButton, this)
                .setNegativeButton(negativeButton, this);
        winDialog = builder.create();
    }

    private void initLoseDialog(Context context,@StringRes int loseMessage,@StringRes int positiveButton,@StringRes int negativeButton){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(loseMessage)
                .setPositiveButton(positiveButton, this)
                .setNegativeButton(negativeButton, this);
        loseDialog = builder.create();
    }

    public void showWinDialog(){
        winDialog.show();
    }

    public void showLoseDialog(){
        loseDialog.show();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if(which == AlertDialog.BUTTON_NEGATIVE){
            listener.finish();
        }else if(which == AlertDialog.BUTTON_POSITIVE){
            listener.restart();
        }
    }

    public static class GameDialogBuilder{
        private Context context;
        private GameDialogListener listener;
        private @StringRes int winMessage;
        private @StringRes int loseMessage;
        private @StringRes int positiveButton;
        private @StringRes int negativeButton;

        public GameDialogBuilder(Context context){
            this.context = context;
        }

        public GameDialogBuilder setWinMessage(@StringRes int winMessage) {
            this.winMessage = winMessage;
            return this;
        }

        public GameDialogBuilder setLoseMessage(@StringRes int loseMessage) {
            this.loseMessage = loseMessage;
            return this;
        }

        public GameDialogBuilder setPositiveButton(@StringRes int positiveButton) {
            this.positiveButton = positiveButton;
            return this;
        }

        public GameDialogBuilder setNegativeButton(@StringRes int negativeButton) {
            this.negativeButton = negativeButton;
            return this;
        }

        public GameDialogBuilder setListener(GameDialogListener listener){
            this.listener = listener;
            return this;
        }

        public GameDialog create(){
            return new GameDialog(context,listener,winMessage,loseMessage,positiveButton,negativeButton);
        }
    }

    public interface GameDialogListener{
        public void finish();

        public void restart();
    }
}
