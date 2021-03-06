//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.08.20 at 03:14:56 PM CEST 
//


package xml.skos;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SKOSConceptProperty.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="SKOSConceptProperty">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="altLabel"/>
 *     &lt;enumeration value="prefLabel"/>
 *     &lt;enumeration value="hiddenLabel"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "SKOSConceptProperty")
@XmlEnum
public enum SKOSConceptProperty {

    @XmlEnumValue("altLabel")
    ALT_LABEL("altLabel"),
    @XmlEnumValue("prefLabel")
    PREF_LABEL("prefLabel"),
    @XmlEnumValue("hiddenLabel")
    HIDDEN_LABEL("hiddenLabel");
    private final String value;

    SKOSConceptProperty(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SKOSConceptProperty fromValue(String v) {
        for (SKOSConceptProperty c: SKOSConceptProperty.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
