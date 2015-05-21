package org.isisaddons.app.kitchensink.dom.bulk;

import org.datanucleus.query.typesafe.*;
import org.datanucleus.api.jdo.query.*;

public class QWorkflowObject extends org.datanucleus.api.jdo.query.PersistableExpressionImpl<WorkflowObject> implements PersistableExpression<WorkflowObject>
{
    public static final QWorkflowObject jdoCandidate = candidate("this");

    public static QWorkflowObject candidate(String name)
    {
        return new QWorkflowObject(null, name, 5);
    }

    public static QWorkflowObject candidate()
    {
        return jdoCandidate;
    }

    public static QWorkflowObject parameter(String name)
    {
        return new QWorkflowObject(WorkflowObject.class, name, ExpressionType.PARAMETER);
    }

    public static QWorkflowObject variable(String name)
    {
        return new QWorkflowObject(WorkflowObject.class, name, ExpressionType.VARIABLE);
    }

    public final StringExpression name;
    public final ObjectExpression<org.isisaddons.app.kitchensink.dom.bulk.State> state;
    public final ObjectExpression<org.apache.isis.applib.DomainObjectContainer> container;
    public final ObjectExpression<org.apache.isis.applib.services.actinvoc.ActionInvocationContext> actionInvocationContext;

    public QWorkflowObject(PersistableExpression parent, String name, int depth)
    {
        super(parent, name);
        this.name = new StringExpressionImpl(this, "name");
        this.state = new ObjectExpressionImpl<org.isisaddons.app.kitchensink.dom.bulk.State>(this, "state");
        this.container = new ObjectExpressionImpl<org.apache.isis.applib.DomainObjectContainer>(this, "container");
        this.actionInvocationContext = new ObjectExpressionImpl<org.apache.isis.applib.services.actinvoc.ActionInvocationContext>(this, "actionInvocationContext");
    }

    public QWorkflowObject(Class type, String name, org.datanucleus.api.jdo.query.ExpressionType exprType)
    {
        super(type, name, exprType);
        this.name = new StringExpressionImpl(this, "name");
        this.state = new ObjectExpressionImpl<org.isisaddons.app.kitchensink.dom.bulk.State>(this, "state");
        this.container = new ObjectExpressionImpl<org.apache.isis.applib.DomainObjectContainer>(this, "container");
        this.actionInvocationContext = new ObjectExpressionImpl<org.apache.isis.applib.services.actinvoc.ActionInvocationContext>(this, "actionInvocationContext");
    }
}
