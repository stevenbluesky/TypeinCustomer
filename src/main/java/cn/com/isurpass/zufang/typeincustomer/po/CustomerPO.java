package cn.com.isurpass.zufang.typeincustomer.po;
import java.util.Date;
import javax.persistence.*;


@Entity
@Table(name = "customer")
public class CustomerPO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerid;
    private Long personid;
    private String name;
    private Integer sex;
    private String countrycode;
    private String phonenumber;
    private Integer identitytype;
    private String identity;
    private String mail;
    private Integer title;
    private String address;
    private String fingerprint;
    private String cardnumber;
    private String password;
    private String label;
    private String customerinfo;
    private Date createtime;

    public Integer getCustomerid() {
        return customerid;
    }

    public void setCustomerid(Integer customerid) {
        this.customerid = customerid;
    }

    public Long getPersonid() {
        return personid;
    }

    public void setPersonid(Long personid) {
        this.personid = personid;
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

    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public Integer getIdentitytype() {
        return identitytype;
    }

    public void setIdentitytype(Integer identitytype) {
        this.identitytype = identitytype;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Integer getTitle() {
        return title;
    }

    public void setTitle(Integer title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public String getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(String cardnumber) {
        this.cardnumber = cardnumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getCustomerinfo() {
        return customerinfo;
    }

    public void setCustomerinfo(String customerinfo) {
        this.customerinfo = customerinfo;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return "CustomerPO{" +
                "customerid=" + customerid +
                ", personid=" + personid +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", countrycode='" + countrycode + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", identitytype=" + identitytype +
                ", identity='" + identity + '\'' +
                ", mail='" + mail + '\'' +
                ", title=" + title +
                ", address='" + address + '\'' +
                ", fingerprint='" + fingerprint + '\'' +
                ", cardnumber='" + cardnumber + '\'' +
                ", password='" + password + '\'' +
                ", label='" + label + '\'' +
                ", customerinfo='" + customerinfo + '\'' +
                ", createtime=" + createtime +
                '}';
    }
}

