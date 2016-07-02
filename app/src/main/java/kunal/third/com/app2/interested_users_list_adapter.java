package kunal.third.com.app2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Kunal on 6/22/2016.
 */
public class interested_users_list_adapter extends ArrayAdapter<interested_user_object> {


    public interested_users_list_adapter(Context context, ArrayList<interested_user_object> interested_users){
        super(context, R.layout.interested_users_object, interested_users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflator = LayoutInflater.from(getContext());
        View customView = inflator.inflate(R.layout.interested_users_object, parent, false);

        interested_user_object currentInterestedUser = getItem(position);

        TextView interested_username = (TextView) customView.findViewById(R.id.interested_username);
        TextView interested_college = (TextView) customView.findViewById(R.id.interested_college);

        interested_username.setText(currentInterestedUser.getUsername());
        interested_college.setText(currentInterestedUser.getCollege());



        return customView;

    }



}
