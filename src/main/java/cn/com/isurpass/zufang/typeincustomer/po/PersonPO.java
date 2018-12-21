package cn.com.isurpass.zufang.typeincustomer.po;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="person" )
public class PersonPO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String countrycode;
    /**用户名*/
    @Column(name = "personcode")
    private String personCode;

    /**用户密码*/
    @Column(name = "personpassword")
    private String personPassword;

    /**姓名*/
    @Column(name = "realname")
    private String realName;

    /**证件号*/
    @Column(name = "idno")
    private String idNo;

    /**手机号*/
    private String phone;

    /**邮箱*/
    private String email;

    /**头像*/
    @Column(name = "personimgurl")
    private String personImgUrl;

    /**插入时间*/
    @Column(name = "inputdate")
    private Date inputDate;
    private Integer type;
    private Integer superpersonid;
    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }

    public String getPersonCode() {
        return personCode;
    }

    public void setPersonCode(String personCode) {
        this.personCode = personCode;
    }

    public String getPersonPassword() {
        return personPassword;
    }

    public void setPersonPassword(String personPassword) {
        this.personPassword = personPassword;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPersonImgUrl() {
        return personImgUrl;
    }

    public void setPersonImgUrl(String personImgUrl) {
        this.personImgUrl = personImgUrl;
    }

    public Date getInputDate() {
        return inputDate;
    }

    public void setInputDate(Date inputDate) {
        this.inputDate = inputDate;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSuperpersonid() {
        return superpersonid;
    }

    public void setSuperpersonid(Integer superpersonid) {
        this.superpersonid = superpersonid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
