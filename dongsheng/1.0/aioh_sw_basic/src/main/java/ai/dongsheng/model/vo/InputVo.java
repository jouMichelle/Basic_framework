package ai.dongsheng.model.vo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import ai.dongsheng.common.JsonUtil;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class InputVo {
	private String signature; 	// 加密签名,md5(sort(token,nonc,version))
	private String nonc; 		// 客户端随机数(4位)
	private String reqId; 		// 请求ID，随意定义的字符或数字，最终原样返回给前端
	private String token;
	private String data;

	private JSONObject node;

	public <T> T data(Class<T> typeClass) throws IOException {
		return JsonUtil.fromJson(data, typeClass);
	}

	/**
	 * 获得字符参数
	 */
	public String getString(String name) {
		if (data == null) {
			return null;
		}
		if(node == null){
			node = JSON.parseObject(data);
		}
		return node.getString(name);
	}

	/**
	 * 获得int参数
	 */
	public Integer getInt(String name) {
		if (data == null) {
			return 0;
		}
		if(node == null){
			node = JSON.parseObject(data);
		}
		Integer integer = node.getInteger(name);
		if(integer != null){
			return integer;
		}
		else{
			return 0;
		}
	}

	/**
	 * 获得dobule参数
	 */
	public Double getDouble(String name) {
		if (data == null) {
			return 0D;
		}
		if(node == null){
			node = JSON.parseObject(data);
		}
		Double d = node.getDouble(name);
		if(d != null){
			return d;
		}
		else{
			return 0D;
		}
	}

	/**
	 * 获得Long参数
	 */
	public Long getLong(String name) {
		if (data == null) {
			return 0L;
		}
		if(node == null){
			node = JSON.parseObject(data);
		}
		Long l = node.getLong(name);
		if(l != null){
			return l;
		}
		else{
			return 0L;
		}
	}

	/**
	 * 获得一个pojo对象
	 */
	@SuppressWarnings("unchecked")
	public <T> T getObject(Class<T> clazz) {
		if (data == null) {
			return null;
		}
		if(node == null){
			node = JSON.parseObject(data);
		}
		return (T) node.get(data);
	}

	/**
	 * 获得一个Iterator
	 * 
	 * @param name
	 */
	public JSONArray getIterator(String name) {
		if (data == null) {
			return null;
		}
		if(node == null){
			node = JSON.parseObject(data);
		}
		return node.getJSONArray(name);
	}
		
	/**
	 * 获得一个列表
	 */
	public List<JSONObject> getObjectList(String name) {
		JSONArray it = this.getIterator(name);
		if (it == null) return null;
		List<JSONObject> list = new ArrayList<JSONObject>();
		int size = it.size();
		for (int i = 0; i < size; i++) {
			list.add(it.getJSONObject(i));
		}
		return list;
	}

	/**
	 * 获得一个列表
	 */
	public List<Object> getList(String name) {
		JSONArray it = this.getIterator(name);
		if (it == null) return null;
		List<Object> list = new ArrayList<Object>();
		int size = it.size();
		for (int i = 0; i < size; i++) {
			list.add(it.get(i));
		}
		return list;
	}

	/***
	 * 获得一个json对象 
	 */
	public JSONObject getJson(String name){
		if (data == null) {
			return null;
		}
		if(node == null){
			node = JSON.parseObject(data);
		}
		return node.getJSONObject(name);
	}
	
	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}	
}
