package com.revature.Jacksontemplates;

import java.util.Objects;

public class MessageTemplate {
	private String message;

	public MessageTemplate() {
		super();
	}
	

	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public MessageTemplate(String message) {
		super();
		this.message = message;
	}


	@Override
	public int hashCode() {
		return Objects.hash(message);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof MessageTemplate)) {
			return false;
		}
		MessageTemplate other = (MessageTemplate) obj;
		return Objects.equals(message, other.message);
	}


	@Override
	public String toString() {
		return "MessageTemplate [message=" + message + "]";
	}
}

