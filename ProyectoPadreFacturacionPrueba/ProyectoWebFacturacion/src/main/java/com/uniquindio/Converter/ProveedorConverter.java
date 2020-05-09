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

import co.uniquindio.entidades.Proveedor;


/**
 * @author jpgb9
 *
 */
@FacesConfig(version= Version.JSF_2_3)
@Named(value="proveedorConverter")
@ApplicationScoped
public class ProveedorConverter implements Converter<Proveedor>{

	@EJB
	NegocioEJB adminEJB;
	
	@Override
	public Proveedor getAsObject(FacesContext context, UIComponent component, String value) {
		Proveedor tipo = null;
		if (value != null && !"".equals(value)) {
			try {
				
				tipo = adminEJB.buscarProveedor(value);
			} catch (Exception e) {
				throw new ConverterException(new FacesMessage(component.getClientId() + ":código no válido"));
			}
		}
		return tipo;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Proveedor value) {
		if (value != null) {
			return (value.getCedula());
		}
		return "";
	}

}
