package carlos.ifrs.com.listapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private ListView listView1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Weather weather_data[] = new Weather[]
                {
                        new Weather(R.mipmap.icon1, "Icone 1"),
                        new Weather(R.mipmap.icon2, "Icone 2"),
                        new Weather(R.mipmap.icon3, "Icone 3"),
                        new Weather(R.mipmap.icon4, "Icone 4"),
                        new Weather(R.mipmap.icon5, "Icone 5"),
                };

        WeatherAdapter adapter = new WeatherAdapter(this,
                R.layout.listview_item_row, weather_data);


        listView1 = (ListView)findViewById(R.id.listView1);

        View header = (View)getLayoutInflater().inflate(R.layout.listview_header_row, null);
        listView1.addHeaderView(header);

        listView1.setAdapter(adapter);
    }
}

