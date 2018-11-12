package com.gs.common.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/30.
 */

public class TestBean {


    /**
     * code : 200
     * msg : 查询成功
     * ret : [{"id":9,"uid":2,"sex":"male","symptom":"关节疼痛、无明显急缓特征、关节酸困","result":["风湿性关节炎/2269/风湿病科","炎症性肠病性关节炎/2367/风湿病科","创伤性骨关节炎/4735/骨科"],"title":"智能自诊记录","created_at":"2018-01-11 16:27:03","islock":1,"iscollect":1},{"id":8,"uid":2,"sex":"male","symptom":"关节疼痛、无明显急缓特征、关节酸困","result":["风湿性关节炎/2269/风湿病科","炎症性肠病性关节炎/2367/风湿病科","创伤性骨关节炎/4735/骨科"],"title":"智能自诊记录","created_at":"2018-01-11 16:27:03","islock":1,"iscollect":1},{"id":7,"uid":2,"sex":"male","symptom":"关节疼痛、无明显急缓特征、关节酸困","result":["风湿性关节炎/2269/风湿病科","炎症性肠病性关节炎/2367/风湿病科","创伤性骨关节炎/4735/骨科"],"title":"智能自诊记录","created_at":"2018-01-11 16:27:03","islock":1,"iscollect":1}]
     */

    private int code;
    private String msg;
    private List<RetBean> ret;

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

    public List<RetBean> getRet() {
        return ret;
    }

    public void setRet(List<RetBean> ret) {
        this.ret = ret;
    }

    public static class RetBean {
        /**
         * id : 9
         * uid : 2
         * sex : male
         * symptom : 关节疼痛、无明显急缓特征、关节酸困
         * result : ["风湿性关节炎/2269/风湿病科","炎症性肠病性关节炎/2367/风湿病科","创伤性骨关节炎/4735/骨科"]
         * title : 智能自诊记录
         * created_at : 2018-01-11 16:27:03
         * islock : 1
         * iscollect : 1
         */

        private int id;
        private int uid;
        private String sex;
        private String symptom;
        private String title;
        private String created_at;
        private int islock;
        private int iscollect;
        private List<String> result;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getSymptom() {
            return symptom;
        }

        public void setSymptom(String symptom) {
            this.symptom = symptom;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public int getIslock() {
            return islock;
        }

        public void setIslock(int islock) {
            this.islock = islock;
        }

        public int getIscollect() {
            return iscollect;
        }

        public void setIscollect(int iscollect) {
            this.iscollect = iscollect;
        }

        public List<String> getResult() {
            return result;
        }

        public void setResult(List<String> result) {
            this.result = result;
        }
    }
}
