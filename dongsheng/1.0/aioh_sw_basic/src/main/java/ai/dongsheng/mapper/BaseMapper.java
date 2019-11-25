package ai.dongsheng.mapper;

import java.util.List;

import ai.dongsheng.model.entity.BaseEntity;


public interface BaseMapper {
	
	/**
	 * 新增
	 * 
	 * @param entity
	 */
	public int insert(BaseEntity entity);
	
	/**
	 * 修改O
	 * 
	 * @param newEntity
	 */
	public int update(BaseEntity newEntity);

	/**
	 * 通过ID删除
	 * 
	 * @param id
	 */
	public int delete(Integer id);
	
	/**
	 * 通过ID查询单条数据
	 * 
	 * @param id
	 * @return
	 */
	public <T extends BaseEntity> T get(Integer id);
	
	/**
	 * 查询列表
	 * @param entity
	 * @return
	 */
	public List<? extends BaseEntity> getEntityList(BaseEntity entity);
	
}