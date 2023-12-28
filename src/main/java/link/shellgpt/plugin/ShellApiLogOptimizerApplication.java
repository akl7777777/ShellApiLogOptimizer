package link.shellgpt.plugin;

import cn.easyes.starter.register.EsMapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EsMapperScan("link.shellgpt.plugin.*.*.dao")
public class ShellApiLogOptimizerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShellApiLogOptimizerApplication.class, args);
	}

}
