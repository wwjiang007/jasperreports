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
package net.sf.jasperreports.engine.fill;

import java.math.BigDecimal;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRVariable;


/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id$
 */
public class JRBigDecimalIncrementerFactory implements JRIncrementerFactory
{


	/**
	 *
	 */
	protected static final BigDecimal ZERO = new BigDecimal(0);
	protected static final BigDecimal ONE = new BigDecimal(1);


	/**
	 *
	 */
	private static JRBigDecimalIncrementerFactory mainInstance = new JRBigDecimalIncrementerFactory();


	/**
	 *
	 */
	private JRBigDecimalIncrementerFactory()
	{
	}


	/**
	 *
	 */
	public static JRBigDecimalIncrementerFactory getInstance()
	{
		return mainInstance;
	}


	/**
	 *
	 */
	public JRIncrementer getIncrementer(byte calculation)
	{
		JRIncrementer incrementer = null;

		switch (calculation)
		{
			case JRVariable.CALCULATION_COUNT :
			{
				incrementer = JRBigDecimalCountIncrementer.getInstance();
				break;
			}
			case JRVariable.CALCULATION_SUM :
			{
				incrementer = JRBigDecimalSumIncrementer.getInstance();
				break;
			}
			case JRVariable.CALCULATION_AVERAGE :
			{
				incrementer = JRBigDecimalAverageIncrementer.getInstance();
				break;
			}
			case JRVariable.CALCULATION_LOWEST :
			case JRVariable.CALCULATION_HIGHEST :
			{
				incrementer = JRComparableIncrementerFactory.getInstance().getIncrementer(calculation);
				break;
			}
			case JRVariable.CALCULATION_STANDARD_DEVIATION :
			{
				incrementer = JRBigDecimalStandardDeviationIncrementer.getInstance();
				break;
			}
			case JRVariable.CALCULATION_VARIANCE :
			{
				incrementer = JRBigDecimalVarianceIncrementer.getInstance();
				break;
			}
			case JRVariable.CALCULATION_SYSTEM :
			case JRVariable.CALCULATION_NOTHING :
			default :
			{
				incrementer = JRDefaultIncrementerFactory.getInstance().getIncrementer(calculation);
				break;
			}
		}
		
		return incrementer;
	}


}


/**
 *
 */
class JRBigDecimalCountIncrementer implements JRIncrementer
{
	/**
	 *
	 */
	private static JRBigDecimalCountIncrementer mainInstance = new JRBigDecimalCountIncrementer();

	/**
	 *
	 */
	private JRBigDecimalCountIncrementer()
	{
	}

	/**
	 *
	 */
	public static JRBigDecimalCountIncrementer getInstance()
	{
		return mainInstance;
	}

	/**
	 *
	 */
	public Object increment(
		JRFillVariable variable, 
		Object expressionValue,
		AbstractValueProvider valueProvider
		) throws JRException
	{
		BigDecimal value = (BigDecimal)variable.getValue();

		if (expressionValue == null)
		{
			if (variable.isInitialized())
			{
				return JRBigDecimalIncrementerFactory.ZERO;
			}
			else
			{
				return value;
			}
		}
		else
		{
			if (value == null || variable.isInitialized())
			{
				value = JRBigDecimalIncrementerFactory.ZERO;
			}

			return value.add(JRBigDecimalIncrementerFactory.ONE);
		}
	}
}


/**
 *
 */
class JRBigDecimalSumIncrementer implements JRIncrementer
{
	/**
	 *
	 */
	private static JRBigDecimalSumIncrementer mainInstance = new JRBigDecimalSumIncrementer();

	/**
	 *
	 */
	private JRBigDecimalSumIncrementer()
	{
	}

	/**
	 *
	 */
	public static JRBigDecimalSumIncrementer getInstance()
	{
		return mainInstance;
	}

	/**
	 *
	 */
	public Object increment(
		JRFillVariable variable, 
		Object expressionValue,
		AbstractValueProvider valueProvider
		) throws JRException
	{
		BigDecimal value = (BigDecimal)variable.getValue();
		BigDecimal newValue = (BigDecimal)expressionValue;

		if (newValue == null)
		{
			if (variable.isInitialized())
			{
				return null;
			}
			else
			{
				return value;
			}
		}
		else
		{
			if (value == null || variable.isInitialized())
			{
				value = JRBigDecimalIncrementerFactory.ZERO;
			}

			return value.add(newValue);
		}
	}
}


/**
 *
 */
class JRBigDecimalAverageIncrementer implements JRIncrementer
{
	/**
	 *
	 */
	private static JRBigDecimalAverageIncrementer mainInstance = new JRBigDecimalAverageIncrementer();

	/**
	 *
	 */
	private JRBigDecimalAverageIncrementer()
	{
	}

	/**
	 *
	 */
	public static JRBigDecimalAverageIncrementer getInstance()
	{
		return mainInstance;
	}

	/**
	 *
	 */
	public Object increment(
		JRFillVariable variable, 
		Object expressionValue,
		AbstractValueProvider valueProvider
		) throws JRException
	{
		if (expressionValue == null)
		{
			if (variable.isInitialized())
			{
				return null;
			}
			else
			{
				return variable.getValue();
			}
		}
		else
		{
			BigDecimal countValue = (BigDecimal)valueProvider.getValue((JRFillVariable)variable.getCountVariable());
			BigDecimal sumValue = (BigDecimal)valueProvider.getValue((JRFillVariable)variable.getSumVariable());
			return sumValue.divide(countValue, BigDecimal.ROUND_HALF_UP);
		}
	}
}


/**
 *
 */
class JRBigDecimalStandardDeviationIncrementer implements JRIncrementer
{
	/**
	 *
	 */
	private static JRBigDecimalStandardDeviationIncrementer mainInstance = new JRBigDecimalStandardDeviationIncrementer();

	/**
	 *
	 */
	private JRBigDecimalStandardDeviationIncrementer()
	{
	}

	/**
	 *
	 */
	public static JRBigDecimalStandardDeviationIncrementer getInstance()
	{
		return mainInstance;
	}

	/**
	 *
	 */
	public Object increment(
		JRFillVariable variable, 
		Object expressionValue,
		AbstractValueProvider valueProvider
		) throws JRException
	{
		if (expressionValue == null)
		{
			if (variable.isInitialized())
			{
				return null;
			}
			else
			{
				return variable.getValue(); 
			}
		}
		else
		{
			Number varianceValue = (Number)valueProvider.getValue((JRFillVariable)variable.getVarianceVariable());
			return new BigDecimal( Math.sqrt(varianceValue.doubleValue()) );
		}
	}
}


/**
 *
 */
class JRBigDecimalVarianceIncrementer implements JRIncrementer
{
	/**
	 *
	 */
	private static JRBigDecimalVarianceIncrementer mainInstance = new JRBigDecimalVarianceIncrementer();

	/**
	 *
	 */
	private JRBigDecimalVarianceIncrementer()
	{
	}

	/**
	 *
	 */
	public static JRBigDecimalVarianceIncrementer getInstance()
	{
		return mainInstance;
	}

	/**
	 *
	 */
	public Object increment(
		JRFillVariable variable, 
		Object expressionValue,
		AbstractValueProvider valueProvider
		) throws JRException
	{
		BigDecimal value = (BigDecimal)variable.getValue();
		BigDecimal newValue = (BigDecimal)expressionValue;
		
		if (newValue == null)
		{
			if (variable.isInitialized())
			{
				return null;
			}
			else
			{
				return value;
			}
		}
		else if (value == null || variable.isInitialized())
		{
			return JRBigDecimalIncrementerFactory.ZERO;
		}
		else
		{
			BigDecimal countValue = (BigDecimal)valueProvider.getValue((JRFillVariable)variable.getCountVariable());
			BigDecimal sumValue = (BigDecimal)valueProvider.getValue((JRFillVariable)variable.getSumVariable());
			return
				countValue.subtract(JRBigDecimalIncrementerFactory.ONE).multiply(value).divide(countValue, BigDecimal.ROUND_HALF_UP).add(
					sumValue.divide(countValue, BigDecimal.ROUND_HALF_UP).subtract(newValue).multiply(
						sumValue.divide(countValue, BigDecimal.ROUND_HALF_UP).subtract(newValue)
						).divide(countValue.subtract(JRBigDecimalIncrementerFactory.ONE), BigDecimal.ROUND_HALF_UP)
					);
		}
	}
}
