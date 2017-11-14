package MineMVP.MineBean;

/**
 * Created by 蒋丁然 on 2017/11/13.
 */

public class ZhuceEventBusBean {
    private String mobile;
    private String password;


    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ZhuceEventBusBean(String mobile, String password) {
        this.mobile = mobile;
        this.password = password;
    }

    public ZhuceEventBusBean() {
    }
}
