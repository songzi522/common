package com.gs.common.bean;

/**
 * Created by Administrator on 2018/1/3.
 */

public class OrgaInfoBean {


    /**
     * code : 200
     * msg : 获取成功
     * ret : {"name":"医悦健康管理（深圳）体验中心 ","province":"广东省","city":"深圳市","district":"福田区","address":"车公庙泰然工贸园210栋西座3G","phone":"075582977404","reg_qrcode_url":"http://service.phmd247.com/h5/register.html?jg=JG100&rand=1501726623"}
     */

    private int code;
    private String msg;
    private RetBean ret;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public RetBean getRet() {
        return ret;
    }

    public void setRet(RetBean ret) {
        this.ret = ret;
    }

    public static class RetBean {
        /**
         * name : 医悦健康管理（深圳）体验中心
         * province : 广东省
         * city : 深圳市
         * district : 福田区
         * address : 车公庙泰然工贸园210栋西座3G
         * phone : 075582977404
         * reg_qrcode_url : http://service.phmd247.com/h5/register.html?jg=JG100&rand=1501726623
         */

        private String name;
        private String province;
        private String city;
        private String district;
        private String address;
        private String phone;
        private String reg_qrcode_url;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getReg_qrcode_url() {
            return reg_qrcode_url;
        }

        public void setReg_qrcode_url(String reg_qrcode_url) {
            this.reg_qrcode_url = reg_qrcode_url;
        }
    }
}
