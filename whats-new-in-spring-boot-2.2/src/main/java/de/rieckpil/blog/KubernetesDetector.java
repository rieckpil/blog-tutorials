package de.rieckpil.blog;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnCloudPlatform;
import org.springframework.boot.cloud.CloudPlatform;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnCloudPlatform(CloudPlatform.KUBERNETES)
public class KubernetesDetector implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		System.out.println("--- You are running on KUBERNETES ;)");
	}

}
