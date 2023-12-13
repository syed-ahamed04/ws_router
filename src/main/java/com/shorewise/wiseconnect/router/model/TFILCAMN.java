package com.shorewise.wiseconnect.router.model;


import javax.xml.bind.annotation.*;
import java.util.List;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class TFILCAMN {

    @XmlElement(name = "EventNotificationss", namespace = "urn:messages.service.ti.apps.tiplus2.misys.com")
    private EventNotificationss eventNotificationss;
	
    @XmlElement(name = "Context", namespace = "urn:messages.service.ti.apps.tiplus2.misys.com")
    private Context context;
    
    @XmlElement(name = "MasterRef", namespace = "urn:messages.service.ti.apps.tiplus2.misys.com")
    private MasterRef masterRef;
    
    @XmlElement(name = "Applicant", namespace = "urn:messages.service.ti.apps.tiplus2.misys.com")
    private Applicant applicant;
    
    @XmlElement(name = "LCAmount", namespace = "urn:messages.service.ti.apps.tiplus2.misys.com")
    private LCAmount lCAmount;
    

    @XmlElement(name = "Revocable", namespace = "urn:messages.service.ti.apps.tiplus2.misys.com")
    private Revocable revocable;
    
    
    @XmlElement(name = "Revolving", namespace = "urn:messages.service.ti.apps.tiplus2.misys.com")
    private Revolving revolving;
    
    
    @XmlElement(name = "ApplicationDate", namespace = "urn:messages.service.ti.apps.tiplus2.misys.com")
    private String  applicationDate;
    
    @XmlElement(name = "IssueDate", namespace = "urn:messages.service.ti.apps.tiplus2.misys.com")
    private String issueDate;
    
    
    @XmlElement(name = "TermsOfPayment", namespace = "urn:messages.service.ti.apps.tiplus2.misys.com")
    private TermsOfPayment termsOfPayment;
    
    
    @XmlElement(name = "eBankMasterRef", namespace = "urn:messages.service.ti.apps.tiplus2.misys.com")
    private String eBankMasterRef;
    
    @XmlElement(name = "eBankEvent", namespace = "urn:messages.service.ti.apps.tiplus2.misys.com")
    private String eBankEvent;
    
    @XmlElement(name = "Sender", namespace = "urn:messages.service.ti.apps.tiplus2.misys.com")
    private Sender sender;
    
    @XmlElement(name = "IncreaseAmount", namespace = "urn:messages.service.ti.apps.tiplus2.misys.com")
    private IncreaseAmount increaseAmount;
    
    @XmlElement(name = "AmendmentNarrative", namespace = "urn:messages.service.ti.apps.tiplus2.misys.com")
    private String amendmentNarrative;
    
    
    @XmlElement(name = "AmendDate", namespace = "urn:messages.service.ti.apps.tiplus2.misys.com")
    private String amendDate;
    

	public EventNotificationss getEventNotificationss() {
		return eventNotificationss;
	}

	public MasterRef getMasterRef() {
		return masterRef;
	}

	public Applicant getApplicant() {
		return applicant;
	}

	public LCAmount getlCAmount() {
		return lCAmount;
	}

	public Revocable getRevocable() {
		return revocable;
	}

	public Revolving getRevolving() {
		return revolving;
	}

	public String getApplicationDate() {
		return applicationDate;
	}

	public String getIssueDate() {
		return issueDate;
	}

	public TermsOfPayment getTermsOfPayment() {
		return termsOfPayment;
	}

	public String geteBankMasterRef() {
		return eBankMasterRef;
	}

	public String geteBankEvent() {
		return eBankEvent;
	}

	public Sender getSender() {
		return sender;
	}

	public IncreaseAmount getIncreaseAmount() {
		return increaseAmount;
	}

	public String getAmendmentNarrative() {
		return amendmentNarrative;
	}

	public String getAmendDate() {
		return amendDate;
	}

	public void setEventNotificationss(EventNotificationss eventNotificationss) {
		this.eventNotificationss = eventNotificationss;
	}

	public void setMasterRef(MasterRef masterRef) {
		this.masterRef = masterRef;
	}

	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}

	public void setlCAmount(LCAmount lCAmount) {
		this.lCAmount = lCAmount;
	}

	public void setRevocable(Revocable revocable) {
		this.revocable = revocable;
	}

	public void setRevolving(Revolving revolving) {
		this.revolving = revolving;
	}

	public void setApplicationDate(String applicationDate) {
		this.applicationDate = applicationDate;
	}

	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}

	public void setTermsOfPayment(TermsOfPayment termsOfPayment) {
		this.termsOfPayment = termsOfPayment;
	}

	public void seteBankMasterRef(String eBankMasterRef) {
		this.eBankMasterRef = eBankMasterRef;
	}

	public void seteBankEvent(String eBankEvent) {
		this.eBankEvent = eBankEvent;
	}

	public void setSender(Sender sender) {
		this.sender = sender;
	}

	public void setIncreaseAmount(IncreaseAmount increaseAmount) {
		this.increaseAmount = increaseAmount;
	}

	public void setAmendmentNarrative(String amendmentNarrative) {
		this.amendmentNarrative = amendmentNarrative;
	}

	public void setAmendDate(String amendDate) {
		this.amendDate = amendDate;
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

   
}

