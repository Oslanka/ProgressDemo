package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.List;

import processdemo.oslanka.com.processdemo.R;
import utils.Programe;

/**
 * Created by caining on 17/3/31.
 */


public class ProgressAdapter extends BaseAdapter {

    private List<Programe> list;
    LayoutInflater inflater;

    Context context;

    public ProgressAdapter(Context context, List<Programe> list) {

        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.progress_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.imge = (ImageView) convertView.findViewById(R.id.iv_image);
            viewHolder.radioButton = (RadioButton) convertView.findViewById(R.id.rd_select);
            viewHolder.tv_content = (TextView) convertView.findViewById(R.id.tv_content);
            viewHolder.radioButton.setFocusable(false);
            convertView.setTag(viewHolder);
        } else
            viewHolder = (ViewHolder) convertView.getTag();
            viewHolder.imge.setImageDrawable(list.get(position).getIcon());
            viewHolder.tv_content.setText(list.get(position).getProcessName());
            viewHolder.radioButton.setChecked(list.get(position).isSelect());

        return convertView;

    }

    class ViewHolder {
        RadioButton radioButton;
        ImageView imge;
        TextView tv_content;
    }
}
