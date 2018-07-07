package cn.com.isurpass.zufang.typeincustomer.vo;

public class CustomerVO {
    private Integer customerid;
    private String name;
    private Integer sex;
    private String phonenumber;
    private Integer title;
    private String label;

    public Integer getCustomerid() {
        return customerid;
    }

    public void setCustomerid(Integer customerid) {
        this.customerid = customerid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public Integer getTitle() {
        return title;
    }

    public void setTitle(Integer title) {
        this.title = title;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "CustomerVO{" +
                "customerid=" + customerid +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", phonenumber='" + phonenumber + '\'' +
                ", title=" + title +
                ", label='" + label + '\'' +
                '}';
    }
}
