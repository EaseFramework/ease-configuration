package ease.configuration;

public class ConfigurationException extends RuntimeException {

	private static final long serialVersionUID = 6331777577874918599L;

	public ConfigurationException(String message) {
		this(message, null);
	}

	public ConfigurationException(Throwable t) {
		this(null, t);
	}

	public ConfigurationException(String message, Throwable t) {
		super(message, t);
	}
}
