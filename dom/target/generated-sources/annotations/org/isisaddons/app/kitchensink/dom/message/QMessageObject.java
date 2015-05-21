package org.isisaddons.app.kitchensink.dom.message;

import org.datanucleus.query.typesafe.*;
import org.datanucleus.api.jdo.query.*;

public class QMessageObject extends org.datanucleus.api.jdo.query.PersistableExpressionImpl<MessageObject> implements PersistableExpression<MessageObject>
{
    public static final QMessageObject jdoCandidate = candidate("this");

    public static QMessageObject candidate(String name)
    {
        return new QMessageObject(null, name, 5);
    }

    public static QMessageObject candidate()
    {
        return jdoCandidate;
    }

    public static QMessageObject parameter(String name)
    {
        return new QMessageObject(MessageObject.class, name, ExpressionType.PARAMETER);
    }

    public static QMessageObject variable(String name)
    {
        return new QMessageObject(MessageObject.class, name, ExpressionType.VARIABLE);
    }

    public final StringExpression name;
    public final ObjectExpression<org.apache.isis.applib.DomainObjectContainer> container;
    public final ObjectExpression<org.isisaddons.app.kitchensink.dom.message.MessageObjects> messageObjects;

    public QMessageObject(PersistableExpression parent, String name, int depth)
    {
        super(parent, name);
        this.name = new StringExpressionImpl(this, "name");
        this.container = new ObjectExpressionImpl<org.apache.isis.applib.DomainObjectContainer>(this, "container");
        this.messageObjects = new ObjectExpressionImpl<org.isisaddons.app.kitchensink.dom.message.MessageObjects>(this, "messageObjects");
    }

    public QMessageObject(Class type, String name, org.datanucleus.api.jdo.query.ExpressionType exprType)
    {
        super(type, name, exprType);
        this.name = new StringExpressionImpl(this, "name");
        this.container = new ObjectExpressionImpl<org.apache.isis.applib.DomainObjectContainer>(this, "container");
        this.messageObjects = new ObjectExpressionImpl<org.isisaddons.app.kitchensink.dom.message.MessageObjects>(this, "messageObjects");
    }
}
