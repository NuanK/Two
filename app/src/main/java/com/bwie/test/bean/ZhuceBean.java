package com.bwie.test.bean;

/**
 * Created by ASUS on 2017/10/19.
 */

public class ZhuceBean {

    /**
     * msg : 注册成功
     * code : 0
     * data : {"age":null,"createtime":"2017-10-19T00:00:00","gender":0,"icon":null,"mobile":"17710340313","money":0,"nickname":null,"password":"123456","uid":982,"username":"17710340313"}
     */

    private String msg;
    private String code;
   // private DataBean data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public static class DataBean {
        /**
         * age : null
         * createtime : 2017-10-19T00:00:00
         * gender : 0
         * icon : null
         * mobile : 17710340313
         * money : 0
         * nickname : null
         * password : 123456
         * uid : 982
         * username : 17710340313
         */

        private Object age;
        private String createtime;
        private int gender;
        private Object icon;
        private String mobile;
        private int money;
        private Object nickname;
        private String password;
        private int uid;
        private String username;

        public Object getAge() {
            return age;
        }

        public void setAge(Object age) {
            this.age = age;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public Object getIcon() {
            return icon;
        }

        public void setIcon(Object icon) {
            this.icon = icon;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }

        public Object getNickname() {
            return nickname;
        }

        public void setNickname(Object nickname) {
            this.nickname = nickname;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
