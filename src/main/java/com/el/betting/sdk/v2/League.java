package com.el.betting.sdk.v2;


import com.el.betting.sdk.v4.Participant;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.repository.query.parser.Part;

import java.util.*;

public class League implements Cloneable {

    private long id;

    private long sportID;

    /** The real name gotten from bookmaker */
    private String originalName;

    /** This name represents the official version of originalName */
    private String name;

    /** Represents the category under which this league is present **/
    private String category;

    /** Represents the list of subcategories of category, till this league **/
    private Set<String> subCategories;

    private Optional<String> groupName;

    private boolean hasContent;

    private List<Event<? extends Participant>> events;

    private Map<String, Object> additionalProperties;

    public League(String originalName) {
        this(originalName, null);
    }

    public League(String originalName, String name) {
        this(originalName, name, null);
    }

    public League(String originalName, String name, String category) {
        this(originalName, name, category, new LinkedHashSet<>());
    }

    @PersistenceConstructor
    public League(String originalName, String name, String category, Set<String> subCategories) {
        this.originalName = originalName;
        this.name = name;
        this.category = category;
        this.subCategories = subCategories;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSetName() {
        return name;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSportID() {
        return sportID;
    }

    public void setSportID(long sportID) {
        this.sportID = sportID;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Set<String> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(Set<String> subCategories) {
        this.subCategories = subCategories;
    }

    public String getName() {
        if (name == null) {
            return originalName;
        }
        return name;
    }

    public boolean isHasContent() {
        return hasContent;
    }

    public void setHasContent(boolean hasContent) {
        this.hasContent = hasContent;
    }

    public List<Event<? extends Participant>> getEvents() {
        return events;
    }

    public void setEvents(List<Event<? extends Participant>> events) {
        this.events = events;
    }

    public Optional<String> getGroupName() {
        return groupName;
    }

    public void setGroupName(Optional<String> groupName) {
        this.groupName = groupName;
    }

    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }

    public void addProperty(String name, Object value) {
        if (additionalProperties == null) {
            additionalProperties = new HashMap<>();
        }
        additionalProperties.put(name, value);
    }

    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }

    public Object getProperty(String name) {
        return additionalProperties.get(name);
    }

    @Override
    public String toString() {
        return "League{" +
                "id=" + id +
                ", sportID=" + sportID +
                ", originalName='" + originalName + '\'' +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", subCategories=" + subCategories +
                ", groupName=" + groupName +
                ", hasContent=" + hasContent +
                ", additionalProperties=" + additionalProperties +
                '}';
    }

    public League clone() {
        try {
            return (League) super.clone();
        } catch (CloneNotSupportedException e) {
            // This should never happen
            throw new InternalError(e.toString());
        }
    }
}
