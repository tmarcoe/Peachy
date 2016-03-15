package com.gemma.web.ftl_helper;

public class ObjectMath {

	public Object addObject(Object l, Object r) {
		if (l == null || r == null) {
			return null;
		}

		String lName = l.getClass().getName().substring(10);
		Object retObj = null;
		switch (lName) {
		case "Double":
			retObj = (Double) l + (Double) r;
			break;

		case "Long":
			retObj = (Long) l + (Long) r;
			break;

		case "String":
			retObj = ((String) l).concat((String) r);
			break;
		}

		return retObj;
	}

	public Object subObject(Object l, Object r) {
		if (l == null || r == null) {
			return null;
		}

		String lName = l.getClass().getName().substring(10);
		Object retObj = null;

		switch (lName) {
		case "Double":
			retObj = (Double) l - (Double) r;
			break;

		case "Long":
			retObj = (Long) l - (Long) r;
			break;

		}

		return retObj;
	}
	public Object multObject(Object l, Object r) {
		if (l == null || r == null) {
			return null;
		}

		Object retObj = null;
		String lName = l.getClass().getName().substring(10);

		switch (lName) {
		case "Double":
			retObj = (Double) l * (Double) r;
			break;

		case "Long":
			retObj = (Long) l * (Long) r;
			break;
		}
		
		return retObj;
	}
	
	public Object divObject(Object l, Object r) {
		String lName = l.getClass().getName().substring(10);
		Object retObj = null;

		if (l == null || r == null) {
			return null;
		}

		switch (lName) {
		case "Double":
			retObj = (Double) l / (Double) r;
			break;

		case "Long":
			retObj = (Long) l / (Long) r;
			break;

		}

		return retObj;
	}

}
