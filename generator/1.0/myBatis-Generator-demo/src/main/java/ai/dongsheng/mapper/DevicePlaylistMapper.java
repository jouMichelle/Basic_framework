package ai.dongsheng.mapper;

import ai.dongsheng.model.entity.DevicePlaylist;

public interface DevicePlaylistMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_playlist
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_playlist
     *
     * @mbggenerated
     */
    int insert(DevicePlaylist record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_playlist
     *
     * @mbggenerated
     */
    int insertSelective(DevicePlaylist record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_playlist
     *
     * @mbggenerated
     */
    DevicePlaylist selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_playlist
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(DevicePlaylist record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_playlist
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(DevicePlaylist record);
}