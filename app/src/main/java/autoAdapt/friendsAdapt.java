package autoAdapt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.login_bmob.R;

import java.util.ArrayList;

import entity.admin;

public class friendsAdapt extends BaseAdapter {

    private ArrayList<admin>ad;
    private Context context;

    public friendsAdapt(ArrayList<admin> ad, Context context){
    this.ad=ad;
    this.context=context;
    }
    @Override
    public int getCount() {
        return ad.size();
    }

    @Override
    public Object getItem(int position) {
        return ad.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.friendslist,null);
            holder=new ViewHolder();
            holder.fname=convertView.findViewById(R.id.fname);
            holder.femail=convertView.findViewById(R.id.email);
            convertView.setTag(holder);
        }else {
            holder=(ViewHolder) convertView.getTag();
        }


            holder.fname.setText(ad.get(position).getAdname());
            holder.femail.setText(ad.get(position).getEmail());
            return convertView;

    }

    class ViewHolder{
        TextView fname;
        TextView femail;
    }
}
