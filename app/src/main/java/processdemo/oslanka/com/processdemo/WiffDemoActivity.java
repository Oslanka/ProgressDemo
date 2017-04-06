package processdemo.oslanka.com.processdemo;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import utils.Content;

import static processdemo.oslanka.com.processdemo.R.id.rb_;
import static utils.Content.isSelect;

/**
 * Created by caining on 17/4/5.
 */


public class WiffDemoActivity extends AppCompatActivity {

    private ListView listView;
    private WifiManager wifiManager;
    private List<ScanResult> list;
    private WiffAdapter wiffAdapter;
    private int lastposition,nextposition;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wiffdemo);
        listView= (ListView) findViewById(R.id.lv_wiff);
        list=new ArrayList<>();
        wiffAdapter=new WiffAdapter();
        inflater=getLayoutInflater();

        initData();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RadioButton radio= (RadioButton) ((RelativeLayout) view).getChildAt(1);
                radio.setChecked(true);
                Toast.makeText(WiffDemoActivity.this,"点击了"+position,Toast.LENGTH_SHORT).show();

                for (ScanResult item:list) {
                    if (item==list.get(position)){
                        isSelect=true;
                    }else{
                        isSelect=false;
                    }
                }
                wiffAdapter.notifyDataSetChanged();
            }

        });

    }

    private void initData() {
        wifiManager= (WifiManager) getSystemService(Context.WIFI_SERVICE);
        list=wifiManager.getScanResults();
        listView.setAdapter(wiffAdapter);

    }

    private LayoutInflater inflater;

    private class WiffAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            final ViewHolder viewHolder;
            if (convertView==null){
                convertView=inflater.inflate(R.layout.wiff_item,parent,false);
                viewHolder=new ViewHolder();
                viewHolder.tv_text= (TextView) convertView.findViewById(R.id.tv_wiff);
                viewHolder.rb_= (RadioButton) convertView.findViewById(rb_);
                convertView.setTag(viewHolder);

            }else{
                viewHolder= (ViewHolder) convertView.getTag();
            }



             if (list!=null){
                 viewHolder.tv_text.setText(list.get(position).SSID);
                 viewHolder.rb_.setChecked(Content.isSelect);

             }else{
                 listView.setVisibility(View.GONE);

             }





            return convertView;
        }

    }
    class  ViewHolder{
        TextView tv_text;
        RadioButton rb_;
    }
}

