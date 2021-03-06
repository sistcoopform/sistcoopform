package org.sistcoopform.manager.api.model;

import java.util.Set;

public interface FormModel extends Model {

	String TITLE = "title";
	String DESCRIPTION = "description";

	String getId();

	String getTitle();

	void setTitle(String title);

	String getDescription();

	void setDescription(String description);

	boolean isActive();

	void active();
	
	Set<SectionModel> getSections();

}