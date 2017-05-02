package com.kexie.acloud.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.kexie.acloud.domain.JsonSerializer.MajorConvert;
import com.kexie.acloud.domain.JsonSerializer.MajorDeserializer;
import com.kexie.acloud.domain.JsonSerializer.MajorSerializer;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Created : wen
 * DateTime : 16-11-18 上午12:12
 * Description : 用户实体
 */
@Entity
public class User {

    // 用户登录表单验证接口,直接写一个空接口就可以了
    public interface LoginForm {
    }

    public interface RegisterForm {
    }

    // 返回用户信息不需要返回的字段
    public static final String[] CLIENT_IGNORE_FIELD = new String[]{
            "password", "salt", "hash"
    };

    // 用户Id
    @Id
    @Email(message = "你确定你写的是邮箱？",
            groups = {LoginForm.class, RegisterForm.class})
    private String userId;

    // 密码
    @Transient
    @Length(min = 6,
            message = "密码要大于6,字母数字你随意",
            groups = {LoginForm.class, RegisterForm.class})
    private String password;

    // 随机盐
    private String salt;

    // MD5(salt + password)
    private String hash;

    // 真实姓名
    private String realName;

    // 昵称
    @Length(min = 1, max = 10, message = "昵称长度：1-10", groups = RegisterForm.class)
    private String nickName;

    // 学号
    @Length(min = 1, message = "学号不能为空", groups = RegisterForm.class)
    private String stuId;

    // 专业
    @ManyToOne
    @NotNull(message = "专业不能为空", groups = RegisterForm.class)
    @JSONField(serializeUsing = MajorSerializer.class, deserializeUsing = MajorDeserializer.class)
    @Convert(converter = MajorConvert.class)
    private Major major;

    // 班级
    private String classNum;

    // 电话号码
    @NotNull(message = "手机号码不能为空", groups = RegisterForm.class)
    private long phone;

    // 性别
    @NotNull(message = "你是男还是女啊", groups = RegisterForm.class)
    private int gender;

    // 图片Url TODO: 2017/4/30 设置默认值
    private String logoUrl;

    public User() {
    }

    public User(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"userId\":\"")
                .append(userId).append('\"');
        sb.append(",\"password\":\"")
                .append(password).append('\"');
        sb.append(",\"salt\":\"")
                .append(salt).append('\"');
        sb.append(",\"hash\":\"")
                .append(hash).append('\"');
        sb.append(",\"realName\":\"")
                .append(realName).append('\"');
        sb.append(",\"nickName\":\"")
                .append(nickName).append('\"');
        sb.append(",\"stuId\":\"")
                .append(stuId).append('\"');
        sb.append(",\"major\":")
                .append(major);
        sb.append(",\"classNum\":\"")
                .append(classNum).append('\"');
        sb.append(",\"phone\":")
                .append(phone);
        sb.append(",\"gender\":")
                .append(gender);
        sb.append(",\"logoUrl\":\"")
                .append(logoUrl).append('\"');
        sb.append('}');
        return sb.toString();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }
}

