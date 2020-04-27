package bean;

import java.io.Serializable;

import utils.Measurable;

public class Student implements Measurable, Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	private String first_name;
	private String last_name;
	private String group;
	private String registration_time;

	public Student(String id, String first_name, String last_name, String group) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.group = group;
	}

	public String getId() {
		return id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public String getGroup() {
		return group;
	}

	public void setRegistration_time(String registration_time) {
		this.registration_time = registration_time;
	}

	public String getRegistration_time() {
		return registration_time;
	}

	@Override
	public int measure() {
		return id.getBytes().length + first_name.getBytes().length + last_name.getBytes().length
				+ group.getBytes().length + registration_time.getBytes().length;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", first_name=" + first_name + ", last_name=" + last_name + ", group=" + group
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Student other = (Student) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
