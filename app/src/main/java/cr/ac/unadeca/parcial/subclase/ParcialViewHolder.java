package cr.ac.unadeca.parcial.subclase;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import org.sufficientlysecure.htmltextview.HtmlTextView;

import cr.ac.unadeca.parcial.R;

/**
 * Created by pc on 1/3/2018.
 */

public class ParcialViewHolder extends RecyclerView.ViewHolder {
    public HtmlTextView html;
    public ImageView borrar;
    public ParcialViewHolder(View itemView) {
        super(itemView);
        html = itemView.findViewById(R.id.html_text);
        borrar = itemView.findViewById(R.id.delete);

    }
}
