package ai.dongsheng.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author MichelleJou
 * @since 2019-11-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Favorite implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 机器人ID
     */
    private String robotId;

    /**
     * 通道ID
     */
    private Long channelId;

    /**
     * 序号，降序，越大，越靠前
     */
    private Long orderNum;

    /**
     * 专辑id
     */
    private Long albumId;

    /**
     * 专辑分类ID
     */
    private Long categoryId;

    /**
     * 资源id
     */
    private Long resId;

    /**
     * 资源类型
     */
    private Integer resType;

    /**
     * 专辑的名字
     */
    private String albumName;

    /**
     * 资源的名字
     */
    private String resName;

    /**
     * 逻辑删
     */
    private Integer deleted;

    /**
     * 修改的时间戳（毫秒）
     */
    private Date updateTime;

    /**
     * 创建时间
     */
    private Date createTime;


}
