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

//@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE)
@javax.jdo.annotations.PersistenceCapable(
        identityType=IdentityType.DATASTORE,
        schema = "regulation",
        table = "DefinitionItem"
)
@javax.jdo.annotations.DatastoreIdentity(
        strategy=javax.jdo.annotations.IdGeneratorStrategy.IDENTITY,
         column="id")
@javax.jdo.annotations.Version(
        strategy=VersionStrategy.VERSION_NUMBER, 
        column="version")
@javax.jdo.annotations.Uniques({
    @javax.jdo.annotations.Unique(
            name="DefinitionItem_description_must_be_unique", 
            members={"ownedBy","sectionTitle"})
})
@javax.jdo.annotations.Queries( {
    @javax.jdo.annotations.Query(
            name = "findByDefinitionItem", language = "JDOQL",
            value = "SELECT "
                    + "FROM dom.regulation.DefinitionItem "
                    + "WHERE ownedBy == :ownedBy")
    })
@DomainObject(objectType="DEFINITIONITEM", autoCompleteRepository=DefinitionItem.class, autoCompleteAction="autocomplete")
// default unless overridden by autoCompleteNXxx() method
//@Bounded - if there were a small number of instances only (overrides autoComplete functionality)
//@Bookmarkable
@MemberGroupLayout (columnSpans={6,6,0},left={"DefinitionItem","Rule"}, middle={"General"})
public class DefinitionItem implements Comparable<DefinitionItem> {
    //region > LOG
    /**
     * It isn't common for entities to log, but they can if required.  
     * Isis uses slf4j API internally (with log4j as implementation), and is the recommended API to use. 
     */
//    private final static org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(ToDoItem.class);
    private final static org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(DefinitionItem.class);
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
        return !isFinalized() ? "definitionItem" : "done";
         }
    //endregion


    private String sectionTitle;

   @javax.jdo.annotations.Column(allowsNull="false", length=255)
//      @PropertyInteraction()
   @Property(regexPattern="\\w[@&:\\-\\,\\.\\+ \\w]*") 
   @MemberOrder(name="DefinitionItem", sequence="10")
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
    @MemberOrder(name="DefinitionItem", sequence="60")
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

    //region > definedTermName (property)
    private String definedTermName;

    @javax.jdo.annotations.Column(allowsNull="false", length=1000)
    @MemberOrder(name="DefinitionItem", sequence="20")
    @PropertyLayout(typicalLength=500)
    public String getDefinedTermName() {
    	        return definedTermName;
    }

    
   @javax.jdo.annotations.Column(allowsNull="false", length=200)
    public void setDefinedTermName(final String definedTermName) {
        this.definedTermName = definedTermName;
    }
    //endregion
    
    
    //region > definedTermDefinition (property)
    private String definedTermDefinition;

    @javax.jdo.annotations.Column(allowsNull="false", length=10000)
    @MemberOrder(name="DefinitionItem", sequence="30")
    @PropertyLayout(typicalLength=500,multiLine=7)
    public String getDefinedTermDefinition() {
    	        return definedTermDefinition;
    }

    
   @javax.jdo.annotations.Column(allowsNull="false", length=200)
    public void setDefinedTermDefinition(final String definedTermDefinition) {
        this.definedTermDefinition = definedTermDefinition;
    }
    //endregion


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
    public DefinitionItem finalized() {
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
    public DefinitionItem notYetFinalized() {
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

	

	// Add Regulation to DefinitionItem
    // department=regulation
    //empolyee=DefinitionItem
    
        // mapping is done to this property:
        @javax.jdo.annotations.Column(allowsNull="true")
        private Regulation regulation;
        @javax.jdo.annotations.Column(allowsNull="true")
        public Regulation getRegulation() { return regulation; }
        @javax.jdo.annotations.Column(allowsNull="true")
        public void setRegulation(Regulation regulation) { this.regulation = regulation; }
        
// End   Regulation to DefinitionItem

	
	
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

    //region > Sub List Item (property), add (action), remove (action)
    // overrides the natural ordering
    public static class DefinitionItemComparator implements Comparator<DefinitionItem> {
        @Override
        public int compare(DefinitionItem p, DefinitionItem q) {
            Ordering<DefinitionItem> bySectionTitle = new Ordering<DefinitionItem>() {
                public int compare(final DefinitionItem p, final DefinitionItem q) {
                    return Ordering.natural().nullsFirst().compare(p.getSectionTitle(), q.getSectionTitle());
                }
            };
            return bySectionTitle
                    .compound(Ordering.<DefinitionItem>natural())
                    .compare(p, q);
        }
    }

    
 

    //region > clone (action)
    // the name of the action in the UI
    // nb: method is not called "clone()" is inherited by java.lang.Object and
    // (a) has different semantics and (b) is in any case automatically ignored
    // by the framework
    public DefinitionItem duplicate(
          final @Parameter(regexPattern="\\w[@&:\\-\\,\\.\\+ \\w]*") @ParameterLayout(named="Section Title") String sectionTitle, 
          final  	 @ParameterLayout(named="Defined Term Name") String definedTermName,
          final  					@ParameterLayout(named="Defined Term Definition") String definedTermDefinition,
          final @Parameter(optionality=Optionality.OPTIONAL) @ParameterLayout(named="Subject (key words)") String subject,
          final @Parameter(optionality=Optionality.OPTIONAL) @ParameterLayout(named="Invalidated") boolean invalidated,
             final @Parameter(optionality=Optionality.OPTIONAL) @ParameterLayout(named="Regulation") Regulation regulation
            ) 
    {
        return lists.newDefinitionItem(sectionTitle, definedTermName, definedTermDefinition, subject, invalidated,  regulation
        		);
    }
    public String default0Duplicate() {
        return getSectionTitle() + " - Copy";
    }
    public String default1Duplicate() {
        return getDefinedTermName();
    }
    public String default2Duplicate() {
        return getDefinedTermDefinition();
    }
    public String default3Duplicate() {
        return getSubject();
    }
      
       public boolean default4Duplicate() {
           return getInvalidated();
       }
          
    //region > delete (action)
	//@ActionInteraction(DeletedEvent.class)
	@Action(domainEvent=DeletedEvent.class, invokeOn = InvokeOn.OBJECT_AND_COLLECTION)
   // @Bulk
    public List<DefinitionItem> delete() {
      
    		  container.removeIfNotAlready(this);

    		  container.informUser("Deleted " + container.titleOf(this));
    		  return container.allMatches(
    	                new QueryDefault<DefinitionItem>(DefinitionItem.class, 
    	                        "findByDefinitionItem", 
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
	public static abstract class AbstractActionInteractionEvent extends ActionInteractionEvent<DefinitionItem> {
        private static final long serialVersionUID = 1L;
        private final String description;
        public AbstractActionInteractionEvent(
                final String description,
                final DefinitionItem source,
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
                final DefinitionItem source, 
                final Identifier identifier, 
                final Object... arguments) {
            super("finalized", source, identifier, arguments);
        }
    }

    public static class NoLongerFinalizedEvent extends AbstractActionInteractionEvent {
        private static final long serialVersionUID = 1L;
        public NoLongerFinalizedEvent(
                final DefinitionItem source, 
                final Identifier identifier, 
                final Object... arguments) {
            super("no longer finalized", source, identifier, arguments);
        }
    }

    public static class DeletedEvent extends AbstractActionInteractionEvent {
        private static final long serialVersionUID = 1L;
        public DeletedEvent(
                final DefinitionItem source, 
                final Identifier identifier, 
                final Object... arguments) {
            super("deleted", source, identifier, arguments);
        }
    }

    //endregion

    //region > predicates

    public static class Predicates {
        
        public static Predicate<DefinitionItem> thoseOwnedBy(final String currentUser) {
            return new Predicate<DefinitionItem>() {
                @Override
                public boolean apply(final DefinitionItem list) {
                    return Objects.equal(list.getOwnedBy(), currentUser);
                }
            };
        }

        public static Predicate<DefinitionItem> thoseFinalized(
                final boolean finalized) {
            return new Predicate<DefinitionItem>() {
                @Override
                public boolean apply(final DefinitionItem t) {
                    return Objects.equal(t.isFinalized(), finalized);
                }
            };
        }

        public static Predicate<DefinitionItem> thoseWithSimilarDescription(final String sectionTitle) {
            return new Predicate<DefinitionItem>() {
                @Override
                public boolean apply(final DefinitionItem t) {
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
    	return ObjectContracts.toString(this, "sectionTitle, definedTermName, subject, ownedBy");
    }

    /**
     * Required so can store in {@link SortedSet sorted set}s (eg {@link #getDependencies()}). 
     */
    @Override
    public int compareTo(final DefinitionItem other) {
    	
    	return ObjectContracts.compare(this, other, "sectionTitle, definedTermName, subject, ownedBy");
    	    }
    //endregion

    //region > injected services
    @javax.inject.Inject
    private DomainObjectContainer container;

   
    @javax.inject.Inject
    private DefinitionItems lists;

    
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

    //endregion

}   