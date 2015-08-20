/*
 * Copyright 2005-2010 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ene.quesle.tool.ldap;

import javax.naming.ldap.LdapName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.support.LdapUtils;

/**
 * Simple class representing a single person.
 * 
 * @author Mattias Hellborg Arthursson
 * @author Ulrik Sandberg
 */
public class Person  {
	private String fullName;

	private String lastName;

	private String description;

	private String country;

	private String company;

	private String phone;
	
	public Person(){
		
	}

	public Person(DirContextOperations context) {
		LdapName dn = LdapUtils.newLdapName(context.getDn());
		
		this.setCountry(LdapUtils.getStringValue(dn, 0));
		this.setCompany(LdapUtils.getStringValue(dn, 1));
		this.setFullName(context.getStringAttribute("cn"));
		this.setLastName(context.getStringAttribute("sn"));
		this.setDescription(context.getStringAttribute("description"));
		this.setPhone(context.getStringAttribute("telephoneNumber"));
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}

}
