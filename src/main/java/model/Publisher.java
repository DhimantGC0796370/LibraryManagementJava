package model;

/**
 * 
 * @author dhimantgodhani Description - Model and getter setters for publisher
 *         data.
 */
public class Publisher {

	private String pub_address;
	private String pub_name;
	private int pub_id;

	public String getPub_address() {
		return pub_address;
	}

	public void setPub_address(String pub_address) {
		this.pub_address = pub_address;
	}

	public String getPub_name() {
		return pub_name;
	}

	public void setPub_name(String pub_name) {
		this.pub_name = pub_name;
	}

	public int getPub_id() {
		return pub_id;
	}

	public void setPub_id(int pub_id) {
		this.pub_id = pub_id;
	}
}
