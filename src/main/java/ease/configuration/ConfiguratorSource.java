package ease.configuration;

public interface ConfiguratorSource {
	public String getName();
	public Configurator create();
}

