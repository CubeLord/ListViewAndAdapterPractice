package markovic.milorad.adapterpractice;

import android.content.Context;
import android.content.res.Resources;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView list = findViewById(R.id.listView);
        list.setAdapter(new MyContactsAdapter(this));
    }
}

class Contact {
    Integer img;
    String name;
    String index;
    String date;

    Contact(Integer img, String name, String index, String date){
        this.img = img;
        this.name = name;
        this.index = index;
        this.date = date;
    }
}

class MyContactsAdapter extends BaseAdapter {

    ArrayList<Contact> list;
    Context context;

    public MyContactsAdapter(Context c) {
        context = c;
        list = new ArrayList<Contact>();
        Resources res = context.getResources();
        String[] names = res.getStringArray(R.array.names);
        String[] indexes =res.getStringArray(R.array.indexes);
        String[] dates = res.getStringArray(R.array.dates);

        for (int i = 0; i < (res.getStringArray(R.array.names)).length; i++)
        {
            list.add(new Contact(R.mipmap.ic_launcher_round, names[i], indexes[i], dates[i]));
        }
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.listitem, viewGroup, false);

        TextView name = row.findViewById(R.id.name);
        TextView index = row.findViewById(R.id.index);
        TextView date = row.findViewById(R.id.date);
        ImageView imgv = row.findViewById(R.id.Image);



        final Contact contact = list.get(i);
        name.setText(contact.name);
        index.setText(contact.index);
        date.setText(contact.date);
        imgv.setImageResource(contact.img);

        imgv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(context, "Image Clicked!", Toast.LENGTH_LONG);
                toast.show();
            }
        });

        return row;
    }
}