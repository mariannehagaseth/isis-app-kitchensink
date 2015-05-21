/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package dom.regulation;

/*
#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
*/

//import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.Collection;

import javax.jdo.JDOHelper;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.VersionStrategy;

import com.google.common.base.Objects;
import com.google.common.base.Predicate;
import com.google.common.collect.Ordering;

import dom.regulation.Regulation.FinalizedEvent;
import dom.regulation.Regulation.RegulationsComparator;

import org.joda.time.LocalDate;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.Identifier;
import org.apache.isis.applib.NonRecoverableException;
import org.apache.isis.applib.RecoverableException;
import org.apache.isis.applib.annotation.*;
import org.apache.isis.applib.annotation.ActionSemantics.Of;
import org.apache.isis.applib.annotation.Bulk.AppliesTo;
import org.apache.isis.applib.annotation.Bulk.InteractionContext.InvokedAs;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.security.UserMemento;
import org.apache.isis.applib.services.eventbus.ActionInteractionEvent;
import org.apache.isis.applib.services.eventbus.EventBusService;
import org.apache.isis.applib.services.scratchpad.Scratchpad;
import org.apache.isis.applib.services.wrapper.HiddenException;
import org.apache.isis.applib.services.wrapper.WrapperFactory;
import org.apache.isis.applib.util.ObjectContracts;
import org.apache.isis.applib.util.TitleBuffer;
import org.apache.isis.applib.value.Blob;
import org.apache.isis.applib.value.Clob;
import org.apache.isis.applib.services.clock.ClockService;



import dom.regulation.RESTclientTest;

//@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE)
@javax.jdo.annotations.PersistenceCapable(
        identityType=IdentityType.DATASTORE,
        schema = "regulation",
        table = "RegulationRule"
)
@javax.jdo.annotations.DatastoreIdentity(
        strategy=javax.jdo.annotations.IdGeneratorStrategy.IDENTITY,
         column="id")
@javax.jdo.annotations.Version(
        strategy=VersionStrategy.VERSION_NUMBER, 
        column="version")
@javax.jdo.annotations.Uniques({
    @javax.jdo.annotations.Unique(
            name="RegulationRule_description_must_be_unique", 
            members={"ownedBy","sectionTitle"})
})
@javax.jdo.annotations.Queries( {
    @javax.jdo.annotations.Query(
            name = "findByRegulationRule", language = "JDOQL",
            value = "SELECT "
                    + "FROM dom.regulation.RegulationRule "
                    + "WHERE ownedBy == :ownedBy")
    })
@DomainObject(objectType="REGULATIONRULE", autoCompleteRepository=RegulationRule.class, autoCompleteAction="autocomplete")
// default unless overridden by autoCompleteNXxx() method
//@Bounded - if there were a small number of instances only (overrides autoComplete functionality)
//@Bookmarkable
@MemberGroupLayout (columnSpans={6,6,0},left={"RegulationRule","Rule"}, middle={"General"})
public class RegulationRule implements Comparable<RegulationRule> {
    //region > LOG
    /**
     * It isn't common for entities to log, but they can if required.  
     * Isis uses slf4j API internally (with log4j as implementation), and is the recommended API to use. 
     */
//    private final static org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(ToDoItem.class);
    private final static org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(RegulationRule.class);
      //endregion

    // region > title, icon
    public String title() {
        final TitleBuffer buf = new TitleBuffer();
        buf.append(getSectionTitle());
        if (isFinalized()) {
            buf.append("- TEXT Finalized!");
        } else {
            try {
                     buf.append("");
                }
            catch(HiddenException ex) {
                // ignore
            }
        }
        return buf.toString();
    }
    
    public String iconName() {
        return !isFinalized() ? "regulationRule" : "done";
         }
    //endregion


    private String sectionTitle;

   @javax.jdo.annotations.Column(allowsNull="false", length=255)
//      @PropertyInteraction()
   @Property(regexPattern="\\w[@&:\\-\\,\\.\\+ \\w]*") 
   @MemberOrder(name="RegulationRule", sequence="10")
   @PropertyLayout(typicalLength=100)
    public String getSectionTitle() {
      return sectionTitle;
    }

     public void setSectionTitle(final String sectionTitle) {
     this.sectionTitle = sectionTitle;
    }
    public void modifySectionTitle(final String sectionTitle) {
        setSectionTitle(sectionTitle);
    }
    public void clearSectionTitle() {
        setSectionTitle(null);
    }
    //endregion

  //region > Subject:list of key words fetched from Luxid (property)

    private String subject;
    @javax.jdo.annotations.Column(allowsNull="true", length=255)
    @MemberOrder(name="RegulationRule", sequence="60")
    //@PropertyLayout(typicalLength=380,multiLine=2)
    @Property(editing= Editing.DISABLED,editingDisabledReason="Subject is a list of keywords fetched from Luxid: Cannot be edited")
    public String getSubject() {
    	        return subject;
    }

    @javax.jdo.annotations.Column(allowsNull="true", length=255)
    public void setSubject(final String subject) {
        this.subject = subject;
    }
    //endregion
   
    
   // mhaga: Must be changed to class
   // Region: Target 
   private String hasTarget;

  @javax.jdo.annotations.Column(allowsNull="false", length=1000)
//     @PropertyInteraction()
  @Property(regexPattern="\\w[@&:\\-\\,\\.\\+ \\w]*") 
  @MemberOrder(name="RegulationRule", sequence="20")
  @PropertyLayout(typicalLength=1000, multiLine=4)
   public String getHasTarget() {
     return hasTarget;
   }

    public void setHasTarget(final String hasTarget) {
    this.hasTarget = hasTarget;
   }
   public void modifyHasTarget(final String hasTarget) {
       setHasTarget(hasTarget);
   }
   public void clearHasTarget() {
       setHasTarget(null);
   }
   //endregion Target

   //Start region REST client TEST
   //region > showRestTest (property), setShowRestTest (action)
   // Call Consolidation Web Service
  
   private String showRestTest;
   //private BigDecimal cost;

   @javax.jdo.annotations.Column(allowsNull="true", length=1000)
   @Property(editing= Editing.DISABLED,editingDisabledReason="Use action to update Finalized")
   @MemberOrder(name="RegulationRule", sequence="28")
   @PropertyLayout(typicalLength=1000, multiLine=4)
   public String getShowRestTest() {
	   //public BigDecimal getCost() {
	   // return restClientTest.getTest();
	  return showRestTest;
	   }

    public void setShowRestTest(final String showRestTest) {
  // Does not work!!this.showRestTest = restClientTest.getTest();
    	 this.showRestTest = showRestTest;
    	   }
    
   public void modifyShowRestTest(final String showRestTest) {
       setShowRestTest(showRestTest);
   }
   public void clearShowRestTest() {
       setShowRestTest(null);
   }
     
  	//region > RestTest (action)
    @Action(semantics=SemanticsOf.NON_IDEMPOTENT)
   public String testRestClient() {
    	 // public ToDoItem updateCost(
    	String returnValue= restClientTest.getTest() ;
        setShowRestTest(returnValue);
    	return returnValue; 
    	}
 
   //endregion REST client TEST
   
   
     
   // mhaga: Must be changed to class
   // Region: Requirement
   private String hasRequirement;

  @javax.jdo.annotations.Column(allowsNull="false", length=1000)
//     @PropertyInteraction()
  @Property(regexPattern="\\w[@&:\\-\\,\\.\\+ \\w]*") 
  @MemberOrder(name="RegulationRule", sequence="30")
  @PropertyLayout(typicalLength=1000, multiLine=4)
   public String getHasRequirement() {
     return hasRequirement;
   }

    public void setHasRequirement(final String hasRequirement) {
    this.hasRequirement = hasRequirement;
   }
   public void modifyHasRequirement(final String hasRequirement) {
       setHasRequirement(hasRequirement);
   }
   public void clearHasRequirement() {
       setHasRequirement(null);
   }
   //endregion Requirement


   // mhaga: Must be changed to class
   // Region: Context 
   private String hasContext;

  @javax.jdo.annotations.Column(allowsNull="true", length=1000)
//     @PropertyInteraction()
  @Property(regexPattern="\\w[@&:\\-\\,\\.\\+ \\w]*") 
  @MemberOrder(name="RegulationRule", sequence="40")
  @PropertyLayout(typicalLength=1000, multiLine=4)
   public String getHasContext() {
     return hasContext;
   }

    public void setHasContext(final String hasContext) {
    this.hasContext = hasContext;
   }
   public void modifyHasContext(final String hasContext) {
       setHasContext(hasContext);
   }
   public void clearHasContext() {
       setHasContext(null);
   }
   //endregion Context

   // mhaga: Must be changed to class
   // Region: Exception
   private String hasException;

  @javax.jdo.annotations.Column(allowsNull="true", length=1000)
//     @PropertyInteraction()
  @Property(regexPattern="\\w[@&:\\-\\,\\.\\+ \\w]*") 
  @MemberOrder(name="RegulationRule", sequence="45")
  @PropertyLayout(typicalLength=1000, multiLine=4)
   public String getHasException() {
     return hasException;
   }

    public void setHasException(final String hasException) {
    this.hasException = hasException;
   }
   public void modifyHasException(final String hasException) {
       setHasException(hasException);
   }
   public void clearHasException() {
       setHasException(null);
   }
   //endregion Exception
   

   
/****
 * mhaga: ??? ???
   //region > rule (property)
   private Rule rule;
   @javax.jdo.annotations.Column(name="ruleId",allowsNull="true")
   @MemberOrder(name="General", sequence="80")
   public Rule getRule() {
   	        return rule;
   }
   public void setRule(final Rule rule) {
       this.rule = rule;
   }
   //endregion

  ***/ 
   
// region Invalidated (property)
@javax.jdo.annotations.Column(allowsNull="true")
private boolean invalidated;
@MemberOrder(sequence="50")

public boolean getInvalidated() {
    return invalidated;
}

public void setInvalidated(final boolean invalidated) {
    this.invalidated = invalidated;
}
// end region

// region isMandatory (property)
@javax.jdo.annotations.Column(allowsNull="true")
private boolean isMandatory;
@MemberOrder(sequence="51")

public boolean getIsMandatory() {
    return isMandatory;
}

public void setIsMandatory(final boolean isMandatory) {
    this.isMandatory = isMandatory;
}
// end region    

//region ruleAND (property)
@javax.jdo.annotations.Column(allowsNull="true")
private boolean ruleAND;
@MemberOrder(sequence="52")

public boolean getRuleAND() {
 return ruleAND;
}

public void setRuleAND(final boolean ruleAND) {
 this.ruleAND = ruleAND;
}
//end region   

//region ruleOR(property)
@javax.jdo.annotations.Column(allowsNull="true")
private boolean ruleOR;
@MemberOrder(sequence="53")

public boolean getRuleOR() {
 return ruleOR;
}

public void setRuleOR(final boolean ruleOR) {
 this.ruleOR = ruleOR;
}
//end region   


    //region > ownedBy (property)

    private String ownedBy;
    @PropertyLayout(hidden=Where.EVERYWHERE)
    @ActionLayout(hidden=Where.EVERYWHERE)
    @javax.jdo.annotations.Column(allowsNull="true")
    public String getOwnedBy() {
        return ownedBy;
    }
	@ActionLayout(hidden=Where.EVERYWHERE)
    @javax.jdo.annotations.Column(allowsNull="true")
    public void setOwnedBy(final String ownedBy) {
        this.ownedBy = ownedBy;
    }
    //endregion


    //region > finalized (property), finalized (action), notYetFinalized (action)

    private boolean finalized;

  @Property(editing= Editing.DISABLED,editingDisabledReason="Use action to update Finalized")
    public boolean isFinalized() {
        return finalized;
    }

//    @Named("Finalized") Trouble with updating regulation when
// 	  putting this here. Why ??? Where to put it ???
    public void setFinalized(final boolean finalize) {
        this.finalized = finalize;
    }

 ///	@ActionInteraction(FinalizedEvent.class)
	@Action(domainEvent=FinalizedEvent.class, invokeOn = InvokeOn.OBJECT_AND_COLLECTION)
////    @Bulk
//  public ToDoItem completed() {
    public RegulationRule finalized() {
        setFinalized(true);
        
        //
        // remainder of method just demonstrates the use of the Bulk.InteractionContext service 
        //
        @SuppressWarnings("unused")
        final List<Object> allObjects = bulkInteractionContext.getDomainObjects();
        
        LOG.debug("finalized: "
                + bulkInteractionContext.getIndex() +
                " [" + bulkInteractionContext.getSize() + "]"
                + (bulkInteractionContext.isFirst() ? " (first)" : "")
                + (bulkInteractionContext.isLast() ? " (last)" : ""));

        // if invoked as a regular action, return this object;
        // otherwise (if invoked as bulk), return null (so go back to the list)
        return bulkInteractionContext.getInvokedAs() == InvokedAs.REGULAR? this: null;
    }
 

	// disable action dependent on state of object
    public String disableFinalized() {
        return isFinalized() ? "Already Finalized" : null;
    }

    

	//@ActionInteraction(NoLongerFinalizedEvent.class)
	@Action(domainEvent=NoLongerFinalizedEvent.class, invokeOn = InvokeOn.OBJECT_AND_COLLECTION)
    //@Bulk
    public RegulationRule notYetFinalized() {
        setFinalized(false);

        // if invoked as a regular action, return this object;
        // otherwise (if invoked as bulk), return null (so go back to the list)
        return bulkInteractionContext.getInvokedAs() == InvokedAs.REGULAR? this: null;
    }
    // disable action dependent on state of object
    public String disableNotYetFinalized() {
        return !finalized ? "Not yet Finalized" : null;
    }
    //endregion

    //region > completeSlowly (property)
	@ActionLayout(hidden=Where.EVERYWHERE)
    public void finalizeSlowly(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
        }
        setFinalized(true);
    }
    //endregion
	
/*** mhaga. not needed??
	// Add Regulation to Section (FreeText)
    // department=regulation
    //empolyee=freeText
    
        // mapping is done to this property:
        @javax.jdo.annotations.Column(allowsNull="true")
        private Regulation regulation;
        @javax.jdo.annotations.Column(allowsNull="true")
        public Regulation getRegulation() { return regulation; }
        @javax.jdo.annotations.Column(allowsNull="true")
        public void setRegulation(Regulation regulation) { this.regulation = regulation; }
        
// End   Regulation to Section (FreeText)
   
        ***/
	
	

	// Add Regulation to RegulationRule
    // department=regulation
    //empolyee=RegulationRule
    
        // mapping is done to this property:
        @javax.jdo.annotations.Column(allowsNull="true")
        private Regulation regulation;
        @javax.jdo.annotations.Column(allowsNull="true")
        public Regulation getRegulation() { return regulation; }
        @javax.jdo.annotations.Column(allowsNull="true")
        public void setRegulation(Regulation regulation) { this.regulation = regulation; }
        
// End   Regulation to RegulationRule

	
	
	//region > version (derived property)
    public Long getVersionSequence() {
        if(!(this instanceof javax.jdo.spi.PersistenceCapable)) {
            return null;
        } 
        javax.jdo.spi.PersistenceCapable persistenceCapable = (javax.jdo.spi.PersistenceCapable) this;
        final Long version = (Long) JDOHelper.getVersion(persistenceCapable);
        return version;
    }
    // hide property (imperatively, based on state of object)
    public boolean hideVersionSequence() {
        return !(this instanceof javax.jdo.spi.PersistenceCapable);
    }
    //endregion

    
 

    //region > clone (action)
    // the name of the action in the UI
    // nb: method is not called "clone()" is inherited by java.lang.Object and
    // (a) has different semantics and (b) is in any case automatically ignored
    // by the framework
    public RegulationRule duplicate(
          final @Parameter(regexPattern="\\w[@&:\\-\\,\\.\\+ \\w]*") @ParameterLayout(named="Section Title") String sectionTitle, 
          final  											 @ParameterLayout(named="Target") String hasTarget,
          final  @Parameter(optionality=Optionality.OPTIONAL)@ParameterLayout(named="Context") String hasContext,
          final  											 @ParameterLayout(named="Requirement") String hasRequirement,
          final @Parameter(optionality=Optionality.OPTIONAL)@ParameterLayout(named="ShowREST") String showRestTest,
          final   @Parameter(optionality=Optionality.OPTIONAL) @ParameterLayout(named="Exception") String hasException,
          final @Parameter(optionality=Optionality.OPTIONAL) @ParameterLayout(named="Invalidated") boolean invalidated,
          final @Parameter(optionality=Optionality.OPTIONAL) @ParameterLayout(named="IsMandatory") boolean isMandatory,
          final @Parameter(optionality=Optionality.OPTIONAL) @ParameterLayout(named="RuleAND") boolean ruleAND,
          final @Parameter(optionality=Optionality.OPTIONAL) @ParameterLayout(named="RuleOR") boolean ruleOR,
                      final @Parameter(optionality=Optionality.OPTIONAL) @ParameterLayout(named="Subject (key words)") String subject,
             final @Parameter(optionality=Optionality.OPTIONAL) @ParameterLayout(named="Regulation") Regulation regulation
             //,
             //final @Parameter(optionality=Optionality.OPTIONAL) @ParameterLayout(named="Rule") Rule rule
            ) 
    {
        return regulationRules.newRegulationRule(sectionTitle, 
        		hasTarget,
        		hasContext,
        		hasRequirement,
        		showRestTest,
        		hasException,
        		subject,
        		invalidated, 
        		isMandatory, 
        		ruleAND,
        		ruleOR,
        		regulation
        		//, rule
        		);
    }
    
    /***
    public String default0Duplicate() {
        return getSectionTitle() + " - Copy";
    }
  
       public String default1Duplicate() {
        return getHasTarget();
    }
       public String default2Duplicate() {
           return getHasContext();
       }
       public String default3Duplicate() {
           return getHasRequirement();
       }
       public String default4Duplicate() {
           return getHasException();
       }
       
       public String default5Duplicate() {
           return getSubject();
       }
      
       public boolean default6Duplicate() {
           return getInvalidated();
       }
          public boolean default7Duplicate() {
              return getIsMandatory();
          }
          public boolean default8Duplicate() {
              return getRuleAND();
          }
          public boolean default9Duplicate() {
              return getRuleOR();
          }
    ***/
    
    //region > delete (action)
	//@ActionInteraction(DeletedEvent.class)
	@Action(domainEvent=DeletedEvent.class, invokeOn = InvokeOn.OBJECT_AND_COLLECTION)
   // @Bulk
    public List<RegulationRule> delete() {
      
    		  container.removeIfNotAlready(this);

    		  container.informUser("Deleted " + container.titleOf(this));
    		  return container.allMatches(
    	                new QueryDefault<RegulationRule>(RegulationRule.class, 
    	                        "findByRegulationRule", 
    	                        "ownedBy", container.getUser().getName())
    	                        );    		  
    	  }
    //endregion


    
    //region > lifecycle callbacks

    public void created() {
        LOG.debug("lifecycle callback: created: " + this.toString());
    }

    public void loaded() {
        LOG.debug("lifecycle callback: loaded: " + this.toString());
    }

    public void persisting() {
        LOG.debug("lifecycle callback: persisting: " + this.toString());
    }

    public void persisted() {
        LOG.debug("lifecycle callback: persisted: " + this.toString());
    }

    public void updating() {
        LOG.debug("lifecycle callback: updating: " + this.toString());
    }
    public void updated() {
        LOG.debug("lifecycle callback: updated: " + this.toString());
    }

    public void removing() {
        LOG.debug("lifecycle callback: removing: " + this.toString());
    }

    public void removed() {
        LOG.debug("lifecycle callback: removed: " + this.toString());
    }
    //endregion

    //region > events

    @SuppressWarnings("deprecation")
	public static abstract class AbstractActionInteractionEvent extends ActionInteractionEvent<RegulationRule> {
        private static final long serialVersionUID = 1L;
        private final String description;
        public AbstractActionInteractionEvent(
                final String description,
                final RegulationRule source,
                final Identifier identifier,
                final Object... arguments) {
            super(source, identifier, arguments);
            this.description = description;
        }
        public String getEventDescription() {
            return description;
        }
    }


    public static class FinalizedEvent extends AbstractActionInteractionEvent {
        private static final long serialVersionUID = 1L;
        public FinalizedEvent(
                final RegulationRule source, 
                final Identifier identifier, 
                final Object... arguments) {
            super("finalized", source, identifier, arguments);
        }
    }

    public static class NoLongerFinalizedEvent extends AbstractActionInteractionEvent {
        private static final long serialVersionUID = 1L;
        public NoLongerFinalizedEvent(
                final RegulationRule source, 
                final Identifier identifier, 
                final Object... arguments) {
            super("no longer finalized", source, identifier, arguments);
        }
    }

    public static class DeletedEvent extends AbstractActionInteractionEvent {
        private static final long serialVersionUID = 1L;
        public DeletedEvent(
                final RegulationRule source, 
                final Identifier identifier, 
                final Object... arguments) {
            super("deleted", source, identifier, arguments);
        }
    }

    //endregion

    //region > predicates

    public static class Predicates {
        
        public static Predicate<RegulationRule> thoseOwnedBy(final String currentUser) {
            return new Predicate<RegulationRule>() {
                @Override
                public boolean apply(final RegulationRule regulationRule) {
                    return Objects.equal(regulationRule.getOwnedBy(), currentUser);
                }
            };
        }

        public static Predicate<RegulationRule> thoseFinalized(
                final boolean finalized) {
            return new Predicate<RegulationRule>() {
                @Override
                public boolean apply(final RegulationRule t) {
                    return Objects.equal(t.isFinalized(), finalized);
                }
            };
        }

        public static Predicate<RegulationRule> thoseWithSimilarDescription(final String sectionTitle) {
            return new Predicate<RegulationRule>() {
                @Override
                public boolean apply(final RegulationRule t) {
                    return t.getSectionTitle().contains(sectionTitle);
                }
            };
        }
           
    }

    //endregion

    //region > toString, compareTo
    @Override
    public String toString() {
// This must match the autocomplete stuff
    	return ObjectContracts.toString(this, "sectionTitle, subject, ownedBy");
    }

    /**
     * Required so can store in {@link SortedSet sorted set}s (eg {@link #getDependencies()}). 
     */
    @Override
    public int compareTo(final RegulationRule other) {
    	
    	return ObjectContracts.compare(this, other, "sectionTitle, subject, ownedBy");
    	    }
    //endregion

    //region > injected services
    @javax.inject.Inject
    private DomainObjectContainer container;

   
    @javax.inject.Inject
    private RegulationRules regulationRules;

    
    @SuppressWarnings("deprecation")
	Bulk.InteractionContext bulkInteractionContext;
    public void injectBulkInteractionContext(@SuppressWarnings("deprecation") Bulk.InteractionContext bulkInteractionContext) {
        this.bulkInteractionContext = bulkInteractionContext;
    }

    @javax.inject.Inject

    EventBusService eventBusService;
    public void injectEventBusService(EventBusService eventBusService) {
        this.eventBusService = eventBusService;
    }

    @javax.inject.Inject
    private WrapperFactory wrapperFactory;

    // maga ?? OK to inject here?
    @javax.inject.Inject 
    private RESTclientTest restClientTest; 

    //endregion

}   