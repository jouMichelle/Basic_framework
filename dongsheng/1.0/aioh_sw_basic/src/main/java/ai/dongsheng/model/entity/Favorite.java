package ai.dongsheng.model.entity;

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
 * @author MichelleJou
 * @since 2019-11-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Favorite implements Serializable {

    private static final long serialVersionUID = 1L;

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
    private LocalDateTime updateTime;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


}
