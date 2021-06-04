package com.example.infrastructure.utils.id.perfect;

/**
 * ID生成器
 */
public enum IdsGen {
    /**
     * 基础公共
     */
    BASIC(0),

    /**
     * 业务服务
     */
    BUSSINESS(1),

    /**
     * 其它
     */
    OTHER(255);

    private SnowflakeIdGenerator snowflakeIdGenerator;

    IdsGen(final int service) {
        snowflakeIdGenerator = new SnowflakeIdGenerator(service);
    }

    public long getIdGen() {
        return snowflakeIdGenerator.nextId();
    }

    public String getIdGenStr() {
        return String.valueOf(snowflakeIdGenerator.nextId());
    }
}
