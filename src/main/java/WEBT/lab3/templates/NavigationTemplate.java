package WEBT.lab3.templates;

public class NavigationTemplate extends PropertyTemplate{
	public NavigationTemplate(String name, String url) {
		super(name, url);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return value;
	}
	public void setUrl(String url) {
		value = url;
	}
}
// Made by EugeneVV