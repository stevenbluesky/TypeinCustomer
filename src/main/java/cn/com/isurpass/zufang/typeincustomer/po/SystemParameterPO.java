package cn.com.isurpass.zufang.typeincustomer.po;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "systemparameter")
public class SystemParameterPO {
    @Id
    @GenericGenerator(name = "generator", strategy = "assigned")
    private String strkey;
    private String strvalue;
    private Integer intvalue;

    public SystemParameterPO() {
    }

    public SystemParameterPO(String strkey, String strvalue) {
        this.strkey = strkey;
        this.strvalue = strvalue;
    }

    public String getStrkey() {
        return strkey;
    }

    public void setStrkey(String strkey) {
        this.strkey = strkey;
    }

    public String getStrvalue() {
        return strvalue;
    }

    public void setStrvalue(String strvalue) {
        this.strvalue = strvalue;
    }

    public Integer getIntvalue() {
        return intvalue;
    }

    public void setIntvalue(Integer intvalue) {
        this.intvalue = intvalue;
    }
}

