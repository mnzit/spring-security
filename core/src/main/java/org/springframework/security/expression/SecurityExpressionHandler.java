package org.springframework.security.expression;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.security.Authentication;
import org.springframework.security.intercept.web.FilterInvocation;

/**
 * Facade which isolates Spring Security's requirements from the implementation of the underlying
 * expression objects.
 *
 * @author Luke Taylor
 * @version $Id$
 * @since 2.5
 */
public interface SecurityExpressionHandler {
    /**
     * @return an expression parser for the expressions used by the implementation.
     */
    ExpressionParser getExpressionParser();

    /**
     * Provides an evaluation context in which to evaluate security expressions for a method invocation.
     */
    EvaluationContext createEvaluationContext(Authentication authentication, MethodInvocation mi);

    /**
     * Provides an evaluation context in which to evaluate security expressions for a web invocation.
     */
    EvaluationContext createEvaluationContext(Authentication authentication, FilterInvocation fi);

    /**
     * Filters a target collection or array.
     * Only applies to method invocations.
     *
     * @param filterTarget the array or collection to be filtered.
     * @param filterExpression the expression which should be used as the filter condition. If it returns false on
     *          evaluation, the object will be removed from the returned collection
     * @param ctx the current evaluation context (as created through a call to
     *          {@link #createEvaluationContext(Authentication, MethodInvocation)}
     * @return the filtered collection or array
     */
    Object filter(Object filterTarget, Expression filterExpression, EvaluationContext ctx);

    /**
     * Used to inform the expression system of the return object for the given evaluation context.
     * Only applies to method invocations.
     *
     * @param returnObject the return object value
     * @param ctx the context within which the object should be set (as created through a call to
     *          {@link #createEvaluationContext(Authentication, MethodInvocation)}
     */
    void setReturnObject(Object returnObject, EvaluationContext ctx);

}
