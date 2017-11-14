package HomeMVP.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.test.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import HomeMVP.adapter.HomeAdapter;
import HomeMVP.bean.Mysupclass;
import HomeMVP.presenter.Fragmentonepresenter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 蒋丁然 on 2017/11/8.
 */

public class FragmentHome extends Fragment implements Iview {

    @BindView(R.id.address)
    ImageView address;
    @BindView(R.id.xrecylerview)
    XRecyclerView xrecylerview;
    Unbinder unbinder;
    private Fragmentonepresenter fragmentonepresenter;
    private HomeAdapter mAdapter;
    int curr=0;
    TextView tv;
    List list;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragmenthome_layout, container, false);
         tv= view.findViewById(R.id.tv);
        list=new ArrayList();
        fragmentonepresenter = new Fragmentonepresenter(this);
        fragmentonepresenter.getdata();
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void getdata(Mysupclass.DataBean data) {


        xrecylerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        xrecylerview.setAdapter(mAdapter = new HomeAdapter(getActivity(),data));


//下拉刷新
        xrecylerview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                curr=0;
                list.clear();

                xrecylerview.refreshComplete();

            }

            @Override
            public void onLoadMore() {
                curr++;

//                xrecylerview.loadMoreComplete();
            }
        });



    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
