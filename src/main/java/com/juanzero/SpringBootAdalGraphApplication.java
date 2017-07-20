package com.juanzero;

import com.juanzero.util.TokenUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.*;
import javax.security.cert.CertificateException;
import javax.security.cert.X509Certificate;

@SpringBootApplication
public class SpringBootAdalGraphApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAdalGraphApplication.class, args);
	}

	@Bean
	public Boolean disableSSLValidation() throws Exception {

		final SSLContext sslContext = SSLContext.getInstance("TLS");

		sslContext.init(null, new X509TrustManager[]{new X509TrustManager() {

			public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
			}

			public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
			}

			@Override
			public void checkClientTrusted(java.security.cert.X509Certificate[] x509Certificates, String s) throws java.security.cert.CertificateException {

			}

			@Override
			public void checkServerTrusted(java.security.cert.X509Certificate[] x509Certificates, String s) throws java.security.cert.CertificateException {

			}

			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return new java.security.cert.X509Certificate[0];
			}
		}}, null);

		HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
		HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
			public boolean verify(String hostname, SSLSession session) {
				return true;
			}
		});

		return true;
	}

	@Bean
    public TokenUtils tokenUtils(){
        return new TokenUtils();
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
