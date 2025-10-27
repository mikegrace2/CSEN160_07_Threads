package G_memory;

public class DataObject {
	private Integer id;
	private String first_name;
	private String last_name;
	private Double pi;
	
	public DataObject(Integer id, String first_name, String last_name, Double pi) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.pi = pi;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public Double getPi() {
		return pi;
	}

	public void setPi(Double pi) {
		this.pi = pi;
	}

	@Override
	public String toString() {
		return "DataObject [id=" + id + ", first_name=" + first_name + ", last_name=" + last_name + ", pi=" + pi + "]";
	}
}
