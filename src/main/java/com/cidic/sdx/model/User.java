package com.cidic.sdx.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


@Entity
@Table(name="user")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;
	
	@Column(name="user_id") 
	private String userId;
	
	@Column(name="create_time") 
	private Date createTime;

	@OneToMany(mappedBy = "user",cascade=CascadeType.ALL,fetch = FetchType.EAGER)
	private List<UserStyle> userStyleList;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public List<UserStyle> getUserStyleList() {
		return userStyleList;
	}

	public void setUserStyleList(List<UserStyle> userStyleList) {
		this.userStyleList = userStyleList;
	}
	
	
}
