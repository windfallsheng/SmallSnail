package com.smallsnailtech.smallsnail.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author luzhaosheng
 * @date 2018年11月06日
 */
public class UserInfoEntity implements Serializable {

	@SerializedName("ticket")
	private String token;
	@SerializedName("uuid")
	private String userId;
	@SerializedName(value = "headIconUrl", alternate = { "displayPhoto", "picUrl" })
	private String headIconUrl;
	@SerializedName(value = "userAccount", alternate = { "telephoneNumber", "phoneNumber" })
	private String userAccount;
	@SerializedName(value = "username", alternate = { "loginName", "name" })
	private String username;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getHeadIconUrl() {
		return headIconUrl;
	}

	public void setHeadIconUrl(String headIconUrl) {
		this.headIconUrl = headIconUrl;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "UserInfoEntity [token=" + token + ", userId=" + userId + ", headIconUrl=" + headIconUrl
				+ ", userAccount=" + userAccount + ", username=" + username + "]";
	}

}
