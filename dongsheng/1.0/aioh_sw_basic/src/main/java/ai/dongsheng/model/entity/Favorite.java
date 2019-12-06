package ai.dongsheng.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户收藏列表
 * </p>
 *
 * @author MichelleJou
 * @since 2019-12-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Favorite对象", description="用户收藏列表")
public class Favorite implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "自增id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "设备ID")
    private String deviceId;

    @ApiModelProperty(value = "通道ID")
    private Long channelId;

    @ApiModelProperty(value = "序号，降序，越大，越靠前")
    private Long orderNum;

    @ApiModelProperty(value = "专辑id")
    private Long albumId;

    @ApiModelProperty(value = "专辑分类ID")
    private Long categoryId;

    @ApiModelProperty(value = "专辑类型")
    private Integer albumType;

    @ApiModelProperty(value = "专辑的名字")
    private String albumName;

    @ApiModelProperty(value = "资源id")
    private Long resId;

    @ApiModelProperty(value = "资源类型")
    private Integer resType;

    @ApiModelProperty(value = "资源的名字")
    private String resName;

    @ApiModelProperty(value = "逻辑删")
    private Integer deleted;

    @ApiModelProperty(value = "修改的时间戳（毫秒）")
    private Date updateTime;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;


}
