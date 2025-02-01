package ltd.shellapi.plugin;

import org.dromara.easyes.starter.register.EsMapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EsMapperScan("ltd.shellapi.plugin.*.*.dao")
public class ShellApiLogOptimizerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShellApiLogOptimizerApplication.class, args);
	}

}
