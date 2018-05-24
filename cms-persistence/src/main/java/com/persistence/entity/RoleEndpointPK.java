package com.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the role_endpoint database table.
 * 
 */
@Embeddable
public class RoleEndpointPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="role_id", insertable=false, updatable=false)
	private int roleId;

	@Column(name="endpoint_id", insertable=false, updatable=false)
	private int endpointId;

	public RoleEndpointPK() {
	}
	public int getRoleId() {
		return this.roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public int getEndpointId() {
		return this.endpointId;
	}
	public void setEndpointId(int endpointId) {
		this.endpointId = endpointId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RoleEndpointPK)) {
			return false;
		}
		RoleEndpointPK castOther = (RoleEndpointPK)other;
		return 
			(this.roleId == castOther.roleId)
			&& (this.endpointId == castOther.endpointId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.roleId;
		hash = hash * prime + this.endpointId;
		
		return hash;
	}
}