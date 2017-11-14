package HomeMVP.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwie.test.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import HomeMVP.bean.Mysupclass;

/**
 * Created by 蒋丁然 on 2017/11/10.
 */

public class Goods_Adapter extends RecyclerView.Adapter<Goods_Adapter.MyViewHolder>{

    private Context context;
    private  List<Mysupclass.DataBean.DefaultGoodsListBean> listshop;

    public Goods_Adapter(Context context, List<Mysupclass.DataBean.DefaultGoodsListBean> listshop) {
        this.context = context;
        this.listshop = listshop;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.item_goods, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position)
    {
        Uri uri = Uri.parse(listshop.get(position).getGoods_img());
        holder.simpleshop.setImageURI(uri);
        holder.tnum.setText(listshop.get(position).getGoods_name());
    }

    @Override
    public int getItemCount()
    {
        return listshop.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        SimpleDraweeView simpleshop;
        TextView tnum;
        public MyViewHolder(View itemView) {
            super(itemView);
            simpleshop= itemView.findViewById(R.id.simpleshop);
           tnum= itemView.findViewById(R.id.id_num);
        }

    }
}
