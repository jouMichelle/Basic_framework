package ai.dongsheng.model.entity;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class BaseEntity implements Cloneable,Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	private Date createTime;

	public BaseEntity(){
		createTime = Calendar.getInstance().getTime();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id){
		this.id = id;
	}

	@Override
	public int hashCode() {
		if(id != 0){
			return id.intValue();
		}
		else{
			return super.hashCode();
		}
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		BaseEntity entity = (BaseEntity) super.clone();
		entity.id = 0L;
		return entity;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj){
			return true;
		}

		if((this.id == 0) || (obj == null) || !(this.getClass().equals(obj.getClass()))){
			return false;
		}

		return this.id .equals(((BaseEntity)obj).getId());
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
