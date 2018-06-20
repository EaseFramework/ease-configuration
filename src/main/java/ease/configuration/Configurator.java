package ease.configuration;

import java.util.Optional;
import java.util.function.BiConsumer;

public interface Configurator {

	public void configure();

	public Optional<String> get(String key);

	public void set(String key, String value);
	
	public void forEach(BiConsumer<String, String> consumer);

}
