/*
 * ============================================================================
 *                   GNU Lesser General Public License
 * ============================================================================
 *
 * JasperReports - Free Java report-generating library.
 * Copyright (C) 2001-2004 Teodor Danciu teodord@users.sourceforge.net
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307, USA.
 * 
 * Teodor Danciu
 * 173, Calea Calarasilor, Bl. 42, Sc. 1, Ap. 18
 * Postal code 030615, Sector 3
 * Bucharest, ROMANIA
 * Email: teodord@users.sourceforge.net
 */
package net.sf.jasperreports.engine.export;

import net.sf.jasperreports.engine.JRExporterParameter;


/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id$
 */
public class JRHtmlExporterParameter extends JRExporterParameter
{


	/**
	 *
	 */
	protected JRHtmlExporterParameter(String name)
	{
		super(name);
	}


	/**
	 *
	 */
	public static final JRHtmlExporterParameter IMAGES_MAP = new JRHtmlExporterParameter("Images Map Object");
	public static final JRHtmlExporterParameter IMAGES_DIR = new JRHtmlExporterParameter("Images Directory");
	public static final JRHtmlExporterParameter IMAGES_DIR_NAME = new JRHtmlExporterParameter("Images Directory Name");
	public static final JRHtmlExporterParameter IS_OUTPUT_IMAGES_TO_DIR = new JRHtmlExporterParameter("Is Output Images to Directory Flag");
	public static final JRHtmlExporterParameter IMAGES_URI = new JRHtmlExporterParameter("Images URI");
	public static final JRHtmlExporterParameter HTML_HEADER = new JRHtmlExporterParameter("HTML Header");
	public static final JRHtmlExporterParameter BETWEEN_PAGES_HTML = new JRHtmlExporterParameter("Between Pages HTML");
	public static final JRHtmlExporterParameter HTML_FOOTER = new JRHtmlExporterParameter("HTML Footer");
	public static final JRHtmlExporterParameter IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS = new JRHtmlExporterParameter("Is Remove Empty Space Between Rows");
	public static final JRHtmlExporterParameter IS_WHITE_PAGE_BACKGROUND = new JRHtmlExporterParameter("Is White Page Background");
	public static final JRHtmlExporterParameter IS_USING_IMAGES_TO_ALIGN = new JRHtmlExporterParameter("Is Using Images To Align");


}
