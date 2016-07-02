package kunal.third.com.app2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Kunal on 6/22/2016.
 */
public class textbook_list_self_adapter extends ArrayAdapter<textbook_object> {

    public textbook_list_self_adapter(Context context, ArrayList<textbook_object> textbooks) {
        super(context, R.layout.textbook_list_self_object, textbooks);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = convertView;

        customView = inflater.inflate(R.layout.textbook_list_self_object, parent, false);

        textbook_object currentTextbook = getItem(position);

        TextView textbook_title = (TextView) customView.findViewById(R.id.display_list_textbook_title);
        TextView textbook_subject = (TextView) customView.findViewById(R.id.display_list_textbook_subject);
        TextView textbook_author = (TextView) customView.findViewById(R.id.display_list_textbook_author);
        TextView textbook_number_interetsed = (TextView) customView.findViewById(R.id.display_list_number_interested_users);
        ImageView textbook_image = (ImageView) customView.findViewById(R.id.display_list_textbook_image);

        textbook_title.setText(currentTextbook.getTextbook_title().toString());
        textbook_author.setText(currentTextbook.getTextbook_author().toString());
        textbook_subject.setText(currentTextbook.getTextbook_subject().toString());
        textbook_number_interetsed.setText("Users Interested:" + currentTextbook.getTextbook_intrested().size());
        textbook_image.setImageResource(R.drawable.book);
        return customView;

    }



}
