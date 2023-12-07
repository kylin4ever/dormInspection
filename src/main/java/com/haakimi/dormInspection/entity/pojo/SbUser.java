package com.haakimi.dormInspection.entity.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author lcc
 * @since 2020-04-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SbUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 微信openid
     */
    private String openid;

    private String unionid;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 性别，（0：代表女，1：代表男）
     */
    private Integer sex;

    /**
     * 用户呢称（方便后台查询使用）
     */
    private String loginName;

    /**
     * 头像
     */
    private String headImg;

    /**
     * 最近一次登录时间
     */
    private LocalDateTime loginTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 注册时间
     */
    private LocalDateTime newTime;

    /**
     * 是否删除，0：未删除，1：已删除
     */
    private Integer isDelete;


    public static final String ID = "id";

    public static final String OPENID = "openid";

    public static final String UNIONID = "unionid";

    public static final String PHONE = "phone";

    public static final String SEX = "sex";

    public static final String LOGIN_NAME = "login_name";

    public static final String HEAD_IMG = "head_img";

    public static final String LOGIN_TIME = "login_time";

    public static final String UPDATE_TIME = "update_time";

    public static final String NEW_TIME = "new_time";

    public static final String IS_DELETE = "is_delete";

}
