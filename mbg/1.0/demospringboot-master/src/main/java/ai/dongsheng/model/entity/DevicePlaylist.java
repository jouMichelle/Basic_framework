package ai.dongsheng.model.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "device_playlist")
public class DevicePlaylist {
    /**
     * 自增ID
     */
    @Id
    private Long id;

    /**
     * 设备ID
     */
    @Column(name = "device_id")
    private Long deviceId;

    /**
     * 序号，降序，越大，越靠前
     */
    @Column(name = "order_num")
    private Long orderNum;

    /**
     * 专辑ID
     */
    @Column(name = "album_id")
    private Long albumId;

    /**
     * 专辑分类ID
     */
    @Column(name = "category_id")
    private Long categoryId;

    /**
     * 专辑类型
     */
    @Column(name = "album_type")
    private Byte albumType;

    /**
     * 专辑的名字
     */
    @Column(name = "album_name")
    private String albumName;

    /**
     * 资源ID
     */
    @Column(name = "res_id")
    private Long resId;

    /**
     * 资源类型
     */
    @Column(name = "res_type")
    private Byte resType;

    /**
     * 资源的名字
     */
    @Column(name = "res_name")
    private String resName;

    /**
     * 逻辑删
     */
    private Byte deleted;

    /**
     * 修改的时间戳（毫秒）
     */
    @Column(name = "sync_key")
    private Long syncKey;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 获取自增ID
     *
     * @return id - 自增ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置自增ID
     *
     * @param id 自增ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取设备ID
     *
     * @return device_id - 设备ID
     */
    public Long getDeviceId() {
        return deviceId;
    }

    /**
     * 设置设备ID
     *
     * @param deviceId 设备ID
     */
    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * 获取序号，降序，越大，越靠前
     *
     * @return order_num - 序号，降序，越大，越靠前
     */
    public Long getOrderNum() {
        return orderNum;
    }

    /**
     * 设置序号，降序，越大，越靠前
     *
     * @param orderNum 序号，降序，越大，越靠前
     */
    public void setOrderNum(Long orderNum) {
        this.orderNum = orderNum;
    }

    /**
     * 获取专辑ID
     *
     * @return album_id - 专辑ID
     */
    public Long getAlbumId() {
        return albumId;
    }

    /**
     * 设置专辑ID
     *
     * @param albumId 专辑ID
     */
    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    /**
     * 获取专辑分类ID
     *
     * @return category_id - 专辑分类ID
     */
    public Long getCategoryId() {
        return categoryId;
    }

    /**
     * 设置专辑分类ID
     *
     * @param categoryId 专辑分类ID
     */
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * 获取专辑类型
     *
     * @return album_type - 专辑类型
     */
    public Byte getAlbumType() {
        return albumType;
    }

    /**
     * 设置专辑类型
     *
     * @param albumType 专辑类型
     */
    public void setAlbumType(Byte albumType) {
        this.albumType = albumType;
    }

    /**
     * 获取专辑的名字
     *
     * @return album_name - 专辑的名字
     */
    public String getAlbumName() {
        return albumName;
    }

    /**
     * 设置专辑的名字
     *
     * @param albumName 专辑的名字
     */
    public void setAlbumName(String albumName) {
        this.albumName = albumName == null ? null : albumName.trim();
    }

    /**
     * 获取资源ID
     *
     * @return res_id - 资源ID
     */
    public Long getResId() {
        return resId;
    }

    /**
     * 设置资源ID
     *
     * @param resId 资源ID
     */
    public void setResId(Long resId) {
        this.resId = resId;
    }

    /**
     * 获取资源类型
     *
     * @return res_type - 资源类型
     */
    public Byte getResType() {
        return resType;
    }

    /**
     * 设置资源类型
     *
     * @param resType 资源类型
     */
    public void setResType(Byte resType) {
        this.resType = resType;
    }

    /**
     * 获取资源的名字
     *
     * @return res_name - 资源的名字
     */
    public String getResName() {
        return resName;
    }

    /**
     * 设置资源的名字
     *
     * @param resName 资源的名字
     */
    public void setResName(String resName) {
        this.resName = resName == null ? null : resName.trim();
    }

    /**
     * 获取逻辑删
     *
     * @return deleted - 逻辑删
     */
    public Byte getDeleted() {
        return deleted;
    }

    /**
     * 设置逻辑删
     *
     * @param deleted 逻辑删
     */
    public void setDeleted(Byte deleted) {
        this.deleted = deleted;
    }

    /**
     * 获取修改的时间戳（毫秒）
     *
     * @return sync_key - 修改的时间戳（毫秒）
     */
    public Long getSyncKey() {
        return syncKey;
    }

    /**
     * 设置修改的时间戳（毫秒）
     *
     * @param syncKey 修改的时间戳（毫秒）
     */
    public void setSyncKey(Long syncKey) {
        this.syncKey = syncKey;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}