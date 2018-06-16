package ease.configuration;

public interface Configuration {

	public String getOrDefault(String key, String defaultValue);

	public Boolean getBooleanOrDefault(String key, Boolean defaultValue);

	public Integer getIntOrDefault(String key, Integer defaultValue);

	public Float getFloatOrDefault(String key, Float defaultValue);

	public Double getDoubleOrDefault(String key, Double defaultValue);
	
}
