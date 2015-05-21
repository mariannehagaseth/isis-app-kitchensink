package org.isisaddons.app.kitchensink.dom.date;

import org.datanucleus.query.typesafe.*;
import org.datanucleus.api.jdo.query.*;

public class QDateObject extends org.datanucleus.api.jdo.query.PersistableExpressionImpl<DateObject> implements PersistableExpression<DateObject>
{
    public static final QDateObject jdoCandidate = candidate("this");

    public static QDateObject candidate(String name)
    {
        return new QDateObject(null, name, 5);
    }

    public static QDateObject candidate()
    {
        return jdoCandidate;
    }

    public static QDateObject parameter(String name)
    {
        return new QDateObject(DateObject.class, name, ExpressionType.PARAMETER);
    }

    public static QDateObject variable(String name)
    {
        return new QDateObject(DateObject.class, name, ExpressionType.VARIABLE);
    }

    public final StringExpression name;
    public final DateTimeExpression someJavaUtilDateMandatory;
    public final DateTimeExpression someJavaUtilDateOptional;
    public final DateTimeExpression someJavaUtilDateHidden;
    public final DateTimeExpression someJavaUtilDateDisabled;
    public final DateTimeExpression someJavaUtilDateWithValidation;
    public final DateTimeExpression someJavaUtilDateMandatoryWithChoices;
    public final DateTimeExpression someJavaUtilDateOptionalWithChoices;
    public final DateExpression someJavaSqlDateMandatory;
    public final DateExpression someJavaSqlDateOptional;
    public final DateExpression someJavaSqlDateHidden;
    public final DateExpression someJavaSqlDateDisabled;
    public final DateExpression someJavaSqlDateWithValidation;
    public final DateExpression someJavaSqlDateMandatoryWithChoices;
    public final DateExpression someJavaSqlDateOptionalWithChoices;
    public final ObjectExpression<org.joda.time.LocalDate> someJodaLocalDateMandatory;
    public final ObjectExpression<org.joda.time.LocalDate> someJodaLocalDateOptional;
    public final ObjectExpression<org.joda.time.LocalDate> someJodaLocalDateHidden;
    public final ObjectExpression<org.joda.time.LocalDate> someJodaLocalDateDisabled;
    public final ObjectExpression<org.joda.time.LocalDate> someJodaLocalDateWithValidation;
    public final ObjectExpression<org.joda.time.LocalDate> someJodaLocalDateMandatoryWithChoices;
    public final ObjectExpression<org.joda.time.LocalDate> someJodaLocalDateOptionalWithChoices;
    public final ObjectExpression<org.joda.time.DateTime> someJodaDateTimeMandatory;
    public final ObjectExpression<org.joda.time.DateTime> someJodaDateTimeOptional;
    public final ObjectExpression<org.joda.time.DateTime> someJodaDateTimeHidden;
    public final ObjectExpression<org.joda.time.DateTime> someJodaDateTimeDisabled;
    public final ObjectExpression<org.joda.time.DateTime> someJodaDateTimeWithValidation;
    public final ObjectExpression<org.joda.time.DateTime> someJodaDateTimeMandatoryWithChoices;
    public final ObjectExpression<org.joda.time.DateTime> someJodaDateTimeOptionalWithChoices;
    public final ObjectExpression<java.sql.Timestamp> someJavaSqlTimestampMandatory;
    public final ObjectExpression<java.sql.Timestamp> someJavaSqlTimestampOptional;
    public final ObjectExpression<java.sql.Timestamp> someJavaSqlTimestampHidden;
    public final ObjectExpression<java.sql.Timestamp> someJavaSqlTimestampDisabled;
    public final ObjectExpression<java.sql.Timestamp> someJavaSqlTimestampWithValidation;
    public final ObjectExpression<java.sql.Timestamp> someJavaSqlTimestampMandatoryWithChoices;
    public final ObjectExpression<java.sql.Timestamp> someJavaSqlTimestampOptionalWithChoices;
    public final ObjectExpression<com.google.common.base.Function<org.joda.time.LocalDate,org.joda.time.DateTime>> LOCALDATE_AS_DATETIME;
    public final ObjectExpression<com.google.common.base.Function<org.joda.time.DateTime,java.util.Date>> DATETIME_AS_JAVAUTILDATE;
    public final ObjectExpression<com.google.common.base.Function<org.joda.time.LocalDate,java.util.Date>> LOCALDATE_AS_JAVAUTILDATE;
    public final ObjectExpression<com.google.common.base.Function<java.util.Date,java.sql.Date>> JAVAUTILDATE_AS_JAVASQLDATE;
    public final ObjectExpression<com.google.common.base.Function<java.util.Date,java.sql.Timestamp>> JAVAUTILDATE_AS_JAVASQTIMESTAMP;
    public final ObjectExpression<com.google.common.base.Function<org.joda.time.LocalDate,java.sql.Date>> LOCALDATE_AS_JAVASQLDATE;
    public final ObjectExpression<com.google.common.base.Function<org.joda.time.LocalDate,java.sql.Timestamp>> LOCALDATE_AS_JAVASQLTIMESTAMP;
    public final ObjectExpression<org.apache.isis.applib.DomainObjectContainer> container;
    public final ObjectExpression<org.apache.isis.applib.services.clock.ClockService> clockService;

    public QDateObject(PersistableExpression parent, String name, int depth)
    {
        super(parent, name);
        this.name = new StringExpressionImpl(this, "name");
        this.someJavaUtilDateMandatory = new DateTimeExpressionImpl(this, "someJavaUtilDateMandatory");
        this.someJavaUtilDateOptional = new DateTimeExpressionImpl(this, "someJavaUtilDateOptional");
        this.someJavaUtilDateHidden = new DateTimeExpressionImpl(this, "someJavaUtilDateHidden");
        this.someJavaUtilDateDisabled = new DateTimeExpressionImpl(this, "someJavaUtilDateDisabled");
        this.someJavaUtilDateWithValidation = new DateTimeExpressionImpl(this, "someJavaUtilDateWithValidation");
        this.someJavaUtilDateMandatoryWithChoices = new DateTimeExpressionImpl(this, "someJavaUtilDateMandatoryWithChoices");
        this.someJavaUtilDateOptionalWithChoices = new DateTimeExpressionImpl(this, "someJavaUtilDateOptionalWithChoices");
        this.someJavaSqlDateMandatory = new DateExpressionImpl(this, "someJavaSqlDateMandatory");
        this.someJavaSqlDateOptional = new DateExpressionImpl(this, "someJavaSqlDateOptional");
        this.someJavaSqlDateHidden = new DateExpressionImpl(this, "someJavaSqlDateHidden");
        this.someJavaSqlDateDisabled = new DateExpressionImpl(this, "someJavaSqlDateDisabled");
        this.someJavaSqlDateWithValidation = new DateExpressionImpl(this, "someJavaSqlDateWithValidation");
        this.someJavaSqlDateMandatoryWithChoices = new DateExpressionImpl(this, "someJavaSqlDateMandatoryWithChoices");
        this.someJavaSqlDateOptionalWithChoices = new DateExpressionImpl(this, "someJavaSqlDateOptionalWithChoices");
        this.someJodaLocalDateMandatory = new ObjectExpressionImpl<org.joda.time.LocalDate>(this, "someJodaLocalDateMandatory");
        this.someJodaLocalDateOptional = new ObjectExpressionImpl<org.joda.time.LocalDate>(this, "someJodaLocalDateOptional");
        this.someJodaLocalDateHidden = new ObjectExpressionImpl<org.joda.time.LocalDate>(this, "someJodaLocalDateHidden");
        this.someJodaLocalDateDisabled = new ObjectExpressionImpl<org.joda.time.LocalDate>(this, "someJodaLocalDateDisabled");
        this.someJodaLocalDateWithValidation = new ObjectExpressionImpl<org.joda.time.LocalDate>(this, "someJodaLocalDateWithValidation");
        this.someJodaLocalDateMandatoryWithChoices = new ObjectExpressionImpl<org.joda.time.LocalDate>(this, "someJodaLocalDateMandatoryWithChoices");
        this.someJodaLocalDateOptionalWithChoices = new ObjectExpressionImpl<org.joda.time.LocalDate>(this, "someJodaLocalDateOptionalWithChoices");
        this.someJodaDateTimeMandatory = new ObjectExpressionImpl<org.joda.time.DateTime>(this, "someJodaDateTimeMandatory");
        this.someJodaDateTimeOptional = new ObjectExpressionImpl<org.joda.time.DateTime>(this, "someJodaDateTimeOptional");
        this.someJodaDateTimeHidden = new ObjectExpressionImpl<org.joda.time.DateTime>(this, "someJodaDateTimeHidden");
        this.someJodaDateTimeDisabled = new ObjectExpressionImpl<org.joda.time.DateTime>(this, "someJodaDateTimeDisabled");
        this.someJodaDateTimeWithValidation = new ObjectExpressionImpl<org.joda.time.DateTime>(this, "someJodaDateTimeWithValidation");
        this.someJodaDateTimeMandatoryWithChoices = new ObjectExpressionImpl<org.joda.time.DateTime>(this, "someJodaDateTimeMandatoryWithChoices");
        this.someJodaDateTimeOptionalWithChoices = new ObjectExpressionImpl<org.joda.time.DateTime>(this, "someJodaDateTimeOptionalWithChoices");
        this.someJavaSqlTimestampMandatory = new ObjectExpressionImpl<java.sql.Timestamp>(this, "someJavaSqlTimestampMandatory");
        this.someJavaSqlTimestampOptional = new ObjectExpressionImpl<java.sql.Timestamp>(this, "someJavaSqlTimestampOptional");
        this.someJavaSqlTimestampHidden = new ObjectExpressionImpl<java.sql.Timestamp>(this, "someJavaSqlTimestampHidden");
        this.someJavaSqlTimestampDisabled = new ObjectExpressionImpl<java.sql.Timestamp>(this, "someJavaSqlTimestampDisabled");
        this.someJavaSqlTimestampWithValidation = new ObjectExpressionImpl<java.sql.Timestamp>(this, "someJavaSqlTimestampWithValidation");
        this.someJavaSqlTimestampMandatoryWithChoices = new ObjectExpressionImpl<java.sql.Timestamp>(this, "someJavaSqlTimestampMandatoryWithChoices");
        this.someJavaSqlTimestampOptionalWithChoices = new ObjectExpressionImpl<java.sql.Timestamp>(this, "someJavaSqlTimestampOptionalWithChoices");
        this.LOCALDATE_AS_DATETIME = new ObjectExpressionImpl<com.google.common.base.Function<org.joda.time.LocalDate,org.joda.time.DateTime>>(this, "LOCALDATE_AS_DATETIME");
        this.DATETIME_AS_JAVAUTILDATE = new ObjectExpressionImpl<com.google.common.base.Function<org.joda.time.DateTime,java.util.Date>>(this, "DATETIME_AS_JAVAUTILDATE");
        this.LOCALDATE_AS_JAVAUTILDATE = new ObjectExpressionImpl<com.google.common.base.Function<org.joda.time.LocalDate,java.util.Date>>(this, "LOCALDATE_AS_JAVAUTILDATE");
        this.JAVAUTILDATE_AS_JAVASQLDATE = new ObjectExpressionImpl<com.google.common.base.Function<java.util.Date,java.sql.Date>>(this, "JAVAUTILDATE_AS_JAVASQLDATE");
        this.JAVAUTILDATE_AS_JAVASQTIMESTAMP = new ObjectExpressionImpl<com.google.common.base.Function<java.util.Date,java.sql.Timestamp>>(this, "JAVAUTILDATE_AS_JAVASQTIMESTAMP");
        this.LOCALDATE_AS_JAVASQLDATE = new ObjectExpressionImpl<com.google.common.base.Function<org.joda.time.LocalDate,java.sql.Date>>(this, "LOCALDATE_AS_JAVASQLDATE");
        this.LOCALDATE_AS_JAVASQLTIMESTAMP = new ObjectExpressionImpl<com.google.common.base.Function<org.joda.time.LocalDate,java.sql.Timestamp>>(this, "LOCALDATE_AS_JAVASQLTIMESTAMP");
        this.container = new ObjectExpressionImpl<org.apache.isis.applib.DomainObjectContainer>(this, "container");
        this.clockService = new ObjectExpressionImpl<org.apache.isis.applib.services.clock.ClockService>(this, "clockService");
    }

    public QDateObject(Class type, String name, org.datanucleus.api.jdo.query.ExpressionType exprType)
    {
        super(type, name, exprType);
        this.name = new StringExpressionImpl(this, "name");
        this.someJavaUtilDateMandatory = new DateTimeExpressionImpl(this, "someJavaUtilDateMandatory");
        this.someJavaUtilDateOptional = new DateTimeExpressionImpl(this, "someJavaUtilDateOptional");
        this.someJavaUtilDateHidden = new DateTimeExpressionImpl(this, "someJavaUtilDateHidden");
        this.someJavaUtilDateDisabled = new DateTimeExpressionImpl(this, "someJavaUtilDateDisabled");
        this.someJavaUtilDateWithValidation = new DateTimeExpressionImpl(this, "someJavaUtilDateWithValidation");
        this.someJavaUtilDateMandatoryWithChoices = new DateTimeExpressionImpl(this, "someJavaUtilDateMandatoryWithChoices");
        this.someJavaUtilDateOptionalWithChoices = new DateTimeExpressionImpl(this, "someJavaUtilDateOptionalWithChoices");
        this.someJavaSqlDateMandatory = new DateExpressionImpl(this, "someJavaSqlDateMandatory");
        this.someJavaSqlDateOptional = new DateExpressionImpl(this, "someJavaSqlDateOptional");
        this.someJavaSqlDateHidden = new DateExpressionImpl(this, "someJavaSqlDateHidden");
        this.someJavaSqlDateDisabled = new DateExpressionImpl(this, "someJavaSqlDateDisabled");
        this.someJavaSqlDateWithValidation = new DateExpressionImpl(this, "someJavaSqlDateWithValidation");
        this.someJavaSqlDateMandatoryWithChoices = new DateExpressionImpl(this, "someJavaSqlDateMandatoryWithChoices");
        this.someJavaSqlDateOptionalWithChoices = new DateExpressionImpl(this, "someJavaSqlDateOptionalWithChoices");
        this.someJodaLocalDateMandatory = new ObjectExpressionImpl<org.joda.time.LocalDate>(this, "someJodaLocalDateMandatory");
        this.someJodaLocalDateOptional = new ObjectExpressionImpl<org.joda.time.LocalDate>(this, "someJodaLocalDateOptional");
        this.someJodaLocalDateHidden = new ObjectExpressionImpl<org.joda.time.LocalDate>(this, "someJodaLocalDateHidden");
        this.someJodaLocalDateDisabled = new ObjectExpressionImpl<org.joda.time.LocalDate>(this, "someJodaLocalDateDisabled");
        this.someJodaLocalDateWithValidation = new ObjectExpressionImpl<org.joda.time.LocalDate>(this, "someJodaLocalDateWithValidation");
        this.someJodaLocalDateMandatoryWithChoices = new ObjectExpressionImpl<org.joda.time.LocalDate>(this, "someJodaLocalDateMandatoryWithChoices");
        this.someJodaLocalDateOptionalWithChoices = new ObjectExpressionImpl<org.joda.time.LocalDate>(this, "someJodaLocalDateOptionalWithChoices");
        this.someJodaDateTimeMandatory = new ObjectExpressionImpl<org.joda.time.DateTime>(this, "someJodaDateTimeMandatory");
        this.someJodaDateTimeOptional = new ObjectExpressionImpl<org.joda.time.DateTime>(this, "someJodaDateTimeOptional");
        this.someJodaDateTimeHidden = new ObjectExpressionImpl<org.joda.time.DateTime>(this, "someJodaDateTimeHidden");
        this.someJodaDateTimeDisabled = new ObjectExpressionImpl<org.joda.time.DateTime>(this, "someJodaDateTimeDisabled");
        this.someJodaDateTimeWithValidation = new ObjectExpressionImpl<org.joda.time.DateTime>(this, "someJodaDateTimeWithValidation");
        this.someJodaDateTimeMandatoryWithChoices = new ObjectExpressionImpl<org.joda.time.DateTime>(this, "someJodaDateTimeMandatoryWithChoices");
        this.someJodaDateTimeOptionalWithChoices = new ObjectExpressionImpl<org.joda.time.DateTime>(this, "someJodaDateTimeOptionalWithChoices");
        this.someJavaSqlTimestampMandatory = new ObjectExpressionImpl<java.sql.Timestamp>(this, "someJavaSqlTimestampMandatory");
        this.someJavaSqlTimestampOptional = new ObjectExpressionImpl<java.sql.Timestamp>(this, "someJavaSqlTimestampOptional");
        this.someJavaSqlTimestampHidden = new ObjectExpressionImpl<java.sql.Timestamp>(this, "someJavaSqlTimestampHidden");
        this.someJavaSqlTimestampDisabled = new ObjectExpressionImpl<java.sql.Timestamp>(this, "someJavaSqlTimestampDisabled");
        this.someJavaSqlTimestampWithValidation = new ObjectExpressionImpl<java.sql.Timestamp>(this, "someJavaSqlTimestampWithValidation");
        this.someJavaSqlTimestampMandatoryWithChoices = new ObjectExpressionImpl<java.sql.Timestamp>(this, "someJavaSqlTimestampMandatoryWithChoices");
        this.someJavaSqlTimestampOptionalWithChoices = new ObjectExpressionImpl<java.sql.Timestamp>(this, "someJavaSqlTimestampOptionalWithChoices");
        this.LOCALDATE_AS_DATETIME = new ObjectExpressionImpl<com.google.common.base.Function<org.joda.time.LocalDate,org.joda.time.DateTime>>(this, "LOCALDATE_AS_DATETIME");
        this.DATETIME_AS_JAVAUTILDATE = new ObjectExpressionImpl<com.google.common.base.Function<org.joda.time.DateTime,java.util.Date>>(this, "DATETIME_AS_JAVAUTILDATE");
        this.LOCALDATE_AS_JAVAUTILDATE = new ObjectExpressionImpl<com.google.common.base.Function<org.joda.time.LocalDate,java.util.Date>>(this, "LOCALDATE_AS_JAVAUTILDATE");
        this.JAVAUTILDATE_AS_JAVASQLDATE = new ObjectExpressionImpl<com.google.common.base.Function<java.util.Date,java.sql.Date>>(this, "JAVAUTILDATE_AS_JAVASQLDATE");
        this.JAVAUTILDATE_AS_JAVASQTIMESTAMP = new ObjectExpressionImpl<com.google.common.base.Function<java.util.Date,java.sql.Timestamp>>(this, "JAVAUTILDATE_AS_JAVASQTIMESTAMP");
        this.LOCALDATE_AS_JAVASQLDATE = new ObjectExpressionImpl<com.google.common.base.Function<org.joda.time.LocalDate,java.sql.Date>>(this, "LOCALDATE_AS_JAVASQLDATE");
        this.LOCALDATE_AS_JAVASQLTIMESTAMP = new ObjectExpressionImpl<com.google.common.base.Function<org.joda.time.LocalDate,java.sql.Timestamp>>(this, "LOCALDATE_AS_JAVASQLTIMESTAMP");
        this.container = new ObjectExpressionImpl<org.apache.isis.applib.DomainObjectContainer>(this, "container");
        this.clockService = new ObjectExpressionImpl<org.apache.isis.applib.services.clock.ClockService>(this, "clockService");
    }
}
