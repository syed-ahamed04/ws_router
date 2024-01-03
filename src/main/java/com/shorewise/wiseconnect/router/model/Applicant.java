package com.shorewise.wiseconnect.router.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

//@XmlRootElement(name = "Applicant", namespace = "urn:messages.service.ti.apps.tiplus2.misys.com")
public class Applicant {
    private String customer;
    private String reference;

    @XmlElement(name = "Customer", namespace = "urn:common.service.ti.apps.tiplus2.misys.com")
    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    @XmlElement(name = "Reference", namespace = "urn:common.service.ti.apps.tiplus2.misys.com")
    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
