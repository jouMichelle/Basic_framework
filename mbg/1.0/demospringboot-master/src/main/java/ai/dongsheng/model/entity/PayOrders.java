package ai.dongsheng.model.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "pay_orders")
public class PayOrders {
    /**
     * 主键
     */
    @Id
    private Long id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 订单系统订单号，必须唯一
     */
    @Column(name = "order_id")
    private String orderId;

    /**
     * 第三方用户标识，目前只存喜盟
     */
    @Column(name = "open_id")
    private String openId;

    /**
     * 订单来源，如 喜盟、小智、动声APP等
     */
    @Column(name = "order_source")
    private String orderSource;

    /**
     * 订单来源系统，0： Android，1：IOS,  2 ：H5 ,3  PC  
     */
    @Column(name = "source_system")
    private Byte sourceSystem;

    /**
     * 商品id
     */
    @Column(name = "goods_id")
    private String goodsId;

    /**
     * 商品名称
     */
    @Column(name = "goods_name")
    private String goodsName;

    /**
     * 商品描述
     */
    @Column(name = "goods_description")
    private String goodsDescription;

    /**
     * 订单金额 单位（分）
     */
    @Column(name = "order_price")
    private Integer orderPrice;

    /**
     * 异步回调通知地址
     */
    @Column(name = "notify_url")
    private String notifyUrl;

    /**
     * 支付系统订单号
     */
    @Column(name = "pay_order_id")
    private String payOrderId;

    /**
     * 支付金额，单位（分）
     */
    @Column(name = "pay_price")
    private Integer payPrice;

    /**
     * 币种 0  RMB(人民币)，默认 0
     */
    private Byte currency;

    /**
     * 支付状态 0  待支付，1  取消支付，2 支付成功，3 支付失败，4 支付中 ，默认 0
     */
    private Byte status;

    /**
     * 第三方订单号
     */
    @Column(name = "third_party_order")
    private String thirdPartyOrder;

    /**
     * 第三方回调信息
     */
    @Column(name = "`third party notification`")
    private String thirdPartyNotification;

    /**
     * 支付类型, 0 支付宝APP支付 ，1 微信APP支付， 2 微信公众号支付 ， 3 喜盟APP支付 
     */
    @Column(name = "pay_type")
    private Byte payType;

    /**
     * 支付渠道编码   比如：1 为支付宝第一个，2 为支付宝第二个
     */
    @Column(name = "pay_channel")
    private String payChannel;

    /**
     * 通知状态 0  未通知，1 通知中，2 通知成功，3 通知失败，默认 0
     */
    @Column(name = "notify_status")
    private Byte notifyStatus;

    /**
     * 通知次数, 默认 0
     */
    @Column(name = "notify_amount")
    private Integer notifyAmount;

    /**
     * 乐观锁
     */
    private Integer revision;

    /**
     * 通知时间
     */
    @Column(name = "notify_times")
    private Date notifyTimes;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "updated_time")
    private Date updatedTime;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * 获取订单系统订单号，必须唯一
     *
     * @return order_id - 订单系统订单号，必须唯一
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * 设置订单系统订单号，必须唯一
     *
     * @param orderId 订单系统订单号，必须唯一
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    /**
     * 获取第三方用户标识，目前只存喜盟
     *
     * @return open_id - 第三方用户标识，目前只存喜盟
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * 设置第三方用户标识，目前只存喜盟
     *
     * @param openId 第三方用户标识，目前只存喜盟
     */
    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    /**
     * 获取订单来源，如 喜盟、小智、动声APP等
     *
     * @return order_source - 订单来源，如 喜盟、小智、动声APP等
     */
    public String getOrderSource() {
        return orderSource;
    }

    /**
     * 设置订单来源，如 喜盟、小智、动声APP等
     *
     * @param orderSource 订单来源，如 喜盟、小智、动声APP等
     */
    public void setOrderSource(String orderSource) {
        this.orderSource = orderSource == null ? null : orderSource.trim();
    }

    /**
     * 获取订单来源系统，0： Android，1：IOS,  2 ：H5 ,3  PC  
     *
     * @return source_system - 订单来源系统，0： Android，1：IOS,  2 ：H5 ,3  PC  
     */
    public Byte getSourceSystem() {
        return sourceSystem;
    }

    /**
     * 设置订单来源系统，0： Android，1：IOS,  2 ：H5 ,3  PC  
     *
     * @param sourceSystem 订单来源系统，0： Android，1：IOS,  2 ：H5 ,3  PC  
     */
    public void setSourceSystem(Byte sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    /**
     * 获取商品id
     *
     * @return goods_id - 商品id
     */
    public String getGoodsId() {
        return goodsId;
    }

    /**
     * 设置商品id
     *
     * @param goodsId 商品id
     */
    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId == null ? null : goodsId.trim();
    }

    /**
     * 获取商品名称
     *
     * @return goods_name - 商品名称
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * 设置商品名称
     *
     * @param goodsName 商品名称
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    /**
     * 获取商品描述
     *
     * @return goods_description - 商品描述
     */
    public String getGoodsDescription() {
        return goodsDescription;
    }

    /**
     * 设置商品描述
     *
     * @param goodsDescription 商品描述
     */
    public void setGoodsDescription(String goodsDescription) {
        this.goodsDescription = goodsDescription == null ? null : goodsDescription.trim();
    }

    /**
     * 获取订单金额 单位（分）
     *
     * @return order_price - 订单金额 单位（分）
     */
    public Integer getOrderPrice() {
        return orderPrice;
    }

    /**
     * 设置订单金额 单位（分）
     *
     * @param orderPrice 订单金额 单位（分）
     */
    public void setOrderPrice(Integer orderPrice) {
        this.orderPrice = orderPrice;
    }

    /**
     * 获取异步回调通知地址
     *
     * @return notify_url - 异步回调通知地址
     */
    public String getNotifyUrl() {
        return notifyUrl;
    }

    /**
     * 设置异步回调通知地址
     *
     * @param notifyUrl 异步回调通知地址
     */
    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl == null ? null : notifyUrl.trim();
    }

    /**
     * 获取支付系统订单号
     *
     * @return pay_order_id - 支付系统订单号
     */
    public String getPayOrderId() {
        return payOrderId;
    }

    /**
     * 设置支付系统订单号
     *
     * @param payOrderId 支付系统订单号
     */
    public void setPayOrderId(String payOrderId) {
        this.payOrderId = payOrderId == null ? null : payOrderId.trim();
    }

    /**
     * 获取支付金额，单位（分）
     *
     * @return pay_price - 支付金额，单位（分）
     */
    public Integer getPayPrice() {
        return payPrice;
    }

    /**
     * 设置支付金额，单位（分）
     *
     * @param payPrice 支付金额，单位（分）
     */
    public void setPayPrice(Integer payPrice) {
        this.payPrice = payPrice;
    }

    /**
     * 获取币种 0  RMB(人民币)，默认 0
     *
     * @return currency - 币种 0  RMB(人民币)，默认 0
     */
    public Byte getCurrency() {
        return currency;
    }

    /**
     * 设置币种 0  RMB(人民币)，默认 0
     *
     * @param currency 币种 0  RMB(人民币)，默认 0
     */
    public void setCurrency(Byte currency) {
        this.currency = currency;
    }

    /**
     * 获取支付状态 0  待支付，1  取消支付，2 支付成功，3 支付失败，4 支付中 ，默认 0
     *
     * @return status - 支付状态 0  待支付，1  取消支付，2 支付成功，3 支付失败，4 支付中 ，默认 0
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置支付状态 0  待支付，1  取消支付，2 支付成功，3 支付失败，4 支付中 ，默认 0
     *
     * @param status 支付状态 0  待支付，1  取消支付，2 支付成功，3 支付失败，4 支付中 ，默认 0
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 获取第三方订单号
     *
     * @return third_party_order - 第三方订单号
     */
    public String getThirdPartyOrder() {
        return thirdPartyOrder;
    }

    /**
     * 设置第三方订单号
     *
     * @param thirdPartyOrder 第三方订单号
     */
    public void setThirdPartyOrder(String thirdPartyOrder) {
        this.thirdPartyOrder = thirdPartyOrder == null ? null : thirdPartyOrder.trim();
    }

    /**
     * 获取第三方回调信息
     *
     * @return third party notification - 第三方回调信息
     */
    public String getThirdPartyNotification() {
        return thirdPartyNotification;
    }

    /**
     * 设置第三方回调信息
     *
     * @param thirdPartyNotification 第三方回调信息
     */
    public void setThirdPartyNotification(String thirdPartyNotification) {
        this.thirdPartyNotification = thirdPartyNotification == null ? null : thirdPartyNotification.trim();
    }

    /**
     * 获取支付类型, 0 支付宝APP支付 ，1 微信APP支付， 2 微信公众号支付 ， 3 喜盟APP支付 
     *
     * @return pay_type - 支付类型, 0 支付宝APP支付 ，1 微信APP支付， 2 微信公众号支付 ， 3 喜盟APP支付 
     */
    public Byte getPayType() {
        return payType;
    }

    /**
     * 设置支付类型, 0 支付宝APP支付 ，1 微信APP支付， 2 微信公众号支付 ， 3 喜盟APP支付 
     *
     * @param payType 支付类型, 0 支付宝APP支付 ，1 微信APP支付， 2 微信公众号支付 ， 3 喜盟APP支付 
     */
    public void setPayType(Byte payType) {
        this.payType = payType;
    }

    /**
     * 获取支付渠道编码   比如：1 为支付宝第一个，2 为支付宝第二个
     *
     * @return pay_channel - 支付渠道编码   比如：1 为支付宝第一个，2 为支付宝第二个
     */
    public String getPayChannel() {
        return payChannel;
    }

    /**
     * 设置支付渠道编码   比如：1 为支付宝第一个，2 为支付宝第二个
     *
     * @param payChannel 支付渠道编码   比如：1 为支付宝第一个，2 为支付宝第二个
     */
    public void setPayChannel(String payChannel) {
        this.payChannel = payChannel == null ? null : payChannel.trim();
    }

    /**
     * 获取通知状态 0  未通知，1 通知中，2 通知成功，3 通知失败，默认 0
     *
     * @return notify_status - 通知状态 0  未通知，1 通知中，2 通知成功，3 通知失败，默认 0
     */
    public Byte getNotifyStatus() {
        return notifyStatus;
    }

    /**
     * 设置通知状态 0  未通知，1 通知中，2 通知成功，3 通知失败，默认 0
     *
     * @param notifyStatus 通知状态 0  未通知，1 通知中，2 通知成功，3 通知失败，默认 0
     */
    public void setNotifyStatus(Byte notifyStatus) {
        this.notifyStatus = notifyStatus;
    }

    /**
     * 获取通知次数, 默认 0
     *
     * @return notify_amount - 通知次数, 默认 0
     */
    public Integer getNotifyAmount() {
        return notifyAmount;
    }

    /**
     * 设置通知次数, 默认 0
     *
     * @param notifyAmount 通知次数, 默认 0
     */
    public void setNotifyAmount(Integer notifyAmount) {
        this.notifyAmount = notifyAmount;
    }

    /**
     * 获取乐观锁
     *
     * @return revision - 乐观锁
     */
    public Integer getRevision() {
        return revision;
    }

    /**
     * 设置乐观锁
     *
     * @param revision 乐观锁
     */
    public void setRevision(Integer revision) {
        this.revision = revision;
    }

    /**
     * 获取通知时间
     *
     * @return notify_times - 通知时间
     */
    public Date getNotifyTimes() {
        return notifyTimes;
    }

    /**
     * 设置通知时间
     *
     * @param notifyTimes 通知时间
     */
    public void setNotifyTimes(Date notifyTimes) {
        this.notifyTimes = notifyTimes;
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
     * @return updated_time - 更新时间
     */
    public Date getUpdatedTime() {
        return updatedTime;
    }

    /**
     * 设置更新时间
     *
     * @param updatedTime 更新时间
     */
    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
}