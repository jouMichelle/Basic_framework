package ai.dongsheng.starter.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class HttpTest {
	public static void main0(String[] args) {
		String url="https://k8s.aidongsheng.com/v3/IM2/machine/playMessage";
		RestTemplate restTemplate = new RestTemplate();
		
		//headers
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("SN", "A2020E000600000000000001");
        requestHeaders.add("Authorization", "Basic MWMzYmM2Y2M3M2EyYTRiOTA2ODFmYzkzNGQ2MzNiNTMsMTU3MTg5OTI4MTI3MyxjNTcwN2MzZWE3YjVkMWFhMGQyMzM3ZGM2OWE1NjkwOTplYzUyZjI0ODExOTBhNWVhM2E2NDEyYjMyNzM3ZWVlZQ==");
        
        //body
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("roundid", "1");
        
        //HttpEntity
        HttpEntity<MultiValueMap> requestEntity = new HttpEntity<MultiValueMap>(requestBody, requestHeaders);
 
        //post
        ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);
        System.out.println(response.getStatusCodeValue());
        System.out.println(response.getBody());
        System.out.println(response.getHeaders());

	}
	
	public static void main1(String[] args) {
		String url="http://k8s.aidongsheng.com/hw/im/play";
		//String url="https://k8s.aidongsheng.com/v3/IM2/machine/playMessage";
		RestTemplate restTemplate = new RestTemplate();
		
		//headers
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("SN", "A2020E000600000000000001");
        requestHeaders.add("Authorization", "Basic MWMzYmM2Y2M3M2EyYTRiOTA2ODFmYzkzNGQ2MzNiNTMsMTU3MTg5OTI4MTI3MyxjNTcwN2MzZWE3YjVkMWFhMGQyMzM3ZGM2OWE1NjkwOTplYzUyZjI0ODExOTBhNWVhM2E2NDEyYjMyNzM3ZWVlZQ==");
        
        //HttpEntity
        HttpEntity<MultiValueMap> requestEntity = new HttpEntity<MultiValueMap>(requestHeaders);
 
        //post
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
        System.out.println(response.getStatusCodeValue());
        //System.out.println(response.getBody());
        System.out.println(response.getHeaders());

	}

}
