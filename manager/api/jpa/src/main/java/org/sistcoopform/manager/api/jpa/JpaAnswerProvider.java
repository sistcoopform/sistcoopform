package org.sistcoopform.manager.api.jpa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.sistcoopform.manager.api.jpa.entities.AnswerEntity;
import org.sistcoopform.manager.api.jpa.entities.DateTimeAnswerEntity;
import org.sistcoopform.manager.api.jpa.entities.FormAnswerEntity;
import org.sistcoopform.manager.api.jpa.entities.GridAnswerEntity;
import org.sistcoopform.manager.api.jpa.entities.NumericAnswerEntity;
import org.sistcoopform.manager.api.jpa.entities.QuestionEntity;
import org.sistcoopform.manager.api.jpa.entities.ScaleAnswerEntity;
import org.sistcoopform.manager.api.jpa.entities.SelectAnswerEntity;
import org.sistcoopform.manager.api.jpa.entities.TextAnswerEntity;
import org.sistcoopform.manager.api.model.AnswerModel;
import org.sistcoopform.manager.api.model.AnswerProvider;
import org.sistcoopform.manager.api.model.DateTimeAnswerModel;
import org.sistcoopform.manager.api.model.FormAnswerModel;
import org.sistcoopform.manager.api.model.GridAnswerModel;
import org.sistcoopform.manager.api.model.NumericAnswerModel;
import org.sistcoopform.manager.api.model.QuestionModel;
import org.sistcoopform.manager.api.model.ScaleAnswerModel;
import org.sistcoopform.manager.api.model.SelectAnswerModel;
import org.sistcoopform.manager.api.model.TextAnswerModel;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

@Named
@Stateless
@Local(AnswerProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaAnswerProvider extends AbstractHibernateStorage implements AnswerProvider {

	private static final String TITLE = "title";

	@PersistenceContext
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return this.em;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
	}

	private FormAnswerEntity getFormAnswerEntity(FormAnswerModel formAnswer) {
		return em.find(FormAnswerEntity.class, formAnswer.getId());
	}

	private QuestionEntity getQuestionEntity(QuestionModel question) {
		return em.find(QuestionEntity.class, question.getId());
	}

	@Override
	public TextAnswerModel createTextAnswer(FormAnswerModel formAnswer, QuestionModel question, String value) {
		FormAnswerEntity formAnswerEntity = getFormAnswerEntity(formAnswer);
		QuestionEntity questionEntity = getQuestionEntity(question);

		TextAnswerEntity entity = new TextAnswerEntity();
		entity.setFormAnswer(formAnswerEntity);
		entity.setQuestion(questionEntity);
		entity.setValue(value);
		em.persist(entity);
		return new TextAnswerAdapter(em, entity);
	}

	@Override
	public NumericAnswerModel createNumberAnswer(FormAnswerModel formAnswer, QuestionModel question, double value) {
		FormAnswerEntity formAnswerEntity = getFormAnswerEntity(formAnswer);
		QuestionEntity questionEntity = getQuestionEntity(question);

		NumericAnswerEntity entity = new NumericAnswerEntity();
		entity.setFormAnswer(formAnswerEntity);
		entity.setQuestion(questionEntity);
		entity.setValue(value);
		em.persist(entity);
		return new NumericAnswerAdapter(em, entity);
	}

	@Override
	public DateTimeAnswerModel createDateTimeAnswer(FormAnswerModel formAnswer, QuestionModel question, Date datetime) {
		FormAnswerEntity formAnswerEntity = getFormAnswerEntity(formAnswer);
		QuestionEntity questionEntity = getQuestionEntity(question);

		DateTimeAnswerEntity entity = new DateTimeAnswerEntity();
		entity.setFormAnswer(formAnswerEntity);
		entity.setQuestion(questionEntity);
		entity.setDate(datetime);
		entity.setTime(datetime);
		em.persist(entity);
		return new DateTimeAnswerAdapter(em, entity);
	}

	@Override
	public ScaleAnswerModel createScaleAnswer(FormAnswerModel formAnswer, QuestionModel question, int value) {
		FormAnswerEntity formAnswerEntity = getFormAnswerEntity(formAnswer);
		QuestionEntity questionEntity = getQuestionEntity(question);

		ScaleAnswerEntity entity = new ScaleAnswerEntity();
		entity.setFormAnswer(formAnswerEntity);
		entity.setQuestion(questionEntity);
		entity.setValue(value);
		em.persist(entity);
		return new ScaleAnswerAdapter(em, entity);
	}

	@Override
	public SelectAnswerModel createSelectAnswer(FormAnswerModel formAnswer, QuestionModel question,
			Set<String> values) {
		FormAnswerEntity formAnswerEntity = getFormAnswerEntity(formAnswer);
		QuestionEntity questionEntity = getQuestionEntity(question);

		SelectAnswerEntity entity = new SelectAnswerEntity();
		entity.setFormAnswer(formAnswerEntity);
		entity.setQuestion(questionEntity);
		entity.setValues(values);
		em.persist(entity);
		return new SelectAnswerAdapter(em, entity);
	}

	@Override
	public GridAnswerModel createGridAnswer(FormAnswerModel formAnswer, QuestionModel question,
			Map<String, String> values) {
		FormAnswerEntity formAnswerEntity = getFormAnswerEntity(formAnswer);
		QuestionEntity questionEntity = getQuestionEntity(question);

		GridAnswerEntity entity = new GridAnswerEntity();
		entity.setFormAnswer(formAnswerEntity);
		entity.setQuestion(questionEntity);
		entity.setValues(values);
		em.persist(entity);
		return new GridAnswerAdapter(em, entity);
	}

	@Override
	public AnswerModel findById(String id) {
		AnswerEntity questionEntity = em.find(AnswerEntity.class, id);
		return AbstractAnswerAdapter.toAnswerModel(questionEntity, em);
	}

	@Override
	public boolean remove(AnswerModel question) {
		AnswerEntity questionEntity = em.find(AnswerEntity.class, question.getId());
		em.remove(questionEntity);
		return true;
	}

	@Override
	public List<AnswerModel> getAll(FormAnswerModel formAnswer) {
		return getAll(formAnswer, -1, -1);
	}

	@Override
	public List<AnswerModel> getAll(FormAnswerModel formAnswer, int firstResult, int maxResults) {
		TypedQuery<AnswerEntity> query = em.createNamedQuery("AnswerEntity.findByFormAnswerId", AnswerEntity.class);
		query.setParameter("answerId", formAnswer.getId());
		if (firstResult != -1) {
			query.setFirstResult(firstResult);
		}
		if (maxResults != -1) {
			query.setMaxResults(maxResults);
		}
		List<AnswerEntity> entities = query.getResultList();
		List<AnswerModel> models = new ArrayList<AnswerModel>();
		for (AnswerEntity questionEntity : entities) {
			models.add(AbstractAnswerAdapter.toAnswerModel(questionEntity, em));
		}
		return models;
	}

}