package model;

/**
 * 
 * @author aayushrishi Description - Model and getter setters for Member data.
 */
public class Member {

	private String mem_name;
	private String mem_address;
	private int mem_id;
	private String mem_date;
	private String expiry_date;
	private String mem_type;

	public String getMem_name() {
		return mem_name;
	}

	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}

	public String getMem_address() {
		return mem_address;
	}

	public void setMem_address(String mem_address) {
		this.mem_address = mem_address;
	}

	public int getMem_id() {
		return mem_id;
	}

	public void setMem_id(int mem_id) {
		this.mem_id = mem_id;
	}

	public String getMem_date() {
		return mem_date;
	}

	public void setMem_date(String mem_date) {
		this.mem_date = mem_date;
	}

	public String getExpiry_date() {
		return expiry_date;
	}

	public void setExpiry_date(String expiry_date) {
		this.expiry_date = expiry_date;
	}

	public String getMem_type() {
		return mem_type;
	}

	public void setMem_type(String mem_type) {
		this.mem_type = mem_type;
	}

}
