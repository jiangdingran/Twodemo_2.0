package HomeMVP.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwie.test.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import HomeMVP.Glide.GlideImageLoader;
import HomeMVP.bean.Mysupclass;

/**
 * Created by 蒋丁然 on 2017/11/9.
 */

public class HomeAdapter extends RecyclerView.Adapter {

    private Context context;
    private Mysupclass.DataBean data;

    List list=new ArrayList();
    public HomeAdapter(Context context, Mysupclass.DataBean data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

       switch (viewType){
           case 0:
               BannerView holder0=new BannerView(LayoutInflater.from(context)
                       .inflate(R.layout.item_banner,parent,false));
               return holder0;
           case 1:
               PullView holder1 = new PullView(LayoutInflater.from(context)
                       .inflate(R.layout.item_pull, parent, false));
               return holder1;
           case 2:
               FourimageView holder2 = new FourimageView(LayoutInflater.from(context)
                       .inflate(R.layout.item_fourimg, parent, false));
               return holder2;
           case 3:
               FecyclerView holder3 = new FecyclerView(LayoutInflater.from(context)
                       .inflate(R.layout.item_recycler, parent, false));
               return holder3;
           case 4:
               ShopView holder4 = new ShopView(LayoutInflater.from(context)
                       .inflate(R.layout.item_shop, parent, false));
              return holder4;
       }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        switch (getItemViewType(position)){
            case 0:
                BannerView holder0= (BannerView) holder;
                for(int i=0;i<data.getAd1().size();i++){

                    String image = data.getAd1().get(i).getImage();
                    list.add(image);
                }
                holder0.mybanner.setImageLoader(new GlideImageLoader());
               holder0.mybanner.setImages(list);
                holder0.mybanner.start();
                break;
            case 1:
               break;
            case 2:
                FourimageView holder2= (FourimageView) holder;
                ArrayList<String> imgurl = new ArrayList<>();
                for (int i = 0; i < data.getAd5().size(); i ++) {
                    imgurl.add(i,data.getAd5().get(i).getImage());

                }
                Uri urlimg0 = Uri.parse(imgurl.get(0));
                Uri urlimg1 = Uri.parse(imgurl.get(1));
                Uri urlimg2 = Uri.parse(imgurl.get(2));
                Uri urlimg3 = Uri.parse(imgurl.get(3));

                holder2.simple.setImageURI(urlimg0);
                holder2.simple2.setImageURI(urlimg1);
                holder2.simple3.setImageURI(urlimg2);
                holder2.simple4.setImageURI(urlimg3);


                break;
            case 3:
                FecyclerView holder3= (FecyclerView) holder;
                List<Mysupclass.DataBean.SubjectsBean> list = data.getSubjects();
                holder3.recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL));
                holder3.recyclerView.setAdapter(new FecyclerViewAdapter(context,list));
                break;
            case 4:
                ShopView holder4= (ShopView) holder;
                List<Mysupclass.DataBean.DefaultGoodsListBean> listshop = data.getDefaultGoodsList();
                holder4.recyclerViewshop.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
                holder4.recyclerViewshop.setAdapter(new Goods_Adapter(context,listshop));
                break;

        }
    }


    private class BannerView extends RecyclerView.ViewHolder {
        Banner mybanner;
        public BannerView(View itemView) {
            super(itemView);
          mybanner= itemView.findViewById(R.id.mybanner);
        }
    }

  private class PullView extends RecyclerView.ViewHolder{
      public PullView(View itemView) {
          super(itemView);
      }
  }
    private class FourimageView extends RecyclerView.ViewHolder{

        SimpleDraweeView simple,simple2,simple3,simple4;
        public FourimageView(View itemView) {
            super(itemView);
             simple = itemView.findViewById(R.id.simple);
            simple2=itemView.findViewById(R.id.simple2);
            simple3=itemView.findViewById(R.id.simple3);
            simple4=itemView.findViewById(R.id.simple4);
        }
    }

    private class FecyclerView extends RecyclerView.ViewHolder{

        RecyclerView recyclerView;
        public FecyclerView(View itemView) {
            super(itemView);
           recyclerView= itemView.findViewById(R.id.id_recycler);
        }
    }
    private class ShopView extends RecyclerView.ViewHolder{

        RecyclerView recyclerViewshop;
        public ShopView(View itemView) {
            super(itemView);
            recyclerViewshop= itemView.findViewById(R.id.id_recyclershop);
        }
    }




    @Override
    public int getItemCount() {
        return 5;
    }

    @Override
    public int getItemViewType(int position) {

        if(position==0) {
            return 0;
        }else if(position==1){
            return 1;
        }else if(position==2) {
            return 2;
        }else if(position==3) {
            return 3;
        }else if(position==4){
            return 4;
        }
        return 0;
    }
}
