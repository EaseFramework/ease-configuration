package ease.configuration;

public abstract class ConfiguratorSourceBase implements ConfiguratorSource {

	private String name;

	public ConfiguratorSourceBase(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

}
