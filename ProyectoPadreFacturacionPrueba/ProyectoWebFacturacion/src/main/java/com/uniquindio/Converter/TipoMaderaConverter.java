/**
 * 
 */
package com.uniquindio.Converter;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Named;

import com.uniquindio.ejb.NegocioEJB;

import co.uniquindio.entidades.Tipo_Madera;




/**
 * @author jpgb9
 *
 */
@FacesConfig(version= Version.JSF_2_3)
@Named(value="tipoMaderaConverter")
@ApplicationScoped
public class TipoMaderaConverter implements Converter<Tipo_Madera>  {
	
	@EJB
	private NegocioEJB adminEJB;

	@Override
	public Tipo_Madera getAsObject(FacesContext context, UIComponent component, String value) {
		Tipo_Madera tipo = null;
		if (value != null && !"".equals(value)) {
			try {
				int id =Integer.parseInt(value);
				tipo = adminEJB.buscarTipoMadera(id);
			} catch (Exception e) {
				throw new ConverterException(new FacesMessage(component.getClientId() + ":código no válido"));
			}
		}
		return tipo;
	}


	@Override
	public String getAsString(FacesContext context, UIComponent component, Tipo_Madera value) {
		if (value != null) {
			return (value.getId_madera()+"");
		}
		return "";
	}

}
