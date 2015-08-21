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

//import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.jdo.JDOHelper;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.VersionStrategy;

import com.google.common.base.Objects;
import com.google.common.base.Predicate;
import com.google.common.collect.Ordering;

import org.joda.time.LocalDate;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.Identifier;
import org.apache.isis.applib.NonRecoverableException;
import org.apache.isis.applib.RecoverableException;
import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.Bulk;
import org.apache.isis.applib.annotation.CollectionLayout;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.Editing;
import org.apache.isis.applib.annotation.InvokeOn;
import org.apache.isis.applib.annotation.MemberGroupLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.MinLength;
import org.apache.isis.applib.annotation.Optionality;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.Property;
import org.apache.isis.applib.annotation.PropertyLayout;
import org.apache.isis.applib.annotation.RenderType;
import org.apache.isis.applib.annotation.RestrictTo;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.annotation.Where;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.eventbus.ActionInteractionEvent;
import org.apache.isis.applib.services.eventbus.EventBusService;
import org.apache.isis.applib.services.wrapper.HiddenException;
import org.apache.isis.applib.services.wrapper.WrapperFactory;
import org.apache.isis.applib.util.ObjectContracts;
import org.apache.isis.applib.util.TitleBuffer;



@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(
        strategy=javax.jdo.annotations.IdGeneratorStrategy.IDENTITY,
         column="id")
@javax.jdo.annotations.Version(
        strategy=VersionStrategy.VERSION_NUMBER, 
        column="version")
@javax.jdo.annotations.Uniques({
    @javax.jdo.annotations.Unique(
            name="Regulation_description_must_be_unique", 
            members={"ownedBy","regulationTitle"})
})
@javax.jdo.annotations.Queries( {
    @javax.jdo.annotations.Query(
            name = "findByOwnedBy", language = "JDOQL",
            value = "SELECT "
                    + "FROM dom.regulation.Regulation "
                    + "WHERE ownedBy == :ownedBy"),
    @javax.jdo.annotations.Query(
            name = "findByOwnedByAndCompleteIsFalse", language = "JDOQL",
            value = "SELECT "
                    + "FROM dom.regulation.Regulation "
                    + "WHERE ownedBy == :ownedBy "
                    + "   && finalized == false"),
    @javax.jdo.annotations.Query(
            name = "findByOwnedByAndCompleteIsTrue", language = "JDOQL",
            value = "SELECT "
                    + "FROM dom.regulation.Regulation "
                    + "WHERE ownedBy == :ownedBy "
                    + "&& finalized == true"),
    @javax.jdo.annotations.Query(
            name = "findByOwnedByAndKpi", language = "JDOQL",
            value = "SELECT "
                    + "FROM dom.regulation.Regulation "
                    + "WHERE ownedBy == :ownedBy "
                    + "&& kpi == :kpi"),
    @javax.jdo.annotations.Query(
            name = "findByOwnedByAndRegulationTitleContains", language = "JDOQL",
            value = "SELECT "
                    + "FROM dom.regulation.Regulation "
                    + "WHERE ownedBy == :ownedBy && "
                    + "regulationTitle.indexOf(:regulationTitle) >= 0"),
   @javax.jdo.annotations.Query(
            name = "findByClauseOwnedBy", language = "JDOQL",
            value = "SELECT "
                  + "FROM dom.regulation.Clause "
                  + "WHERE ownedBy == :ownedBy"),
   @javax.jdo.annotations.Query(
                          name = "findByListItem", language = "JDOQL",
                          value = "SELECT "
                                + "FROM dom.regulation.ListItem "
                                + "WHERE ownedBy == :ownedBy"),
    
                                @javax.jdo.annotations.Query(
                                        name = "findByRegulationRule", language = "JDOQL",
                                        value = "SELECT "
                                              + "FROM dom.regulation.RegulationRule "
                                              + "WHERE ownedBy == :ownedBy"),
    @javax.jdo.annotations.Query(
            name = "findByDefinitionItem", language = "JDOQL",
            value = "SELECT "
                  + "FROM dom.regulation.DefinitionItem "
                  + "WHERE ownedBy == :ownedBy")

})
@DomainObject(objectType="REGULATION",autoCompleteRepository=Regulations.class, autoCompleteAction="autoComplete")
 // default unless overridden by autoCompleteNXxx() method
//@Bookmarkable
@MemberGroupLayout (
		columnSpans={6,6,0,12},
		left={"Regulation","Clause","Rule"},
		middle={"General"})
public class Regulation implements Categorized, Comparable<Regulation> {

    //region > LOG
    /**
     * It isn't common for entities to log, but they can if required.  
     * Isis uses slf4j API internally (with log4j as implementation), and is the recommended API to use. 
     */
    private final static org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(Regulation.class);
      //endregion

    // region > title, icon
    public String title() {
        final TitleBuffer buf = new TitleBuffer();
        buf.append(getRegulationTitle());
        if (isFinalized()) {
            buf.append("- REGULATION Finalized!");
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
        return !isFinalized() ? "regulation" : "done";
         }
    //endregion


    // Region regulationTitle
    
    private String regulationTitle;

    @javax.jdo.annotations.Column(allowsNull="false", length=255)
  //  @Property(domainEvent=)
   // @PropertyInteraction()
    @Property(regexPattern="\\w[@&:\\-\\,\\.\\+ \\w]*") 
    @MemberOrder(name="Regulation", sequence="10")
    @PropertyLayout(typicalLength=80)
    public String getRegulationTitle() {
      return regulationTitle;
    }

     public void setRegulationTitle(final String regulationTitle) {
     this.regulationTitle = regulationTitle;
    }
    public void modifyRegulationTitle(final String regulationTitle) {
        setRegulationTitle(regulationTitle);
    }
    public void clearRegulationTitle() {
        setRegulationTitle(null);
    }
    //endregion

    // Start region validityDate
    @javax.jdo.annotations.Persistent(defaultFetchGroup="true")
    private LocalDate validityDate;
    
    @javax.jdo.annotations.Column(allowsNull="true")
    @MemberOrder(sequence="40")
    public LocalDate getValidityDate() {
            return validityDate;
    }

    public void setValidityDate(final LocalDate validityDate) {
        this.validityDate = validityDate;
    }
    
    public void clearValidityDate() {
        setValidityDate(null);
    }
    
   public String validateValidityDate(final LocalDate validityDate) {
        if (validityDate == null) {
            return null;
        }
        return regulations.validateValidityDate(validityDate);
          } 
    //endregion ValidityDate
   
   // Start region amendmentDate
   @javax.jdo.annotations.Persistent(defaultFetchGroup="true")
   private LocalDate amendmentDate;
   
   @javax.jdo.annotations.Column(allowsNull="true")
   @MemberOrder(sequence="50")
   public LocalDate getAmendmentDate() {
           return amendmentDate;
   }

   public void setAmendmentDate(final LocalDate amendmentDate) {
	   this.amendmentDate = amendmentDate;
   }
   
   public void clearAmendmentDate() {
       setAmendmentDate(null);
   }
   
  public String validateAmendmentDate(final LocalDate amendmentDate) {
       if (amendmentDate == null) {
           return null;
       }
      //return regulations.validateAmendmentDate(amendmentDate);
       return null; // do not want to test
        } 
   //endregion

  // Start region applicabilityDate
  @javax.jdo.annotations.Persistent(defaultFetchGroup="true")
  private LocalDate applicabilityDate;
  
  @javax.jdo.annotations.Column(allowsNull="true")
  @MemberOrder(sequence="60")
  public LocalDate getApplicabilityDate() {
          return applicabilityDate;
  }

  public void setApplicabilityDate(final LocalDate applicabilityDate) {
      this.applicabilityDate = applicabilityDate;
  }
  
  public void clearApplicabilityDate() {
      setApplicabilityDate(null);
  }
  
 public String validateApplicabilityDate(final LocalDate applicabilityDate) {
      if (applicabilityDate == null) {
          return null;
      }
      return regulations.validateApplicabilityDate(applicabilityDate);
        } 
  //endregion

 
 // Start region expirationDate
 @javax.jdo.annotations.Persistent(defaultFetchGroup="true")
 private LocalDate expirationDate;
  @javax.jdo.annotations.Column(allowsNull="true")
 @MemberOrder(sequence="60")
 public LocalDate getExpirationDate() {
         return expirationDate;
 }

 public void setExpirationDate(final LocalDate expirationDate) {
     this.expirationDate = expirationDate;
 }
 
 public void clearExpirationDate() {
     setApplicabilityDate(null);
 }
 
//public String validateExpirationDate(final LocalDate expirationDate) {
//     if (expirationDate == null) {
//         return null;
//     }
//     return regulations.validateExpirationDate(expirationDate);
//       } 
 //endregion

 
//region > conformsTo (property)

////////////////// Must be changed to a domain entity.  
public enum RegulationTemplateType {
    Regulation,
    Definition,
    List,
    Rule}
@javax.jdo.annotations.Persistent(defaultFetchGroup="true") // ok ???
private RegulationTemplateType conformsTo;
@javax.jdo.annotations.Column(allowsNull="false")
@MemberOrder(name="Regulation", sequence="92")
public RegulationTemplateType getConformsTo() {
	        return conformsTo;
}
public void setConformsTo(final RegulationTemplateType conformsTo) {
    this.conformsTo = conformsTo;
}
//endregion
 

//region > Language (property)

//////////////////Must be changed to a domain entity.  
public enum LanguageType {
English,
French,
Spanish}

@javax.jdo.annotations.Persistent(defaultFetchGroup="true") // ok ???
private LanguageType language;
@javax.jdo.annotations.Column(allowsNull="false")
@MemberOrder(sequence="98")
public LanguageType getLanguage() {
return language;
}
public void setLanguage(final LanguageType language) {
this.language = language;
}
//endregion



//region > Subject:list of key words fetched from Luxid (property)

@javax.jdo.annotations.Persistent(defaultFetchGroup="true")
private String subject;
@javax.jdo.annotations.Column(allowsNull="true", length=255)
@MemberOrder(name="Regulation", sequence="94")
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

//region isMandatory (property)
@javax.jdo.annotations.Column(allowsNull="true")
@javax.jdo.annotations.Persistent(defaultFetchGroup="true")
private boolean isMandatory;
@MemberOrder(sequence="80")

public boolean getIsMandatory() {
 return isMandatory;
}

public void setIsMandatory(final boolean isMandatory) {
 this.isMandatory = isMandatory;
}
//end region

//region RegulationAND (property)
@javax.jdo.annotations.Column(allowsNull="true")
@javax.jdo.annotations.Persistent(defaultFetchGroup="true")
private boolean regulationAND;
@MemberOrder(sequence="82")

public boolean getRegulationAND() {
return regulationAND;
}

public void setRegulationAND(final boolean regulationAND) {
this.regulationAND = regulationAND;
}
//end region

//region RegulationOR (property)
@javax.jdo.annotations.Column(allowsNull="true")
@javax.jdo.annotations.Persistent(defaultFetchGroup="true")
private boolean regulationOR;
@MemberOrder(sequence="84")

public boolean getRegulationOR() {
return regulationOR;
}

public void setRegulationOR(final boolean regulationOR) {
this.regulationOR = regulationOR;
}
//end region


// region Invalidated (property)
@javax.jdo.annotations.Column(allowsNull="true")
@javax.jdo.annotations.Persistent(defaultFetchGroup="true")
private boolean invalidated;
@MemberOrder(sequence="97")

public boolean getInvalidated() {
    return invalidated;
}

public void setInvalidated(final boolean invalidated) {
    this.invalidated = invalidated;
}
// end region

//region > kpi (property)

    	   public static enum KPI {
    		   ManagementLeadershipAndAccountability,
    		   RecruitmentAndManagementOfShoreBasedPersonnel,
    		   RecruitmentAndManagementOfShipPersonnel,
    		   ReliabilityAndMaintenanceStandards,
    		   NavigationalSafety,
    		   CargoAndBallastOperations,
    		   ManagementOfChange,
    		   IncidentInvestigationAndAnalysis,
    		   SafetyManagementShoreBasedMonitoring,
    		   EnvironmentalManagement,
    		   EmergencyPreparednessAndContingencyPlanning,
    		   MeasurementAnalysisAndImprovement;
    }
 @javax.jdo.annotations.Persistent(defaultFetchGroup="true")  
    private KPI kpi;

    @javax.jdo.annotations.Column(allowsNull="true")
    // ???
    @Property(editing= Editing.DISABLED,editingDisabledReason="Use action to update kpi")
    /*The @Disabled annotation means that the member cannot be used in any instance of the class. When applied to the property it means that the user may not modify the value of that property (though it may still be modified programmatically). When applied to an action method, it means that the user cannot invoke that method.*/
    @MemberOrder(name="Regulation", sequence="50")
    public KPI getKpi() {
        return kpi;
    }

    public void setKpi(final KPI kpi) {
        this.kpi = kpi;
    }

    //endregion

    
    //region > regulationType (property)

    	   public static enum RegulationType {
    		   Certificate,
    		   Procedure,
    		   Checklist,
    		   TechnicalSpecification,
    		   OperationalSpecification,
    		   FunctionalRequirement,
    		   GoalBasedRegulation,
    		   Guideline,
    		   ReportSpecification,
    		   Template,
    		   Other;
    }
  @javax.jdo.annotations.Persistent(defaultFetchGroup="true")
  private RegulationType regulationType;

    @javax.jdo.annotations.Column(allowsNull="true")
    // ???
    @Property(editing= Editing.DISABLED,editingDisabledReason="Use action to update regulationType")
    @MemberOrder(name="Regulation", sequence="30")
    public RegulationType getRegulationType() {
        return regulationType;
    }
    public void setRegulationType(final RegulationType regulationType) {
        this.regulationType = regulationType;
    }
    //endregion

  
    //region > objective (property)
    @javax.jdo.annotations.Persistent(defaultFetchGroup="true")
    private String objective;
    @javax.jdo.annotations.Column(allowsNull="true", length=10000)
    @MemberOrder(name="Regulation", sequence="20")
//  //  @MultiLine(numberOfLines=5, preventWrapping=false)
    @PropertyLayout(typicalLength=50, multiLine=5)
    public String getObjective() {
    	        return objective;
    }
    public void setObjective(final String objective) {
        this.objective = objective;
    }
    //endregion


    //region > regulationText (property)
    @javax.jdo.annotations.Persistent(defaultFetchGroup="true")
    private String regulationText;
    @javax.jdo.annotations.Column(allowsNull="true", length=10000)
    @MemberOrder(name="Regulation", sequence="21")
//  //  @MultiLine(numberOfLines=5, preventWrapping=false)
    @PropertyLayout(typicalLength=50, multiLine=5)
    public String getRegulationText() {
    	        return regulationText;
    }
    public void setRegulationText(final String regulationText) {
        this.regulationText = regulationText;
    }
    //endregion
    
    //region > publishedIn (property)
    
  ////////////////// Must be changed to a domain entity.  
    public enum DocumentType {
        SOLAS,
        MARPOL}
   
    @javax.jdo.annotations.Persistent(defaultFetchGroup="true")
    private DocumentType publishedIn;
    @javax.jdo.annotations.Column(allowsNull="true")
    @MemberOrder(name="Regulation", sequence="40")
    public DocumentType getPublishedIn() {
    	        return publishedIn;
    }
    public void setPublishedIn(final DocumentType publishedIn) {
        this.publishedIn = publishedIn;
    }
    //endregion

    
    
    //region > ownedBy (property)
    @javax.jdo.annotations.Persistent(defaultFetchGroup="true")
    private String ownedBy;
    @PropertyLayout(hidden=Where.EVERYWHERE)
	@ActionLayout(hidden=Where.EVERYWHERE)
    @javax.jdo.annotations.Column(allowsNull="false")
    @Property(editing= Editing.DISABLED,editingDisabledReason="OwnedBy is static")
    public String getOwnedBy() {
        return ownedBy;
    }
	@ActionLayout(hidden=Where.EVERYWHERE)
    public void setOwnedBy(final String ownedBy) {
        this.ownedBy = ownedBy;
    }
    //endregion


	 //region > finalized (property)
	   @javax.jdo.annotations.Persistent(defaultFetchGroup="true")
    private boolean finalized;
   // @Property(editing= Editing.DISABLED,editingDisabledReason="Finialized is changed elsewhere")
    
	   @javax.jdo.annotations.Column(allowsNull="true")
	    public boolean getFinalized() {
	    	        return finalized;
	    }
	    public void setFinalized(final boolean finalized) {
	        this.finalized = finalized;
	    }
	  
	    public boolean isFinalized() {
	        return finalized;
	    }
	    //endregion
	   
	   /***
	  
    public void setFinalized(final boolean finalized) {
        this.finalized = finalized;
    }
    @SuppressWarnings({ "deprecation", "deprecation" })
	@Action(domainEvent=FinalizedEvent.class, invokeOn = InvokeOn.OBJECT_AND_COLLECTION)
   // @Bulk    
    public Regulation finalized() {
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
    @SuppressWarnings("deprecation")
	@Action(domainEvent=NoLongerFinalizedEvent.class, invokeOn = InvokeOn.OBJECT_AND_COLLECTION)
 //  @Bulk
//  public ToDoItem notYetCompleted() {
    public Regulation notYetFinalized() {
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
    // ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@ActionLayout(hidden=Where.EVERYWHERE)
    public void finalizedSlowly(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
        }
        setFinalized(true);
    }
    //endregion
 ***/

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


  
    //region > Dependent Regulations (property), add (action), remove (action)
    // overrides the natural ordering
    public static class RegulationsComparator implements Comparator<Regulation> {
        @Override
        public int compare(Regulation p, Regulation q) {
            Ordering<Regulation> byRegulationTitle = new Ordering<Regulation>() {
                public int compare(final Regulation p, final Regulation q) {
                    return Ordering.natural().nullsFirst().compare(p.getRegulationTitle(), q.getRegulationTitle());
                }
            };
            return byRegulationTitle
                    .compound(Ordering.<Regulation>natural())
                    .compare(p, q);
        }
    }

   @javax.jdo.annotations.Persistent(table="regulation_dependency")
   @javax.jdo.annotations.Join(column="regulationId")
    @javax.jdo.annotations.Element(column="dependentRegulationId")
     
    private SortedSet<Regulation> dependentRegulations = new TreeSet<Regulation>();
    @MemberOrder(name="Regulation", sequence = "98")

	//@CollectionInteraction
    //@Collection(domainEvent=Regulation.Dependencies.class)
    @CollectionLayout(sortedBy=RegulationsComparator.class,render=RenderType.EAGERLY)
    public SortedSet<Regulation> getDependentRegulations() {
        return dependentRegulations;
    }
    public void setDependentRegulations(final SortedSet<Regulation> dependentRegulations) {
        this.dependentRegulations = dependentRegulations;
    }
    public void addToDependentRegulations(final Regulation regulation) {
        getDependentRegulations().add(regulation);
    }
    public void removeFromDependentRegulations(final Regulation regulation) {
        getDependentRegulations().remove(regulation);
    }

// This is the add-Button!!! 
  @MemberOrder(name="Dependent Regulations", sequence = "10")
  public Regulation add(final @ParameterLayout(typicalLength=20) Regulation regulation) {
    	// By wrapping the call, Isis will detect that the collection is modified 
    	// and it will automatically send CollectionInteractionEvents to the Event Bus.
    	// ToDoItemSubscriptions is a demo subscriber to this event
        wrapperFactory.wrapSkipRules(this).addToDependentRegulations(regulation);
        return this;
    }
    
    public List<Regulation> autoComplete0Add(final @MinLength(2) String search) {
        final List<Regulation> list = regulations.autoComplete(search);
        list.removeAll(getDependentRegulations());
        list.remove(this);
        return list;
    }

    public String disableAdd(final Regulation regulation) {
    	        if(isFinalized()) {
            return "Cannot add dependencies for items that are Finalized";
        }
        return null;
    }
    // validate the provided argument prior to invoking action
    public String validateAdd(final Regulation regulation) {
    	        if(getDependentRegulations().contains(regulation)) {
            return "Already a dependency";
        }
        if(regulation == this) {
            return "Can't set up a dependency to self";
        }
        return null;
    }

    // This is the Remove-Button!!
    @MemberOrder(name="Dependent Regulations", sequence = "20")
    public Regulation remove(final @ParameterLayout(typicalLength=20) Regulation regulation) {
    	// By wrapping the call, Isis will detect that the collection is modified 
    	// and it will automatically send a CollectionInteractionEvent to the Event Bus.
        // ToDoItemSubscriptions is a demo subscriber to this event
        wrapperFactory.wrapSkipRules(this).removeFromDependentRegulations(regulation);
        return this;
    }
    // disable action dependent on state of object
    public String disableRemove(final Regulation regulation) {
        if(isFinalized()) {
            return "Cannot remove dependencies for items that are Finalized";
        }
        return getDependentRegulations().isEmpty()? "No dependencies to remove": null;
    }
    // validate the provided argument prior to invoking action
    public String validateRemove(final Regulation regulation) {
    	        if(!getDependentRegulations().contains(regulation)) {
            return "Not a dependency";
        }
        return null;
    }
    // provide a drop-down
    public Collection<Regulation> choices0Remove() {
    	        return getDependentRegulations();
    }
    //endregion Dependent Regulations
 
 
    //region > Sub Regulations (property), add (action), remove (action)
    // overrides the natural ordering

  
   @javax.jdo.annotations.Persistent(table="sub_regulation")
 //   @javax.jdo.annotations.PersistentCapable(table="sub_regulation")
       @javax.jdo.annotations.Join(column="regulationId")
    @javax.jdo.annotations.Element(column="subRegulationId")
    private SortedSet<Regulation> subRegulations = new TreeSet<Regulation>();
    @MemberOrder(name="Regulation", sequence = "81")

	//@CollectionInteraction
    //@Collection(domainEvent=Regulation.Dependencies.class)
    @CollectionLayout(sortedBy=RegulationsComparator.class,render=RenderType.EAGERLY)
    public SortedSet<Regulation> getSubRegulations() {
        return subRegulations;
    }
    public void setSubRegulations(final SortedSet<Regulation> subRegulations) {
        this.subRegulations = subRegulations;
    }
    public void addToSubRegulations(final Regulation regulation) {
        getSubRegulations().add(regulation);
    }
    public void removeFromSubRegulations(final Regulation regulation) {
        getSubRegulations().remove(regulation);
    }

// This is the add-Button!!! 
  @MemberOrder(name="Sub Regulations", sequence = "10")
     public Regulation addSubRegulations(final @ParameterLayout(typicalLength=20) Regulation regulation) {
    	// By wrapping the call, Isis will detect that the collection is modified 
    	// and it will automatically send CollectionInteractionEvents to the Event Bus.
    	// ToDoItemSubscriptions is a demo subscriber to this event
        wrapperFactory.wrapSkipRules(this).addToSubRegulations(regulation);
        return this;
    }
    
    public List<Regulation> autoComplete0AddSubRegulations(final @MinLength(2) String search) {
        final List<Regulation> list = regulations.autoComplete(search);
        list.removeAll(getSubRegulations());
        list.remove(this);
        return list;
    }

 //   public String disableAddLinkedRegulation(final Regulation regulation) {
    public String disableAddSubRegulations(final Regulation regulation) {
    	        if(isFinalized()) {
            return "Cannot add sub regulation for regulations that are Finalized";
        }
        return null;
    }
    // validate the provided argument prior to invoking action
    public String validateAddSubRegulations(final Regulation regulation) {
    	        if(getSubRegulations().contains(regulation)) {
            return "Already a sub regulation";
        }
        if(regulation == this) {
            return "Can't set up a sub regulation to self";
        }
        return null;
    }

    // This is the Remove-Button!!
    @MemberOrder(name="Sub Regulations", sequence = "20")
    
    public Regulation removeSubRegulations(final @ParameterLayout(typicalLength=20) Regulation regulation) {
    	// By wrapping the call, Isis will detect that the collection is modified 
    	// and it will automatically send a CollectionInteractionEvent to the Event Bus.
        // ToDoItemSubscriptions is a demo subscriber to this event
        wrapperFactory.wrapSkipRules(this).removeFromSubRegulations(regulation);
        return this;
    }
    // disable action dependent on state of object
    public String disableRemoveSubRegulations(final Regulation regulation) {
        if(isFinalized()) {
            return "Cannot remove sub regulation for regulations that are Finalized";
        }
        return getSubRegulations().isEmpty()? "No sub regulation to remove": null;
    }
    // validate the provided argument prior to invoking action
    public String validateRemoveSubRegulations(final Regulation regulation) {
    	        if(!getSubRegulations().contains(regulation)) {
            return "Not a sub regulation";
        }
        return null;
    }
    // provide a drop-down
    public Collection<Regulation> choices0RemoveSubRegulations() {
    	        return getSubRegulations();
    }
    //endregion Sub Regulations

   

    //region > clone (action)

    // the name of the action in the UI
    // nb: method is not called "clone()" is inherited by java.lang.Object and
    // (a) has different semantics and (b) is in any case automatically ignored
    // by the framework
    public Regulation duplicate(
            final     @Parameter(regexPattern="\\w[@&:\\-\\,\\.\\+ \\w]*") @ParameterLayout(named="Regulation Title") String regulationTitle, 
            final @ParameterLayout(named="Validity Date") LocalDate validityDate,
            final @Parameter(optionality=Optionality.OPTIONAL) @ParameterLayout(named="Key Performance Index") KPI kpi,
            final@Parameter(optionality=Optionality.OPTIONAL)  @ParameterLayout(named="Regulation Type") RegulationType regulationType,
            final @Parameter(optionality=Optionality.OPTIONAL)  @ParameterLayout(named="Objective") String objective,
            final @Parameter(optionality=Optionality.OPTIONAL)  @ParameterLayout(named="RegulationText") String regulationText,
            final @Parameter(optionality=Optionality.OPTIONAL) @ParameterLayout(named="Published In") DocumentType publishedIn,
            final @Parameter(optionality=Optionality.OPTIONAL) @ParameterLayout(named="Amendment Date") LocalDate amendmentDate,
            final @Parameter(optionality=Optionality.OPTIONAL) @ParameterLayout(named="Applicability Date") LocalDate applicabilityDate,
            final @Parameter(optionality=Optionality.OPTIONAL) @ParameterLayout(named="Expiration Date") LocalDate expirationDate,
            final 											   @ParameterLayout(named="Conforms To (Template)") RegulationTemplateType conformsTo,
            final 											   @ParameterLayout(named="Language") LanguageType language,
            final @Parameter(optionality=Optionality.OPTIONAL) @ParameterLayout(named="Subject (Key words)") String subject,
            final @Parameter(optionality=Optionality.OPTIONAL) @ParameterLayout(named="IsMandatory") Boolean isMandatory,
            final @Parameter(optionality=Optionality.OPTIONAL) @ParameterLayout(named="RegulationAND") Boolean regulationAND,
            final @Parameter(optionality=Optionality.OPTIONAL) @ParameterLayout(named="RegulationOR") Boolean regulationOR,
            final @Parameter(optionality=Optionality.OPTIONAL) @ParameterLayout(named="Invalidated") Boolean invalidated,
            final @Parameter(optionality=Optionality.OPTIONAL) @ParameterLayout(named="Finalized") Boolean finalized
             ) 
    {
        return regulations.newRegulation(regulationTitle, objective,
        									regulationText,
        									regulationType,
        									validityDate, 
        									kpi,
        									publishedIn,
        									amendmentDate,
        									applicabilityDate,
        									expirationDate,
        									conformsTo,
        									language,
        									subject,
        									isMandatory,
        									regulationAND,
        									regulationOR,
        									invalidated,
        									finalized);
    }
    public String default0Duplicate() {
        return getRegulationTitle() + " - Copy";
    }
    public LocalDate default1Duplicate() {
        return getValidityDate();
    }
    public KPI default2Duplicate() {
        return getKpi();
    }
    public RegulationType default3Duplicate() {
        return getRegulationType();
    }

    public String default4Duplicate() {
        return getObjective();
    }

    //endregion 

    @SuppressWarnings("deprecation")
	//region > delete (action)
    @Action(domainEvent=DeletedEvent.class,invokeOn = InvokeOn.OBJECT_AND_COLLECTION)
 // @Bulk
    public List<Regulation> delete() {
      
    	  if(isFinalized()) {
    	      container.informUser("Regulation has been Finalized and thus cannot be deleted");
    	        return regulations.isFinalized(); 
    	  }
    	  else
    	  {
    		  container.removeIfNotAlready(this);

    		  container.informUser("Deleted " + container.titleOf(this));
        
    		  // invalid to return 'this' (cannot render a deleted object)
              return regulations.notYetFinalized(); 
    	  }
    }
    //endregion

    //region > openSourceCodeOnGithub (action)
    @SuppressWarnings("deprecation")
	
    @Action(semantics=SemanticsOf.SAFE,restrictTo=RestrictTo.PROTOTYPING)
    public URL openSourceCodeOnGithub() throws MalformedURLException {
        return new URL("https://github.com/apache/isis/tree/master/example/application/myapp/dom/src/main/java/dom/todo/ToDoItem.java");
          }
    //endregion

    //region > demoException (action)

    static enum DemoExceptionType {
        RecoverableException,
        RecoverableExceptionAutoEscalated,
        NonRecoverableException;
    }
    
	//@Prototype
	@Action(semantics = SemanticsOf.SAFE,restrictTo=RestrictTo.PROTOTYPING)
    public void demoException(final @ParameterLayout(named="Type") DemoExceptionType type) {
        switch(type) {
        case NonRecoverableException:
            throw new NonRecoverableException("Demo throwing " + type.name());
        case RecoverableException:
            throw new RecoverableException("Demo throwing " + type.name());
        case RecoverableExceptionAutoEscalated:
            try {
                // this will trigger an exception (because category cannot be null), causing the xactn to be aborted
                setRegulationTitle(null);
                container.flush();
            } catch(Exception e) {
                // it's a programming mistake to throw only a recoverable exception here, because of the xactn's state.
                // the framework should instead auto-escalate this to a non-recoverable exception
                throw new RecoverableException("Demo throwing " + type.name(), e);
            }
        }
    }
    //endregion

/***	
  // Add clauses to Regulation region
  // department=regulation
    //empolyee=clause

        @javax.jdo.annotations.Persistent(mappedBy="regulation")
        @javax.jdo.annotations.Join // Make a separate join table.
        private SortedSet<Clause> clauses = new TreeSet<Clause>();
        // ???
        @SuppressWarnings("deprecation")
		//@CollectionInteraction
        //@Collection(domainEvent=Regulation.Clauses.class)
	    @CollectionLayout(sortedBy=ClausesComparator.class,render=RenderType.EAGERLY)
       public SortedSet<Clause> getClauses() {
        	return clauses;
        	}
        public void setClauses(SortedSet<Clause> clauses) {
        	this.clauses = clauses; 
        	}
        
    
  // END - Add clauses to Regulation region
 
// Start region Link Regulation --> to Clause    
  //region > clauses (property), add (action), remove (action)
    
    // Is this OK ???
 // overrides the natural ordering
    public static class ClausesComparator implements Comparator<Clause> {
        @Override
        public int compare(Clause p, Clause q) {
            Ordering<Clause> byClauseTitle = new Ordering<Clause>() {
                public int compare(final Clause p, final Clause q) {
                    return Ordering.natural().nullsFirst().compare(p.getClauseTitle(), q.getClauseTitle());
                }
            };
            return byClauseTitle
                    .compound(Ordering.<Clause>natural())
                    .compare(p, q);
        }
    }
    
    public void removeFromClauses(final Clause clause) {
        if(clause == null || !getClauses().contains(clauses)) return;
        // ??? Check this ??? clause.setRegulation(null);  
        getClauses().remove(clause);
        // skip this: Set the previous instead. OK??  clauses.remove(c);
    }


//This is the add-Button!!!  
    @MemberOrder(name="Clauses", sequence = "40")
    @PropertyLayout(typicalLength=30)
    public Regulation addClause(final Clause clause) {
    wrapperFactory.wrapSkipRules(clause).setRegulation(this);
    getClauses().add(clause);
    return this;
}


  
public List<Clause> autoComplete0AddClause(final @MinLength(2) String search) {
	final List<Clause> list = container.allMatches(new QueryDefault<Clause>(Clause.class,"findByClauseOwnedBy", "ownedBy", "ecompliance"));
	list.removeAll(getClauses());//Remove those Clauses already linked to the Regulation
	return list;
}

public String disableAddClause(final Clause clause) {
    if(isFinalized()) {
        return "Cannot add clauses for regulatons that are Finalized";
    }
    return null;
}


// validate the provided argument prior to invoking action
public String validateAddClause(final Clause clause) {
    if(getClauses().contains(clause)) {
        return "Already a clause";
    }
    return null;
}

// This is the Remove-Button!!
@MemberOrder(name="clauses", sequence = "20")
public Regulation removeClause(final @ParameterLayout(typicalLength=30) Clause clause) {
	// By wrapping the call, Isis will detect that the collection is modified 
	// and it will automatically send a CollectionInteractionEvent to the Event Bus.
    // ToDoItemSubscriptions is a demo subscriber to this event
	// wrapperFactory.wrapSkipRules(this).removeFromClauses(clause);
    wrapperFactory.wrapSkipRules(this).removeFromClauses(clause);
    return this;
}

// disable action dependent on state of object
public String disableRemoveClause(final Clause clause) {
    if(isFinalized()) {
        return "Cannot remove clauses for regulations that are Finalized";
    }
    return getClauses().isEmpty()? "No clauses to remove": null;
}
// validate the provided argument prior to invoking action
public String validateRemoveClause(final Clause clause) {
    if(!getClauses().contains(clause)) {
        return "Not a clause";
    }
    return null;
}
// provide a drop-down
public Collection<Clause> choices0RemoveClause() {
    return getClauses();
}
 
//endregion region Link Regulation --> to Clause   
 ***/  
    
 
	/***  
     // Add ListItem to Regulation region

          @javax.jdo.annotations.Persistent(mappedBy="regulation")
          @javax.jdo.annotations.Join // Make a separate join table.
//          private SortedSet<FreeText> freeTexts = new TreeSet<FreeText>();
              private SortedSet<ListItem> listItems = new TreeSet<ListItem>();
                   @MemberOrder(name="Regulation", sequence = "80")
                   @PropertyLayout(named="ListItems")
          // ???
          @SuppressWarnings("deprecation")
  		//@CollectionInteraction
          //@Collection(domainEvent=Regulation.Clauses.class)
  	    @CollectionLayout(sortedBy=ListItemsComparator.class,render=RenderType.EAGERLY)
         public SortedSet<ListItem> getListItems() {
          	return listItems;
          	}
          public void setListItems(SortedSet<ListItem> listItems) {
          	this.listItems = listItems; 
          	}
          
    // overrides the natural ordering
      public static class ListItemsComparator implements Comparator<ListItem> {
          @Override
          public int compare(ListItem p, ListItem q) {
              Ordering<ListItem> bySectionTitle = new Ordering<ListItem>() {
                  public int compare(final ListItem p, final ListItem q) {
                      return Ordering.natural().nullsFirst().compare(p.getSectionTitle(), q.getSectionTitle());
                  }
              };
              return bySectionTitle
                      .compound(Ordering.<ListItem>natural())
                      .compare(p, q);
          }
      }
      
      public void removeFromListItems(final ListItem listItem) {
          if(listItem == null || !getListItems().contains(listItem)) return;
          getListItems().remove(listItem);
      }
      
  //This is the add-Button!!!  
 
      @MemberOrder(name="List Items", sequence = "10")
      public Regulation addListItem(final ListItem listItem) {
      wrapperFactory.wrapSkipRules(listItem).setRegulation(this);
      getListItems().add(listItem);
      return this;
  }


    
  public List<ListItem> autoComplete0AddListItem(final @MinLength(2) String search) {
  	final List<ListItem> list = container.allMatches(new QueryDefault<ListItem>(ListItem.class,"findByListItem", "ownedBy", "ecompliance"));
  	list.removeAll(getListItems());//Remove those Clauses already linked to the Regulation
  	return list;
  }

  public String disableAddListItem(final ListItem listItem) {
      if(isFinalized()) {
          return "Cannot add ListItem for Regulatons that are Finalized";
      }
      return null;
  }


  // validate the provided argument prior to invoking action
  public String validateAddListItem(final ListItem listItem) {
      if(getListItems().contains(listItem)) {
          return "Already a List Item";
      }
      return null;
  }

  
// This is the Remove-Button!!
  @MemberOrder(name="List Items", sequence = "20")
  public Regulation removeListItem(final @ParameterLayout(typicalLength=30) ListItem listItem) {
  	// By wrapping the call, Isis will detect that the collection is modified 
  	// and it will automatically send a CollectionInteractionEvent to the Event Bus.
      // ToDoItemSubscriptions is a demo subscriber to this event
      wrapperFactory.wrapSkipRules(this).removeFromListItems(listItem);
      return this;
  }

  // disable action dependent on state of object
  public String disableRemoveListItem(final ListItem listItem) {
      if(isFinalized()) {
          return "Cannot remove List Items from regulations that are Finalized";
      }
      return getListItems().isEmpty()? "No List Items to remove": null;
  }
  // validate the provided argument prior to invoking action
  public String validateRemoveListItem(final ListItem listItem) {
      if(!getListItems().contains(listItem)) {
          return "Not a List Item";
      }
      return null;
  }
  // provide a drop-down
  public Collection<ListItem> choices0RemoveListItem() {
      return getListItems();
  }
   
  //endregion region Link Regulation --> to (several) list items

***/
  
  // Add RegulationRule to Regulation region

  @javax.jdo.annotations.Persistent(mappedBy="regulation")
  @javax.jdo.annotations.Join // Make a separate join table.
//  private SortedSet<FreeText> freeTexts = new TreeSet<FreeText>();
      private SortedSet<RegulationRule> regulationRules = new TreeSet<RegulationRule>();
           @MemberOrder(name="Regulation", sequence = "82")
           @PropertyLayout(named="RegulationRules")
  // ???
  @SuppressWarnings("deprecation")
	//@CollectionInteraction
  //@Collection(domainEvent=Regulation.Clauses.class)
  @CollectionLayout(sortedBy=RegulationRulesComparator.class,render=RenderType.EAGERLY)
 public SortedSet<RegulationRule> getRegulationRules() {
  	return regulationRules;
  	}
  public void setRegulationRules(SortedSet<RegulationRule> regulationRule) {
  	this.regulationRules = regulationRule; 
  	}
  
// overrides the natural ordering
public static class RegulationRulesComparator implements Comparator<RegulationRule> {
  @Override
  public int compare(RegulationRule p, RegulationRule q) {
      Ordering<RegulationRule> bySectionTitle = new Ordering<RegulationRule>() {
          public int compare(final RegulationRule p, final RegulationRule q) {
              return Ordering.natural().nullsFirst().compare(p.getSectionTitle(), q.getSectionTitle());
          }
      };
      return bySectionTitle
              .compound(Ordering.<RegulationRule>natural())
              .compare(p, q);
  }
}

public void removeFromRegulationRules(final RegulationRule regulationRule) {
  if(regulationRule == null || !getRegulationRules().contains(regulationRule)) return;
  getRegulationRules().remove(regulationRule);
}

//This is the add-Button!!!  

@MemberOrder(name="RegulationRules", sequence = "10")
public Regulation addRegulationRule(final RegulationRule regulationRule) {
wrapperFactory.wrapSkipRules(regulationRule).setRegulation(this);
getRegulationRules().add(regulationRule);
return this;
}



public List<RegulationRule> autoComplete0AddRegulationRule(final @MinLength(2) String search) {
final List<RegulationRule> list = container.allMatches(new QueryDefault<RegulationRule>(RegulationRule.class,"findByRegulationRule", "ownedBy", "ecompliance"));
list.removeAll(getRegulationRules());//Remove those Clauses already linked to the Regulation
return list;
}

public String disableAddRegulationRule(final RegulationRule regulationRule) {
if(isFinalized()) {
  return "Cannot add Rule for Regulatons that are Finalized";
}
return null;
}


// validate the provided argument prior to invoking action
public String validateAddRegulationRule(final RegulationRule regulationRule) {
if(getRegulationRules().contains(regulationRule)) {
  return "Already a Rule";
}
return null;
}


//This is the Remove-Button!!
@MemberOrder(name="RegulationRules", sequence = "20")
public Regulation removeRegulationRule(final @ParameterLayout(typicalLength=30) RegulationRule regulationRule) {
// By wrapping the call, Isis will detect that the collection is modified 
// and it will automatically send a CollectionInteractionEvent to the Event Bus.
// ToDoItemSubscriptions is a demo subscriber to this event
wrapperFactory.wrapSkipRules(this).removeRegulationRule(regulationRule);
return this;
}

// disable action dependent on state of object
public String disableRemoveRegulationRule(final RegulationRule regulationRule) {
if(isFinalized()) {
  return "Cannot remove Rule from regulations that are Finalized";
}
return getRegulationRules().isEmpty()? "No Rules to remove": null;
}
// validate the provided argument prior to invoking action
public String validateRemoveRegulationRule(final RegulationRule regulationRule) {
if(!getRegulationRules().contains(regulationRule)) {
  return "Not a Rule";
}
return null;
}
// provide a drop-down
public Collection<RegulationRule> choices0RemoveRegulationRule() {
return getRegulationRules();
}

//endregion region Link Regulation --> to (several) Rules


// Add ListItem to Regulation region

@javax.jdo.annotations.Persistent(mappedBy="regulation")
@javax.jdo.annotations.Join // Make a separate join table.
    private SortedSet<DefinitionItem> definitionItems = new TreeSet<DefinitionItem>();
         @MemberOrder(name="Regulation", sequence = "84")
         @PropertyLayout(named="DefinitionItems")
// ???
@SuppressWarnings("deprecation")
//@CollectionInteraction
//@Collection(domainEvent=Regulation.Clauses.class)
@CollectionLayout(sortedBy=DefinitionItemsComparator.class,render=RenderType.EAGERLY)
public SortedSet<DefinitionItem> getDefinitionItems() {
	return definitionItems;
	}
public void setDefinitionItems(SortedSet<DefinitionItem> definitionItems) {
	this.definitionItems = definitionItems; 
	}

// overrides the natural ordering
public static class DefinitionItemsComparator implements Comparator<DefinitionItem> {
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

public void removeFromDefinitionItems(final DefinitionItem definitionItem) {
if(definitionItem == null || !getDefinitionItems().contains(definitionItem)) return;
getDefinitionItems().remove(definitionItem);
}

//This is the add-Button!!!  

@MemberOrder(name="DefinitionItems", sequence = "10")
public Regulation addDefinitionItem(final DefinitionItem definitionItem) {
wrapperFactory.wrapSkipRules(definitionItem).setRegulation(this);
getDefinitionItems().add(definitionItem);
return this;
}



public List<DefinitionItem> autoComplete0AddDefinitionItem(final @MinLength(2) String search) {
final List<DefinitionItem> list = container.allMatches(new QueryDefault<DefinitionItem>(DefinitionItem.class,"findByDefinitionItem", "ownedBy", "ecompliance"));
list.removeAll(getDefinitionItems());//Remove those Definitions already linked to the Regulation
return list;
}

public String disableAddDefinitionItem(final DefinitionItem definitionItem) {
if(isFinalized()) {
return "Cannot add Definition Item for Regulatons that are Finalized";
}
return null;
}


// validate the provided argument prior to invoking action
public String validateAddDefinitionItem(final DefinitionItem definitionItem) {
if(getDefinitionItems().contains(definitionItem)) {
return "Already a Definition Item";
}
return null;
}


//This is the Remove-Button!!
@MemberOrder(name="DefinitionItems", sequence = "20")
public Regulation removeDefinitionItem(final @ParameterLayout(typicalLength=30) DefinitionItem definitionItem) {
// By wrapping the call, Isis will detect that the collection is modified 
// and it will automatically send a CollectionInteractionEvent to the Event Bus.
// ToDoItemSubscriptions is a demo subscriber to this event
wrapperFactory.wrapSkipRules(this).removeFromDefinitionItems(definitionItem);
return this;
}

// disable action dependent on state of object
public String disableRemoveDefinitionItem(final DefinitionItem definitionItem) {
if(isFinalized()) {
return "Cannot remove Definitions from regulations that are Finalized";
}
return getDefinitionItems().isEmpty()? "No Definitions to remove": null;
}
// validate the provided argument prior to invoking action
public String validateRemoveDefinitionItem(final DefinitionItem definitionItem) {
if(!getDefinitionItems().contains(definitionItem)) {
return "Not a Definition";
}
return null;
}
// provide a drop-down
public Collection<DefinitionItem> choices0RemoveDefinitionItem() {
return getDefinitionItems();
}

//endregion region Link Regulation --> to (several) Definition items
  



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

    //region > object-level validation

    /**
     * In a real app, if this were actually a rule, then we'd expect that
     * invoking the {@link #completed() done} action would clear the {@link #getDueBy() dueBy}
     * property (rather than require the user to have to clear manually).
     */
    //endregion


    //region > events

    public static abstract class AbstractActionInteractionEvent extends ActionInteractionEvent<Regulation> {
        private static final long serialVersionUID = 1L;
        private final String description;
        public AbstractActionInteractionEvent(
                final String description,
                final Regulation source,
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
                final Regulation source, 
                final Identifier identifier, 
                final Object... arguments) {
            super("finalized", source, identifier, arguments);
        }
    }

    public static class NoLongerFinalizedEvent extends AbstractActionInteractionEvent {
        private static final long serialVersionUID = 1L;
        public NoLongerFinalizedEvent(
                final Regulation source, 
                final Identifier identifier, 
                final Object... arguments) {
            super("no longer finalized", source, identifier, arguments);
        }
    }

    public static class DeletedEvent extends AbstractActionInteractionEvent {
        private static final long serialVersionUID = 1L;
        public DeletedEvent(
                final Regulation source, 
                final Identifier identifier, 
                final Object... arguments) {
            super("deleted", source, identifier, arguments);
        }
    }

    //endregion

    //region > predicates

    public static class Predicates {
        
        public static Predicate<Regulation> thoseOwnedBy(final String currentUser) {
            return new Predicate<Regulation>() {
                @Override
                public boolean apply(final Regulation regulation) {
                    return Objects.equal(regulation.getOwnedBy(), currentUser);
                }
            };
        }

        public static Predicate<Regulation> thoseFinalized(
                final boolean finalized) {
            return new Predicate<Regulation>() {
                @Override
                public boolean apply(final Regulation t) {
                    return Objects.equal(t.isFinalized(), finalized);
                }
            };
        }

        public static Predicate<Regulation> thoseWithSimilarDescription(final String regulationTitle) {
            return new Predicate<Regulation>() {
                @Override
                public boolean apply(final Regulation t) {
                    return t.getRegulationTitle().contains(regulationTitle);
                }
            };
        }
 
        @SuppressWarnings("unchecked")
        public static Predicate<Regulation> thoseSimilarTo(final Regulation regulation) {
            return com.google.common.base.Predicates.and(
                    thoseNot(regulation),
                    thoseOwnedBy(regulation.getOwnedBy()),
                    thoseKPIs(regulation.getKpi()));
        }

        public static Predicate<Regulation> thoseNot(final Regulation regulation) {
            return new Predicate<Regulation>() {
                @Override
                public boolean apply(final Regulation t) {
                    return t != regulation;
                }
            };
        }

        public static Predicate<Regulation> thoseKPIs(final KPI kpi) {
            return new Predicate<Regulation>() {
                @Override
                public boolean apply(final Regulation regulation) {
                    return Objects.equal(regulation.getKpi(), kpi);
                }
            };
        }

    }

    //endregion

    //region > toString, compareTo
    @Override
    public String toString() {
//        return ObjectContracts.toString(this, "description,complete,dueBy,ownedBy");
        return ObjectContracts.toString(this, "regulationTitle,regulationType,objective,regulationText, ownedBy");
    }

    /**
     * Required so can store in {@link SortedSet sorted set}s (eg {@link #getDependencies()}). 
     */
    @Override
    public int compareTo(final Regulation other) {
        return ObjectContracts.compare(this, other, "regulationTitle,regulationType,objective,regulationText, ownedBy");
    }
    //endregion

    //region > injected services
    @javax.inject.Inject
    private DomainObjectContainer container;

    @javax.inject.Inject
//  private ToDoItems toDoItems;
    private Regulations regulations;

    @SuppressWarnings("deprecation")
	Bulk.InteractionContext bulkInteractionContext;
    public void injectBulkInteractionContext(@SuppressWarnings("deprecation") Bulk.InteractionContext bulkInteractionContext) {
        this.bulkInteractionContext = bulkInteractionContext;
    }

    @javax.inject.Inject
//    private Scratchpad scratchpad;

    EventBusService eventBusService;
    public void injectEventBusService(EventBusService eventBusService) {
        this.eventBusService = eventBusService;
    }

    @javax.inject.Inject
    private WrapperFactory wrapperFactory;

    
  
    //endregion

    
}
