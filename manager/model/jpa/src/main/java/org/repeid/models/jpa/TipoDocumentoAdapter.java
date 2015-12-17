package org.repeid.models.jpa;

import javax.persistence.EntityManager;

import org.sistcoopform.models.FormularioModel;
import org.sistcoopform.models.enums.TipoPreguntaSeleccion;
import org.sistcoopform.models.jpa.entities.TipoDocumentoEntity;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

public class TipoDocumentoAdapter implements FormularioModel {

    private static final long serialVersionUID = 1L;

    private TipoDocumentoEntity tipoDocumentoEntity;
    private transient EntityManager em;

    public TipoDocumentoAdapter(EntityManager em, TipoDocumentoEntity tipoDocumentoEntity) {
        this.em = em;
        this.tipoDocumentoEntity = tipoDocumentoEntity;
    }

    public static TipoDocumentoEntity toTipoDocumentoEntity(FormularioModel model, EntityManager em) {
        if (model instanceof TipoDocumentoAdapter) {
            return ((TipoDocumentoAdapter) model).getTipoDocumentEntity();
        }
        return em.getReference(TipoDocumentoEntity.class, model.getAbreviatura());
    }

    public TipoDocumentoEntity getTipoDocumentEntity() {
        return tipoDocumentoEntity;
    }

    @Override
    public String getId() {
        return tipoDocumentoEntity.getId();
    }

    @Override
    public String getAbreviatura() {
        return tipoDocumentoEntity.getAbreviatura();
    }

    @Override
    public void setAbreviatura(String abreviatura) {
        tipoDocumentoEntity.setAbreviatura(abreviatura);
    }

    @Override
    public String getDenominacion() {
        return tipoDocumentoEntity.getDenominacion();
    }

    @Override
    public void setDenominacion(String denominacion) {
        tipoDocumentoEntity.setDenominacion(denominacion);
    }

    @Override
    public int getCantidadCaracteres() {
        return tipoDocumentoEntity.getCantidadCaracteres();
    }

    @Override
    public void setCantidadCaracteres(int cantidadCaracteres) {
        tipoDocumentoEntity.setCantidadCaracteres(cantidadCaracteres);
    }

    @Override
    public TipoPreguntaSeleccion getTipoPersona() {
        String tipoPersona = tipoDocumentoEntity.getTipoPersona();
        return TipoPreguntaSeleccion.valueOf(tipoPersona);
    }

    @Override
    public void setTipoPersona(TipoPreguntaSeleccion tipoPersona) {
        tipoDocumentoEntity.setTipoPersona(tipoPersona.toString());
    }

    @Override
    public boolean getEstado() {
        return tipoDocumentoEntity.isEstado();
    }

    @Override
    public void setEstado(boolean estado) {
        tipoDocumentoEntity.setEstado(estado);
    }

    @Override
    public void commit() {
        em.merge(tipoDocumentoEntity);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAbreviatura() == null) ? 0 : getAbreviatura().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof FormularioModel))
            return false;
        FormularioModel other = (FormularioModel) obj;
        if (getAbreviatura() == null) {
            if (other.getAbreviatura() != null)
                return false;
        } else if (!getAbreviatura().equals(other.getAbreviatura()))
            return false;
        return true;
    }

}