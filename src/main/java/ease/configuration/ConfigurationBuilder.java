package ease.configuration;

import java.util.List;
import java.util.Map;

public interface ConfigurationBuilder {
	public void addSource(ConfiguratorSource source);

	public void addMemoryConfig(String name, Map<String, String> configurationValues);

	public List<ConfiguratorSource> getSources();

	public Configuration build();

}
