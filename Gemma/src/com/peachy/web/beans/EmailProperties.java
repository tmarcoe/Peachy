package com.peachy.web.beans;

public class EmailProperties {
	
	//Outgoing settings (smtp)
	private String transportProtocol;
	private String smtpAuth;
	private String smtpHost;
	private String smtpPort;
	private String smtpStarttlsEnable;
	
	//Incoming settings (pop3)
	private String pop3Host;
	private String pop3Port;
	private String pop3StarttlsEnable;
	
	public String getTransportProtocol() {
		return transportProtocol;
	}
	public void setTransportProtocol(String transportProtocol) {
		this.transportProtocol = transportProtocol;
	}
	public String getSmtpAuth() {
		return smtpAuth;
	}
	public void setSmtpAuth(String smtpAuth) {
		this.smtpAuth = smtpAuth;
	}
	public String getSmtpHost() {
		return smtpHost;
	}
	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}
	public String getSmtpPort() {
		return smtpPort;
	}
	public void setSmtpPort(String smtpPort) {
		this.smtpPort = smtpPort;
	}
	public String getSmtpStarttlsEnable() {
		return smtpStarttlsEnable;
	}
	public void setSmtpStarttlsEnable(String smtpStarttlsEnable) {
		this.smtpStarttlsEnable = smtpStarttlsEnable;
	}
	public String getPop3Host() {
		return pop3Host;
	}
	public void setPop3Host(String pop3Host) {
		this.pop3Host = pop3Host;
	}
	public String getPop3Port() {
		return pop3Port;
	}
	public void setPop3Port(String pop3Port) {
		this.pop3Port = pop3Port;
	}
	public String getPop3StarttlsEnable() {
		return pop3StarttlsEnable;
	}
	public void setPop3StarttlsEnable(String pop3StarttlsEnable) {
		this.pop3StarttlsEnable = pop3StarttlsEnable;
	}
	
	

}
