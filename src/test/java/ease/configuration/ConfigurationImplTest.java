package ease.configuration;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ease.configuration.memory.MemoryConfigurator;

public class ConfigurationImplTest {

	private Configuration configuration;
	private MemoryConfigurator memoryConfigurator1;
	private MemoryConfigurator memoryConfigurator2;

	@Before
	public void setUp() {
		memoryConfigurator1 = mock(MemoryConfigurator.class);
		memoryConfigurator2 = mock(MemoryConfigurator.class);

		List<Configurator> configuratorList = new LinkedList<>();
		configuratorList.add(memoryConfigurator1);
		configuratorList.add(memoryConfigurator2);

		configuration = new ConfigurationImpl(configuratorList);
	}
	
	@Test
	public void getSectionOrDefault() {
		MemoryConfigurator configurator1 = new MemoryConfigurator(new HashMap<>());
		configurator1.set("app.bean.name", "test bean");
		
		MemoryConfigurator configurator2 = new MemoryConfigurator(new HashMap<>());
		configurator1.set("app.bean.child.name", "child test bean");
		
		List<Configurator> configuratorList = new LinkedList<>();
		configuratorList.add(configurator1);
		configuratorList.add(configurator2);

		Configuration configuration = new ConfigurationImpl(configuratorList);
		TestBean result = configuration.getSectionOrDefault("app.bean", null, TestBean.class);
		
		Assert.assertNotNull(result);
		Assert.assertEquals("test bean", result.getName());
		Assert.assertEquals("child test bean", result.getChild().getName());	
	}

	@Test
	public void getStringOrDefault_returnsValue() {
		when(memoryConfigurator2.get("parent.child1")).thenReturn(Optional.empty());
		when(memoryConfigurator1.get("parent.child1")).thenReturn(Optional.of("value"));

		String value = configuration.getStringOrDefault("parent.child1", null);

		Assert.assertNotNull(value);
		Assert.assertEquals("value", value);

		verify(memoryConfigurator2).get("parent.child1");
		verify(memoryConfigurator1).get("parent.child1");
	}

	@Test
	public void getStringOrDefault_returnsDefault() {
		when(memoryConfigurator2.get("parent.child1")).thenReturn(Optional.empty());
		when(memoryConfigurator1.get("parent.child1")).thenReturn(Optional.empty());

		String value = configuration.getStringOrDefault("parent.child1", "default");

		Assert.assertNotNull(value);
		Assert.assertEquals("default", value);

		verify(memoryConfigurator2).get("parent.child1");
		verify(memoryConfigurator1).get("parent.child1");
	}
	
	
	public static class TestBean {
		private String name;
		private TestBean child;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public TestBean getChild() {
			return child;
		}
		public void setChild(TestBean child) {
			this.child = child;
		}	
	}
}
