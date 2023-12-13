package com.shorewise.wiseconnect.router.model;

import javax.xml.bind.annotation.*;


@XmlAccessorType(XmlAccessType.FIELD)
public class Context {

    @XmlElement(name = "Customer", namespace = "urn:common.service.ti.apps.tiplus2.misys.com")
    private String customer;
    
    @XmlElement(name = "OurReference", namespace = "urn:common.service.ti.apps.tiplus2.misys.com")
    private String ourReference;

    @XmlElement(name = "TheirReference", namespace = "urn:common.service.ti.apps.tiplus2.misys.com")
    private String theirReference;

    @XmlElement(name = "BehalfOfBranch", namespace = "urn:common.service.ti.apps.tiplus2.misys.com")
    private String behalfOfBranch;

	public String getOurReference() {
		return ourReference;
	}

	public String getTheirReference() {
		return theirReference;
	}

	public String getBehalfOfBranch() {
		return behalfOfBranch;
	}

	public void setOurReference(String ourReference) {
		this.ourReference = ourReference;
	}

	public void setTheirReference(String theirReference) {
		this.theirReference = theirReference;
	}

	public void setBehalfOfBranch(String behalfOfBranch) {
		this.behalfOfBranch = behalfOfBranch;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}
  
}

