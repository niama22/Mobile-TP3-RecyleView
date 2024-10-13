package com.example.startlist;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ShareCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.startlist.Adapter.AdapterStar;
import com.example.startlist.Bean.star;
import com.example.startlist.service.ListService;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private RecyclerView list;
    private ListService service;
    private AdapterStar adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialToolbar toolbar = findViewById(R.id.materialToolbar);
        setSupportActionBar(toolbar);

        list = findViewById(R.id.list_item);
        service = ListService.getInstance();


        service.create(new star("SpongeBob SquarePants",
                "https://yt3.ggpht.com/ytc/AKedOLS_nXv4qdyJRCn2t_l5ZY0IB5tw1OaNXJG6bjiM=s900-c-k-c0x00ffffff-no-rj", 5));
        service.create(new star("Patrick Star",
                "https://ih1.redbubble.net/image.1362613611.8246/flat,750x,075,f-pad,750x1000,f8f8f8.jpg", 3.22f));
        service.create(new star("Squidward Tentacles",
                "https://yt3.ggpht.com/a/AGF-l7_ZdRetR0KbBXe2OSjEq7Y2cbWe48SLkbbxZA=s900-c-k-c0xffffffff-no-rj-mo", 2.22f));
        service.create(new star("Eugene H. Krabs",
                "https://th.bing.com/th/id/R.c15d8d8537db66c1f2a4951fd6f17a5a?rik=HRqQxXH6%2fsY%2bOQ&riu=http%3a%2f%2fmedia.sbmania.net%2fpictures%2f44a%2f154.png&ehk=uRGpt%2fGYhkKU%2bW8yJJCrup%2ffPvaleKF8S%2b%2bvlIf8VbQ%3d&risl=&pid=ImgRaw&r=0", 4.2f));
        service.create(new star("Sheldon J. Plankton",
                "https://res.cloudinary.com/teepublic/image/private/s---w0xXaDL--/t_Preview/b_rgb:6e2229,c_limit,f_auto,h_630,q_90,w_630/v1509109320/production/designs/2004374_1.jpg", 3));
        service.create(new star("Sandy Cheeks",
                "https://pm1.narvii.com/6102/2cd8c0c970a4f4837d97c056a898c516e95654b3_hq.jpg", 1.22f));
        service.create(new star("Mrs. Puff",
                "https://th.bing.com/th/id/R.ac1b506fd1d24ae338ca319e7020fb85?rik=PFiGvwr83oy0PQ&riu=http%3a%2f%2fwww.cartoonbucket.com%2fwp-content%2fuploads%2f2015%2f12%2fMrs.Puff-Picture-rw222.jpg&ehk=cAxcnRHg%2fQZRF7ePyElwL729oEbBOmSfhxuS%2b1w4ppM%3d&risl=&pid=ImgRaw&r=0", 0.22f));

        service.create(new star("Gary the Snail",
                "https://th.bing.com/th/id/OIP.MyGvnpRMoRioGFyrP-fAKgHaHa?w=658&h=658&rs=1&pid=ImgDetMain", 5));

        service.create(new star("Pearl Krabs",
                "https://th.bing.com/th/id/OIP.AAKKa_HTVF0w9sFjtLxmGgHaFp?rs=1&pid=ImgDetMain", 4));

        adapter = new AdapterStar(service.findAll(), this);
        list.setAdapter(adapter);
        list.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.mymenu, menu);


        MenuItem searchItem = menu.findItem(R.id.search_bar);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                adapter.getFilter().filter(newText);
                return true;
            }
        });

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.share){
            String txt = "Stars";
            String mimeType = "text/plain";
            ShareCompat.IntentBuilder
                    .from(this)
                    .setType(mimeType)
                    .setChooserTitle("Stars")
                    .setText(txt)
                    .startChooser();
        }
        return super.onOptionsItemSelected(item);
    }
}
