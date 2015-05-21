package org.isisaddons.app.kitchensink.dom.reference;

import org.datanucleus.query.typesafe.*;
import org.datanucleus.api.jdo.query.*;

public class QReferenceObject extends org.datanucleus.api.jdo.query.PersistableExpressionImpl<ReferenceObject> implements PersistableExpression<ReferenceObject>
{
    public static final QReferenceObject jdoCandidate = candidate("this");

    public static QReferenceObject candidate(String name)
    {
        return new QReferenceObject(null, name, 5);
    }

    public static QReferenceObject candidate()
    {
        return jdoCandidate;
    }

    public static QReferenceObject parameter(String name)
    {
        return new QReferenceObject(ReferenceObject.class, name, ExpressionType.PARAMETER);
    }

    public static QReferenceObject variable(String name)
    {
        return new QReferenceObject(ReferenceObject.class, name, ExpressionType.VARIABLE);
    }

    public final StringExpression name;
    public final org.isisaddons.app.kitchensink.dom.other.QOtherObject someOtherObjectMandatoryWithChoices;
    public final org.isisaddons.app.kitchensink.dom.other.QOtherObject someOtherObjectOptionalWithChoices;
    public final org.isisaddons.app.kitchensink.dom.other.QOtherObject someOtherObjectActionOnlyWithChoices;
    public final org.isisaddons.app.kitchensink.dom.other.QOtherObject someOtherObjectOptionalWithoutChoices;
    public final org.isisaddons.app.kitchensink.dom.other.QOtherObject someOtherObjectActionOnlyWithoutChoices;
    public final org.isisaddons.app.kitchensink.dom.other.QOtherBoundedObject someOtherBoundedObjectMandatory;
    public final org.isisaddons.app.kitchensink.dom.other.QOtherBoundedObject someOtherBoundedObjectOptional;
    public final ObjectExpression<org.apache.isis.applib.DomainObjectContainer> container;
    public final ObjectExpression<org.isisaddons.app.kitchensink.dom.other.OtherObjects> otherObjects;
    public final ObjectExpression<org.isisaddons.app.kitchensink.dom.other.OtherBoundedObjects> otherBoundedObjects;

    public QReferenceObject(PersistableExpression parent, String name, int depth)
    {
        super(parent, name);
        this.name = new StringExpressionImpl(this, "name");
        if (depth > 0)
        {
            this.someOtherObjectMandatoryWithChoices = new org.isisaddons.app.kitchensink.dom.other.QOtherObject(this, "someOtherObjectMandatoryWithChoices", depth-1);
        }
        else
        {
            this.someOtherObjectMandatoryWithChoices = null;
        }
        if (depth > 0)
        {
            this.someOtherObjectOptionalWithChoices = new org.isisaddons.app.kitchensink.dom.other.QOtherObject(this, "someOtherObjectOptionalWithChoices", depth-1);
        }
        else
        {
            this.someOtherObjectOptionalWithChoices = null;
        }
        if (depth > 0)
        {
            this.someOtherObjectActionOnlyWithChoices = new org.isisaddons.app.kitchensink.dom.other.QOtherObject(this, "someOtherObjectActionOnlyWithChoices", depth-1);
        }
        else
        {
            this.someOtherObjectActionOnlyWithChoices = null;
        }
        if (depth > 0)
        {
            this.someOtherObjectOptionalWithoutChoices = new org.isisaddons.app.kitchensink.dom.other.QOtherObject(this, "someOtherObjectOptionalWithoutChoices", depth-1);
        }
        else
        {
            this.someOtherObjectOptionalWithoutChoices = null;
        }
        if (depth > 0)
        {
            this.someOtherObjectActionOnlyWithoutChoices = new org.isisaddons.app.kitchensink.dom.other.QOtherObject(this, "someOtherObjectActionOnlyWithoutChoices", depth-1);
        }
        else
        {
            this.someOtherObjectActionOnlyWithoutChoices = null;
        }
        if (depth > 0)
        {
            this.someOtherBoundedObjectMandatory = new org.isisaddons.app.kitchensink.dom.other.QOtherBoundedObject(this, "someOtherBoundedObjectMandatory", depth-1);
        }
        else
        {
            this.someOtherBoundedObjectMandatory = null;
        }
        if (depth > 0)
        {
            this.someOtherBoundedObjectOptional = new org.isisaddons.app.kitchensink.dom.other.QOtherBoundedObject(this, "someOtherBoundedObjectOptional", depth-1);
        }
        else
        {
            this.someOtherBoundedObjectOptional = null;
        }
        this.container = new ObjectExpressionImpl<org.apache.isis.applib.DomainObjectContainer>(this, "container");
        this.otherObjects = new ObjectExpressionImpl<org.isisaddons.app.kitchensink.dom.other.OtherObjects>(this, "otherObjects");
        this.otherBoundedObjects = new ObjectExpressionImpl<org.isisaddons.app.kitchensink.dom.other.OtherBoundedObjects>(this, "otherBoundedObjects");
    }

    public QReferenceObject(Class type, String name, org.datanucleus.api.jdo.query.ExpressionType exprType)
    {
        super(type, name, exprType);
        this.name = new StringExpressionImpl(this, "name");
        this.someOtherObjectMandatoryWithChoices = new org.isisaddons.app.kitchensink.dom.other.QOtherObject(this, "someOtherObjectMandatoryWithChoices", 5);
        this.someOtherObjectOptionalWithChoices = new org.isisaddons.app.kitchensink.dom.other.QOtherObject(this, "someOtherObjectOptionalWithChoices", 5);
        this.someOtherObjectActionOnlyWithChoices = new org.isisaddons.app.kitchensink.dom.other.QOtherObject(this, "someOtherObjectActionOnlyWithChoices", 5);
        this.someOtherObjectOptionalWithoutChoices = new org.isisaddons.app.kitchensink.dom.other.QOtherObject(this, "someOtherObjectOptionalWithoutChoices", 5);
        this.someOtherObjectActionOnlyWithoutChoices = new org.isisaddons.app.kitchensink.dom.other.QOtherObject(this, "someOtherObjectActionOnlyWithoutChoices", 5);
        this.someOtherBoundedObjectMandatory = new org.isisaddons.app.kitchensink.dom.other.QOtherBoundedObject(this, "someOtherBoundedObjectMandatory", 5);
        this.someOtherBoundedObjectOptional = new org.isisaddons.app.kitchensink.dom.other.QOtherBoundedObject(this, "someOtherBoundedObjectOptional", 5);
        this.container = new ObjectExpressionImpl<org.apache.isis.applib.DomainObjectContainer>(this, "container");
        this.otherObjects = new ObjectExpressionImpl<org.isisaddons.app.kitchensink.dom.other.OtherObjects>(this, "otherObjects");
        this.otherBoundedObjects = new ObjectExpressionImpl<org.isisaddons.app.kitchensink.dom.other.OtherBoundedObjects>(this, "otherBoundedObjects");
    }
}
