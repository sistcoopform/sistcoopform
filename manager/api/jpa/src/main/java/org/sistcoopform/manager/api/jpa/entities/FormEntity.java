package org.sistcoopform.manager.api.jpa.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

@Entity
@Table(name = "FORM")
@NamedQueries(value = { @NamedQuery(name = "FormEntity.findAll", query = "SELECT f FROM FormEntity f"),
		@NamedQuery(name = "FormEntity.findByTitle", query = "SELECT f FROM FormEntity f WHERE f.title = :title"),
		@NamedQuery(name = "FormEntity.findByFilterText", query = "SELECT f FROM FormEntity f WHERE LOWER(f.title) LIKE LOWER(:filterText)") })
public class FormEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "ID")
	private String id;

	@NotNull
	@Size(min = 1, max = 200)
	private String title;

	@Size(min = 0, max = 400)
	private String description;

	@NotNull
	@Type(type = "org.hibernate.type.TrueFalseType")
	private boolean active;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "form", orphanRemoval = true, cascade = { CascadeType.REMOVE })
	private Set<SectionEntity> sections = new HashSet<SectionEntity>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "form", orphanRemoval = true, cascade = { CascadeType.REMOVE })
	private Set<FormAnswerEntity> formAnswers = new HashSet<FormAnswerEntity>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Set<SectionEntity> getSections() {
		return sections;
	}

	public void setSections(Set<SectionEntity> sections) {
		this.sections = sections;
	}

	public Set<FormAnswerEntity> getFormAnswers() {
		return formAnswers;
	}

	public void setFormAnswers(Set<FormAnswerEntity> formAnswers) {
		this.formAnswers = formAnswers;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FormEntity other = (FormEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
