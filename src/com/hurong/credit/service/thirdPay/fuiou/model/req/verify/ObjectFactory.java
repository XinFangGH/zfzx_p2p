//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.2-147 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.11.24 at 10:07:11 ���� CST 
//


package com.hurong.credit.service.thirdPay.fuiou.model.req.verify;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.hurong.credit.service.thirdPay.fuiou.model.req.verify package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Verifyreq_QNAME = new QName("", "verifyreq");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.hurong.credit.service.thirdPay.fuiou.model.req.verify
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link VerifyReqType }
     * 
     */
    public VerifyReqType createVerifyReqType() {
        return new VerifyReqType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VerifyReqType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "verifyreq")
    public JAXBElement<VerifyReqType> createVerifyreq(VerifyReqType value) {
        return new JAXBElement<VerifyReqType>(_Verifyreq_QNAME, VerifyReqType.class, null, value);
    }

}