package HomeMVP.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwie.test.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import HomeMVP.bean.Mysupclass;

/**
 * Created by 蒋丁然 on 2017/11/9.
 */

public class FecyclerViewAdapter  extends RecyclerView.Adapter<FecyclerViewAdapter.MyViewHolder>{

    private Context context;
    private List<Mysupclass.DataBean.SubjectsBean> list;

    public FecyclerViewAdapter(Context context, List<Mysupclass.DataBean.SubjectsBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.item_img, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position)
    {

        Uri uri = Uri.parse(list.get(position).getImage());
            holder.simple.setImageURI(uri);
    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        SimpleDraweeView simple;

        public MyViewHolder(View itemView) {
            super(itemView);
            simple= itemView.findViewById(R.id.simpleimg);

        }

    }
}
