@XmlSchema(
    namespace = "urn:control.services.tiplus2.misys.com",
    elementFormDefault = XmlNsForm.QUALIFIED,
    xmlns = {
        @XmlNs(prefix = "", namespaceURI = "http://www.w3.org/2001/XMLSchema-instance"),
        @XmlNs(prefix = "m", namespaceURI = "urn:messages.service.ti.apps.tiplus2.misys.com"),
        @XmlNs(prefix = "c", namespaceURI = "urn:common.service.ti.apps.tiplus2.misys.com"),
        @XmlNs(prefix = "x", namespaceURI = "urn:custom.service.ti.apps.tiplus2.misys.com")
    }
)
package com.shorewise.wiseconnect.router.model;

import javax.xml.bind.annotation.*;
