package ai.dongsheng.mapper;

import ai.dongsheng.model.entity.Account;

/**
 * @author xujing Created by Dell on 2019/8/19.
 */
public interface AccountMapper extends BaseMapper {

	/**
	 * 根据用户ID查询用户信息(不限机器人还是APP账号)
	 * 
	 * @param id
	 * @return
	 */
	public Account getAccountById(Long id);

	/**
	 * 根据用户ID查询APP用户信息
	 * 
	 * @param id
	 * @return
	 */
	public Account getAPPUserById(Long id);

}
