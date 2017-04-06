package processdemo.oslanka.com.processdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.List;

import adapter.ProgressAdapter;
import utils.ProcessInfo;
import utils.Programe;


public class MainActivity extends AppCompatActivity {
    private ListView lv_listView;
    private Button btn_start,btn_wiff;
    private List<Programe> list;
    private ProcessInfo processInfo;
    ProgressAdapter progressAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        lv_listView= (ListView) findViewById(R.id.lv_list);
        btn_start= (Button) findViewById(R.id.btn_start);
        btn_wiff= (Button) findViewById(R.id.btn_wiff);
        processInfo=new ProcessInfo();
        list=new ArrayList<Programe>();
        list=processInfo.getAllPackages(MainActivity.this);
        lv_listView.setSelection(0);
        progressAdapter=new ProgressAdapter(MainActivity.this,list);
        lv_listView.setAdapter(progressAdapter);

        lv_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Programe item = (Programe) parent.getAdapter().getItem(position);
                RadioButton radioButton= (RadioButton) ((LinearLayout) view).getChildAt(0);
                radioButton.setChecked(true);
                for(Programe bean:list) {
                   if (bean==list.get(position))
                      bean.setSelect(true);
                   else
                       bean.setSelect(false);

                }
                progressAdapter.notifyDataSetChanged();
            }
        });

        btn_wiff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,WiffDemoActivity.class);
                startActivity(intent);
            }
        });

    }



}
