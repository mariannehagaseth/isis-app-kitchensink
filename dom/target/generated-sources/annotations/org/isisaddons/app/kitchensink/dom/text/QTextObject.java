package org.isisaddons.app.kitchensink.dom.text;

import org.datanucleus.query.typesafe.*;
import org.datanucleus.api.jdo.query.*;

public class QTextObject extends org.datanucleus.api.jdo.query.PersistableExpressionImpl<TextObject> implements PersistableExpression<TextObject>
{
    public static final QTextObject jdoCandidate = candidate("this");

    public static QTextObject candidate(String name)
    {
        return new QTextObject(null, name, 5);
    }

    public static QTextObject candidate()
    {
        return jdoCandidate;
    }

    public static QTextObject parameter(String name)
    {
        return new QTextObject(TextObject.class, name, ExpressionType.PARAMETER);
    }

    public static QTextObject variable(String name)
    {
        return new QTextObject(TextObject.class, name, ExpressionType.VARIABLE);
    }

    public final StringExpression name;
    public final CharacterExpression someChar;
    public final CharacterExpression someCharHidden;
    public final CharacterExpression someCharDisabled;
    public final CharacterExpression someCharWithValidation;
    public final CharacterExpression someCharWithChoices;
    public final ObjectExpression<java.lang.Character> someCharacterWrapperMandatory;
    public final ObjectExpression<java.lang.Character> someCharacterWrapperOptional;
    public final ObjectExpression<java.lang.Character> someCharacterWrapperHidden;
    public final ObjectExpression<java.lang.Character> someCharacterWrapperDisabled;
    public final ObjectExpression<java.lang.Character> someCharacterWrapperWithValidation;
    public final ObjectExpression<java.lang.Character> someCharacterWrapperMandatoryWithChoices;
    public final ObjectExpression<java.lang.Character> someCharacterWrapperOptionalWithChoices;
    public final StringExpression someStringMandatory;
    public final StringExpression someStringOptional;
    public final StringExpression someStringHidden;
    public final StringExpression someStringDisabled;
    public final StringExpression someStringWithValidation;
    public final StringExpression someStringMandatoryWithChoices;
    public final StringExpression someStringOptionalWithChoices;
    public final StringExpression someString20;
    public final StringExpression someString50;
    public final StringExpression somePasswordMandatoryStr;
    public final StringExpression somePasswordOptionalStr;
    public final StringExpression somePasswordHiddenStr;
    public final StringExpression somePasswordDisabledStr;
    public final StringExpression somePasswordWithValidationStr;
    public final StringExpression somePasswordMandatoryWithChoicesStr;
    public final StringExpression somePasswordOptionalWithChoicesStr;
    public final StringExpression someStringMulti;
    public final StringExpression someStringMultiNoWrap;
    public final ObjectExpression<org.apache.isis.applib.DomainObjectContainer> container;

    public QTextObject(PersistableExpression parent, String name, int depth)
    {
        super(parent, name);
        this.name = new StringExpressionImpl(this, "name");
        this.someChar = new CharacterExpressionImpl(this, "someChar");
        this.someCharHidden = new CharacterExpressionImpl(this, "someCharHidden");
        this.someCharDisabled = new CharacterExpressionImpl(this, "someCharDisabled");
        this.someCharWithValidation = new CharacterExpressionImpl(this, "someCharWithValidation");
        this.someCharWithChoices = new CharacterExpressionImpl(this, "someCharWithChoices");
        this.someCharacterWrapperMandatory = new ObjectExpressionImpl<java.lang.Character>(this, "someCharacterWrapperMandatory");
        this.someCharacterWrapperOptional = new ObjectExpressionImpl<java.lang.Character>(this, "someCharacterWrapperOptional");
        this.someCharacterWrapperHidden = new ObjectExpressionImpl<java.lang.Character>(this, "someCharacterWrapperHidden");
        this.someCharacterWrapperDisabled = new ObjectExpressionImpl<java.lang.Character>(this, "someCharacterWrapperDisabled");
        this.someCharacterWrapperWithValidation = new ObjectExpressionImpl<java.lang.Character>(this, "someCharacterWrapperWithValidation");
        this.someCharacterWrapperMandatoryWithChoices = new ObjectExpressionImpl<java.lang.Character>(this, "someCharacterWrapperMandatoryWithChoices");
        this.someCharacterWrapperOptionalWithChoices = new ObjectExpressionImpl<java.lang.Character>(this, "someCharacterWrapperOptionalWithChoices");
        this.someStringMandatory = new StringExpressionImpl(this, "someStringMandatory");
        this.someStringOptional = new StringExpressionImpl(this, "someStringOptional");
        this.someStringHidden = new StringExpressionImpl(this, "someStringHidden");
        this.someStringDisabled = new StringExpressionImpl(this, "someStringDisabled");
        this.someStringWithValidation = new StringExpressionImpl(this, "someStringWithValidation");
        this.someStringMandatoryWithChoices = new StringExpressionImpl(this, "someStringMandatoryWithChoices");
        this.someStringOptionalWithChoices = new StringExpressionImpl(this, "someStringOptionalWithChoices");
        this.someString20 = new StringExpressionImpl(this, "someString20");
        this.someString50 = new StringExpressionImpl(this, "someString50");
        this.somePasswordMandatoryStr = new StringExpressionImpl(this, "somePasswordMandatoryStr");
        this.somePasswordOptionalStr = new StringExpressionImpl(this, "somePasswordOptionalStr");
        this.somePasswordHiddenStr = new StringExpressionImpl(this, "somePasswordHiddenStr");
        this.somePasswordDisabledStr = new StringExpressionImpl(this, "somePasswordDisabledStr");
        this.somePasswordWithValidationStr = new StringExpressionImpl(this, "somePasswordWithValidationStr");
        this.somePasswordMandatoryWithChoicesStr = new StringExpressionImpl(this, "somePasswordMandatoryWithChoicesStr");
        this.somePasswordOptionalWithChoicesStr = new StringExpressionImpl(this, "somePasswordOptionalWithChoicesStr");
        this.someStringMulti = new StringExpressionImpl(this, "someStringMulti");
        this.someStringMultiNoWrap = new StringExpressionImpl(this, "someStringMultiNoWrap");
        this.container = new ObjectExpressionImpl<org.apache.isis.applib.DomainObjectContainer>(this, "container");
    }

    public QTextObject(Class type, String name, org.datanucleus.api.jdo.query.ExpressionType exprType)
    {
        super(type, name, exprType);
        this.name = new StringExpressionImpl(this, "name");
        this.someChar = new CharacterExpressionImpl(this, "someChar");
        this.someCharHidden = new CharacterExpressionImpl(this, "someCharHidden");
        this.someCharDisabled = new CharacterExpressionImpl(this, "someCharDisabled");
        this.someCharWithValidation = new CharacterExpressionImpl(this, "someCharWithValidation");
        this.someCharWithChoices = new CharacterExpressionImpl(this, "someCharWithChoices");
        this.someCharacterWrapperMandatory = new ObjectExpressionImpl<java.lang.Character>(this, "someCharacterWrapperMandatory");
        this.someCharacterWrapperOptional = new ObjectExpressionImpl<java.lang.Character>(this, "someCharacterWrapperOptional");
        this.someCharacterWrapperHidden = new ObjectExpressionImpl<java.lang.Character>(this, "someCharacterWrapperHidden");
        this.someCharacterWrapperDisabled = new ObjectExpressionImpl<java.lang.Character>(this, "someCharacterWrapperDisabled");
        this.someCharacterWrapperWithValidation = new ObjectExpressionImpl<java.lang.Character>(this, "someCharacterWrapperWithValidation");
        this.someCharacterWrapperMandatoryWithChoices = new ObjectExpressionImpl<java.lang.Character>(this, "someCharacterWrapperMandatoryWithChoices");
        this.someCharacterWrapperOptionalWithChoices = new ObjectExpressionImpl<java.lang.Character>(this, "someCharacterWrapperOptionalWithChoices");
        this.someStringMandatory = new StringExpressionImpl(this, "someStringMandatory");
        this.someStringOptional = new StringExpressionImpl(this, "someStringOptional");
        this.someStringHidden = new StringExpressionImpl(this, "someStringHidden");
        this.someStringDisabled = new StringExpressionImpl(this, "someStringDisabled");
        this.someStringWithValidation = new StringExpressionImpl(this, "someStringWithValidation");
        this.someStringMandatoryWithChoices = new StringExpressionImpl(this, "someStringMandatoryWithChoices");
        this.someStringOptionalWithChoices = new StringExpressionImpl(this, "someStringOptionalWithChoices");
        this.someString20 = new StringExpressionImpl(this, "someString20");
        this.someString50 = new StringExpressionImpl(this, "someString50");
        this.somePasswordMandatoryStr = new StringExpressionImpl(this, "somePasswordMandatoryStr");
        this.somePasswordOptionalStr = new StringExpressionImpl(this, "somePasswordOptionalStr");
        this.somePasswordHiddenStr = new StringExpressionImpl(this, "somePasswordHiddenStr");
        this.somePasswordDisabledStr = new StringExpressionImpl(this, "somePasswordDisabledStr");
        this.somePasswordWithValidationStr = new StringExpressionImpl(this, "somePasswordWithValidationStr");
        this.somePasswordMandatoryWithChoicesStr = new StringExpressionImpl(this, "somePasswordMandatoryWithChoicesStr");
        this.somePasswordOptionalWithChoicesStr = new StringExpressionImpl(this, "somePasswordOptionalWithChoicesStr");
        this.someStringMulti = new StringExpressionImpl(this, "someStringMulti");
        this.someStringMultiNoWrap = new StringExpressionImpl(this, "someStringMultiNoWrap");
        this.container = new ObjectExpressionImpl<org.apache.isis.applib.DomainObjectContainer>(this, "container");
    }
}
