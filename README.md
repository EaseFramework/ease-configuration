# ease-configuration

This library provides a means to create a configuration from different sources.  A client can access a built configuration via the configuration API.



## Creating A Configuration
To create a configuration a ConfigurationBuilder is used.  The configuration builder allows you to add one or more ConfiguratorSource objects.  A ConfiguratorSource object is used to create a Configurator.  The Configurator is used to construct and expose a configuration through its API.  In this sense the configuration can be extended to include configuration from different sources such as a custom file format, relational DB, NoSQL store etc.  


```
	ConfigurationBuilder builder = new ConfigurationBuilderDefault();
	builder.addSource(new MemoryConfiguratorSource(...));

	Configuration configuration = builder.build();

```


The above code creates a new configuration adding a MemoryConfiguratorSource. The MemoryConfiguratorSource is a source that takes the values from a Map<String,String> and adds the keys as configuration keys and the values as the configuration values.


These configuration values can then be accessed via the configuration API.  For example using the above configuration object configuration values can be accessed.


```
	String configValue = configuration.getOrDefault("parent.child", "defaultValue");
	Integer configValue = configuration.getIntOrDefault("parent.child", 1);
	
```

It is important to note that if you add multiple configuration sources to the builder.  The configuration values will be resolved in reverse order.  


```
	ConfigurationBuilder builder = new ConfigurationBuilderDefault();
	builder.addSource(new MemoryConfiguratorSource("memoryConfig1", ...));
	builder.addSource(new MemoryConfiguratorSource("memoryConfig2", ...));

	Configuration configuration = builder.build();

```

In the above example if the key parent.child exists in both memoryConfig1 and memoryConfig2 sources.  The value from memoryConfig2 will be returned.




## Configurator
A Configurator is used to expose a set of configuration values.  The Configurator must expose configuration values as java.lang.String.  The Configurator must accept a key and return a String value. Note, the Configuration API provides support for exposing the String value as a specific type. 

Moreover if you are creating a custom Configurator, you should follow the configuration key syntax.  This will allow your Configuration values to work with the Configuration API. 


## Configuration Key
The configuration key uses a delimiter to separate key parts.  For example:


```
	parentPart.childPart
	
```

The above key delegates the parent key as parentPath and the child key as childPart.


When storing list values the following format should be used.

```
	parentPart.0.childKey	
	
```

The above would return the first item of the sequence of the parentPart then resolve its childKey value.


   


