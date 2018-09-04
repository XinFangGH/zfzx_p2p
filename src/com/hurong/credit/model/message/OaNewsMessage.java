package com.hurong.credit.model.message;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * 
 * @author 
 *
 */
/**
 * OaNewsMessage Base Java Bean, base class for the.credit.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * 站内信
 */
public class OaNewsMessage extends com.hurong.core.model.BaseModel {

    protected Long id;
	protected String title;//标题
	protected String content;//内容
	protected java.util.Date sendTime;//发送时间
	protected Long recipient;//接收人
	protected String operator;//操作人
	protected String addresser;//发件人（全名）
	protected String status;//状态：0未读，1已读
	protected java.util.Date readTime;//阅读时间
	protected String isDelete;//是否删除
	protected String comment1;
	protected String comment2;
	protected String comment3;
	protected String type;
	protected String typename;
	protected String isAllSend;//是否发送全部 0表示否，1表示发送全部

	
	/**
	 * Default Empty Constructor for class OaNewsMessage
	 */
	public OaNewsMessage () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class OaNewsMessage
	 */
	public OaNewsMessage (
		 Long in_id
        ) {
		this.setId(in_id);
    }

    

	/**
	 * 	 * @return Long
     * @hibernate.id column="id" type="java.lang.Long" generator-class="native"
	 */
	public Long getId() {
		return this.id;
	}
	
	/**
	 * Set the id
	 */	
	public void setId(Long aValue) {
		this.id = aValue;
	}	

	public String getIsAllSend() {
		return isAllSend;
	}

	public void setIsAllSend(String isAllSend) {
		this.isAllSend = isAllSend;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	/**
	 * 标题	 * @return String
	 * @hibernate.property column="title" type="java.lang.String" length="255" not-null="false" unique="false"
	 */
	public String getTitle() {
		return this.title;
	}
	
	/**
	 * Set the title
	 */	
	public void setTitle(String aValue) {
		this.title = aValue;
	}	

	/**
	 * 内容	 * @return String
	 * @hibernate.property column="content" type="java.lang.String" length="255" not-null="false" unique="false"
	 */
	public String getContent() {
		return this.content;
	}
	
	/**
	 * Set the content
	 */	
	public void setContent(String aValue) {
		this.content = aValue;
	}	

	/**
	 * 发送时间	 * @return java.util.Date
	 * @hibernate.property column="sendTime" type="java.util.Date" length="19" not-null="false" unique="false"
	 */
	public java.util.Date getSendTime() {
		return this.sendTime;
	}
	
	/**
	 * Set the sendTime
	 */	
	public void setSendTime(java.util.Date aValue) {
		this.sendTime = aValue;
	}	

	/**
	 * 接收人	 * @return Long
	 * @hibernate.property column="recipient" type="java.lang.Long" length="19" not-null="false" unique="false"
	 */
	public Long getRecipient() {
		return this.recipient;
	}
	
	/**
	 * Set the recipient
	 */	
	public void setRecipient(Long aValue) {
		this.recipient = aValue;
	}	

	/**
	 * 操作人	 * @return String
	 * @hibernate.property column="operator" type="java.lang.String" length="255" not-null="false" unique="false"
	 */
	public String getOperator() {
		return this.operator;
	}
	
	/**
	 * Set the operator
	 */	
	public void setOperator(String aValue) {
		this.operator = aValue;
	}	

	/**
	 * 发件人（全名）	 * @return String
	 * @hibernate.property column="addresser" type="java.lang.String" length="255" not-null="false" unique="false"
	 */
	public String getAddresser() {
		return this.addresser;
	}
	
	/**
	 * Set the addresser
	 */	
	public void setAddresser(String aValue) {
		this.addresser = aValue;
	}	

	/**
	 * 状态（已读，未读）	 * @return String
	 * @hibernate.property column="status" type="java.lang.String" length="255" not-null="false" unique="false"
	 */
	public String getStatus() {
		return this.status;
	}
	
	/**
	 * Set the status
	 */	
	public void setStatus(String aValue) {
		this.status = aValue;
	}	

	/**
	 * 阅读时间	 * @return java.util.Date
	 * @hibernate.property column="readTime" type="java.util.Date" length="19" not-null="false" unique="false"
	 */
	public java.util.Date getReadTime() {
		return this.readTime;
	}
	
	/**
	 * Set the readTime
	 */	
	public void setReadTime(java.util.Date aValue) {
		this.readTime = aValue;
	}	

	/**
	 * 是否删除	 * @return String
	 * @hibernate.property column="isDelete" type="java.lang.String" length="255" not-null="false" unique="false"
	 */
	public String getIsDelete() {
		return this.isDelete;
	}
	
	/**
	 * Set the isDelete
	 */	
	public void setIsDelete(String aValue) {
		this.isDelete = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="comment1" type="java.lang.String" length="255" not-null="false" unique="false"
	 */
	public String getComment1() {
		return this.comment1;
	}
	
	/**
	 * Set the comment1
	 */	
	public void setComment1(String aValue) {
		this.comment1 = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="comment2" type="java.lang.String" length="255" not-null="false" unique="false"
	 */
	public String getComment2() {
		return this.comment2;
	}
	
	/**
	 * Set the comment2
	 */	
	public void setComment2(String aValue) {
		this.comment2 = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="comment3" type="java.lang.String" length="255" not-null="false" unique="false"
	 */
	public String getComment3() {
		return this.comment3;
	}
	
	/**
	 * Set the comment3
	 */	
	public void setComment3(String aValue) {
		this.comment3 = aValue;
	}	

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof OaNewsMessage)) {
			return false;
		}
		OaNewsMessage rhs = (OaNewsMessage) object;
		return new EqualsBuilder()
				.append(this.id, rhs.id)
				.append(this.title, rhs.title)
				.append(this.content, rhs.content)
				.append(this.sendTime, rhs.sendTime)
				.append(this.recipient, rhs.recipient)
				.append(this.operator, rhs.operator)
				.append(this.addresser, rhs.addresser)
				.append(this.status, rhs.status)
				.append(this.readTime, rhs.readTime)
				.append(this.isDelete, rhs.isDelete)
				.append(this.comment1, rhs.comment1)
				.append(this.comment2, rhs.comment2)
				.append(this.comment3, rhs.comment3)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.id) 
				.append(this.title) 
				.append(this.content) 
				.append(this.sendTime) 
				.append(this.recipient) 
				.append(this.operator) 
				.append(this.addresser) 
				.append(this.status) 
				.append(this.readTime) 
				.append(this.isDelete) 
				.append(this.comment1) 
				.append(this.comment2) 
				.append(this.comment3) 
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("id", this.id) 
				.append("title", this.title) 
				.append("content", this.content) 
				.append("sendTime", this.sendTime) 
				.append("recipient", this.recipient) 
				.append("operator", this.operator) 
				.append("addresser", this.addresser) 
				.append("status", this.status) 
				.append("readTime", this.readTime) 
				.append("isDelete", this.isDelete) 
				.append("comment1", this.comment1) 
				.append("comment2", this.comment2) 
				.append("comment3", this.comment3) 
				.toString();
	}



}
