package br.ss.core.dao;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.persistence.criteria.From;
import javax.persistence.metamodel.SingularAttribute;

import org.joda.time.LocalDate;

public class DateRestriction<D extends AbstractDAO<D, T>, T, P extends Calendar> extends Restriction<D, T, P> {

	public DateRestriction( From<?,T> f, D dao, SingularAttribute<T, P> a ) {
		super( f, dao, a);
	}

	public Restriction<?,?,?> between( Calendar begin, Calendar end ) {
		if ( isValueNullOrEmpty( begin ) && isValueNullOrEmpty( end ) ) {
			nullValue = true;
			return this;
		}
		
		if ( begin != null && end != null ) {
			condition = dao.getBuilder().between( from.get( attribute ) , begin, end );
			nullValue = isValueNullOrEmpty( begin ) || isValueNullOrEmpty( end );
		}else if ( begin == null ) {
			isLessThanOrEqualTo( end );
		} else {
			isGreaterThanOrEqualTo( begin );
		}
		
		return this;
	}
	
	public Restriction<?,?,?> between( LocalDate beginDate, LocalDate endDate ) {
		Calendar begin = null;
		Calendar end = null;
		if ( beginDate != null ) {
			begin = new GregorianCalendar();
			begin.setTime( beginDate.toDate() );
		}
		if ( endDate != null ) {
			end = new GregorianCalendar();
			end.setTime( endDate.toDate() );
		}
		
		return between( begin, end );
	}
	
	public Restriction<?,?,?> isVigente( Calendar end ) {
		condition = dao.getBuilder().or(
					from.get( attribute ).isNull(),
					dao.getBuilder().greaterThanOrEqualTo( from.get( attribute ), end ) );
		nullValue = isValueNullOrEmpty( end );
		return this;
	}
	
	public Restriction<?,?,?> isLessThanOrEqualTo( Calendar date ) {
		condition = dao.getBuilder().lessThanOrEqualTo( from.get( attribute ), date );
		nullValue = isValueNullOrEmpty( date );
		return this;
	}
	
	public Restriction<?,?,?> isGreaterThanOrEqualTo( Calendar date ) {
		condition = dao.getBuilder().greaterThanOrEqualTo( from.get( attribute ), date );
		nullValue = isValueNullOrEmpty( date );
		return this;
	}
	
	public Restriction<?,?,?> isGreaterThan( Calendar date ) {
		condition = dao.getBuilder().greaterThan( from.get( attribute ), date );
		nullValue = isValueNullOrEmpty( date );
		return this;
	}
	
}
