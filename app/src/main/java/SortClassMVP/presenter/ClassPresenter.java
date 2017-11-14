package SortClassMVP.presenter;

import java.util.List;

import SortClassMVP.ClassBean.DataleftBean;
import SortClassMVP.model.Classmodel;
import SortClassMVP.view.ClassIview;

/**
 * Created by 蒋丁然 on 2017/11/14.
 */

public class ClassPresenter implements Classmodel.OnclassFinishlister{

    private final ClassIview classIview;
    private final Classmodel classmodel;

    public ClassPresenter(ClassIview classIview) {
        this.classIview = classIview;
        this.classmodel =new Classmodel();
    }
    public void getData(String url){
        classmodel.getUrl(url);
    }

    @Override
    public void onclassfinsh(List<DataleftBean.DatasBean.ClassListBean> class_list) {
        classIview.getData(class_list);
    }
}
