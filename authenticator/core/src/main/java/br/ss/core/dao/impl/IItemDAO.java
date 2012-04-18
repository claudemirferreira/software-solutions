package br.ss.core.dao.impl;

import br.ss.core.dao.IGenericDAO;
import br.ss.core.model.entity.Bid;
import br.ss.core.model.entity.Item;

public interface IItemDAO extends IGenericDAO<Item, Long> {
	 
    public static final String QUERY_MAXBID = "ItemDAO.QUERY_MAXBID";
    public static final String QUERY_MINBID = "ItemDAO.QUERY_MINBID";
 
    Bid getMaxBid(Long itemId);
    Bid getMinBid(Long itemId);
 
}