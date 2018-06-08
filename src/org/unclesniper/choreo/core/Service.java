package org.unclesniper.choreo.core;

import org.unclesniper.choreo.RunContext;
import org.unclesniper.choreo.ChoreoRunException;
import org.unclesniper.choreo.annotation.ElementClass;
import org.unclesniper.choreo.annotation.DefaultAdder;

@ElementClass("service")
public class Service<T> implements ChoreoExpr<T> {

	private ChoreoExpr<String> key;

	private ChoreoExpr<Boolean> permitNull;

	private ChoreoExpr<Class<? extends T>> requiredType;

	private Class<? extends T> returnType;

	public Service() {}

	public Service(ChoreoExpr<String> key, ChoreoExpr<Boolean> permitNull,
			ChoreoExpr<Class<? extends T>> requiredType) {
		this.key = ExprUtils.ensureReturnType(key, String.class);
		this.permitNull = ExprUtils.ensureReturnType(permitNull, Boolean.class);
		setRequiredType(requiredType);
	}

	public ChoreoExpr<String> getKey() {
		return key;
	}

	@DefaultAdder
	public void setKey(ChoreoExpr<String> key) {
		this.key = ExprUtils.ensureReturnType(key, String.class);
	}

	public void setKey(String key) {
		this.key = ConstantExpr.from(key);
	}

	public ChoreoExpr<Boolean> getPermitNull() {
		return permitNull;
	}

	public void setPermitNull(ChoreoExpr<Boolean> permitNull) {
		this.permitNull = ExprUtils.ensureReturnType(permitNull, Boolean.class);
	}

	public void setPermitNull(Boolean permitNull) {
		this.permitNull = ConstantExpr.from(permitNull);
	}

	public ChoreoExpr<Class<? extends T>> getRequiredType() {
		return requiredType;
	}

	@SuppressWarnings("unchecked")
	public void setRequiredType(ChoreoExpr<Class<? extends T>> requiredType) {
		this.requiredType = ExprUtils.ensureReturnType(requiredType, (Class<Class<? extends T>>)(Class)Class.class);
		returnType = null;
	}

	public void setRequiredType(Class<? extends T> requiredType) {
		this.requiredType = ConstantExpr.from(requiredType);
		returnType = requiredType;
	}

	public Class<? extends T> getReturnType() {
		return returnType;
	}

	@SuppressWarnings("unchecked")
	public T evaluate(RunContext context) throws ChoreoRunException {
		String ekey = ExprUtils.reduce(key, context);
		Boolean enull = ExprUtils.reduce(permitNull, context);
		boolean pnull = enull == null || enull;
		Class<? extends T> rtype = ExprUtils.reduce(requiredType, context);
		Object svc = context.getServiceRegistry().getServiceObject(ekey);
		if(!pnull && svc == null)
			throw new NoSuchServiceException(ekey);
		if(svc != null && rtype != null)
			throw new IllTypedServiceException(ekey, rtype, svc.getClass());
		return (T)svc;
	}

}
