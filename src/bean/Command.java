package bean;

import java.io.Serializable;

import utils.Measurable;

public class Command implements Serializable, Measurable {

	private static final long serialVersionUID = 1L;
	private String id;
	private String type;
	private String created_at;
	private String comment;

	public Command(String id, String type, String created_at, String comment) {
		super();
		this.id = id;
		this.type = type;
		this.created_at = created_at;
		this.comment = comment;
	}

	public Command(String id, String type, String comment) {
		super();
		this.id = id;
		this.type = type;
		this.comment = comment;
	}

	public String getId() {
		return id;
	}

	public String getType() {
		return type;
	}

	public String getCreated_at() {
		return created_at;
	}

	public String getComment() {
		return comment;
	}

	@Override
	public String toString() {
		return "Command [id=" + id + ", type=" + type + ", created_at=" + created_at + ", comment=" + comment + "]";
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comment == null) ? 0 : comment.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Command other = (Command) obj;
		if (comment == null) {
			if (other.comment != null)
				return false;
		} else if (!comment.equals(other.comment))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	/*
	 * return count of bytes
	 */
	@Override
	public int measure() {
		return id.getBytes().length + type.getBytes().length + comment.getBytes().length;
	}

}
