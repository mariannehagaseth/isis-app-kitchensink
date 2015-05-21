package org.isisaddons.app.kitchensink.dom.blobclob;

import org.datanucleus.query.typesafe.*;
import org.datanucleus.api.jdo.query.*;

public class QBlobClobObject extends org.datanucleus.api.jdo.query.PersistableExpressionImpl<BlobClobObject> implements PersistableExpression<BlobClobObject>
{
    public static final QBlobClobObject jdoCandidate = candidate("this");

    public static QBlobClobObject candidate(String name)
    {
        return new QBlobClobObject(null, name, 5);
    }

    public static QBlobClobObject candidate()
    {
        return jdoCandidate;
    }

    public static QBlobClobObject parameter(String name)
    {
        return new QBlobClobObject(BlobClobObject.class, name, ExpressionType.PARAMETER);
    }

    public static QBlobClobObject variable(String name)
    {
        return new QBlobClobObject(BlobClobObject.class, name, ExpressionType.VARIABLE);
    }

    public final StringExpression name;
    public final ObjectExpression<org.apache.isis.applib.value.Blob> someBlob;
    public final ObjectExpression<org.apache.isis.applib.value.Blob> someImage;
    public final ObjectExpression<org.apache.isis.applib.value.Clob> someClob;
    public final ObjectExpression<org.apache.isis.applib.DomainObjectContainer> container;
    public final ObjectExpression<org.isisaddons.app.kitchensink.dom.other.OtherObjects> otherObjects;
    public final ObjectExpression<org.isisaddons.app.kitchensink.dom.other.OtherBoundedObjects> otherBoundedObjects;

    public QBlobClobObject(PersistableExpression parent, String name, int depth)
    {
        super(parent, name);
        this.name = new StringExpressionImpl(this, "name");
        this.someBlob = new ObjectExpressionImpl<org.apache.isis.applib.value.Blob>(this, "someBlob");
        this.someImage = new ObjectExpressionImpl<org.apache.isis.applib.value.Blob>(this, "someImage");
        this.someClob = new ObjectExpressionImpl<org.apache.isis.applib.value.Clob>(this, "someClob");
        this.container = new ObjectExpressionImpl<org.apache.isis.applib.DomainObjectContainer>(this, "container");
        this.otherObjects = new ObjectExpressionImpl<org.isisaddons.app.kitchensink.dom.other.OtherObjects>(this, "otherObjects");
        this.otherBoundedObjects = new ObjectExpressionImpl<org.isisaddons.app.kitchensink.dom.other.OtherBoundedObjects>(this, "otherBoundedObjects");
    }

    public QBlobClobObject(Class type, String name, org.datanucleus.api.jdo.query.ExpressionType exprType)
    {
        super(type, name, exprType);
        this.name = new StringExpressionImpl(this, "name");
        this.someBlob = new ObjectExpressionImpl<org.apache.isis.applib.value.Blob>(this, "someBlob");
        this.someImage = new ObjectExpressionImpl<org.apache.isis.applib.value.Blob>(this, "someImage");
        this.someClob = new ObjectExpressionImpl<org.apache.isis.applib.value.Clob>(this, "someClob");
        this.container = new ObjectExpressionImpl<org.apache.isis.applib.DomainObjectContainer>(this, "container");
        this.otherObjects = new ObjectExpressionImpl<org.isisaddons.app.kitchensink.dom.other.OtherObjects>(this, "otherObjects");
        this.otherBoundedObjects = new ObjectExpressionImpl<org.isisaddons.app.kitchensink.dom.other.OtherBoundedObjects>(this, "otherBoundedObjects");
    }
}
