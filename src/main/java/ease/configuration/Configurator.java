package ease.configuration;

import java.util.Optional;

public interface Configurator {

	public void configure();

	public Optional<String> get(String key);

	public void set(String key, String value);

}
